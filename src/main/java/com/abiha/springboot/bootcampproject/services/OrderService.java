package com.abiha.springboot.bootcampproject.services;

import com.abiha.springboot.bootcampproject.entities.*;
import com.abiha.springboot.bootcampproject.enums.Status;
import com.abiha.springboot.bootcampproject.exception.*;
import com.abiha.springboot.bootcampproject.repos.*;
import com.abiha.springboot.bootcampproject.utils.SecurityContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private ProductVariationRepo productVariationRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderProductRepo orderProductRepo;

    @Autowired
    private OrderStatusRepo orderStatusRepo;

    @Autowired
    OrderStatusService orderStatusService;

    public void createOrder(User user, Orders orders) {

        orders.setCustomer(user.getCustomer());
        Address address1 = user.getAddresses().stream().findFirst().orElse(null);
        orders.setCustomerAddressCity(address1.getCity());
        orders.setCustomerAddressAddressLine(address1.getAddressLine());
        orders.setCustomerAddressCountry(address1.getCountry());
        orders.setCustomerAddressZipCode(address1.getZipCode());
    }


    @Transactional
    public String orderAllProducts() {
        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        } else {
            Orders orders = new Orders();
            List<Cart> list = cartRepo.findAllByCustomer(user.getCustomer());
            if (!CollectionUtils.isEmpty(list)) {
                createOrder(user, orders);
                List<OrderProduct> orderProductList = new ArrayList<>();
                Double totalAmount = 0.0;
                for (Cart cart : list) {
                    ProductVariation productVariation = cart.getProductVariation();
                    if (productVariation.getIsActive() && !productVariation.getProduct().getIsDeleted() && productVariation.getQuantityAvailable() > 0) {
                        OrderProduct orderProduct = new OrderProduct();

                        totalAmount += cart.getQuantity() * productVariation.getPrice();

                        orderProduct.setProductVariation(productVariation);
                        orderProduct.setPrice(productVariation.getPrice());
                        orderProduct.setQuantity(cart.getQuantity());
                        orderProduct.setOrders(orders);
                        orderProductList.add(orderProduct);

                    } else {
                        StringBuilder str = new StringBuilder();
                        validateProductVariation(str, productVariation);
                        throw new ValidationFailedException(String.valueOf(str));
                    }
                }
                orders.setOrderProducts(orderProductList);
                orders.setAmountPaid(totalAmount);
                orderRepo.saveAndFlush(orders);
                List<OrderStatus> orderStatusList = orderStatusService.saveAll(orderProductList);
                orderStatusRepo.saveAll(orderStatusList);
                cartRepo.deleteByCustomer(user.getCustomer());
                return "Order placed successfully!" + " " + "Order ID is = " + orders.getId();
            } else {
                throw new CartIsEmptyException("Cart is Empty, please add something!");
            }
        }

    }

    @Transactional
    public String orderPartialProducts(List<Long> ids) {
        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        } else {
            List<Cart> list = cartRepo.findByCustomerAndVarIds(user.getId(), ids);
            if (CollectionUtils.isEmpty(list)) {
                throw new EntityNotFoundException("No products of these ids found in the cart!");
            }
            Orders orders = new Orders();
            createOrder(user, orders);
            List<OrderProduct> orderProductList = new ArrayList<>();
            //
            Double totalAmount = 0.0;
            for (Cart cart : list) {
                ProductVariation productVariation = cart.getProductVariation();
                if (productVariation.getIsActive() && !productVariation.getProduct().getIsDeleted() && productVariation.getQuantityAvailable() > 0) {
                    OrderProduct orderProduct = new OrderProduct();
                    totalAmount += cart.getQuantity() * productVariation.getPrice();

                    orderProduct.setProductVariation(productVariation);
                    orderProduct.setPrice(productVariation.getPrice());
                    orderProduct.setQuantity(cart.getQuantity());
                    orderProduct.setOrders(orders);
                    orderProductList.add(orderProduct);

                } else {
                    StringBuilder str = new StringBuilder();
                    validateProductVariation(str, productVariation);
                    throw new ValidationFailedException(String.valueOf(str));
                }
            }
            orders.setOrderProducts(orderProductList);
            orders.setAmountPaid(totalAmount);

            // updated
            orderRepo.saveAndFlush(orders);
            List<OrderStatus> orderStatusList = orderStatusService.saveAll(orderProductList);
            orderStatusRepo.saveAll(orderStatusList);

            cartRepo.deleteByCustomerAndVarIds(user.getId(), ids);
            return "Order placed successfully!" + " " + "Order ID is = " + orders.getId();
        }
    }


    public String orderDirectly(Long productVarId, int quantity) {

        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        } else {
            Orders orders = new Orders();

            ProductVariation productVariation = productVariationRepo.findById(productVarId).orElse(null);
            if (productVariation != null) {
                if (productVariation.getIsActive() && quantity > 0 && !productVariation.getProduct().getIsDeleted()
                        && productVariation.getQuantityAvailable() > 0 && productVariation.getQuantityAvailable() >= quantity) {

                    createOrder(user, orders);
                    orders.setAmountPaid(quantity * productVariation.getPrice());
                    orders.setPaymentMethod("Cash");

                    OrderProduct orderProduct = new OrderProduct();

                    orderProduct.setProductVariation(productVariation);
                    orderProduct.setPrice(productVariation.getPrice());
                    orderProduct.setQuantity(quantity);
                    orderProduct.setOrders(orders);
                    orders.setOrderProducts(Collections.singletonList(orderProduct));

                    orderRepo.saveAndFlush(orders);

                    List<OrderStatus> orderStatus = orderStatusService.saveAll(Collections.singletonList(orderProduct));
                    orderStatusRepo.saveAll(orderStatus);
                    return "Order placed successfully!" + " " + "Order ID is = " +orders.getId();
                } else {
                    StringBuilder str = new StringBuilder();

                    validateProductVariation(str, productVariation);
                    if (quantity <= 0) {
                        str.append("Quantity should be greater than zero!\n");
                    }
                    if(productVariation.getQuantityAvailable()<quantity)
                    {
                        str.append("Available quantity less than the requested quantity!");
                    }
                    throw new ValidationFailedException(String.valueOf(str));
                }
            } else {
                throw new EntityNotFoundException("Product Variation Not Found");
            }
        }
    }

    public void validateProductVariation(StringBuilder str, ProductVariation productVariation) {

        if (!productVariation.getIsActive()) {
            str.append("Product Variation not active\n");
        }
        if (productVariation.getProduct().getIsDeleted()) {
            str.append("Product is a deleted product!\n");
        }
        if (productVariation.getQuantityAvailable() == 0) {
            str.append("Available quantity is zero, product is out of stock!");
        }
    }


    public String cancel(Long orderProdId) {
        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        } else {
            OrderProduct orderProduct = orderProductRepo.findById(orderProdId).orElse(null);

            if (orderProduct != null) {
                if (Objects.equals(orderProduct.getOrders().getCustomer().getUserId(), user.getId())) {
                    if (orderProduct.getOrderStatus().getFromStatus().equals(Status.ORDER_PLACED) || orderProduct.getOrderStatus().getFromStatus().equals(Status.ORDER_CONFIRMED)) {
                        OrderStatus orderStatus = orderProduct.getOrderStatus();
                        if(orderStatus.getToStatus() != null) {
                            orderStatus.setFromStatus(orderStatus.getToStatus());
                        }
                        orderStatus.setToStatus(Status.CANCELLED);

                        orderStatusRepo.save(orderStatus);
                    } else {
                        throw new ValidationFailedException("Order isn't placed yet!");
                    }

                } else {
                    throw new ResourceForbiddenException("Not accessible to another user!");
                }
            } else {
                throw new EntityNotFoundException("Order Doesn't Exists!");
            }
        }
        return "Order Cancelled!";
    }



    public String returnOrder(Long orderProdId) {
        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        } else {
            OrderProduct orderProduct = orderProductRepo.findById(orderProdId).orElse(null);

            if (orderProduct != null) {
                if (Objects.equals(orderProduct.getOrders().getCustomer(), user.getCustomer())) {

                    if(orderProduct.getOrderStatus().getToStatus() != null && orderProduct.getOrderStatus().getToStatus().equals(Status.CANCELLED) )
                        throw new ValidationFailedException("Order is already cancelled!");

                    if(orderProduct.getOrderStatus().getToStatus() != null && orderProduct.getOrderStatus().getToStatus().equals(Status.RETURN_REQUESTED))
                        throw new ValidationFailedException("Return request already placed!");

                    if (orderProduct.getOrderStatus().getToStatus().equals(Status.DELIVERED)) {
                        OrderStatus orderStatus = orderProduct.getOrderStatus();
                        orderStatus.setFromStatus(orderStatus.getToStatus());
                        orderStatus.setToStatus(Status.RETURN_REQUESTED);

                        orderStatusRepo.save(orderStatus);
                    } else {
                        throw new ValidationFailedException("Order isn't delivered yet!");
                    }

                } else {
                    throw new ResourceForbiddenException("Not accessible to another user!");
                }
            } else {
                throw new EntityNotFoundException("Order Doesn't Exists!");
            }
        }
        return "Order Return initiated!";
    }



    public Orders view(Long orderId) {
        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        } else {
            Orders orders = orderRepo.findById(orderId).orElse(null);
            if (orders != null) {
                return orders;
            } else {
                throw new EntityNotFoundException("Order doesn't exist!");
            }
        }
    }

    // for customer
    public List<Orders> list(int max, int offset, String sort) {
        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        }
        //
        List<Orders> orderList = orderRepo.findAllByCustomer(user.getCustomer(), PageRequest.of(offset, max, Sort.by(sort).descending()));
        return orderList;
    }


    // seller api
    public List<Orders> listSeller(int max, int offset, String sort){
        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        }
        return orderRepo.findAllBySeller(user.getId(),PageRequest.of(offset, max, Sort.by(sort).descending()));
    }

    public String changeStatus(Long id,Status fromStatus, Status toStatus) {
        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        } else {
            OrderProduct orderProduct = orderProductRepo.findById(id).orElse(null);
            if (orderProduct != null) {
                if (Objects.equals(orderProduct.getProductVariation().getProduct().getSeller(), user.getSeller())) {
                    //
                    OrderStatus orderStatus = orderProduct.getOrderStatus();
                    orderStatus.setToStatus(toStatus);
                    orderStatus.setFromStatus(fromStatus);

                    orderStatusRepo.save(orderStatus);
                } else {
                    throw new ValidationFailedException("Only the seller of the product have rights to change status!");
                }
            } else {
                throw new EntityNotFoundException("Order Product Not Found!");
            }
            return "Order status changed!";
        }
    }



    // admin api
    public String changeOrderStatus(Long id,Status fromStatus, Status toStatus) {
        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        } else {
            OrderProduct orderProduct = orderProductRepo.findById(id).orElse(null);
            if (orderProduct != null) {
                {
                    OrderStatus orderStatus = orderProduct.getOrderStatus();
                    orderStatus.setToStatus(toStatus);
                    orderStatus.setFromStatus(fromStatus);

                    orderStatusRepo.save(orderStatus);
                }
            } else {
                throw new EntityNotFoundException("Order Product Not Found!");
            }
            return "Order status changed!";
        }
    }


    public List<Orders> listAll(int max, int offset, String sort){
        String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User Not Found");
        }
        return orderRepo.findAll(PageRequest.of(offset, max, Sort.by(sort).descending())).getContent();
    }




}



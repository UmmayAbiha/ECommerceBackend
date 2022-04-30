package com.abiha.springboot.bootcampproject.contollers;

import com.sun.mail.iap.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginLogoutController {


    @Autowired
    private TokenStore tokenStore;

    @Autowired
    public LoginLogoutController(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @RequestMapping(value = "/logoutUser", method = RequestMethod.POST)
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
            return new ResponseEntity<>("Logout Successfull", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Invalid token", HttpStatus.BAD_REQUEST);
        }
    }
}

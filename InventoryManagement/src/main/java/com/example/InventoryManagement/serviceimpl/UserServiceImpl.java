package com.example.InventoryManagement.serviceimpl;


import com.example.InventoryManagement.JWT.CustomerUserDetailsService;
import com.example.InventoryManagement.constents.ShoeConstant;
import com.example.InventoryManagement.dao.UserDao;
import com.example.InventoryManagement.model.User;
import com.example.InventoryManagement.service.UserService;
import com.example.InventoryManagement.utils.ShoeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;



    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUserDetailsService customerUserDetailsService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return ShoeUtils.getResponseEntity("Successfully Registered.", HttpStatus.OK);
                } else {
                    return ShoeUtils.getResponseEntity("Email already there", HttpStatus.BAD_GATEWAY);
                }
            } else {
                return ShoeUtils.getResponseEntity(ShoeConstant.INVALID_DATA, HttpStatus.BAD_GATEWAY);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ShoeUtils.getResponseEntity(ShoeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }



    private boolean validateSignUpMap(Map<String, String> requestMap){
        if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password")) {

            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String, String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"),requestMap.get("password"))
            );
            if (auth.isAuthenticated()){
                if(customerUserDetailsService.getUserDetail().setStatus();)
            }
        }catch (Exception ex){
            log.error("{}",ex);
        }
    }
}

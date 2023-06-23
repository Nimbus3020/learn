package com.example.InventoryManagement.restimpl;

import com.example.InventoryManagement.constents.ShoeConstant;
import com.example.InventoryManagement.rest.UserRest;
import com.example.InventoryManagement.service.UserService;
import com.example.InventoryManagement.utils.ShoeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try{
            return userService.signUp(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ShoeUtils.getResponseEntity(ShoeConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try{
            return userService.login(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ShoeUtils.getResponseEntity(ShoeConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }






}

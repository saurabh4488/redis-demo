package com.example.redisdemo.controller;

import com.example.redisdemo.model.ActualScriptMaster;
import com.example.redisdemo.model.SecurityIdRequest;
import com.example.redisdemo.model.User;
import com.example.redisdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
public class UserContoller {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        boolean result=userService.saveUser(user);
        if(result)
            return ResponseEntity.ok("User Created Successfully");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<User>> fetchAllUser(){
        List<User> users;
        users=userService.fetchAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/getcsvdata")
    public String getFileData(@RequestParam MultipartFile file) throws IOException {
        log.info("CSV to ActualScriptMaster Data Transfer Started");
        userService.addMasterData(file);
        return "Data Fetched from file successfully";
    }

    @GetMapping("/getcachedata")
    public ResponseEntity<List<ActualScriptMaster>> fetchCacheData(){
        List<ActualScriptMaster> actualScriptMasterList;
        actualScriptMasterList=userService.fetchCacheData();
        return ResponseEntity.ok(actualScriptMasterList);
    }

//    @GetMapping("/security-id")
//    public String getSecurityId(SecurityIdRequest securityIdRequest){
//        String securityId=userService.getSecurityId(securityIdRequest);
//        return securityId;
//    }
}

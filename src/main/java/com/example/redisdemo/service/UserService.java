package com.example.redisdemo.service;

import com.example.redisdemo.model.ActualScriptMaster;
import com.example.redisdemo.model.SecurityIdRequest;
import com.example.redisdemo.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public interface UserService {
    boolean saveUser(User user);

    List<User> fetchAllUsers();

    void addMasterData(MultipartFile file) throws IOException;

    List<ActualScriptMaster> fetchCacheData();

}

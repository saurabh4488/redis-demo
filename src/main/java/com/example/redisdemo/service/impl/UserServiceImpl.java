package com.example.redisdemo.service.impl;

import com.example.redisdemo.model.ActualScriptMaster;
import com.example.redisdemo.model.User;
import com.example.redisdemo.repository.ActualMasterRepository;
import com.example.redisdemo.repository.UserDao;
import com.example.redisdemo.service.UserService;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String REDIS_CACHE_VALUE = "ACTUAL_SCRIPT_MASTER";

    @Autowired
    private UserDao userDao;

    @Autowired
    private ActualMasterRepository actualMasterRepository;


    @Value("${script.csv-path}")
    private String csvPath;
    @Override
    public boolean saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public List<User> fetchAllUsers() {
        return userDao.fetchAllUser();
    }

    @Override
    public void addMasterData(MultipartFile file) throws IOException {
        File convFile = new File(System.getProperty("user.dir") + csvPath+file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();

        List<ActualScriptMaster> csvDataList =new CsvToBeanBuilder(new FileReader(convFile)).withType(ActualScriptMaster.class)
                .withIgnoreQuotations(true)
                .withThrowExceptions(false)
                .build().parse();


        for(ActualScriptMaster script: csvDataList){
            if (script.getSem_instrument_name().equals("EQUITY")){
                ActualScriptMaster actualScriptMaster =new ActualScriptMaster();
                actualScriptMaster.setSem_exm_exch_id(script.getSem_exm_exch_id());
                actualScriptMaster.setSem_segment(script.getSem_segment());
                actualScriptMaster.setSem_smst_security_id(script.getSem_smst_security_id());
                actualScriptMaster.setSem_instrument_name(script.getSem_instrument_name());
                actualScriptMaster.setSem_expiry_code(script.getSem_expiry_code());
                actualScriptMaster.setSem_trading_symbol(script.getSem_trading_symbol());
                actualScriptMaster.setSem_lot_units(script.getSem_lot_units());
                actualScriptMaster.setSem_custom_symbol(script.getSem_custom_symbol());
                actualScriptMaster.setScriptDate(new Date().toString());
                actualMasterRepository.fetchAllData(actualScriptMaster);
            }
        }

    }

    @Override
    public List<ActualScriptMaster> fetchCacheData() {
        return actualMasterRepository.fetchCacheData();
    }

}

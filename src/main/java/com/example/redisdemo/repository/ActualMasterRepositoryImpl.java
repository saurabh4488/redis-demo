package com.example.redisdemo.repository;

import com.example.redisdemo.model.ActualScriptMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActualMasterRepositoryImpl implements ActualMasterRepository {


    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "ACTUAL_SCRIPT_MASTER";


    @Override
    public boolean fetchAllData(ActualScriptMaster actualScriptMaster) {
        try {
            redisTemplate.opsForHash().put(KEY, actualScriptMaster.getSem_smst_security_id(), actualScriptMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ActualScriptMaster> fetchCacheData() {
        List<ActualScriptMaster> actualScriptMasters;
        actualScriptMasters=redisTemplate.opsForHash().values(KEY);
        return actualScriptMasters;
    }

}

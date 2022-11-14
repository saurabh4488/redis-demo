package com.example.redisdemo.repository;


import com.example.redisdemo.model.ActualScriptMaster;

import java.util.List;

public interface ActualMasterRepository {

    boolean fetchAllData(ActualScriptMaster actualScriptMaster);

    List<ActualScriptMaster> fetchCacheData();

}


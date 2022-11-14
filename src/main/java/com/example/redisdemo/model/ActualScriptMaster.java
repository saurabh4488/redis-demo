package com.example.redisdemo.model;


import com.opencsv.bean.CsvBindByName;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class ActualScriptMaster implements Serializable {
    @CsvBindByName(column = "SEM_EXM_EXCH_ID")
    private String sem_exm_exch_id;

    @CsvBindByName(column = "SEM_SEGMENT")
    private String sem_segment;

    @Id
    @CsvBindByName(column = "SEM_SMST_SECURITY_ID")
    private String sem_smst_security_id;

    @CsvBindByName(column = "SEM_INSTRUMENT_NAME")
    private String sem_instrument_name;

    @CsvBindByName(column = "SEM_EXPIRY_CODE")
    private String sem_expiry_code;

    @CsvBindByName(column = "SEM_TRADING_SYMBOL")
    private String sem_trading_symbol;

    @CsvBindByName(column = "SEM_LOT_UNITS")
    private String sem_lot_units;

    @CsvBindByName(column = "SEM_CUSTOM_SYMBOL")
    private String sem_custom_symbol;

    private String scriptDate;
}

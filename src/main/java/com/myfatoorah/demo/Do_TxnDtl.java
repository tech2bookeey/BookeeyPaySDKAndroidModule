package com.myfatoorah.demo;

public class Do_TxnDtl {

    public String getSubMerchUID() {
        return SubMerchUID;
    }

    public void setSubMerchUID(String subMerchUID) {
        SubMerchUID = subMerchUID;
    }

    public String getTxn_AMT() {
        return Txn_AMT;
    }

    public void setTxn_AMT(String txn_AMT) {
        Txn_AMT = txn_AMT;
    }

    private String SubMerchUID;
    private String Txn_AMT;
}

package com.myfatoorah.demo;

public class Do_TxnHdr {

  private String PayFor;

    public String getPayFor() {
        return PayFor;
    }

    public void setPayFor(String payFor) {
        PayFor = payFor;
    }

    public String getMerch_TrackUID() {
        return Merch_TrackUID;
    }

    public void setMerch_TrackUID(String merch_TrackUID) {
        Merch_TrackUID = merch_TrackUID;
    }

    public String getPayMethod() {
        return PayMethod;
    }

    public void setPayMethod(String payMethod) {
        PayMethod = payMethod;
    }

    public String getBKY_Txn_UID() {
        return BKY_Txn_UID;
    }

    public void setBKY_Txn_UID(String BKY_Txn_UID) {
        this.BKY_Txn_UID = BKY_Txn_UID;
    }

    public String getMerch_Txn_UID() {
        return Merch_Txn_UID;
    }

    public void setMerch_Txn_UID(String merch_Txn_UID) {
        Merch_Txn_UID = merch_Txn_UID;
    }

    private String Merch_TrackUID;

    private String PayMethod;


    private String BKY_Txn_UID;

    private String Merch_Txn_UID;
}

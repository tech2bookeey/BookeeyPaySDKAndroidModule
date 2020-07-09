package com.myfatoorah.demo;

public class CreditAmexPojo {

    String price;
    String merchantId;
    String secreatKey;
    String surl;

    public String getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(String paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    String paymentOptions;

    public String getHashMac() {
        return hashMac;
    }

    public void setHashMac(String hashMac) {
        this.hashMac = hashMac;
    }

    String furl;
    String hashMac;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getSecreatKey() {
        return secreatKey;
    }

    public void setSecreatKey(String secreatKey) {
        this.secreatKey = secreatKey;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }

    public String getTranid() {
        return tranid;
    }

    public void setTranid(String tranid) {
        this.tranid = tranid;
    }

    public String getTxntime() {
        return txntime;
    }

    public void setTxntime(String txntime) {
        this.txntime = txntime;
    }

    String tranid;
    String txntime;
}

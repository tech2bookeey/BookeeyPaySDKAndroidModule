package com.myfatoorah.demo;

public class PaymentMethod {


    private Boolean IsDirectPayment;

    private int imageUri;

    private int paymentTypeId;

    public PaymentMethod(Boolean isDirectPayment, Integer imageUrl,int paymentTypeId) {
        IsDirectPayment = isDirectPayment;
        this.imageUri = imageUrl;
        this.paymentTypeId=paymentTypeId;
    }

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Integer getImageUrl() {
        return imageUri;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUri = imageUrl;
    }


    public Boolean getDirectPayment() {
        return IsDirectPayment;
    }

    public void setDirectPayment(Boolean directPayment) {
        IsDirectPayment = directPayment;
    }
}

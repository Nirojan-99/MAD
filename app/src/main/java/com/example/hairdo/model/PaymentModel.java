package com.example.hairdo.model;

public class PaymentModel {
    public String nameOnCard;
    public String cardNumber;
    public String  cvc;
    public String  month;
    public String  year;
    public String date;
    public String salonId;
    public String _id;
    public int amount;

    public PaymentModel() {
    }

    public PaymentModel(String nameOnCard, String cardNumber, String cvc, String month, String year, String date, String salonId, int amount) {
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.month = month;
        this.year = year;
        this.date = date;
        this.salonId = salonId;
        this.amount = amount;
    }
}

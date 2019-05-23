package com.regulatory.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;

/*
@author Shamish
 */
public class StockRecord implements Serializable {


   // Nationality;Country_of_Residence;date_of_birth;unique_Trader_id;Amount;currency;unique_Stock_ID;Buy_or_Sell

    String nationality;
    String countryOfResidence;
    Calendar dob;
    @NotNull(message = "Trader Id is a required field")
    String traderId;
    double amount;
    String currency;
    @NotNull(message = "Stock Id is a required field")
    String stockId;
    String buyOrSell;
    String firstName;
    String lastName;
    Calendar transTime;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Calendar getTransTime() {
        return transTime;
    }

    public void setTransTime(Calendar transTime) {
        this.transTime = transTime;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public Calendar getDob() {
        return dob;
    }

    public void setDob(Calendar dob) {
        this.dob = dob;
    }

    public String getTraderId() {
        return traderId;
    }

    public void setTraderId(String traderId) {
        this.traderId = traderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(String buyOrSell) {
        this.buyOrSell = buyOrSell;
    }


    @Override
    public String toString() {

        String msg="StockRecord{" +
                "nationality='" + nationality + '\'' +
                ", countryOfResidence='" + countryOfResidence + '\'' +
                ", dob=" + dob.getTime() +
                ", traderId='" + traderId + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", stockId='" + stockId + '\'' +
                ", buyOrSell='" + buyOrSell + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\''  ;

        if(transTime != null) {
            return msg + "transTime='" + transTime.getTime() + '}';
        } else
            return msg + '}';

    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (obj instanceof StockRecord) {

            if((this.getStockId().equals(((StockRecord) obj).getStockId()) &&
                    (this.getTraderId().equals(((StockRecord) obj).traderId))))
                return true;
        }
         return false;


    }

    @Override
    public int hashCode() {
        return this.getTraderId().hashCode()* this.getStockId().hashCode();
    }
}

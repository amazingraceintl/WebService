package com.jrealm.integration.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "realmpay-request")
@XmlType(propOrder = {"creditAccount", "creditAmount","creditCurrency"})
@JsonPropertyOrder({"creditAccount", "creditAmount","creditCurrency"})
public class DepositRequest {
    private String creditAccount,creditCurrency;

    public DepositRequest(String creditAccount, String creditCurrency, double creditAmount) {
        this.creditAccount = creditAccount;
        this.creditCurrency = creditCurrency;
        this.creditAmount = creditAmount;
    }

    private double creditAmount;

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getCreditCurrency() {
        return creditCurrency;
    }

    public void setCreditCurrency(String creditCurrency) {
        this.creditCurrency = creditCurrency;
    }

    @Override
    public String toString() {
        return "DepositRequest{" +
                "creditAccount='" + creditAccount + '\'' +
                ", creditCurrency='" + creditCurrency + '\'' +
                ", creditAmount=" + creditAmount +
                '}';
    }
}

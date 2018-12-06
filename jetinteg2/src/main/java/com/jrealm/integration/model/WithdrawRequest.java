package com.jrealm.integration.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "realmpay-request")
@XmlType(propOrder = {"debitAccount", "debitAmount","debitCurrency"})
@JsonPropertyOrder({"debitAccount", "debitAmount","debitCurrency"})
public class WithdrawRequest {
    public WithdrawRequest(String debitAccount, String debitCurrency, double debitAmount) {
        this.debitAccount = debitAccount;
        this.debitCurrency = debitCurrency;
        this.debitAmount = debitAmount;
    }

    private String debitAccount,debitCurrency;
    private double debitAmount;

    public String getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public String getDebitCurrency() {
        return debitCurrency;
    }

    public void setDebitCurrency(String debitCurrency) {
        this.debitCurrency = debitCurrency;
    }


    /*
    <WithdrawRequest>
    <debitAccount>1012847573828</debitAccount>
    <debitAmount>700.00</debitAmount>
    <vin>TT5DHEHRUEASSMFF</vin>
    </WithdrawRequest>
    */

    /*
        {
	"debitAccount" : "1012847573828",
	"debitAmount" :700.00,
	"vin" : "LKJF192883283201028"
}
    */
}

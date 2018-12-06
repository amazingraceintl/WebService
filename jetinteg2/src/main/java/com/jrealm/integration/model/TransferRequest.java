package com.jrealm.integration.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "realmpay-request")

@XmlType(propOrder = {"creditAccount", "debitAccount", "debitAmount","debitCurrency", "txnDescr"})
@JsonPropertyOrder({"creditAccount", "debitAccount", "debitAmount","debitCurrency", "txnDescr"})
public class TransferRequest {
    private String creditAccount, debitAccount, debitCurrency, txnDescr;
    private double debitAmount;

    public TransferRequest(String creditAccount, String debitAccount, String debitCurrency, String txnDescr, double debitAmount) {
        this.creditAccount = creditAccount;
        this.debitAccount = debitAccount;
        this.debitCurrency = debitCurrency;
        this.txnDescr = txnDescr;
        this.debitAmount = debitAmount;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public String getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public String getDebitCurrency() {
        return debitCurrency;
    }

    public void setDebitCurrency(String debitCurrency) {
        this.debitCurrency = debitCurrency;
    }

    public double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public String getTxnDescr() {
        return txnDescr;
    }

    public void setTxnDescr(String txnDescr) {
        this.txnDescr = txnDescr;
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "creditAccount='" + creditAccount + '\'' +
                ", debitAccount='" + debitAccount + '\'' +
                ", debitCurrency='" + debitCurrency + '\'' +
                ", txnDescr='" + txnDescr + '\'' +
                ", debitAmount=" + debitAmount +
                '}';
    }
}
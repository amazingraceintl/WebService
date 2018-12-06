package com.jrealm.integration.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Realmpay-Response")
@XmlType(propOrder = {"responseRef", "success","txnRef", "debitAccount", "debitAmount","debitBalance", "debitCharge",
        "creditAccount","creditAmount", "creditBalance", "currency","responseMessage", "errorAccountNo", "responseVIN"})
@JsonPropertyOrder({"responseRef","success","txnRef" ,"debitAccount", "debitAmount","debitBalance", "debitCharge",
        "creditAccount","creditAmount","creditBalance", "currency","responseMessage", "errorAccountNo", "responseVIN"})
public class RealmTransaction {

    private String errorAccountNo, responseMessage, txnRef,creditAccount, debitAccount,responseRef,responseVIN,currency;
    private boolean success = false;
    private  double debitBalance, creditBalance, debitAmount, debitCharge, creditAmount;

    public RealmTransaction() {
    }

    public RealmTransaction(WithdrawRequest withdrawRequest) {
        debitAccount = withdrawRequest.getDebitAccount();
        debitAmount = withdrawRequest.getDebitAmount();
        currency =withdrawRequest.getDebitCurrency();
    }

    public RealmTransaction(TransferRequest transferRequest) {
        debitAccount = transferRequest.getDebitAccount();
        creditAccount = transferRequest.getCreditAccount();
        debitAmount = transferRequest.getDebitAmount();
        creditAmount = transferRequest.getDebitAmount();
        currency = transferRequest.getDebitCurrency();
    }

    public RealmTransaction(DepositRequest depositRequest) {
        debitAccount = depositRequest.getCreditAccount();
        debitAmount = depositRequest.getCreditAmount();
        currency = depositRequest.getCreditCurrency();
    }

    public String getErrorAccountNo() {
        return errorAccountNo;
    }

    public void setErrorAccountNo(String errorAccountNo) {
        this.errorAccountNo = errorAccountNo;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getTxnRef() {
        return txnRef;
    }

    public void setTxnRef(String txnRef) {
        this.txnRef = txnRef;
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

    public String getResponseRef() {
        return responseRef;
    }

    public void setResponseRef(String responseRef) {
        this.responseRef = responseRef;
    }

    public String getResponseVIN() {
        return responseVIN;
    }

    public void setResponseVIN(String responseVIN) {
        this.responseVIN = responseVIN;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public double getDebitBalance() {
        return debitBalance;
    }

    public void setDebitBalance(double debitBalance) {
        this.debitBalance = debitBalance;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    public double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public double getDebitCharge() {
        return debitCharge;
    }

    public void setDebitCharge(double debitCharge) {
        this.debitCharge = debitCharge;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "RealmTransaction{" +
                "errorAccountNo='" + errorAccountNo + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                ", txnRef='" + txnRef + '\'' +
                ", creditAccount='" + creditAccount + '\'' +
                ", debitAccount='" + debitAccount + '\'' +
                ", responseRef='" + responseRef + '\'' +
                ", responseVIN='" + responseVIN + '\'' +
                ", currency='" + currency + '\'' +
                ", success=" + success +
                ", debitBalance=" + debitBalance +
                ", creditBalance=" + creditBalance +
                ", debitAmount=" + debitAmount +
                ", debitCharge=" + debitCharge +
                ", creditAmount=" + creditAmount +
                '}';
    }
}

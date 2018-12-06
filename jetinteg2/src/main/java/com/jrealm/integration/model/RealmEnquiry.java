package com.jrealm.integration.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement(name = "Realmpay-Enquiry")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"success", "account", "requestType","transactions","accountTitle","availableBalance", "workingBalance","responseMessage" })
@JsonPropertyOrder({"success", "account", "requestType","transactions", "accountTitle","availableBalance", "workingBalance", "responseMessage" })
public class RealmEnquiry {
    private String responseMessage, account, accountTitle;
    private String requestType = "BALANCE";
    private List<Transaction> transactions;
    private boolean success = false;
    private double availableBalance, workingBalance;

    public RealmEnquiry() {
    }

    public RealmEnquiry(String requestType) {
        this.requestType = requestType;
    }

    public RealmEnquiry(BalanceRequest balanceRequest) {
        this.requestType = "BALANCE";
        this.account = balanceRequest.getAccount_id();
    }

    public RealmEnquiry(StatementRequest statementRequest) {
        this.requestType = "STATEMENT";
        this.account = statementRequest.getAccount_id();
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public void setWorkingBalance(double workingBalance) {
        this.workingBalance = workingBalance;
    }

    public String getAccountTitle() {
        return null;
    }

    public String getSuccess() {
        return null;
    }

    public String getRequestType() {
        return null;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getAccount() {
        return account;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public boolean isSuccess() {
        return success;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public double getWorkingBalance() {
        return workingBalance;
    }

}

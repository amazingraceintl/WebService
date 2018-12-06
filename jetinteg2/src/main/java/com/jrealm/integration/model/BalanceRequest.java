package com.jrealm.integration.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "request")
@XmlType(propOrder = {"account_id"})
@JsonPropertyOrder({"account_id"})
public class BalanceRequest {
    private String account_id;

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    @Override
    public String toString() {
        return "BalanceRequest{" +
                "account_id='" + account_id + '\'' +
                '}';
    }
}

package com.jrealm.integration.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "request")
@XmlType(propOrder = {"account_id", "start_date","end_date"})
@JsonPropertyOrder({"account_id", "start_date","end_date"})
public class StatementRequest {

    private String account_id, start_date, end_date;

    public StatementRequest() {

    }

    public StatementRequest(String account_id, String start_date, String end_date) {
        this.account_id = account_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "StatementRequest{" +
                "account_id='" + account_id + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                '}';
    }
}

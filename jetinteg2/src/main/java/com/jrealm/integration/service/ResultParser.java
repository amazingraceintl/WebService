package com.jrealm.integration.service;

import com.jrealm.integration.model.*;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResultParser {

    private DocumentBuilder db;
    private InputSource is = new InputSource();
    private Document doc = null;
    private String txnType = "";
    private String FAIL_FLAG = "FAILED";
    private StringBuilder x = new StringBuilder();

    public ResultParser() {
        try {
            this.db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public RealmTransaction withdraw(RealmTransaction realmTransaction, String response){
        txnType = "WITHDRAWAL";
        try {
            is.setCharacterStream(new StringReader(response));
            doc = db.parse(is);
            String txnStatus = null;
            txnStatus= doc.getElementsByTagName("TXN.STATUS").item(0).getTextContent();
            if (txnStatus.equalsIgnoreCase(FAIL_FLAG)){
                return failUpdate(realmTransaction,doc);
            }else {
                return successUpdate(realmTransaction, doc);
            }
        }
        catch (SAXException | IOException e) {
            e.printStackTrace();
            return realmTransaction;
        }
    }

    public RealmTransaction transfer(RealmTransaction realmTransaction, String response){
        txnType = "TRANSFER";
        try {
            is.setCharacterStream(new StringReader(response));
            doc = db.parse(is);
            String txnStatus = null;
            txnStatus= doc.getElementsByTagName("TXN.STATUS").item(0).getTextContent();
            if (txnStatus.equalsIgnoreCase(FAIL_FLAG)){
                return failUpdate(realmTransaction,doc);
            }else {
                return successUpdate(realmTransaction, doc);
            }
        }
        catch (SAXException | IOException e) {
            e.printStackTrace();
            return realmTransaction;
        }
    }

    public RealmTransaction deposit(RealmTransaction realmTransaction, String response){
        txnType = "DEPOSIT";
        try {
            is.setCharacterStream(new StringReader(response));
            doc = db.parse(is);
            String txnStatus = null;
            txnStatus= doc.getElementsByTagName("TXN.STATUS").item(0).getTextContent();
            if (txnStatus.equalsIgnoreCase(FAIL_FLAG)){
                return failUpdate(realmTransaction,doc);
            }else {
                return successUpdate(realmTransaction,doc);
            }
        }
        catch (SAXException | IOException e) {
            e.printStackTrace();
            return realmTransaction;
        }
    }

    public RealmEnquiry miniStatement(RealmEnquiry realmEnquiry,String response){
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transactions = new Transaction();
        try {
            is.setCharacterStream(new StringReader(response));
            doc = db.parse(is);
            String txnStatus= doc.getElementsByTagName("REQUEST.STATUS").item(0).getTextContent();
            if (txnStatus.equalsIgnoreCase(FAIL_FLAG)){
                realmEnquiry.setSuccess(false);
                NodeList nodes = doc.getElementsByTagName("ERROR");
                realmEnquiry.setResponseMessage(doc.getElementsByTagName("ERROR").item(0).getTextContent());
                return realmEnquiry;
            }else{
                realmEnquiry.setSuccess(true);
                NodeList nodes = doc.getElementsByTagName("STMT");
                for (int i = 0; i < nodes.getLength(); i++) {
                    Element line = (Element) ((Element) nodes.item(i)).getElementsByTagName("DATE").item(0);
                    transactions.setDate(getCharacterDataFromElement(line));
                    line = (Element) ((Element) nodes.item(i)).getElementsByTagName("DESCRIPTION").item(0);
                    transactions.setDescription(getCharacterDataFromElement(line));
                    line = (Element) ((Element) nodes.item(i)).getElementsByTagName("TRANSACTION.AMOUNT").item(0);
                    System.out.println(line.toString());
                    transactions.setTransactionAmount(getCharacterDataFromElement(line));
                    transactionList.add(transactions);
                }
                realmEnquiry.setTransactions(transactionList);
                realmEnquiry.setAccountTitle(doc.getElementsByTagName("ACCOUNT.HOLDER").item(0).getTextContent());
                realmEnquiry.setAvailableBalance(Double.parseDouble(doc.getElementsByTagName("WORKING.BALANCE").item(0).getTextContent()));
                realmEnquiry.setWorkingBalance(Double.parseDouble(doc.getElementsByTagName("ACTUAL.BALANCE").item(0).getTextContent()));
                return realmEnquiry;
            }
        }
        catch (SAXException | IOException e) {
            e.printStackTrace();
            return realmEnquiry;
        }
    }

    public RealmEnquiry balance(RealmEnquiry realmEnquiry,String response){
        try {
            is.setCharacterStream(new StringReader(response));
            doc = db.parse(is);
            realmEnquiry.setSuccess(true);
            realmEnquiry.setAvailableBalance(Double.parseDouble(doc.getElementsByTagName("WORKING.BALANCE").item(0).getTextContent()));
            realmEnquiry.setWorkingBalance(Double.parseDouble(doc.getElementsByTagName("ACTUAL.BALANCE").item(0).getTextContent()));
            realmEnquiry.setAccountTitle(doc.getElementsByTagName("ACCOUNT.HOLDER").item(0).getTextContent());
            return realmEnquiry;
        }
        catch (SAXException | IOException e) {
            e.printStackTrace();
            return realmEnquiry;
        }
    }

    public String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

    private RealmTransaction successUpdate(RealmTransaction realmTransaction, Document doc){
        String txnStatus = null;
        switch (txnType) {
            case "DEPOSIT":
                break;
            case "TRANSFER":
                break;
            case "WITHDRAWAL":
                break;
        }
        txnStatus= doc.getElementsByTagName("TXN.REF").item(0).getTextContent();
        realmTransaction.setResponseMessage(txnStatus);
        realmTransaction.setSuccess(true);
        realmTransaction.setDebitBalance(Double.parseDouble(doc.getElementsByTagName("WORKING.BALANCE").item(0).getTextContent()));
        realmTransaction.setTxnRef(doc.getElementsByTagName("TXN.REF").item(0).getTextContent());

        return realmTransaction;
    }

    private RealmTransaction failUpdate(RealmTransaction realmTransaction, Document doc){
        StringBuilder x = new StringBuilder();
        NodeList nodes = doc.getElementsByTagName("ERROR");
        int nodeCount = nodes.getLength();
        x.append(nodes.item(0).getFirstChild().getTextContent());
        realmTransaction.setResponseMessage(x.toString());
        x = null;
        realmTransaction.setSuccess(false);
        return realmTransaction;
    }

    private Double getDoubleDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return Double.valueOf(cd.getData());
        }
        return Double.valueOf("");
    }
}

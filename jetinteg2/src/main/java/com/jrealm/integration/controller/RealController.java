package com.jrealm.integration.controller;

import com.jrealm.integration.model.*;
import com.jrealm.integration.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;

@RequestMapping(value = "/realm",produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" })
@RestController
public class RealController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    private RealmTransaction withdraw(@RequestBody byte[] xmlMesssage) throws ParserConfigurationException, IOException, SAXException {
 //   private RealmTransaction withdraw(@RequestBody WithdrawRequest request) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new ByteArrayInputStream(xmlMesssage));
        doc.getDocumentElement().normalize();
        String debitAccount = doc.getElementsByTagName("debitAccount").item(0).getTextContent();
        String debitCurrency = doc.getElementsByTagName("debitCurrency").item(0).getTextContent();
        String debitAmount = doc.getElementsByTagName("debitAmount").item(0).getTextContent();
        WithdrawRequest request = new WithdrawRequest(debitAccount,debitCurrency,Double.parseDouble(debitAmount));
    //    public WithdrawRequest(String debitAccount, String debitCurrency, double debitAmount)
        System.out.println(Arrays.toString(new WithdrawRequest[]{request}));
        return transactionService.withdraw(request);
    }

    @PostMapping(value = "/transfer")

    private RealmTransaction transfer(@RequestBody byte[] xmlMesssage) throws ParserConfigurationException, IOException, SAXException {
    //private RealmTransaction transfer(@RequestBody TransferRequest request) throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new ByteArrayInputStream(xmlMesssage));
        doc.getDocumentElement().normalize();
        String creditAccount = doc.getElementsByTagName("creditAccount").item(0).getTextContent();
        String debitAccount = doc.getElementsByTagName("debitAccount").item(0).getTextContent();
        String debitCurrency = doc.getElementsByTagName("debitCurrency").item(0).getTextContent();
        String txnDescr = doc.getElementsByTagName("txnDescr").item(0).getTextContent();
        String debitAmount = doc.getElementsByTagName("debitAmount").item(0).getTextContent();
        TransferRequest request = new TransferRequest(creditAccount,debitAccount,debitCurrency,txnDescr,Double.parseDouble(debitAmount));
         //(String creditAccount, String debitAccount, String debitCurrency, String txnDescr, double debitAmount) {
        System.out.println(request.toString());
        return transactionService.transfer(request);
    }

    @PostMapping(value = "/deposit")

    //private RealmTransaction deposit(@RequestBody DepositRequest request) {
    private RealmTransaction deposit(@RequestBody byte[] xmlMesssage) throws ParserConfigurationException, IOException, SAXException {
        //private RealmTransaction transfer(@RequestBody TransferRequest request) throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new ByteArrayInputStream(xmlMesssage));
        doc.getDocumentElement().normalize();
        String creditAccount = doc.getElementsByTagName("creditAccount").item(0).getTextContent();
        String creditCurrency = doc.getElementsByTagName("creditCurrency").item(0).getTextContent();
        String creditAmount = doc.getElementsByTagName("creditAmount").item(0).getTextContent();
        DepositRequest request = new DepositRequest(creditAccount,creditCurrency,Double.parseDouble(creditAmount));
        //String creditAccount, String creditCurrency, double creditAmount
        System.out.println(request.toString());
        return transactionService.deposit(request);
    }

    @PostMapping(value = "/balance")
    private RealmEnquiry balance(@RequestBody BalanceRequest request) {
        System.out.println(request.toString());
        return transactionService.balance(request);
    }

    @PostMapping(value = "/statment")
    private RealmEnquiry miniStatement(@RequestBody StatementRequest request) {
        System.out.println(request.toString());
        return transactionService.miniStatement(request);
    }
}

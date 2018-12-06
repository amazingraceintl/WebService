package com.jrealm.integration.service;

import com.jrealm.integration.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    InterfaceOperation interfaceOperation;

    @Autowired
    ResultParser resultParser;

    public RealmTransaction withdraw(WithdrawRequest withdrawRequest){
        String[] args = new String[3];
        args[0] = withdrawRequest.getDebitAccount();
        args[1] = String.valueOf(withdrawRequest.getDebitCurrency());
        args[2] = String.valueOf(withdrawRequest.getDebitAmount());

        String response  = interfaceOperation.perform("WTD",args);
        RealmTransaction product = new RealmTransaction(withdrawRequest);
        if(response.equals("CBS Down")) {
            product.setSuccess(false);
            product.setResponseMessage("UNABLE TO REACH CBS");
            System.out.println(product.toString());
            return product;
        }else {
            System.out.println(product.toString());
            return resultParser.withdraw(product,response);
        }
    }

    public RealmTransaction transfer(TransferRequest transferRequest){
        String[] args = new String[5];
        args[0] = transferRequest.getDebitAccount();
        args[1] = transferRequest.getCreditAccount();
        args[2] = String.valueOf(transferRequest.getDebitCurrency());
        args[3] = String.valueOf(transferRequest.getDebitAmount());
        args[4] = transferRequest.getTxnDescr();
        String response  = interfaceOperation.perform("TRF",args);
        RealmTransaction product = new RealmTransaction(transferRequest);
        if(response.equals("CBS Down")) {
            product.setSuccess(false);
            product.setResponseMessage("UNABLE TO REACH CBS");
            System.out.println(product.toString());
            return product;
        }else {
            System.out.println(product.toString());
            return resultParser.transfer(product,response);
        }
    }

    public RealmTransaction deposit(DepositRequest depositRequest){
        String[] args = new String[3];
        args[0] = depositRequest.getCreditAccount();
        args[1] = String.valueOf(depositRequest.getCreditCurrency());
        args[2] = String.valueOf(depositRequest.getCreditAmount());
        String response  = interfaceOperation.perform("DEP",args);
        RealmTransaction product = new RealmTransaction(depositRequest);
        if(response.equals("CBS Down")) {
            product.setSuccess(false);
            product.setResponseMessage("UNABLE TO REACH CBS");
            System.out.println(product.toString());
            return product;
        }else {
            System.out.println(product.toString());
            return resultParser.deposit(product,response);
        }
    }

    public RealmEnquiry miniStatement(StatementRequest statementRequest){
        String[] args = new String[3];
        args[0] = statementRequest.getAccount_id();
        args[1] = statementRequest.getStart_date();
        args[2] = statementRequest.getEnd_date();
        String response  = interfaceOperation.perform("STMT",args);
        RealmEnquiry product = new RealmEnquiry(statementRequest);
        if(response.equalsIgnoreCase("CBS Down")) {
            product.setSuccess(false);
            product.setResponseMessage("UNABLE TO REACH CBS");
            System.out.println(product.toString());
            return product;
        }else {
            System.out.println(product.toString());
            return resultParser.miniStatement(product,response);
        }
    }

    public RealmEnquiry balance(BalanceRequest balanceRequest){
        String[] args = new String[1];
        args[0] = balanceRequest.getAccount_id();
        System.out.println(args[0]);
        String response  = interfaceOperation.perform("BAL",args);
        RealmEnquiry product = new RealmEnquiry(balanceRequest);
        if(response.equalsIgnoreCase("CBS Down")) {
            product.setSuccess(false);
            product.setResponseMessage("UNABLE TO REACH CBS");
            System.out.println(product.toString());
            return product;
        }else {
            System.out.println(product.toString());
            return resultParser.balance(product,response);
        }
    }
}

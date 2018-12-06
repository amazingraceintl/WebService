package com.jrealm.integration.service;

import com.jbase.jremote.JDynArray;
import com.jbase.jremote.JRemoteException;
import com.jbase.jremote.JSubroutineParameters;
import com.jrealm.integration.BasicConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
public class InterfaceOperation {

    @Autowired
    CBSConnection cbsConnection;

    @Autowired
    private BasicConfiguration configuration;

    public String perform(String type, String[] args) {
        String response = null;

        String routine = configuration.getCbsRoutine();
        JSubroutineParameters params = new JSubroutineParameters();
        params.add(new JDynArray(""));
        switch (type) {
            case "WTD":
                params.add(new JDynArray("WTD"));
                params.add(new JDynArray(args[0] + "|" + args[1] + "|" + args[2]));
                break;
            case "DEP":
                params.add(new JDynArray("DEP"));
                params.add(new JDynArray(args[0] + "|" + args[1] + "|" + args[2]));
                break;
            case "BAL":
                params.add(new JDynArray("BAL"));
                params.add(new JDynArray(args[0] + "|"));
                break;
            case "TRF":
                params.add(new JDynArray("TRF"));
                params.add(new JDynArray(args[0] + "|" + args[1] + "|" + args[2] + "|" + args[3] + "|" + args[4]));
                break;
            case "STMT":
                params.add(new JDynArray("STMT"));
                params.add(new JDynArray(args[0] + "|" + args[1] + "|" + args[2]));
                break;
            }
        try {
            params = cbsConnection.connect().call(routine, params);
            System.out.println("As returned from t24");
            System.out.println("--------------------------------------");
            System.out.println(params);
            if (params != null) {
                String[] xa = String.valueOf(params.get(0)).split(",");
                String deli = "]]";
                int apache = StringUtils.countOccurrencesOf(String.valueOf(params), deli);
                response = xa[0].replaceAll("\\|", "\n")
                        .replaceAll(deli, "")
                        .replaceAll("<requests>","")
                        .replaceAll("<request>","")
                        .replaceAll("<<","<")
                        .replaceAll(">>",">");
                for (int i = 1; i <= apache; i++) {
                    response = response.replaceAll("<" + i + ">", "");
                }
                System.out.println(response.trim());
            }
            cbsConnection.close();
            return response;
        } catch (JRemoteException e) {
            e.printStackTrace();
             return "CBS Down";
        }
    }
}
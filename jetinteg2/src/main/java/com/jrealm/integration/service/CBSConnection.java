package com.jrealm.integration.service;

import com.jbase.jremote.DefaultJConnectionFactory;
import com.jbase.jremote.JConnection;
import com.jbase.jremote.JRemoteException;
import com.jrealm.integration.BasicConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CBSConnection {
    @Autowired
    private BasicConfiguration configuration;

    private JConnection jConnection = null;

    public JConnection connect() {

        try {
            DefaultJConnectionFactory factory = new DefaultJConnectionFactory();
            System.out.println(configuration.getCbsHost());
            System.out.println(configuration.getCbsPort());
            String host = configuration.getCbsHost();
            int port = configuration.getCbsPort();
            factory.setHost(host);
            factory.setPort(port);
            System.out.println("Connecting to CBS ...");
            System.out.println(factory.toString());
            jConnection = factory.getConnection();
            System.out.println("Connected Successfully");
            return jConnection;
        } catch (JRemoteException e) {
            System.out.println(e);
            return null;
        }

    }

    public void close() {
        try {
            jConnection.close();
        } catch (JRemoteException e) {
            e.printStackTrace();
        }
    }

}
package com.jrealm.integration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config")
public class BasicConfiguration {
    private String cbsHost, cbsRoutine;
    private int cbsPort;

    public String getCbsHost() {
        return cbsHost;
    }

    public void setCbsHost(String cbsHost) {
        this.cbsHost = cbsHost;
    }

    public String getCbsRoutine() {
        return cbsRoutine;
    }

    public void setCbsRoutine(String cbsRoutine) {
        this.cbsRoutine = cbsRoutine;
    }

    public int getCbsPort() {
        return cbsPort;
    }

    public void setCbsPort(int cbsPort) {
        this.cbsPort = cbsPort;
    }
}

package com.emptytrip.hr.emptytrip;

public class OAuthModel {

    private String service;
    private String token;
    private String xoauth;

    public OAuthModel() {
    }

    public OAuthModel(String service, String token, String xoauth) {
        this.service = service;
        this.token = token;
        this.xoauth = xoauth;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getXoauth() {
        return xoauth;
    }

    public void setXoauth(String xoauth) {
        this.xoauth = xoauth;
    }

    @Override
    public String toString() {
        return "OAuthModel{" +
                "service='" + service + '\'' +
                ", token='" + token + '\'' +
                ", xoauth='" + xoauth + '\'' +
                '}';
    }
}

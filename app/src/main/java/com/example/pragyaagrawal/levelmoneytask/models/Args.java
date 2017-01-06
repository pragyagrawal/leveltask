package com.example.pragyaagrawal.levelmoneytask.models;

public class Args {

    private int uid;

    private boolean json_strict_mode;

    private String api_token;

    private String token;

    private boolean json_verbose_response;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public boolean isJson_strict_mode() {
        return json_strict_mode;
    }

    public void setJson_strict_mode(boolean json_strict_mode) {
        this.json_strict_mode = json_strict_mode;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isJson_verbose_response() {
        return json_verbose_response;
    }

    public void setJson_verbose_response(boolean json_verbose_response) {
        this.json_verbose_response = json_verbose_response;
    }
}

package com.example.onthimusic;

import java.io.Serializable;

public class Music implements Serializable {
    private String id,tencs,tenbaihat;

    public Music(String id, String tencs, String tenbaihat) {
        this.id = id;
        this.tencs = tencs;
        this.tenbaihat = tenbaihat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTencs() {
        return tencs;
    }

    public void setTencs(String tencs) {
        this.tencs = tencs;
    }

    public String getTenbaihat() {
        return tenbaihat;
    }

    public void setTenbaihat(String tenbaihat) {
        this.tenbaihat = tenbaihat;
    }
}

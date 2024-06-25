package com.zbiir.loundry.model;

import lombok.Data;


public class ApiRespond {
    private boolean status;

//    public boolean isStatus() {
//        return status;
//    }

    public ApiRespond setStatus(boolean status) {
        this.status = status;
        return this;
    }
}

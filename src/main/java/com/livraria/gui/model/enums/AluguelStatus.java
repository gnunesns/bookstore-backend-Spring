package com.livraria.gui.model.enums;

import lombok.Getter;

@Getter
public enum AluguelStatus {

    ON_TIME ("ON TIME"),
    DELAYED ("DELAYED"),
    IN_PROGRESS ("IN PROGRESS");

    private final String status;

    public String getAluguelStatus(){
        return this.status;
    }
    AluguelStatus(String status) {
        this.status = status;
    }
}

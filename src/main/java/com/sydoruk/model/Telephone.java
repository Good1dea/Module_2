package com.sydoruk.model;

import lombok.Getter;
import lombok.Setter;

public class Telephone extends Goods {

    @Setter
    @Getter
    private String model;

    public Telephone() {
        setType(ProductType.TELEPHONE);
    }
}
package com.sydoruk.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TV extends Goods {

    private int diagonal;
    private String country;

    public TV() {
        setType(ProductType.TV);
    }
}
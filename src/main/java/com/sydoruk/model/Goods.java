package com.sydoruk.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Goods {

    private ProductType type;
    private String series;
    private String screenType;
    private int price;
}
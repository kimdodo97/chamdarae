package com.chamdarae.domain;

import javax.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    private String zipcode;
    private String mainAddr;
    private String detailAddr;

    protected Address(){

    }

    @Builder
    public Address(String mainAddr, String detailAddr, String zipcode) {
        this.mainAddr = mainAddr;
        this.detailAddr = detailAddr;
        this.zipcode = zipcode;
    }
}

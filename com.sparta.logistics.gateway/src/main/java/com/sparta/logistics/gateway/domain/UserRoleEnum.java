package com.sparta.logistics.gateway.domain;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum UserRoleEnum implements Serializable {
    MASTER(Authority.MASTER),
    HUB_MANAGER(Authority.HUB_MANAGER),
    HUB_DELIVERY(Authority.HUB_DELIVERY),
    COMPANY_DELIVERY(Authority.COMPANY_DELIVERY);

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public static class Authority {
        public static final String MASTER = "ROLE_MASTER";
        public static final String HUB_MANAGER = "ROLE_HUB_MANAGER";
        public static final String HUB_DELIVERY = "ROLE_HUB_DELIVERY";
        public static final String COMPANY_DELIVERY = "ROLE_COMPANY_DELIVERY";
    }

}
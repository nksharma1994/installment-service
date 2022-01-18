package com.daollar.instalment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Parent {
    private Long id;
    private String sender;
    private String receiver;
    private Double totalAmount;
    private Double totalPaidAmount;
}

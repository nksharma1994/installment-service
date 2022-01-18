package com.daollar.instalment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Child {
    private Long id;
    private String sender;
    private String receiver;
    private Double totalAmount;
    private Double paidAmount;
}

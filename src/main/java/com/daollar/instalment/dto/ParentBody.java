package com.daollar.instalment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentBody {
    private String sender;
    private String receiver;
    private Double totalAmount;
}

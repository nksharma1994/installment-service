package com.daollar.instalment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parent_table")
public class ParentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id")
    private Long id;
    @Column(name = "sender")
    private String sender;
    @Column(name = "receiver")
    private String receiver;
    @Column(name = "total_amount")
    private Double totalAmount;
    @Column(name = "total_paid_amount", columnDefinition="Decimal(10,2) default '0.0'")
    private Double totalPaidAmount=0.0;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<ChildEntity> child;
}

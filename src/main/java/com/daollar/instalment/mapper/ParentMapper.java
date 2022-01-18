package com.daollar.instalment.mapper;

import com.daollar.instalment.dto.Child;
import com.daollar.instalment.dto.ChildBody;
import com.daollar.instalment.dto.Parent;
import com.daollar.instalment.dto.ParentBody;
import com.daollar.instalment.entity.ChildEntity;
import com.daollar.instalment.entity.ParentEntity;
import org.mapstruct.Mapper;

@Mapper
public abstract class ParentMapper {
    public abstract Parent parentEntityToParent(ParentEntity parentEntity);

    public abstract ParentEntity parentBodyToParentEntity(ParentBody parentBody);

    public abstract ChildEntity childBodyToChildEntity(ChildBody childBody);

    public Child childEntityToChild(ParentEntity parent, ChildEntity childEntity) {
        Child child = new Child();
        child.setId(childEntity.getId());
        child.setSender(parent.getSender());
        child.setReceiver(parent.getReceiver());
        child.setTotalAmount(parent.getTotalAmount());
        child.setPaidAmount(childEntity.getPaidAmount());
        return child;
    }
}

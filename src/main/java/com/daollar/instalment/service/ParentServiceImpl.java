package com.daollar.instalment.service;

import com.daollar.instalment.dto.*;
import com.daollar.instalment.entity.ChildEntity;
import com.daollar.instalment.entity.ParentEntity;
import com.daollar.instalment.mapper.ParentMapper;
import com.daollar.instalment.repository.ParentRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ParentServiceImpl implements ParentService{

    private final ParentRepository repository;
    private final ParentMapper mapper;

    @Autowired
    public ParentServiceImpl(ParentRepository repository) {
        this.repository=repository;
        this.mapper = Mappers.getMapper(ParentMapper.class);
    }


    @Override
    public PageResponse<Parent> getAllParent(Integer pageNumber, Integer recordsPerPage) {
        Pageable pageable = PageRequest.of(pageNumber-1, recordsPerPage, Sort.by("id").ascending());
        PageResponse<Parent> pageResponse = new PageResponse<>();
        Page<ParentEntity> parentPage = repository.findAll(pageable);
        if(!CollectionUtils.isEmpty(parentPage.getContent())){
            pageResponse.setContent(parentPage.getContent().stream()
                    .map(mapper::parentEntityToParent)
                    .collect(Collectors.toList()));
        } else {
            pageResponse.setContent(Collections.EMPTY_LIST);
        }
        pageResponse.setPageNumber(parentPage.getNumber()+1);
        pageResponse.setPageSize(parentPage.getSize());
        pageResponse.setTotalPages(parentPage.getTotalPages());
        pageResponse.setTotalElements(parentPage.getTotalElements());
        pageResponse.setFirst(parentPage.isFirst());
        pageResponse.setLast(parentPage.isLast());
        pageResponse.setNumberOfElements(parentPage.getNumberOfElements());
        return pageResponse;
    }

    @Override
    public Parent addParent(ParentBody parentBody) {
        ParentEntity parentEntity = mapper.parentBodyToParentEntity(parentBody);
        repository.save(parentEntity);
        return mapper.parentEntityToParent(parentEntity);
    }

    @Override
    public Child addChildToParent(Long id, ChildBody childBody) {
        Optional<ParentEntity> optionalParent = repository.findById(id);
        if(optionalParent.isPresent()){
            ParentEntity parent = optionalParent.get();
            ChildEntity child = mapper.childBodyToChildEntity(childBody);
            child.setParent(parent);
            parent.setTotalPaidAmount(parent.getTotalPaidAmount()  + childBody.getPaidAmount());
            parent.getChild().add(child);
            repository.save(parent);
            return mapper.childEntityToChild(parent, child);
        }
        return null;
    }

    @Override
    public List<Child> getAllChild(Long id) {
        Optional<ParentEntity> optionalParent = repository.findById(id);
        if(optionalParent.isPresent()){
            ParentEntity parent = optionalParent.get();
            return parent.getChild().stream().map(child-> mapper.childEntityToChild(parent, child))
                    .collect(Collectors.toList());
        }
        return null;
    }
}

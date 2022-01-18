package com.daollar.instalment.service;

import com.daollar.instalment.dto.*;

import java.util.List;


public interface ParentService {

    PageResponse<Parent> getAllParent(Integer pageNumber, Integer recordPerPage);
    Parent addParent(ParentBody parent);
    Child addChildToParent(Long id, ChildBody childBody);

    List<Child> getAllChild(Long parentId);
}

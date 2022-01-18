package com.daollar.instalment.repository;

import com.daollar.instalment.entity.ParentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends PagingAndSortingRepository<ParentEntity, Long> {
}

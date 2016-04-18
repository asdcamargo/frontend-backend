package com.frontback.crl.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.frontback.crl.model.Authority;

public interface IAuthorityRepository extends CrudRepository<Authority, Long> {

	Iterable<Authority> findAll(Sort sort);

	Page<Authority> findAll(Pageable pageable);

}

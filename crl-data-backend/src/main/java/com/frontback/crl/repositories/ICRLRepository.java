package com.frontback.crl.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.frontback.crl.model.CRL;

public interface ICRLRepository extends CrudRepository<CRL, Long> {

	CRL findByAuthorityKey(String chaveAutoridade);

	@Override
	List<CRL> findAll();

}

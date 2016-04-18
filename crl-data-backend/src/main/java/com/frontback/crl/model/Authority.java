package com.frontback.crl.model;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Authority {

	@javax.persistence.Id
	@GeneratedValue
	private Long id;

	@ElementCollection
	private Set<String> pontosDistribuicao;

	@OneToOne
	private CRL ultimaLCR;

	public Iterator<String> getPontos() {
		return pontosDistribuicao.iterator();
	}

}

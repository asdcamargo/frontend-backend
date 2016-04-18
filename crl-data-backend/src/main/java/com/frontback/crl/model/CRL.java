package com.frontback.crl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class CRL implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -547099423241715252L;
	@javax.persistence.Id
	@GeneratedValue
	private Long id;

	@Lob
	@Column
	private byte[] content;

	@NotNull
	@NotEmpty
	@Column
	private String authorityKey;

	public CRL(Long id, byte[] content, String authorityKey) {
		super();
		this.id = id;
		this.content = content;
		this.authorityKey = authorityKey;
	}

}

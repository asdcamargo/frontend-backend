package com.frontback.crl.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.IOUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.frontback.crl.exception.ExcecaoNotFound;
import com.frontback.crl.model.Authority;
import com.frontback.crl.model.CRL;
import com.frontback.crl.model.CRLVO;
import com.frontback.crl.model.ResponseError;
import com.frontback.crl.repositories.IAuthorityRepository;
import com.frontback.crl.repositories.ICRLRepository;

@RestController
public class CRLService extends RESTService {

	@Autowired
	private ICRLRepository crlRepository;
	@Autowired
	private IAuthorityRepository authorityRepository;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/crls", method = RequestMethod.POST, consumes = "multipart/form-data")
	public void upload(@RequestPart("data") @Valid CRL crl,
			@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file) throws IOException {
		byte[] bytes;

		if (!file.isEmpty()) {
			bytes = file.getBytes();
			// store file in storage
			crl.setContent(bytes);
			this.crlRepository.save(crl);
		}
		System.out.println(String.format("receive %s", "kk"));

	}

	@RequestMapping(value = "/crls/file/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void save(@PathVariable long id, HttpServletResponse response) throws IOException {
		CRL lcr = this.crlRepository.findOne(id);
		InputStream contentInput = new ByteArrayInputStream(lcr.getContent());
		response.setContentType("application/pkix-crl");
		response.addHeader("Content-Disposition", "attachment; filename=lcr.crl");
		IOUtils.copy(contentInput, response.getOutputStream());
		response.flushBuffer();
		return;
	}

	@RequestMapping(value = "/crls/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		this.crlRepository.delete(id);
	}

	@RequestMapping(value = "/crls", method = RequestMethod.GET)
	public List<CRLVO> getAll() {
		List<CRLVO> crls = new ArrayList<CRLVO>();
		this.crlRepository.findAll().forEach(crl -> crls.add(new CRLVO(crl.getId(), crl.getAuthorityKey())));
		return crls;
	}

	@RequestMapping("/crls/{key}")
	public CRL getAll(@PathVariable String authorityKey) throws ExcecaoNotFound {
		CRL lcr = this.crlRepository.findByAuthorityKey(authorityKey);
		if (lcr == null) {
			throw new ExcecaoNotFound();
		}
		return lcr;
	}

	@RequestMapping("/authorities")
	public Iterable<Authority> getAutoridades() {
		return this.authorityRepository.findAll(new PageRequest(1, 20));
	}

	@ExceptionHandler(ExcecaoNotFound.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseError handleNotFound(ExcecaoNotFound excecao) {
		return new ResponseError("error.resource.notfound", "The requested resource was not found.");
	}

}

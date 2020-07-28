package com.sistem_funeraria.config.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonView;

import com.sistem_funeraria.config.jsonview.JsonBasic;

public class BSPage <T> extends org.springframework.data.domain.PageImpl<T> {
	private static final long serialVersionUID = 4651578016889613857L;

	public BSPage(final List<T> content, final Pageable pageable, final long total) {
	    super(content, pageable, total);
	}

	public BSPage(final List<T> content) {
	    super(content);
	}

	public BSPage(final Page<T> page, final Pageable pageable) {
	    super(page.getContent(), pageable, page.getTotalElements());
	}

	@JsonView(JsonBasic.class)
	public int getTotalPages() {
	    return super.getTotalPages();
	}

	@JsonView(JsonBasic.class)
	public long getTotalElements() {
	    return super.getTotalElements();
	}

	@JsonView(JsonBasic.class)
	public boolean hasNext() {
	    return super.hasNext();
	}

	@JsonView(JsonBasic.class)
	public boolean isLast() {
	    return super.isLast();
	}

	@JsonView(JsonBasic.class)
	public boolean hasContent() {
	    return super.hasContent();
	}

	@JsonView(JsonBasic.class)
	public List<T> getContent() {
	    return super.getContent();
	}

}

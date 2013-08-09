package com.sushant.verma.common.dto;

import java.io.Serializable;

public class SearchResultDto implements Serializable{


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String text;

	private String image;

//	private String extra;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

/*	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
*/


}
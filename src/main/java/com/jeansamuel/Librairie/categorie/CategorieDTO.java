package com.jeansamuel.Librairie.categorie;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Categorie Modele")
public class CategorieDTO implements Comparable<CategorieDTO> {

	public CategorieDTO() {
	}
	
	public CategorieDTO(String code, String label) {
		super();
		this.code = code;
		this.label = label;
	}

	@ApiModelProperty(value = "Categorie code")
	private String code;

	@ApiModelProperty(value = "Categorie label")
	private String label;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public int compareTo(CategorieDTO o) {
		return label.compareToIgnoreCase(o.label);
	}
	
}

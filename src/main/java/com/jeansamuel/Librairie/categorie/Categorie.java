package com.jeansamuel.Librairie.categorie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "CATEGORIE")
public class Categorie {
	
    private String code;
    
    private String label;

	public Categorie() {
		
	}
	public Categorie(String code, String label) {
		super();
		
		this.code = code;
		this.label = label;
		}
	
	@Id
    @Column(name = "CODE")
    public String getCode() {
        return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name = "LABEL", nullable = false)
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}

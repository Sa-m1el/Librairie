package com.jeansamuel.Librairie.livre;

import java.time.LocalDate;

import com.jeansamuel.Librairie.categorie.CategorieDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Livre Modele")
public class LivreDTO implements Comparable<LivreDTO> {

	@ApiModelProperty(value = "Livre id")
	private Integer id;

	@ApiModelProperty(value = "Livre titre")
	private String titre;
	
	@ApiModelProperty(value = "Livre isbn")
	private String isbn;
	
	@ApiModelProperty(value = "Livre date edition par editeur")
	private LocalDate Dateedition;
	
	@ApiModelProperty(value = "Livre date enregistrement dans la librairie")
	private LocalDate enregistrementDate;
	
	@ApiModelProperty(value = "Livre total examplaire")
	private Integer totalExamplaire;
	
	@ApiModelProperty(value = "Livre auteur")
	private String auteur;
	
	@ApiModelProperty(value = "Livre categorie")
	private CategorieDTO categorie;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public LocalDate getDateedition() {
		return Dateedition;
	}
	public void setDateedition(LocalDate dateedition) {
		Dateedition = dateedition;
	}
	
	public LocalDate getEnregistrementDate() {
		return enregistrementDate;
	}
	public void setEnregistrementDate(LocalDate enregistrementDate) {
		this.enregistrementDate = enregistrementDate;
	}
	
	 public Integer getTotalExamplaire() {
		return totalExamplaire;
	}
	 public void setTotalExamplaire(Integer totalExamplaire) {
		this.totalExamplaire = totalExamplaire;
	}
	 
	 public CategorieDTO getCategorie() {
		return categorie;
	}
	 public void setCategorie(CategorieDTO categorie) {
		this.categorie = categorie;
	}
	 
	 public String getAuteur() {
		return auteur;
	}
	 public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	 
	 @Override
		public int compareTo(LivreDTO o) {
			return titre.compareToIgnoreCase(o.getTitre());
		}
		
	 
	 
}

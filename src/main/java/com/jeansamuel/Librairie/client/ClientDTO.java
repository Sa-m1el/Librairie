package com.jeansamuel.Librairie.client;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Client Modele")
public class ClientDTO implements Comparable<ClientDTO> {

	@ApiModelProperty(value = "id Client")
	private Integer id;
	
	@ApiModelProperty(value = "Nom Client")
	private String Nom;
	
	@ApiModelProperty(value = "Prenom Client")
	private String Prenom;
	
	@ApiModelProperty(value = "Client travail")
	private String travail;
	
	@ApiModelProperty(value = "Client addresse")
	private String addresse;
	
	@ApiModelProperty(value = "Client email")
	private String email;
	
	@ApiModelProperty(value = " date inscription client")
	private LocalDate inscriptionDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	
	public String getTravail() {
		return travail;
	}
	public void setTravail(String travail) {
		this.travail = travail;
	}
	
	public String getAddresse() {
		return addresse;
	}
	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getInscriptionDate() {
		return inscriptionDate;
	}
	public void setInscriptionDate(LocalDate inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}
	
	@Override
	public int compareTo(ClientDTO o) {
		return Prenom.compareToIgnoreCase(o.getPrenom());
	}
}

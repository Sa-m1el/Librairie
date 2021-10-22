package com.jeansamuel.Librairie.pret;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Simple Pret Modele")
public class SimplePretDTO {

	@ApiModelProperty(value = "Livre id concerne par le pret")
	private Integer livreId;
	
	@ApiModelProperty(value = "Client id concerne par le pret")
	private Integer clientId;
	
	@ApiModelProperty(value = "date debut pret")
	private LocalDate debutDate;
	
	@ApiModelProperty(value = " date fin du pret")
	private LocalDate finDate;
	
	public Integer getLivreId() {
		return livreId;
	}
	public void setLivreId(Integer livreId) {
		this.livreId = livreId;
	}
	
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	
	public LocalDate getDebutDate() {
		return debutDate;
	}
	public void setDebutDate(LocalDate debutDate) {
		this.debutDate = debutDate;
	}
	
	public LocalDate getFinDate() {
		return finDate;
	}
	public void setFinDate(LocalDate finDate) {
		this.finDate = finDate;
	
	}
	
}

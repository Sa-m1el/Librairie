package com.jeansamuel.Librairie.pret;

import java.time.LocalDate;

import com.jeansamuel.Librairie.client.ClientDTO;
import com.jeansamuel.Librairie.livre.LivreDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Pret Modele")
public class PretDTO implements Comparable<PretDTO> {

	@ApiModelProperty(value = "Book concerned by the loan")
	private LivreDTO livreDTO = new LivreDTO();

	@ApiModelProperty(value = "Customer concerned by the loan")
	private ClientDTO clientDTO = new ClientDTO();

	@ApiModelProperty(value = "date debut pret")
	private LocalDate DatedebutPret;

	@ApiModelProperty(value = "date fin pret")
	private LocalDate DatefinPret;
	
	public LocalDate getDatedebutPret() {
		return DatedebutPret;
	}
	public void setDatedebutPret(LocalDate datedebutPret) {
		DatedebutPret = datedebutPret;
	}
	
	public LocalDate getDatefinPret() {
		return DatefinPret;
	}
	public void setDatefinPret(LocalDate datefinPret) {
		DatefinPret = datefinPret;
	}
	
	public LivreDTO getLivreDTO() {
		return livreDTO;
	}
	public void setLivreDTO(LivreDTO livreDTO) {
		this.livreDTO = livreDTO;
	}
	
	public ClientDTO getClientDTO() {
		return clientDTO;
	}
	public void setClientDTO(ClientDTO clientDTO) {
		this.clientDTO = clientDTO;
	}
	
	@Override
	public int compareTo(PretDTO o) {
		// ordre decroissant
		return o.getDatedebutPret().compareTo(DatedebutPret);
	}
}

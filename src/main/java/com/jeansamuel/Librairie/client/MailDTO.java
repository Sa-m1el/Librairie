package com.jeansamuel.Librairie.client;

import io.swagger.annotations.ApiModel;

import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Mail Model")
public class MailDTO {

	@ApiModelProperty(value = "Mail sender address")
	public final String MAIL_FROM = "noreply.librairie.test@gmail.com";
	
	@ApiModelProperty(value = "Client receiver id")
	private Integer clientId;
	
	@ApiModelProperty(value = "Email sujet")
	private String emailSujet;
	
	@ApiModelProperty(value = "Email contenu")
	private String emailContenu;
	
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	
	public String getEmailContenu() {
		return emailContenu;
	}
	public void setEmailContenu(String emailContenu) {
		this.emailContenu = emailContenu;
	}
	
	public String getEmailSujet() {
		return emailSujet;
	}
	public void setEmailSujet(String emailSujet) {
		this.emailSujet = emailSujet;
	}
}

package com.jeansamuel.Librairie.pret;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import com.jeansamuel.Librairie.client.Client;
import com.jeansamuel.Librairie.livre.Livre;

public class PretId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 168874902798226392L;

    private Livre livre;
    
    private Client client;
    
    private LocalDateTime creationDateTime;
    
    public PretId() {
		super();
	}
    public PretId(Livre livre, Client client) {
        super();
        this.livre = livre;
        this.client = client;
        this.creationDateTime = LocalDateTime.now();
    }
    
    @ManyToOne
    public Livre getLivre() {
		return livre;
	}
    public void setLivre(Livre livre) {
		this.livre = livre;
	}
    
    @ManyToOne
    public Client getClient() {
		return client;
	}
    public void setClient(Client client) {
		this.client = client;
	}
    
    @Column(name = "CREATION_DATE_TIME")
    public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}
    public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((livre == null) ? 0 : livre.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PretId other = (PretId) obj;
		if (livre == null) {
			if (other.livre != null)
				return false;
		} else if (!livre.equals(other.livre))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		return true;
	}
	
}

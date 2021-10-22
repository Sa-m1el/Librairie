package com.jeansamuel.Librairie.pret;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "PRET")
@AssociationOverrides({
@AssociationOverride(name = "pk.livre", joinColumns = @JoinColumn(name = "LIVRE_ID")),
@AssociationOverride(name = "pk.client", joinColumns = @JoinColumn(name = "CLIENT_ID"))
})
public class Pret implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 3787781243369592433L;

	private PretId pk = new PretId();
    
    private LocalDate Datedebut;
    
    private LocalDate Datefin;
    
    private PretStatus status;

    @EmbeddedId
    public PretId getPk() {
		return pk;
	}
    public void setPk(PretId pk) {
		this.pk = pk;
	}
    
    @Column(name = "DATE_DEBUT", nullable = false)
    public LocalDate getDatedebut() {
		return Datedebut;
	}
    public void setDatedebut(LocalDate datedebut) {
		Datedebut = datedebut;
	}
    
    @Column(name = "DATE_FIN", nullable = false)
    public LocalDate getDatefin() {
		return Datefin;
	}
    public void setDatefin(LocalDate datefin) {
		Datefin = datefin;
	}
    
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    public PretStatus getStatus() {
		return status;
	}
    public void setStatus(PretStatus status) {
		this.status = status;
	}
    
}

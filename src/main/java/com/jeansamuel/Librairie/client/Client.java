package com.jeansamuel.Librairie.client;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.jeansamuel.Librairie.pret.Pret;

@Entity
@Table(name = "CUSTOMER")
public class Client {
	 private Integer id;
	    
	    private String Nom;
	    
	    private String Prenom;
	    
	    private String travail;
	    
	    private String addresse;
	    
	    private String email;
	    
	    private LocalDate creationDate;
	    
	    Set<Pret> prets = new HashSet<Pret>();
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "CLIENT_ID")
	    public Integer getId() {
			return id;
		}
	    public void setId(Integer id) {
			this.id = id;
		}
	    
	    @Column(name = "NOM", nullable = false)
	    public String getNom() {
			return Nom;
		}
	    public void setNom(String nom) {
			Nom = nom;
		}
	    
	    @Column(name = "PRENOM", nullable = false)
	    public String getPrenom() {
			return Prenom;
		}
	    public void setPrenom(String prenom) {
			Prenom = prenom;
		}
	    
	    @Column(name = "TRAVAIL")
	    public String getTravail() {
			return travail;
		}
	    public void setTravail(String travail) {
			this.travail = travail;
		}
	    
	    @Column(name = "ADDRESSE")
	    public String getAddresse() {
			return addresse;
		}
	    public void setAddresse(String addresse) {
			this.addresse = addresse;
		}
	    
	    @Column(name = "EMAIL", nullable = false, unique = true)
	    public String getEmail() {
			return email;
		}
	    public void setEmail(String email) {
			this.email = email;
		}
	    
	    @Column(name = "CREATION_DATE", nullable = false)
	    public LocalDate getCreationDate() {
			return creationDate;
		}
	    public void setCreationDate(LocalDate creationDate) {
			this.creationDate = creationDate;
		}
	    
	    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.client", cascade = CascadeType.ALL)
	    public Set<Pret> getLoans() {
			return prets;
		}
	    public void setLoans(Set<Pret> prets) {
			this.prets = prets;
		}
	    
	    
}

package com.jeansamuel.Librairie.livre;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.jeansamuel.Librairie.categorie.Categorie;
import com.jeansamuel.Librairie.pret.Pret;


@Entity
@Table(name = "LIVRE")
public class Livre {
	
	private Integer Id;
	private String titre;
	private String isbn;
	private String auteur;
	private Integer ExemplaireTotal;
	private LocalDate DateEnregistrement;
	private LocalDate DateSortie;
	private Categorie categorie;
	
	Set<Pret> prets = new HashSet<Pret>();
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LIVRE_ID")
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	
	@Column(name = "TITRE", nullable = false)
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	@Column(name = "ISBN", nullable = false, unique = true)
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	@Column(name = "DATE_SORTIE", nullable = false)
	public LocalDate getDateSortie() {
		return DateSortie;
	}
	public void setDateSortie(LocalDate dateSortie) {
		DateSortie = dateSortie;
	}
	
	@Column(name = "DATE_ENREGISTREMENT", nullable = false)
	public LocalDate getDateEnregistrement() {
		return DateEnregistrement;
	}
	public void setDateEnregistrement(LocalDate dateEnregistrement) {
		DateEnregistrement = dateEnregistrement;
	}
	
	@Column(name = "TOTAL_EXEMPLAIRES")
	public Integer getExemplaireTotal() {
		return ExemplaireTotal;
	}
	public void setExemplaireTotal(Integer exemplaireTotal) {
		ExemplaireTotal = exemplaireTotal;
	}
	
	@Column(name = "AUTEUR")
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "CODE_CAT", referencedColumnName = "CODE")
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.livre", cascade = CascadeType.ALL)
	public Set<Pret> getPrets() {
		return prets;
	}
	public void setPrets(Set<Pret> prets) {
		this.prets = prets;
	}
	public void setEnregistrementDate(LocalDate now) {
	
		
	}
}

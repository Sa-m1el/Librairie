package com.jeansamuel.Librairie.livre;

import java.util.List;
public interface ILivreService {

	 public Livre EnrégistrerLivre(Livre livre);
	    
	    public Livre mettreajourLivre(Livre livre);
	    
	    public void supprimerLivre(Integer livreId);
	    
	    public List<Livre> trouverLivresParTitreOuPartieTitre(String titre);
	    
	    public Livre trouverLivreParIsbn(String isbn);
	    
	    public boolean verifierSiIdexiste(Integer id);
	    
	    public List<Livre> trouverLivresParCategorie(String codeCategorie);
}

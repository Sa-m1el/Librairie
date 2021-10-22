package com.jeansamuel.Librairie.livre;

import javax.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("livreService")
@Transactional
public class LivreServiceImpl implements ILivreService {

	@Autowired
    private ILivreDao livreDao;
    
    @Override
    public Livre EnrégistrerLivre (Livre livre) {
        return livreDao.save(livre);
    }
    
    @Override
    public Livre mettreajourLivre(Livre livre) {
        return livreDao.save(livre);
    }
    
    @Override
    public void supprimerLivre(Integer livreId) {
        livreDao.deleteById(livreId);
    }

    @Override
    public boolean verifierSiIdexiste(Integer id) {
        return livreDao.existsById(id);
    }

    @Override
    public List<Livre> trouverLivresParTitreOuPartieTitre(String titre) {
        return livreDao.trouverParTitreCommeIgnorerCas((new StringBuilder()).append("%").append(titre).append("%").toString());
    }

    @Override
    public Livre trouverLivreParIsbn(String isbn) {
        return livreDao.trouverParIsbnIgnorerCas(isbn);
    }

    @Override
    public List<Livre> trouverLivresParCategorie(String codeCategorie) {
        return livreDao.trouverParCategorie(codeCategorie);
    }
}

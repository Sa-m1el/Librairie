package com.jeansamuel.Librairie.livre;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ILivreDao extends JpaRepository<Livre, Integer>{

public Livre trouverParIsbnIgnorerCas(String isbn);
    
    public List<Livre> trouverParTitreCommeIgnorerCas(String titre);
    
       @Query("SELECT l "
            + "FROM Livre l "
            + "INNER JOIN l.categorie cat "
            + "WHERE cat.code = :code"
          )
    public List<Livre> trouverParCategorie(@Param("code") String codeCategorie);

}

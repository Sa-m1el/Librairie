package com.jeansamuel.Librairie.pret;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPretDao extends JpaRepository<Pret, Integer> {

	 public List<Pret> trouverToutPretsParDateFin(LocalDate maxDateFin);
	    
     @Query("SELECT pr "
          + "FROM Pret pr "
          + "INNER JOIN pr.pk.client c "
          + "WHERE UPPER(c.email) = UPPER(?1) "
          + "   AND pr.status = ?2 ")
  public List<Pret> trouverToutPretsEncoursDuClient(String email, PretStatus status);
  
     @Query("SELECT pr "
          + "FROM Pret pr "
          + "INNER JOIN pr.pk.livre l "
          + "INNER JOIN lo.pk.client c "
          + "WHERE l.id =    ?1 "
          + "   AND c.id = ?2 "
          + "   AND pr.status = ?3 ")
  public Pret getPretByCriteria(Integer livreId, Integer clientId, PretStatus status);
}

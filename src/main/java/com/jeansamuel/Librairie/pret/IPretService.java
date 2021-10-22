package com.jeansamuel.Librairie.pret;

import java.time.LocalDate;
import java.util.List;

public interface IPretService {

public List<Pret> trouverToutPretsParDateFin(LocalDate maxDateFin);
    
    public List<Pret> trouverToutPretsEncoursDuClient(String email, PretStatus status);
    
    public Pret trouverPretEncours(SimplePretDTO simplePretDTO);
    
    public boolean verifierSiPretEncours(SimplePretDTO simplePretDTO);
    
    public Pret EnrégistrerPret(Pret pret);
    
    public void ClorePret(Pret pret);
    
    public Pret getOpenedPret(SimplePretDTO simplePretDTO);
   
}

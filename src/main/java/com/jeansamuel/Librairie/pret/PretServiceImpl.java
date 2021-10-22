package com.jeansamuel.Librairie.pret;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("pretService")
@Transactional
public class PretServiceImpl implements IPretService {

	@Autowired
    private IPretDao pretDao;

    @Override
    public List<Pret> trouverToutPretsParDateFin(LocalDate maxDateFin) {
        return pretDao.trouverToutPretsParDateFin(maxDateFin);
    }
    
    @Override
    public List<Pret> trouverToutPretsEncoursDuClient(String email, PretStatus status) {
        return pretDao.trouverToutPretsEncoursDuClient(email, status);
    }
    
    @Override
    public Pret trouverPretEncours(SimplePretDTO simplePretDTO) {
        return pretDao.getPretByCriteria(simplePretDTO.getLivreId(), simplePretDTO.getClientId(), PretStatus.OPEN);
    }
    
    @Override
    public boolean verifierSiPretEncours(SimplePretDTO simplePretDTO) {
        Pret pret = pretDao.getPretByCriteria(simplePretDTO.getLivreId(), simplePretDTO.getClientId(), PretStatus.OPEN);
        if(pret != null) {
            return true;
        } else {
        return false;
        }
    }
    
    @Override
    public Pret EnrégistrerPret(Pret pret) {
        return pretDao.save(pret);
    }
    
    
    @Override
    public void ClorePret(Pret pret) {
        pretDao.save(pret);
    }

	@Override
	public Pret getOpenedPret(SimplePretDTO simplePretDTO) {
		
		return null;
	}
}

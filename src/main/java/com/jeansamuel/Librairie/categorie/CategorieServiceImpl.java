package com.jeansamuel.Librairie.categorie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categorieService")
public class CategorieServiceImpl implements ICategorieService {

	@Autowired
	private ICategorieDao categorieDao;
	
	@Override
	public List<Categorie> getToutCategories(){
		return categorieDao.findAll();
	}	
}

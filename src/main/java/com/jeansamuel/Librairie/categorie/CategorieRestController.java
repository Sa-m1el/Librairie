package com.jeansamuel.Librairie.categorie;

import java.util.Collections;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.modelmapper.ModelMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest/categorie/api")
@Api(value = "Livre Categorie Rest Controller")

public class CategorieRestController {

		
		@Autowired
		private CategorieServiceImpl categorieService;
		
		@GetMapping("/allCategories")
		@ApiOperation(value="Lister tous les livres d'une meme categorie de la Librairie", response = List.class)
		@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Ok: successfully listed"),
				@ApiResponse(code = 204, message = "No Content: no result founded"),
		})
		public ResponseEntity<List<CategorieDTO>> TrouverToutLivreCategories(){
			List<Categorie> categories = categorieService.getToutCategories();
			if(!CollectionUtils.isEmpty(categories)) {
				
				categories.removeAll(Collections.singleton(null));
				List<CategorieDTO> categorieDTOs = categories.stream().map(categorie -> {
					return mapCategorieToCategorieDTO(categorie);
				}).collect(Collectors.toList());
				return new ResponseEntity<List<CategorieDTO>>(categorieDTOs, HttpStatus.OK);
			}
			return new ResponseEntity<List<CategorieDTO>>(HttpStatus.NO_CONTENT);
		}
		
		
		private CategorieDTO mapCategorieToCategorieDTO(Categorie categorie) {
			ModelMapper mapper = new ModelMapper();
			CategorieDTO categorieDTO = mapper.map(categorie, CategorieDTO.class);
			return categorieDTO;
		}
	
}

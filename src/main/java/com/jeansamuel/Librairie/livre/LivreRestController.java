package com.jeansamuel.Librairie.livre;

import java.time.LocalDate;



import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.modelmapper.ModelMapper;

import com.jeansamuel.Librairie.categorie.Categorie;
import com.jeansamuel.Librairie.categorie.CategorieDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/rest/livre/api")
@Api(value = "Livre Rest Controller: contient tout les operation pour la gestion d'un livre")
public class LivreRestController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(LivreRestController.class);

	@Autowired
	private LivreServiceImpl livreService;

	@PostMapping("/ajouterLivre")
	@ApiOperation(value = "Ajouter un nouveau livre dans la librairie", response = LivreDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: le livre existe déja"),
			@ApiResponse(code = 201, message = "Creer: le livre est bien enrégistré"),
			@ApiResponse(code = 304, message = "Non Enrégistrer: le livre n'est pas bien enrégistré") })
	public ResponseEntity<LivreDTO> enregistrNouveauLivre(@RequestBody LivreDTO livreDTORequest) {
		//, UriComponentsBuilder uriComponentBuilder
		Livre existeLivre = livreService.trouverLivreParIsbn(livreDTORequest.getIsbn());
		if (existeLivre != null) {
			return new ResponseEntity<LivreDTO>(HttpStatus.CONFLICT);
		}
		Livre livreRequest = mapLivreDTOToLivre(livreDTORequest);
		Livre livre = livreService.EnrégistrerLivre(livreRequest);
		if (livre != null && livre.getId() != null) {
			LivreDTO livreDTO = mapLivreToLivreDTO(livre);
			return new ResponseEntity<LivreDTO>(livreDTO, HttpStatus.CREATED);
		}
		return new ResponseEntity<LivreDTO>(HttpStatus.NOT_MODIFIED);

	}

	@PutMapping("/mettreajourLivre")
	@ApiOperation(value = "Mettreajour/Modifier un livre existant dans la librairie", response =LivreDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found : the book does not exist"),
			@ApiResponse(code = 200, message = "Ok: le livre est bien enrégistré"),
			@ApiResponse(code = 304, message = "Non Modifier: le livre n'est pas bien enrégistré") })
	public ResponseEntity<LivreDTO> mettreajourLivre(@RequestBody LivreDTO livreDTORequest) {
		//, UriComponentsBuilder uriComponentBuilder
		if (!livreService.verifierSiIdexiste(livreDTORequest.getId())) {
			return new ResponseEntity<LivreDTO>(HttpStatus.NOT_FOUND);
		}
		Livre livreRequest = mapLivreDTOToLivre(livreDTORequest);
		Livre livre = livreService.mettreajourLivre(livreRequest);
		if (livre != null) {
			LivreDTO livreDTO = mapLivreToLivreDTO(livre);
			return new ResponseEntity<LivreDTO>(livreDTO, HttpStatus.OK);
		}
		return new ResponseEntity<LivreDTO>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping("/supprimerLivre/{livreId}")
	@ApiOperation(value = "Supprimer un livre dans la Librairie, Si le livre n'existe pas, ne rien faire", response = String.class)
	@ApiResponse(code = 204, message = "Non present: Livre Supprimer")
	public ResponseEntity<String> supprimerLivre(@PathVariable Integer livreId) {
		livreService.supprimerLivre(livreId);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/chercherParTitre")
	@ApiOperation(value="Chercher Livre dans la Librairie par titre", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: trouver"),
			@ApiResponse(code = 204, message = "No Content: non trouvé"),
	})
	public ResponseEntity<List<LivreDTO>> chercherLivreParTitre(@RequestParam("titre") String titre,
			UriComponentsBuilder uriComponentBuilder) {
		List<Livre> livres = livreService.trouverLivresParTitreOuPartieTitre(titre);
		if (!CollectionUtils.isEmpty(livres)) {
			
			livres.removeAll(Collections.singleton(null));
			List<LivreDTO> livreDTOs = livres.stream().map(livre -> {
				return mapLivreToLivreDTO(livre);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<LivreDTO>>(livreDTOs, HttpStatus.OK);
		}
		return new ResponseEntity<List<LivreDTO>>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/chercherParIsbn")
	@ApiOperation(value="Chercher Livre dans la Librairie par son isbn", response = LivreDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok: trouver"),
			@ApiResponse(code = 204, message = "No Content: non trouvé"),
	})
	public ResponseEntity<LivreDTO> trouverLivreParIsbn(@RequestParam("isbn") String isbn,
			UriComponentsBuilder uriComponentBuilder) {
		Livre livre = livreService.trouverLivreParIsbn(isbn);
		if (livre != null) {
			LivreDTO livreDTO = mapLivreToLivreDTO(livre);
			return new ResponseEntity<LivreDTO>(livreDTO, HttpStatus.OK);
		}
		return new ResponseEntity<LivreDTO>(HttpStatus.NO_CONTENT);
	}

	
	private LivreDTO mapLivreToLivreDTO(Livre livre) {
		ModelMapper mapper = new ModelMapper();
		LivreDTO livreDTO = mapper.map(livre, LivreDTO.class);
		if (livre.getCategorie() != null) {
			livreDTO.setCategorie(new CategorieDTO(livre.getCategorie().getCode(), livre.getCategorie().getLabel()));
		}
		return livreDTO;
	}

	
	private Livre mapLivreDTOToLivre(LivreDTO livreDTO) {
		ModelMapper mapper = new ModelMapper();
		Livre livre = mapper.map(livreDTO, Livre.class);
		livre.setCategorie(new Categorie(livreDTO.getCategorie().getCode(), ""));
		livre.setEnregistrementDate(LocalDate.now());
		return livre;
	}


}

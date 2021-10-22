package com.jeansamuel.Librairie.pret;

import java.time.LocalDate;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.jeansamuel.Librairie.client.Client;
import com.jeansamuel.Librairie.livre.Livre;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest/pret/api")
@Api(value = "Pret Rest Controller: contient tous les operations pour la gestion des pret")
public class PretRestController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(PretRestController.class);

	@Autowired
	private PretServiceImpl pretService;

	@GetMapping("/maxDatefin")
	@ApiOperation(value="List prets realized before the indicated date", response = List.class)
	@ApiResponse(code = 200, message = "Ok: successfully listed")
	public ResponseEntity<List<PretDTO>> chercherToutLivresPretAvantCetteDate(@RequestParam("date") String  maxDatefinStr) {
		List<Pret> prets = pretService.trouverToutPretsParDateFin(LocalDate.parse(maxDatefinStr));
		
		prets.removeAll(Collections.singleton(null));
		List<PretDTO> pretInfosDtos = mapPretDtosFromPrets(prets);
		return new ResponseEntity<List<PretDTO>>(pretInfosDtos, HttpStatus.OK);
	}
	
	@GetMapping("/customerLoans")
	@ApiOperation(value="List prets realized before the indicated date", response = List.class)
	@ApiResponse(code = 200, message = "Ok: successfully listed")
	public ResponseEntity<List<PretDTO>> trouverToutPretsEncoursDuClient(@RequestParam("email") String email) {
		List<Pret> prets = pretService.trouverToutPretsEncoursDuClient(email, PretStatus.OPEN);
		
		prets.removeAll(Collections.singleton(null));
		List<PretDTO> pretInfosDtos = mapPretDtosFromPrets(prets);
		return new ResponseEntity<List<PretDTO>>(pretInfosDtos, HttpStatus.OK);
	}
	
	@PostMapping("/addPret")
	@ApiOperation(value = "Add a new Loan in the Library", response = PretDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflict: the loan already exist"),
			@ApiResponse(code = 201, message = "Created: the loan is successfully inserted"),
			@ApiResponse(code = 304, message = "Not Modified: the loan is unsuccessfully inserted") })
	public ResponseEntity<Boolean> createNewPret(@RequestBody SimplePretDTO simplePretDTORequest,
			UriComponentsBuilder uriComponentBuilder) {
		boolean isPretExists = pretService.verifierSiPretEncours(simplePretDTORequest);
		if (isPretExists) {
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		}
		Pret PretRequest = mapSimplePretDTOToPret(simplePretDTORequest);
		Pret pret = pretService.EnrégistrerPret(PretRequest);
		if (pret != null) {
			return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_MODIFIED);

	}
	
	@PostMapping("/clorePret")
	@ApiOperation(value = "Marks as close a Loan in the Library", response = Boolean.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content: no loan founded"),
			@ApiResponse(code = 200, message = "Ok: le pret is successfully closed"),
			@ApiResponse(code = 304, message = "Not Modified: le pret is unsuccessfully closed") })
	public ResponseEntity<Boolean> closePret(@RequestBody SimplePretDTO simplePretDTORequest,
			UriComponentsBuilder uriComponentBuilder) {
		Pret existingPret = pretService.getOpenedPret(simplePretDTORequest);
		if (existingPret == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
		}
		existingPret.setStatus(PretStatus.CLOSE);
		Pret pret = pretService.EnrégistrerPret(existingPret);
		if (pret != null) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(HttpStatus.NOT_MODIFIED);

	}
	
	private List<PretDTO> mapPretDtosFromPrets(List<Pret> prets) {

		Function<Pret, PretDTO> mapperFunction = (pret) -> {
			
			PretDTO pretDTO = new PretDTO();
			pretDTO.getLivreDTO().setId(pret.getPk().getLivre().getId());
			pretDTO.getLivreDTO().setIsbn(pret.getPk().getLivre().getIsbn());
			pretDTO.getLivreDTO().setTitre(pret.getPk().getLivre().getTitre());

			pretDTO.getClientDTO().setId(pret.getPk().getClient().getId());
			pretDTO.getClientDTO().setNom(pret.getPk().getClient().getNom());
			pretDTO.getClientDTO().setPrenom(pret.getPk().getClient().getPrenom());
			pretDTO.getClientDTO().setEmail(pret.getPk().getClient().getEmail());

			pretDTO.setDatedebutPret(pret.getDatedebut());
			pretDTO.setDatefinPret(pret.getDatefin());
			return pretDTO;
		};

		if (prets !=null && !prets.isEmpty()) {
			return prets.stream().map(mapperFunction).sorted().collect(Collectors.toList());
		}
		return null;
		
	}
	
	private Pret mapSimplePretDTOToPret(SimplePretDTO simplePretDTO) {
		Pret pret = new Pret();
		Livre livre = new Livre();
		livre.setId(simplePretDTO.getLivreId());
		Client client = new Client();
		client.setId(simplePretDTO.getClientId());
		PretId pretId = new PretId(livre, client);
		pret.setPk(pretId);
		pret.setDatedebut(simplePretDTO.getDebutDate());
		pret.setDatefin(simplePretDTO.getFinDate());
		pret.setStatus(PretStatus.OPEN);
		return pret;
	}
}

package com.jeansamuel.Librairie.client;

import java.time.LocalDate;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
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



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
@RequestMapping("/rest/client/api")
@Api(value = "Client Rest Controller: contient tout operations pour gérer le client")
public class ClientRestController {

	 public static final Logger LOGGER = LoggerFactory.getLogger(ClientRestController.class);

	    @Autowired
	    private ClientServiceImpl clientService;
	    
	    @Autowired
	    private JavaMailSender javaMailSender;
	    
	  
	
	    @PostMapping("/ajouterClient")
	    @ApiOperation(value = "Ajouter un nouveau Client dans la Librairie", response = ClientDTO.class)
		@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflit: le client existe deja"),
				@ApiResponse(code = 201, message = "Créer: le client est bien enrégistré"),
				@ApiResponse(code = 304, message = "Non modifié : le client n'est pas enrégistré") })
	    public ResponseEntity<ClientDTO> creerNouveauClient(@RequestBody ClientDTO clientDTORequest) {
	        //, UriComponentsBuilder uriComponentBuilder
	        Client existingClient = clientService.trouverClientParEmail(clientDTORequest.getEmail());
	        if (existingClient != null) {
	            return new ResponseEntity<ClientDTO>(HttpStatus.CONFLICT);
	        }
	        Client clientRequest = mapClientDTOToClient(clientDTORequest);
	        clientRequest.setCreationDate(LocalDate.now());
	        Client clientResponse = clientService.enregistrerClient(clientRequest);
	        if (clientResponse != null) {
	            ClientDTO clientDTO = mapClientToClientDTO(clientResponse);
	            return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.CREATED);
	        }
	        return new ResponseEntity<ClientDTO>(HttpStatus.NOT_MODIFIED);
	    }

	  
	    @PutMapping("/mettreajourClient")
	    public ResponseEntity<ClientDTO> mettreajourClient(@RequestBody ClientDTO clientDTORequest) {
	        //, UriComponentsBuilder uriComponentBuilder
	        if (!clientService.verifierSiIdexiste(clientDTORequest.getId())) {
	            return new ResponseEntity<ClientDTO>(HttpStatus.NOT_FOUND);
	        }
	        Client clientRequest = mapClientDTOToClient(clientDTORequest);
	        Client clientResponse = clientService.mettreajourClient(clientRequest);
	        if (clientResponse != null) {
	            ClientDTO clientDTO = mapClientToClientDTO(clientResponse);
	            return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.OK);
	        }
	        return new ResponseEntity<ClientDTO>(HttpStatus.NOT_MODIFIED);
	    }

	    
	    @DeleteMapping("/supprimerClient/{clientId}")
	    public ResponseEntity<String> supprimerClient(@PathVariable Integer clientId) {
	        clientService.supprimerClient(clientId);
	        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	    }

	  
	    @GetMapping("/chercherParEmail")
	    public ResponseEntity<ClientDTO> chercherClientByEmail(@RequestParam("email") String email) {
	        
	        Client client = clientService.trouverClientParEmail(email);
	        if (client != null) {
	            ClientDTO clientDTO = mapClientToClientDTO(client);
	            return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.OK);
	        }
	        return new ResponseEntity<ClientDTO>(HttpStatus.NO_CONTENT);
	    }
	    
	   
	    @GetMapping("/chercherParPrenom")
	    public ResponseEntity<List<ClientDTO>> chercherLivreParPrenom(@RequestParam("Prenom") String Prenom) {
	        //,    UriComponentsBuilder uriComponentBuilder
	        List<Client> clients = clientService.trouverClientParPrenom(Prenom);
	        if (clients != null && !CollectionUtils.isEmpty(clients)) {
	            List<ClientDTO> clientDTOs = clients.stream().map(client -> {
	                return mapClientToClientDTO(client);
	            }).collect(Collectors.toList());
	            return new ResponseEntity<List<ClientDTO>>(clientDTOs, HttpStatus.OK);
	        }
	        return new ResponseEntity<List<ClientDTO>>(HttpStatus.NO_CONTENT);
	    }
	    
	   
	    @PutMapping("/envoyerEmailAuClient")
	    public ResponseEntity<Boolean> envoyerEmailAuClient(@RequestBody MailDTO pretMailDto, UriComponentsBuilder uriComponentBuilder) {

	        Client client = clientService.trouverClientParId(pretMailDto.getClientId());
	        if (client == null) {
	            String errorMessage = "The selected Customer for sending email is not found in the database";
	            LOGGER.info(errorMessage);
	            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
	        } else if (client != null && StringUtils.isEmpty(client.getEmail())) {
	            String errorMessage = "No existing email for the selected Customer for sending email to";
	            LOGGER.info(errorMessage);
	            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
	        }

	        SimpleMailMessage mail = new SimpleMailMessage();
	        mail.setFrom(pretMailDto.MAIL_FROM);
	        mail.setTo(client.getEmail());
	        mail.setSentDate(new Date());
	        mail.setSubject(pretMailDto.getEmailSujet());
	        mail.setText(pretMailDto.getEmailContenu());

	        try {
	            javaMailSender.send(mail);
	        } catch (MailException e) {
	            return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
	        }

	        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	    }

	    /**
	     
	     * 
	     * @param client
	     * @return
	     */
	    private ClientDTO mapClientToClientDTO(Client client) {
	        ModelMapper mapper = new ModelMapper();
	        ClientDTO clientDTO =  mapper.map(client, ClientDTO.class);
	        return clientDTO;
	    }
	  
	    private Client mapClientDTOToClient(ClientDTO clientDTO) {
	        ModelMapper mapper = new ModelMapper();
	        Client client = mapper.map(clientDTO, Client.class);
	        return client;
	    }
}

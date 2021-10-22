package com.jeansamuel.Librairie.client;

import java.awt.print.Pageable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("clientService")
@Transactional
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientDao clientDao;
	
	@Override
	public Client enregistrerClient(Client client) {
		return clientDao.enregistrer(client);
	}

	@Override
	public Client mettreajourClient(Client client) {
		return clientDao.enregistrer(client);
	}

	@Override
	public void supprimerClient(Integer clientId) {
		clientDao.supprimerParId(clientId);
	}

	@Override
	public boolean verifierSiIdexiste(Integer id) {
		return clientDao.existeParId(id);
	}

	@Override
	public Client trouverClientParEmail(String email) {
		return clientDao.trouverClientParEmailIgnoreCas(email);
	}
	
	public Client trouverClientParId(Integer clientId) {
		return clientDao.getUn(clientId);
	}

	@Override
	public Page<Client> getPaginatedClientList(int debut, int fin){
		Pageable page = (Pageable) PageRequest.of(debut, fin);
		return clientDao.findAll(page);
	}
	
	@Override
	public List<Client> trouverClientParPrenom(String Prenom){
		return clientDao.trouverClientParPrenomIgnoreCas(Prenom);
	}

}

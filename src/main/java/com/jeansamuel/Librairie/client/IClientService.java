package com.jeansamuel.Librairie.client;

import java.util.List;

import org.springframework.data.domain.Page;

public interface IClientService {

	public Client enregistrerClient(Client client);

	public Client mettreajourClient(Client client);

	public void supprimerClient(Integer clientId);
	
	public boolean verifierSiIdexiste(Integer id);

	public Client trouverClientParEmail(String email);
	
	public List<Client> trouverClientParPrenom(String Prenom);
	
	public Client trouverClientParId(Integer clientId);

	public Page<Client> getPaginatedClientList(int debut, int fin);
}

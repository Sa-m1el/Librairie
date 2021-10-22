package com.jeansamuel.Librairie.client;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface IClientDao extends JpaRepository<Client, Integer> {

    public Client trouverClientParEmailIgnoreCas(String email);
	
	public List<Client> trouverClientParPrenomIgnoreCas(String Prenom);

	public void supprimerParId(Integer clientId);

	public Client enregistrer(Client client);

	public boolean existeParId(Integer id);

	public Client getUn(Integer clientId);

	public Page<Client> findAll(Pageable page);
}

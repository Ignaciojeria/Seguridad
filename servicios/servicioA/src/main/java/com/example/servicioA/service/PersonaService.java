package com.example.servicioA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.servicioA.dao.PersonaDAO;
import com.example.servicioA.domain.Persona;

@Service
public class PersonaService {
	
	@Autowired
	private PersonaDAO personaDAO;
	
	public List<Persona> findAll(){
		return personaDAO.findAll();
	}


	public void setPersonaDAO(PersonaDAO personaDAO) {
		this.personaDAO = personaDAO;
	}
	
	public PersonaDAO getPersonaDAO() {
		return personaDAO;
	}

}

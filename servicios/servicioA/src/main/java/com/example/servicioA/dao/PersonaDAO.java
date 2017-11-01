package com.example.servicioA.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.servicioA.domain.Persona;

@Repository
public class PersonaDAO {
	List<Persona> personas;
	
	public PersonaDAO() {
		personas=new ArrayList<Persona>();
		mock();
	}
	
	void mock() {
		personas.add(new Persona("Ignacio Jeria"));
		personas.add(new Persona("Ismael Jeria"));
		personas.add(new Persona("Luis Jeria"));
	}

	public List<Persona> findAll(){
		return personas;
	}

}

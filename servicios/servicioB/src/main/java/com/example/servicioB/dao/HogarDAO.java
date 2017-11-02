package com.example.servicioB.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.servicioB.domain.Hogar;
@Repository
public class HogarDAO {
	private List<Hogar> hogarDAO;
	
	public HogarDAO() {
		hogarDAO=new ArrayList<Hogar>();
		mock();
	}
	
	private void mock() {
		hogarDAO.add(new Hogar("Villa Alemana"));
	}
	
	public List<Hogar> findAll(){
		return hogarDAO;
	}

}

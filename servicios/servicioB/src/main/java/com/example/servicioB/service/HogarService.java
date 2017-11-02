package com.example.servicioB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.servicioB.dao.HogarDAO;
import com.example.servicioB.domain.Hogar;

@Service
public class HogarService {
	
   @Autowired HogarDAO hogarDAO;

   public List<Hogar>findAll(){
	   return hogarDAO.findAll();
   }

}

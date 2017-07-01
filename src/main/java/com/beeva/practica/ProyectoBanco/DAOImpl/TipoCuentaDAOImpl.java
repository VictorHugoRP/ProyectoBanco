package com.beeva.practica.ProyectoBanco.DAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.practica.ProyectoBanco.DAO.TipoCuentaDAO;
import com.beeva.practica.ProyectoBanco.model.Tipocuenta;

@Repository
public class TipoCuentaDAOImpl extends TipoCuentaDAO{
	
	@PersistenceContext
	
	EntityManager entManager;

	@Transactional
	@Override
	public Tipocuenta getTipoCuenta(int id) {
		return entManager.find(Tipocuenta.class, id);
	}

	@Transactional
	@Override
	public Tipocuenta agregarTipoCuenta(Tipocuenta tipoCuenta) {
		entManager.persist(tipoCuenta);
		return tipoCuenta;
	}
	
	

}

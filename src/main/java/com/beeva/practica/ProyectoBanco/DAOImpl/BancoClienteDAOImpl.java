package com.beeva.practica.ProyectoBanco.DAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.practica.ProyectoBanco.DAO.BancoClienteDAO;
import com.beeva.practica.ProyectoBanco.model.Bancocliente;

@Repository
public class BancoClienteDAOImpl extends BancoClienteDAO{
	
	@PersistenceContext
	
	EntityManager entManager;

	@Transactional
	@Override
	public Bancocliente agregarBancoCliente(Bancocliente bancoCliente) {
		entManager.persist(bancoCliente);
		return bancoCliente;
	}

	@Transactional
	@Override
	public Bancocliente getBancoCliente(int id) {
		return entManager.find(Bancocliente.class, id);
	}

}

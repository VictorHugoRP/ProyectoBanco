package com.beeva.practica.ProyectoBanco.DAOImpl;

import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.practica.ProyectoBanco.DAO.ClienteDAO;
import com.beeva.practica.ProyectoBanco.model.Cliente;
import com.beeva.practica.ProyectoBanco.mongoUtils.MongoUtils;

@Repository
public class ClienteDAOImpl extends ClienteDAO{
	
	@PersistenceContext
	
	EntityManager entManager;
	
	@Transactional
	@Override
	public Cliente agregarCliente(Cliente cliente) {
//		boolean registra = false;
//		if(cliente != null){
			entManager.persist(cliente);
//			registra = true;
//			if(registra){
//				MongoUtils mUtils = new MongoUtils();
//				mUtils.logcollection(cliente);
//			}else{
//				System.out.println("No hay un cliente.");
//			}
//		}	
		return cliente;
	}

	@Transactional
	@Override
	public List<Cliente> getCliente(int idCliente) {
		return entManager.createQuery("select c from Cliente c where idcliente = :idcliente").setParameter("idcliente", idCliente).getResultList();
	}
	
	

}

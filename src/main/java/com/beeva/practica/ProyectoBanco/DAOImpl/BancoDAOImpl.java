package com.beeva.practica.ProyectoBanco.DAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.practica.ProyectoBanco.DAO.BancoDao;
import com.beeva.practica.ProyectoBanco.model.Banco;
import com.beeva.practica.ProyectoBanco.mongoUtils.MongoUtils;

@Repository

public class BancoDAOImpl extends BancoDao{
	
	@PersistenceContext
	
	EntityManager entManager;

	@Transactional
	@Override
	public Banco agregarBanco(Banco banco) {
//		boolean registraBanco = false;
//		if(banco != null){
			entManager.persist(banco);
//			registraBanco = true;
//			if(registraBanco){
//				MongoUtils mu = new MongoUtils();
//				mu.logcollectionBanco(banco);
//			}else{
//				System.out.println("No hay Banco");
//			}
//		}
		return banco;
	}

	@Transactional
	@Override
	public Banco getBanco(int id) {
		return entManager.find(Banco.class, id);
	}

}

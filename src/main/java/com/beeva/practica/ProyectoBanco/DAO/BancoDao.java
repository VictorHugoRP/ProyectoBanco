package com.beeva.practica.ProyectoBanco.DAO;

import com.beeva.practica.ProyectoBanco.model.Banco;

public abstract class BancoDao {
	
	public abstract Banco agregarBanco(Banco banco);
	public abstract Banco getBanco(int id);
	

}

package com.beeva.practica.ProyectoBanco.Beans;

import java.net.UnknownHostException;
import java.sql.SQLException;

import com.mongodb.MongoClient;

public class MongoBean {
	
	private String servidor;
	private int puerto;
	
	public MongoClient getConecction() throws SQLException, UnknownHostException {
		MongoClient mongo = null;
		mongo = new MongoClient(servidor, puerto);
		return mongo;
	}
	
	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}


}

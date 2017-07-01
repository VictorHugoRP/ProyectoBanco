package com.beeva.practica.ProyectoBanco.DAO;

import com.beeva.practica.ProyectoBanco.model.Bancocliente;

public abstract class BancoClienteDAO {
	
	public abstract Bancocliente agregarBancoCliente(Bancocliente bancoCliente);
	public abstract Bancocliente getBancoCliente(int id);

}

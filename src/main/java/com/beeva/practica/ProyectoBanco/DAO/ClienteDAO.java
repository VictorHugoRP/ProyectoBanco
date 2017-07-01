package com.beeva.practica.ProyectoBanco.DAO;

import java.util.List;

import com.beeva.practica.ProyectoBanco.model.Cliente;

public abstract class ClienteDAO {
	
	public abstract Cliente agregarCliente(Cliente cliente);
	public abstract List<Cliente> getCliente(int idCliente);

}

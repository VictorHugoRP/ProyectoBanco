package com.beeva.practica.ProyectoBanco.DAO;

import java.util.List;

import com.beeva.practica.ProyectoBanco.model.Cuenta;

public abstract class CuentaDao {
	
	public abstract Cuenta agregarCuenta(Cuenta cuenta);
	public abstract List<Cuenta> getCuenta(int idCliente);
	public abstract boolean deposito(Cuenta cuenta, double cantidad);
	public abstract boolean retiro(Cuenta cuenta, double cantidad);
}

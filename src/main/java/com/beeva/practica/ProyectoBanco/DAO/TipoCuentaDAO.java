package com.beeva.practica.ProyectoBanco.DAO;

import com.beeva.practica.ProyectoBanco.model.Cuenta;
import com.beeva.practica.ProyectoBanco.model.Tipocuenta;

public abstract class TipoCuentaDAO {
	
	public abstract Tipocuenta agregarTipoCuenta(Tipocuenta tipoCuenta);
	public abstract Tipocuenta getTipoCuenta(int id);

}

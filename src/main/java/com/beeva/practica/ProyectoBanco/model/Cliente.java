package com.beeva.practica.ProyectoBanco.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name="cliente")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcliente;

	private String amaterno;

	private String apaterno;

	private String nombre;

	//bi-directional many-to-one association to Bancocliente
	@OneToMany(mappedBy="cliente")
	private List<Bancocliente> bancoclientes;

	//bi-directional many-to-one association to Cuenta
	@OneToMany(mappedBy="cliente")
	private List<Cuenta> cuentas;

	public Cliente() {
	}

	public int getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getAmaterno() {
		return this.amaterno;
	}

	public void setAmaterno(String amaterno) {
		this.amaterno = amaterno;
	}

	public String getApaterno() {
		return this.apaterno;
	}

	public void setApaterno(String apaterno) {
		this.apaterno = apaterno;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Bancocliente> getBancoclientes() {
		return this.bancoclientes;
	}

	public void setBancoclientes(List<Bancocliente> bancoclientes) {
		this.bancoclientes = bancoclientes;
	}

	public Bancocliente addBancocliente(Bancocliente bancocliente) {
		getBancoclientes().add(bancocliente);
		bancocliente.setCliente(this);

		return bancocliente;
	}

	public Bancocliente removeBancocliente(Bancocliente bancocliente) {
		getBancoclientes().remove(bancocliente);
		bancocliente.setCliente(null);

		return bancocliente;
	}

	public List<Cuenta> getCuentas() {
		return this.cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Cuenta addCuenta(Cuenta cuenta) {
		getCuentas().add(cuenta);
		cuenta.setCliente(this);

		return cuenta;
	}

	public Cuenta removeCuenta(Cuenta cuenta) {
		getCuentas().remove(cuenta);
		cuenta.setCliente(null);

		return cuenta;
	}

}
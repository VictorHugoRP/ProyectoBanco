package com.beeva.practica.ProyectoBanco.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bancocliente database table.
 * 
 */
@Entity
@NamedQuery(name="Bancocliente.findAll", query="SELECT b FROM Bancocliente b")
public class Bancocliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idbancocliente;

	//bi-directional many-to-one association to Banco
	@ManyToOne
	@JoinColumn(name="idbanco")
	private Banco banco;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idcliente")
	private Cliente cliente;

	public Bancocliente() {
	}

	public int getIdbancocliente() {
		return this.idbancocliente;
	}

	public void setIdbancocliente(int idbancocliente) {
		this.idbancocliente = idbancocliente;
	}

	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
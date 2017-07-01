package com.beeva.practica.ProyectoBanco.DAOImpl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.practica.ProyectoBanco.DAO.CuentaDao;
import com.beeva.practica.ProyectoBanco.model.Cuenta;
import com.beeva.practica.ProyectoBanco.mongoUtils.MongoUtils;

@Repository
public class CuentaDAOImpl extends CuentaDao {

	@PersistenceContext
	EntityManager entManager;

	@Transactional
	@Override
	public Cuenta agregarCuenta(Cuenta cuenta) {
//		boolean registraCuenta = false;
//		if (cuenta != null) {
			entManager.persist(cuenta);
//			registraCuenta = true;
//			if (registraCuenta) {
//				MongoUtils mutils = new MongoUtils();
//				mutils.logcollectionCuenta(cuenta);
//			}
//		}
		return cuenta;
	}

	public List<Cuenta> getCuenta(int idCliente) {
		return entManager
				.createQuery(
						"select c from Cuenta c where id_cliente = :idcliente")
				.setParameter("idcliente", idCliente).getResultList();
	}

	//
	// public void updateBalanceCuenta(double nuevoBalance, int idCuenta) {
	// // Query query =
	// entManager.createQuery("update Cuenta set balance = :newbalance where idcuenta = :id_cuenta");
	// // query.setParameter("newbalance", nuevoBalance);
	// // query.setParameter("id_cuenta", idCuenta);
	// // query.executeUpdate();
	//
	// }

	@Transactional
	@Override
	public boolean deposito(Cuenta cuenta, double cantidad) {
		boolean deposito = false;
		double cantidadInicial = cuenta.getBalance();
		if (cuenta != null) {
			cantidadInicial = cantidadInicial + cantidad;
			cuenta.setBalance(cantidadInicial);
			entManager.merge(cuenta);
			System.out.println("Cantidad de deposito: $" + cantidad);
			System.out.println("El balance actual con deposito es: $"
					+ cuenta.getBalance());
			deposito = true;
		} else {
			deposito = false;
		}

		return deposito;
	}

	@Transactional
	@Override
	public boolean retiro(Cuenta cuenta, double cantidad) {
		boolean retiro = false;
		double cantidadInicial = cuenta.getBalance();

		if (cuenta.getTipocuenta().getNombrecuenta().equals("Ahorro")) {
			if (cantidadInicial > 5000) {
				cantidadInicial = cantidadInicial - cantidad;
				cuenta.setBalance(cantidadInicial);
				entManager.merge(cuenta);
				System.out.println("Cantidad de retiro: $" + cantidad);
				System.out.println("El balance actual con retiro es: $"
						+ cuenta.getBalance());
				retiro = true;
			} else {
				System.out.println("No puede retirar, su balance tiene: $"
						+ cantidadInicial + " Deberá ser mayor a $5000");
				retiro = false;
			}
		} else if (cuenta.getTipocuenta().getNombrecuenta().equals("Cheques")) {
			Calendar cal = Calendar.getInstance();
			int dia = cal.get(Calendar.DAY_OF_WEEK);
			cantidadInicial = cuenta.getBalance();
			if (dia > 1 && dia < 7) {
				cantidadInicial = cantidadInicial - cantidad;
				cuenta.setBalance(cantidadInicial);
				entManager.merge(cuenta);
				System.out.println("Cantidad de retiro: $" + cantidad);
				System.out.println("El balance actual con retiro es: $"
						+ cuenta.getBalance());
				retiro = true;
			} else {
				if(dia == 1){
					System.out.println("No puede retirar los Domingos.");
				}else if(dia == 7){
					System.out
					.println("No puede retirar los Sabados.");
				}
				retiro = false;
			}
		} else {
			System.out.println("No eligio un tipo de cuenta.");
		}
		return retiro;
	}

}

package com.beeva.practica.ProyectoBanco;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beeva.practica.ProyectoBanco.DAO.BancoClienteDAO;
import com.beeva.practica.ProyectoBanco.DAO.BancoDao;
import com.beeva.practica.ProyectoBanco.DAO.ClienteDAO;
import com.beeva.practica.ProyectoBanco.DAO.CuentaDao;
import com.beeva.practica.ProyectoBanco.DAO.TipoCuentaDAO;
import com.beeva.practica.ProyectoBanco.model.Banco;
import com.beeva.practica.ProyectoBanco.model.Bancocliente;
import com.beeva.practica.ProyectoBanco.model.Cliente;
import com.beeva.practica.ProyectoBanco.model.Cuenta;
import com.beeva.practica.ProyectoBanco.model.Tipocuenta;
import com.beeva.practica.ProyectoBanco.mongoUtils.MongoUtils;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"core-context.xml");

		BancoDao bancoDao = (BancoDao) context.getBean(BancoDao.class);
		CuentaDao cuentaDao = (CuentaDao) context.getBean(CuentaDao.class);
		ClienteDAO clienteDao = (ClienteDAO) context.getBean(ClienteDAO.class);
		BancoClienteDAO bancoClienteDAO = (BancoClienteDAO) context
				.getBean(BancoClienteDAO.class);
		TipoCuentaDAO tipoCuentaDao = context.getBean(TipoCuentaDAO.class);

		// //Crear banco
		
		System.out.println("********************************************************");
		System.out.println("Crear Banco. \nEscribir nombre del banco: ");
		System.out.println("********************************************************");
		String nombreBanco = entrada.next();
		Banco banco = new Banco();
		banco.setNombre(nombreBanco);
		//
		// //Crear Cliente1
		System.out.println("********************************************************");
		System.out.println("Escriba el nombre del cliente para el banco: "
				+ banco.getNombre());
		String nombreCliente = entrada.next();
		System.out.println("Escriba el Apellido Paterno: ");
		String aPaterno = entrada.next();
		System.out.println("Escriba el Apellido Materno: ");
		String aMaterno = entrada.next();
		Cliente cliente = new Cliente();
		cliente.setNombre(nombreCliente);
		cliente.setApaterno(aPaterno);
		cliente.setAmaterno(aMaterno);

		Banco getBanco = bancoDao.agregarBanco(banco);
		Cliente getCliente = clienteDao.agregarCliente(cliente);
		Bancocliente bancoCliente = new Bancocliente();
		bancoCliente.setBanco(getBanco);
		bancoCliente.setCliente(getCliente);
		Bancocliente banCliente = bancoClienteDAO
				.agregarBancoCliente(bancoCliente);
		bancoClienteDAO.getBancoCliente(1);

		System.out.println("Se guardo el banco: " + getBanco.getNombre());
		System.out.println("Se guardo al cliente con el nombre: "
				+ getCliente.getNombre() + " " + getCliente.getApaterno() + " "
				+ getCliente.getAmaterno());
		System.out.println("***********************************************************");

		//
		// //Crear cuenta
		System.out.println("***********************************************************");
		Cuenta cuenta = new Cuenta();
		cuenta.setBalance(cuenta.getBalance());
		cuenta.setCliente(getCliente);
		System.out
				.println("Agreagr tipo de cuenta: (escriba solo el nombre como aparece) \nCuenta 1.Ahorro\nCuenta 2.Cheques");
		String tipoDecuenta = entrada.next();
		Tipocuenta tipocuentaRegistrada = new Tipocuenta();
		tipocuentaRegistrada.setNombrecuenta(tipoDecuenta);
		
		Tipocuenta tipocuenta = tipoCuentaDao.agregarTipoCuenta(tipocuentaRegistrada);
		cuenta.setTipocuenta(tipocuenta);
		Cuenta cuentaCliente = cuentaDao.agregarCuenta(cuenta);

		System.out.println("Banco: " + getBanco.getNombre());
		System.out.println("Cliente: " + cuentaCliente.getCliente().getNombre()
				+ " " + cuentaCliente.getCliente().getApaterno() + " "
				+ cuentaCliente.getCliente().getAmaterno());
		System.out.println("Tipo de Cuenta: "
				+ cuentaCliente.getTipocuenta().getNombrecuenta());
		System.out.println("Balance inicial de la cuenta: $"
				+ cuentaCliente.getBalance());

		System.out.println("Escriba la cantidad de Deposito: ");
		double deposito = entrada.nextDouble();
		cuentaDao.deposito(cuentaCliente, deposito);
		//
		// int tipoOperacion = 0;
		// System.out.println("Elija la operación a realizar:\n1 = Deposito\n2 = Retiro");
		// tipoOperacion = entrada.nextInt();

		int numOperaciones = 1;

		do {
			int tipoOperacion = 0;
			System.out
					.println("Elija la operación a realizar: (ingrese el 1 ó el 2)\n1 = Deposito\n2 = Retiro");
			tipoOperacion = entrada.nextInt();

			if (tipoOperacion == 1) {
				System.out.println("Escriba la cantidad de Deposito: ");
				double deposito1 = entrada.nextDouble();
				cuentaDao.deposito(cuentaCliente, deposito1);
			} else if (tipoOperacion == 2) {
				System.out.println("Escriba la cantidad de Retiro: ");
				double retiro = entrada.nextDouble();
				cuentaDao.retiro(cuentaCliente, retiro);
			}
			numOperaciones += 1;
		} while (numOperaciones < 4);

		MongoUtils mutils = new MongoUtils();
		mutils.logcollection(cliente);
		mutils.logcollectionCuenta(cuenta);

		System.out.println("***************************************************");
		int respuesta = -1;

		do {
			System.out.println("Desea registrar un nuevo cliente en banco: "
					+ getBanco.getNombre() + " (ingrese el 1 ó el 2) \nSi = 1\nNo = 2");
			respuesta = entrada.nextInt();
			switch (respuesta) {
			case 1:
				crearMasclientes(getBanco.getIdbanco());
				break;
			case 2:
				System.out.println("Gracias por elegirnos.");
				break;
			default:
				System.out.println("No eligio ninguna opcion.");
				break;
			}
		} while (respuesta != 2);

	}

	public static void crearMasclientes(int idBanco) {

		// Obtener el banco creado para crear los nuevos clientes
		Scanner entrada = new Scanner(System.in);

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"core-context.xml");

		BancoDao bancoDao = (BancoDao) context.getBean(BancoDao.class);
		CuentaDao cuentaDao = (CuentaDao) context.getBean(CuentaDao.class);
		ClienteDAO clienteDao = (ClienteDAO) context.getBean(ClienteDAO.class);
		BancoClienteDAO bancoClienteDAO = (BancoClienteDAO) context
				.getBean(BancoClienteDAO.class);
		TipoCuentaDAO tipoCuentaDao = context.getBean(TipoCuentaDAO.class);
		Banco bancoClientes = bancoDao.getBanco(idBanco);
		System.out.println("***********************************************");
		System.out.println("Escriba el nombre del cliente para el banco: "
				+ bancoClientes.getNombre());
		String nombreCliente = entrada.next();
		System.out.println("Escriba el Apellido Paterno: ");
		String aPaterno = entrada.next();
		System.out.println("Escriba el Apellido Materno: ");
		String aMaterno = entrada.next();
		Cliente cliente = new Cliente();
		cliente.setNombre(nombreCliente);
		cliente.setApaterno(aPaterno);
		cliente.setAmaterno(aMaterno);

		// Banco getBanco = bancoDao.agregarBanco(banco);
		Cliente getCliente = clienteDao.agregarCliente(cliente);
		Bancocliente bancoCliente = new Bancocliente();
		bancoCliente.setBanco(bancoClientes);
		bancoCliente.setCliente(getCliente);
		Bancocliente banCliente = bancoClienteDAO
				.agregarBancoCliente(bancoCliente);
		bancoClienteDAO.getBancoCliente(1);

		System.out.println("Se guardo el banco: " + bancoClientes.getNombre());
		System.out.println("Se guardo al cliente con el nombre: "
				+ getCliente.getNombre() + " " + getCliente.getApaterno() + " "
				+ getCliente.getAmaterno());

		System.out.println("**************************************************");
		//
		// //Crear cuenta
		System.out.println("**************************************************");
		Cuenta cuenta = new Cuenta();
		cuenta.setBalance(cuenta.getBalance());
		cuenta.setCliente(getCliente);
		System.out
				.println("Agreagr tipo de cuenta (escriba solo el nombre como aparece): \nCuenta 1.Ahorro\nCuenta 2.Cheques");
		String tipoDecuenta = entrada.next();
		Tipocuenta tipocuentaRegistrada = new Tipocuenta();
		tipocuentaRegistrada.setNombrecuenta(tipoDecuenta);
		
		Tipocuenta tipocuenta = tipoCuentaDao.agregarTipoCuenta(tipocuentaRegistrada);
		cuenta.setTipocuenta(tipocuenta);
		Cuenta cuentaCliente = cuentaDao.agregarCuenta(cuenta);

		System.out.println("Banco: " + bancoClientes.getNombre());
		System.out.println("Cliente: " + cuentaCliente.getCliente().getNombre()
				+ " " + cuentaCliente.getCliente().getApaterno() + " "
				+ cuentaCliente.getCliente().getAmaterno());
		System.out.println("Tipo de Cuenta: "
				+ cuentaCliente.getTipocuenta().getNombrecuenta());
		System.out.println("Balance inicial de la cuenta: $"
				+ cuentaCliente.getBalance());

		System.out.println("Escriba la cantidad de Deposito: ");
		double deposito = entrada.nextDouble();
		cuentaDao.deposito(cuentaCliente, deposito);

		int numOperaciones = 1;

		do {
			int tipoOperacion = 0;
			System.out
					.println("Elija la operación a realizar: (ingrese el 1 ó el 2)\n1 = Deposito\n2 = Retiro");
			tipoOperacion = entrada.nextInt();

			if (tipoOperacion == 1) {
				System.out.println("Escriba la cantidad de Deposito: ");
				double deposito1 = entrada.nextDouble();
				cuentaDao.deposito(cuentaCliente, deposito1);
			} else if (tipoOperacion == 2) {
				System.out.println("Escriba la cantidad de Retiro: (antes de ingresar el dato deberá modificar el día del sistema, según usted lo requiera)");
				double retiro = entrada.nextDouble();
				cuentaDao.retiro(cuentaCliente, retiro);
			}
			numOperaciones += 1;
		} while (numOperaciones < 4);

		MongoUtils mutils = new MongoUtils();
		mutils.logcollection(cliente);
		mutils.logcollectionCuenta(cuenta);
	}

}

package com.beeva.practica.ProyectoBanco.mongoUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beeva.practica.ProyectoBanco.Beans.MongoBean;
import com.beeva.practica.ProyectoBanco.model.Banco;
import com.beeva.practica.ProyectoBanco.model.Cliente;
import com.beeva.practica.ProyectoBanco.model.Cuenta;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoUtils {
	
	public void logcollection(Cliente cliente){
		try{
		 String cadenaFecha; 
       	 Date fechaActual = new Date(); 
       	 SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
       	 cadenaFecha = formato.format(fechaActual); 
    	 ApplicationContext context = new ClassPathXmlApplicationContext("core-context.xml");
    	 MongoBean mongoBean = (MongoBean) context.getBean("mongoBean");
         MongoClient mc = mongoBean.getConecction();
         DB db = mc.getDB("BANCOLOG");
			 DBCollection table = db.getCollection("LogCollection");
			 BasicDBObject document = new BasicDBObject();
			 document.put("Mensaje", "Se inserto un cliente");
			 document.put("Fecha", cadenaFecha);
			 document.put("idCliente", cliente.getIdcliente());
			 document.put("Nombre", cliente.getNombre());
			 document.put("Apellido Paterno", cliente.getApaterno());
			 document.put("Apellido Materno", cliente.getAmaterno());
			 table.insert(document);
			 System.out.println("Se ha creado Cliente en Mongo.");
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public void logcollectionCuenta(Cuenta cuenta){
		try{
		 String cadenaFecha; 
       	 Date fechaActual = new Date(); 
       	 SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
       	 cadenaFecha = formato.format(fechaActual); 
    	 ApplicationContext context = new ClassPathXmlApplicationContext("core-context.xml");
    	 MongoBean mongoBean = (MongoBean) context.getBean("mongoBean");
         MongoClient mc = mongoBean.getConecction();
         DB db = mc.getDB("BANCOLOG");
			 DBCollection table = db.getCollection("LogCollection");
			 BasicDBObject document = new BasicDBObject();
			 document.put("Mensaje", "Se inserto una cuenta");
			 document.put("Fecha", cadenaFecha);
			 document.put("idCuenta", cuenta.getIdcuenta());
			 document.put("Nombre Cliente", cuenta.getCliente().getNombre());
			 document.put("Tipo Cuenta", cuenta.getTipocuenta().getNombrecuenta());
			 table.insert(document);
			 System.out.println("Se ha creado Cuenta en Mongo.");
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public void logcollectionBanco(Banco banco){
		try{
		 String cadenaFecha; 
       	 Date fechaActual = new Date(); 
       	 SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
       	 cadenaFecha = formato.format(fechaActual); 
    	 ApplicationContext context = new ClassPathXmlApplicationContext("core-context.xml");
    	 MongoBean mongoBean = (MongoBean) context.getBean("mongoBean");
         MongoClient mc = mongoBean.getConecction();
         DB db = mc.getDB("BANCOLOG");
			 DBCollection table = db.getCollection("LogCollection");
			 BasicDBObject document = new BasicDBObject();
			 document.put("Mensaje", "Se inserto un Banco");
			 document.put("Fecha", cadenaFecha);
			 document.put("idBanco", banco.getIdbanco());
			 document.put("Nombre Banco", banco.getNombre());
			 table.insert(document);
			 System.out.println("Se ha creado la base de Datos");
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

}

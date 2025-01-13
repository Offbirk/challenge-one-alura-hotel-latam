package com.alura.hotel.factory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
/***
 * @parameter Fijacion de la conexión desde un pool de conexiones con la interfaz c3p0.
 */
public class ConnectionFactory {
	private DataSource datasource;
	String DataBaseUrl = System.getenv("DataBaseUrl");
	String Password = System.getenv("Password");

	public ConnectionFactory() {
		var pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl(DataBaseUrl);
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword(Password);
		pooledDataSource.setMaxPoolSize(4);
		
		this.datasource = pooledDataSource;
	}
/**
 * 	
 * @return obtiene la conexión y devuelve una conexión caso que no exista una excepción
 * al correr el programa.
 */
	public Connection recuperaConexion() {
		try {
			return this.datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

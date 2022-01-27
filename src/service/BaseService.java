package service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import annotation.Tabla;
import connection.DbConnection;
import objects.ObjetoBase;

public abstract class BaseService<T extends ObjetoBase> {

	// Propiedades de los servicios

	private DbConnection dbConnection;
	private String nombreTabla;
	private int numeroCampos;
	
	// Getters y Setters

	public DbConnection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(DbConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public String getNombreTabla() {
		return nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	public int getNumeroCampos() {
		return numeroCampos;
	}

	public void setNumeroCampos(int numeroCampos) {
		this.numeroCampos = numeroCampos;
	}

	// Consultas base

	private static String SELECT_BASE = "SELECT * FROM %s";
	private static String DELETE_BASE = "DELETE FROM %s WHERE id = %d";

	public BaseService(T entidad) {
		this.dbConnection = new DbConnection();
		this.nombreTabla = entidad.getClass().getAnnotation(Tabla.class).nombreTabla();
		this.numeroCampos = entidad.getClass().getAnnotation(Tabla.class).numeroCampos();
	}
	
	/**
	 * Obtiene el listado de objetos y lo devuelve configurado como una lista
	 * @return
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException 
	 */
	public List<T> consultaListado()
			throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		ArrayList<T> listaObjetos = new ArrayList<T>();

		ResultSet result = dbConnection.getStatement()
				.executeQuery(String.format(SELECT_BASE, this.nombreTabla));
		
		ResultSetMetaData resultSetMetaData = result.getMetaData();
		
		while(result.next()) {
			for(int i = 1; i<= resultSetMetaData.getColumnCount(); i+=this.numeroCampos) {

				T objeto = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
				
				for(int j = i; j<= resultSetMetaData.getColumnCount(); j++) {
					this.dameSetterPorNombreColumna(this.capitalizaColumna(resultSetMetaData.getColumnName(j)), objeto.dameListaSetters()).invoke(objeto, result.getObject(j));
				}
				
				listaObjetos.add(objeto);
			}
		}
		
		return listaObjetos;
	}
	
	/**
	 * Método que devuelve el método correspondiente al setter de una columna 
	 * @param nombrePropiedad
	 * @param metodos
	 * @return
	 */
	public Method dameSetterPorNombreColumna(String nombrePropiedad, List<Method> metodos) {
		for(Method m : metodos) {
			if(m.getName().equals("set" + nombrePropiedad)) {
				return m;
			}
		}
		return null;
	}
	
	/**
	 * Método que devuelve el método correspondiente al setter de una columna 
	 * @param nombrePropiedad
	 * @param metodos
	 * @return
	 */
	public Method dameGetterPorNombreColumna(String nombrePropiedad, List<Method> metodos) {
		for(Method m : metodos) {
			if(m.getName().equals("get" + nombrePropiedad)) {
				return m;
			}
		}
		return null;
	}

	/**
	 * Pone la primera letra en mayúscula
	 * @param nombreColumna
	 * @return nombre con primera mayúscula
	 */
	public String capitalizaColumna(String nombreColumna) {
		return nombreColumna.substring(0,1).toUpperCase().concat(nombreColumna.substring(1));
	}

}

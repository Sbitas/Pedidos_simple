package objects;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import annotation.Tabla;

@Tabla(nombreTabla = "", numeroCampos = 0, nombreFrame = "")
public abstract class ObjetoBase {

	/*
	 * Todas las entidades tiene un id que las referencia en la bbdd
	 * 
	 */
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	/**
	 * Método que devuelve la lista de setters de la entidad
	 * @return setters
	 */
	public List<Method> dameListaSetters() {
		ArrayList<Method> setters = new ArrayList<Method>();

		for (Method m : Arrays.asList(this.getClass().getMethods())) {
			if (m.getName().startsWith("set")) {
				setters.add(m);
			}
		}

		return setters;
	}
	
	/**
	 * Método que devuelve la lista de getters de la entidad
	 * @return getters
	 */
	public List<Method> dameListaGetters() {
		ArrayList<Method> getters = new ArrayList<Method>();

		for (Method m : Arrays.asList(this.getClass().getMethods())) {
			if (m.getName().startsWith("get")) {
				getters.add(m);
			}
		}

		return getters;
	}
	
	/**
	 * Método que devuelve una lista de propiedades del objeto
	 * @return propiedades
	 */
	public List<String> dameListaPropiedades() {
		List<String> propiedades = new ArrayList<String>();
		for (Field f : getClass().getDeclaredFields()) {
			propiedades.add(f.getName());
		}
		return propiedades;
	}
}

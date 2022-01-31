package view;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import objects.ObjetoBase;
import service.BaseService;

public class VentanaBase<T extends ObjetoBase, ISERVICE extends BaseService<T>> {

	/**
	 * LLama al servicio de la entida para obtener el listado de propiedades de la
	 * misma
	 * 
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@SuppressWarnings("deprecation")
	public List<T> cargaListadoObjetos() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, SecurityException {
		return ((Class<ISERVICE>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1])
				.newInstance().consultaListado();
	}

	/**
	 * Llama al servicio de la entidad para obtener los getters de la misma
	 * 
	 * @param nombrePropiedad
	 * @param metodos
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Method dameGetterPorNombrePropiedad(String nombrePropiedad, List<Method> metodos)
			throws InstantiationException, IllegalAccessException {
		return ((Class<ISERVICE>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1])
				.newInstance().dameGetterPorNombreColumna(nombrePropiedad, metodos);
	}

	/**
	 * LLama al servicio de la entidad para obtener los setters de la misma
	 * 
	 * @param nombrePropiedad
	 * @param metodos
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Method dameSetterPorNombrePropiedad(String nombrePropiedad, List<Method> metodos)
			throws InstantiationException, IllegalAccessException {
		return ((Class<ISERVICE>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1])
				.newInstance().dameSetterPorNombreColumna(nombrePropiedad, metodos);
	}

	/**
	 * Método que llama al servicio para abrir la ventana de la misma
	 * 
	 * @param fila
	 * @throws SQLException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public T dameEntidadPorIdFila(int idEntidad) throws IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, SQLException, InstantiationException, IllegalAccessException {
		ISERVICE service = ((Class<ISERVICE>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[1]).newInstance();
		return service.dameObjetoFromDobleClickEnColumna(idEntidad);
	}
	
	/**
	 * Pone la primera letra en mayúscula
	 * 
	 * @param nombreColumna
	 * @return nombre con primera mayúscula
	 */
	public String capitalizaColumna(String nombreColumna) {
		return nombreColumna.substring(0, 1).toUpperCase().concat(nombreColumna.substring(1));
	}
}

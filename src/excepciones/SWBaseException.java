package excepciones;

import constants.EnumAcciones;

public class SWBaseException {

	private String excepcion;
	private EnumAcciones accion;
	private Object objetoAccion;

	// Getters

	public String getExcepcion() {
		return excepcion;
	}

	public void setExcepcion(String excepcion) {
		this.excepcion = excepcion;
	}

	public EnumAcciones getAccion() {
		return accion;
	}

	public void setAccion(EnumAcciones accion) {
		this.accion = accion;
	}

	public Object getObjetoAccion() {
		return objetoAccion;
	}

	public void setObjetoAccion(Object objetoAccion) {
		this.objetoAccion = objetoAccion;
	}

	// Constructor
	public SWBaseException(String exception, Object objeto, EnumAcciones accion) {
		this.excepcion = exception;
		this.objetoAccion = objeto;
		this.accion = accion;
	}

	@Override
	public String toString() {
		return ("Error al " + this.accion.getValorTraducido() + " el objeto " + this.objetoAccion.toString()
				+ ". Excepcion: " + this.excepcion);
	}

}

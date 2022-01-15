package constants;

/**
 * Tipos de acciones que pueden dar error
 * 
 * @author Sbita
 *
 */
public enum EnumAcciones {

	CREATE("Crear"),

	READ("Consultar"),

	UPDATE("Actualizar"),

	DELETE("Eliminar"),

	;

	String valorTraducido;

	EnumAcciones(String valorTraducido) {
		this.valorTraducido = valorTraducido;
	}

	public String getValorTraducido() {
		return valorTraducido;
	}

	public void setValorTraducido(String valorTraducido) {
		this.valorTraducido = valorTraducido;
	}

}

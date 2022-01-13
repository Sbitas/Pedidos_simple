package constants;

public enum EnumTipoArticulo {

	UNDEFINED(0, ""),

	SUDADERA_BASICA(1, "Sudadera básica"),

	SUDADERA_BICOLOR(2, "Sudadera bicolor"),
	
	CAMISETA(3, "Camiseta"),
	
	GORRO(4, "Gorro"),
	
	FORRO_POLAR(5, "Forro polar"),

	;

	private int codigoNumerico;
	private String nombre;

	// Getters y setters

	public int getCodigoNumerico() {
		return codigoNumerico;
	}

	public void setCodigoNumerico(int codigoNumerico) {
		this.codigoNumerico = codigoNumerico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	// Constructor
	EnumTipoArticulo(int codigoNumerico, String nombre) {
		this.codigoNumerico = codigoNumerico;
		this.nombre = nombre;
	}

	// Métodos

	/**
	 * Método que en función de un código introducido devuelve el valor
	 * correspondiente del enumerado
	 * 
	 * @param codigoNumerico
	 * @return
	 */
	public static EnumTipoArticulo convert(Integer codigoNumerico) {

		EnumTipoArticulo[] v = values();

		for (EnumTipoArticulo x : v) {
			if (x.getCodigoNumerico() == codigoNumerico) {
				return x;
			}
		}

		return UNDEFINED;
	}

}

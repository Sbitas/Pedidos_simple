package constants;

public enum EnumTipoArticulo {

	SUDADERA_BASICA(1, "Sudadera básica", "sudaderaBasica"),

	SUDADERA_BICOLOR(2, "Sudadera bicolor", "sudaderaBicolor"),
	
	CAMISETA(3, "Camiseta", "camiseta"),
	
	GORRO(4, "Gorro", "gorro"),
	
	FORRO_POLAR(5, "Forro polar", "forroPolar"),

	;

	private int codigoNumerico;
	private String nombre;
	private String rutaColores;
	
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
	
	public String getRutaColores() {
		return rutaColores;
	}

	public void setRutaColores(String rutaColores) {
		this.rutaColores = rutaColores;
	}

	// Constructor
	EnumTipoArticulo(int codigoNumerico, String nombre, String rutaColores) {
		this.codigoNumerico = codigoNumerico;
		this.nombre = nombre;
		this.rutaColores = rutaColores;
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

		return null;
	}
	
	public static String[] comboSeleccion() {
		String[] valores = new String[values().length];
		
		int i = 0;
		for(EnumTipoArticulo v : values()) {
			valores[i] = v.getNombre();
			i++;
		}
		
		return valores;
	}

}

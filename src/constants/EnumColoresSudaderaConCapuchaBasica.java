package constants;

public enum EnumColoresSudaderaConCapuchaBasica {

	UNDEFINED(0, "", ""),

	NEGRO(1, "Negro", "negro"),
	
	GRIS_ACERO(2, "Gris acero", "grisAcero"),
	
	AZUL_MARINO(3, "Azul marino", "azulMarino"),
	
	MORADO(4, "Morado", "morado"),
	
	VIOLETA(5, "Violeta", "violeta"),
	
	LAVANDA(6, "Lavanda", "lavanda"),
	
	ROSA_PASTEL(7, "Rosa pastel", "rosaPastel"),
	
	ROSA_GASTADO(8, "Rosa gastado", "rosaGastado"),
	
	AZUL_PASTEL(9, "Azul pastel", "azulPastel"),
	
	AZUL(10, "Azul", "azul"),
	
	AZUL_PETROLEO(11, "Azul petróleo", "azulPetroleo"),
	
	AZUL_ELECTRICO(12, "Azul eléctrico", "azulElectrico"),
	
	VERDE_OSCURO(13, "Verde oscuro", "verdeOscuro"),
	
	VERDE_SUAVE(14, "Verde suave", "verdeSuave"),
	
	VERDE_BRILLANTE(15, "Verde brillante", "verdeBrillante"),
	
	JADE(16, "Jade", "jade"),
	
	ARENA(17, "Arena", "arena"),
	
	AMARILLO_PASTEL(18, "Amarillo pastel", "amarilloPastel"),
	
	AMARILLO(19, "Amarillo", "amarrillo"),
	
	NARANJA(20, "Naranja", "naranja"),
	
	ROJO(21, "Rojo", "rojo"),
	
	BORGONA(22, "Borgoña", "borgona"),
	
	UVA(23, "Uva", "uva"),
	
	BLANCO(24, "Blanco", "blanco"),

	;

	private int codigoNumerico;
	private String nombre;
	private String rutaColor;
	
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
	
	public String getRutaColor() {
		return rutaColor;
	}
	
	public void setRutaColor(String rutaColor) {
		this.rutaColor = rutaColor;
	}
	
	// Constructor

	EnumColoresSudaderaConCapuchaBasica(Integer codigoNumerico, String nombre, String rutaColor) {
		this.codigoNumerico = codigoNumerico;
		this.nombre = nombre;
		this.rutaColor = rutaColor;
	}
	private EnumColoresSudaderaConCapuchaBasica() {
	}
	public static EnumColoresSudaderaConCapuchaBasica convert(int codigoNumerico) {
		
		EnumColoresSudaderaConCapuchaBasica[] v = values();
		
		for(EnumColoresSudaderaConCapuchaBasica x : v) {
			if(x.getCodigoNumerico() == codigoNumerico) {
				return x;
			}
		}
		
		return UNDEFINED;
	}

	@Override
	public String toString() {
		
		return this.getNombre();
	}
	
	public static String[] comboSeleccion() {
		String[] valores = new String[values().length];
		
		int i = 0;
		for(EnumColoresSudaderaConCapuchaBasica v : values()) {
			valores[i] = v.getNombre();
			i++;
		}
		
		return valores;
	}
}

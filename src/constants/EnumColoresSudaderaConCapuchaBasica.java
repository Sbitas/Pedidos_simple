package constants;

public enum EnumColoresSudaderaConCapuchaBasica {

	UNDEFINED(0, ""),

	NEGRO(1, "Negro"),
	
	GRIS_ACERO(2, "Gris acero"),
	
	AZUL_MARINO(3, "Azul marino"),
	
	MORADO(4, "Morado"),
	
	VIOLETA(5, "Violeta"),
	
	LAVANDA(6, "Lavanda"),
	
	ROSA_PASTEL(7, "Rosa pastel"),
	
	ROSA_GASTADO(8, "Rosa gastado"),
	
	AZUL_PASTEL(9, "Azul pastel"),
	
	AZUL(10, "Azul"),
	
	AZUL_PETROLEO(11, "Azul petróleo"),
	
	AZUL_ELECTRICO(12, "Azul eléctrico"),
	
	VERDE_OSCURO(13, "Verde oscuro"),
	
	VERDE_SUAVE(14, "Verde suave"),
	
	VERDE_BRILLANTE(15, "Verde brillante"),
	
	JADE(16, "Jade"),
	
	ARENA(17, "Arena"),
	
	AMARILLO_PASTEL(18, "Amarillo pastel"),
	
	AMARILLO(19, "Amarillo"),
	
	NARANJA(20, "Naranja"),
	
	ROJO(21, "Rojo"),
	
	BORGONA(22, "Borgoña"),
	
	UVA(23, "Uva"),
	
	BLANCO(24, "Blanco"),

	;

	private int codigoNumerico;
	private String nombre;

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

	EnumColoresSudaderaConCapuchaBasica(Integer codigoNumerico, String nombre) {
		this.codigoNumerico = codigoNumerico;
		this.nombre = nombre;
	}

	public static EnumColoresSudaderaConCapuchaBasica convert(Integer codigoNumerico) {
		
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
	
	
}

package constants;

public enum EnumEstadoPedido {

	PEDIDO(0, "Pedido"),

	EN_CAMINO(1, "En camino"),

	EN_CASA(2, "En casa"),

	BORDANDOSE(3, "Bordándose"),

	PROCESANDO(4, "Procesando"),

	ENVIADO(5, "Enviado"),

	PAGADO_PARCIAL(6, "Pagado parcialmente"),

	PAGADO_ENTERO(7, "Todo pagado"),

	CERRADO(8, "Cerrado"),

	;

	private int value;
	private String nombre;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private EnumEstadoPedido(Integer value, String nombre) {
		this.value = value;
		this.nombre = nombre;
	}

	public static EnumEstadoPedido convert(int value) {
		EnumEstadoPedido[] v = values();

		for (EnumEstadoPedido e : v) {
			if (e.getValue() == value) {
				return e;
			}
		}
		return null;
	}
}

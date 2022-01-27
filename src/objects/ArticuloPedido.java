package objects;

import java.math.BigDecimal;

public class ArticuloPedido {
	
	
	private int id;
	
	private Articulo articulo;
	
	private Pedido pedido;
	
	private boolean bordadoDelanteSiNo;
	
	private boolean viniloDetrasSiNo;
	
	private String colorBordadoDelante;
	
	private String colorViniloDentras;
	
	private BigDecimal precioCoste;
	
	private BigDecimal precioVenta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public boolean isBordadoDelanteSiNo() {
		return bordadoDelanteSiNo;
	}

	public void setBordadoDelanteSiNo(boolean bordadoDelanteSiNo) {
		this.bordadoDelanteSiNo = bordadoDelanteSiNo;
	}

	public boolean isViniloDetrasSiNo() {
		return viniloDetrasSiNo;
	}

	public void setViniloDetrasSiNo(boolean viniloDetrasSiNo) {
		this.viniloDetrasSiNo = viniloDetrasSiNo;
	}

	public String getColorBordadoDelante() {
		return colorBordadoDelante;
	}

	public void setColorBordadoDelante(String colorBordadoDelante) {
		this.colorBordadoDelante = colorBordadoDelante;
	}

	public String getColorViniloDentras() {
		return colorViniloDentras;
	}

	public void setColorViniloDentras(String colorViniloDentras) {
		this.colorViniloDentras = colorViniloDentras;
	}

	public BigDecimal getPrecioCoste() {
		return precioCoste;
	}

	public void setPrecioCoste(BigDecimal precioCoste) {
		this.precioCoste = precioCoste;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}
	
	
}

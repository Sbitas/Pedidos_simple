package objects;

import java.math.BigDecimal;

import annotation.Tabla;
import constants.EnumTipoArticulo;

/**
 * Objeto art�culo
 * 
 * @author Sbita
 *
 */
@SuppressWarnings("rawtypes")
@Tabla(nombreTabla = "articulos")
public class Articulo {

	/**
	 * Id de la bbdd
	 */
	private int id;

	/**
	 * Tipo de articulo:
	 * <ul>
	 *  <li> SUDADERA B�SICA </li>
	 *  <li> SUDADERA BICOLOR </li>
	 *  <li> CAMISETA </li>
	 *  <li> GORRO </li>
	 *  </ul>
	 */
	private EnumTipoArticulo tipo;

	/**
	 * Color del art�culo
	 * Uso el enum raw porque en funci�n del tipo de art�culo es un enum u otro
	 */
	private Enum color;
	
	/**
	 * Precio base del art�culo (lo que nos cuesta)
	 */
	private BigDecimal precioBase;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EnumTipoArticulo getTipo() {
		return tipo;
	}

	public void setTipo(EnumTipoArticulo tipo) {
		this.tipo = tipo;
	}

	public Enum getColor() {
		return color;
	}

	public void setColor(Enum color) {
		this.color = color;
	}

	public BigDecimal getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(BigDecimal precioBase) {
		this.precioBase = precioBase;
	}

	@Override
	public String toString() {
		return "Articulo [id=" + id + ", tipo=" + tipo + ", color=" + color + ", precioBase=" + precioBase + "]";
	}
	
	
	
}

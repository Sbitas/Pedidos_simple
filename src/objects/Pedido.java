package objects;

import java.math.BigDecimal;

import annotation.Tabla;
import constants.EnumEstadoPedido;

@Tabla(nombreTabla = "pedidos", numeroCampos = 4, nombreFrame = "PEDIDOS")
public class Pedido extends ObjetoBase {

	private Cliente cliente;

	private BigDecimal precioPedido;

	private EnumEstadoPedido estadoPedido;

	// GETTERS Y SETTERS

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getPrecioPedido() {
		return precioPedido;
	}

	public void setPrecioPedido(BigDecimal precioPedido) {
		this.precioPedido = precioPedido;
	}

	public EnumEstadoPedido getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(EnumEstadoPedido estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

}

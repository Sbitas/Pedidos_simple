package objects;

import java.math.BigDecimal;

import annotation.Tabla;
import constants.EnumEstadoPedido;

@Tabla(nombreTabla = "pedidos", numeroCampos = 4, nombreFrame = "PEDIDOS")
public class Pedido extends ObjetoBase {
	
	private int clienteId;

	private BigDecimal precioPedido;

	private EnumEstadoPedido estadoPedido;
	
	// GETTERS Y SETTERS

	public BigDecimal getPrecioPedido() {
		return precioPedido;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
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

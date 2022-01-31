package view.listados;

import objects.Cliente;
import service.impl.ClienteServiceImpl;
import view.detalle.VentanaDetalleCliente;

public class VentanaListadoClientes extends VentanaListadoBase<Cliente, ClienteServiceImpl, VentanaDetalleCliente> {

	public VentanaListadoClientes() {
		super(new Cliente());
	}
}

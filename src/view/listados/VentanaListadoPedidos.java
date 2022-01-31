package view.listados;

import objects.Pedido;
import service.impl.PedidoServiceImpl;
import view.detalle.VentanaDetallePedido;

public class VentanaListadoPedidos extends VentanaListadoBase<Pedido, PedidoServiceImpl, VentanaDetallePedido> {

	public VentanaListadoPedidos(Pedido entidad) {
		super(entidad);
	}

}

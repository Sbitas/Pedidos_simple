package view;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import objects.Pedido;
import service.impl.PedidoServiceImpl;

public class VentanaListadoPedidos extends ventanaListadoBase<Pedido, PedidoServiceImpl>{

	public VentanaListadoPedidos(Pedido entidad) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException, SQLException {
		super(entidad);
	}
	
}

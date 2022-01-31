package view.detalle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import objects.Cliente;
import service.impl.ClienteServiceImpl;

public class VentanaDetalleCliente extends VentanaDetalleBase<Cliente, ClienteServiceImpl> {

	public VentanaDetalleCliente() throws IOException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		super();
	}

}

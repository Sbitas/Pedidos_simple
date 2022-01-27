package view;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import objects.Cliente;
import service.impl.ClienteServiceImpl;

public class VentanaListadoClientes extends ventanaListadoBase<Cliente, ClienteServiceImpl> {

	public VentanaListadoClientes() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, SQLException {
		super(new Cliente());
	}
}

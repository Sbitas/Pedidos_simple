package service.impl;

import objects.Cliente;
import service.BaseService;
import service.IClienteService;

public class ClienteServiceImpl extends BaseService<Cliente> implements IClienteService {

	public ClienteServiceImpl() {
		super(new Cliente());
	}

}

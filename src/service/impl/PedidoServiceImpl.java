package service.impl;

import objects.Pedido;
import service.BaseService;
import service.IPedidoService;

public class PedidoServiceImpl extends BaseService<Pedido> implements IPedidoService {

	public PedidoServiceImpl() {
		super(new Pedido());
	}

}

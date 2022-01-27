package service.impl;

import objects.Pedido;
import service.BaseService;
import service.IPedidoService;

public class PedidoServiceImpl extends BaseService<Pedido> implements IPedidoService {

	public PedidoServiceImpl(Pedido entidad) {
		super(entidad);
	}

}

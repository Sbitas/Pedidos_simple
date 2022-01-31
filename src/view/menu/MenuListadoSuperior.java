package view.menu;

import java.io.IOException;

import objects.ObjetoBase;
import service.BaseService;
import view.detalle.VentanaDetalleBase;

public class MenuListadoSuperior<T extends ObjetoBase, ISERVICE extends BaseService<T>, VENTANA_DETALLE extends VentanaDetalleBase<T, ISERVICE>>
		extends MenusListadosBase<T, ISERVICE, VENTANA_DETALLE> {

	public MenuListadoSuperior() throws IOException {
		super();
	}

	public MenuListadoSuperior(Class<T> claseObjeto, Class<ISERVICE> claseService,
			Class<VENTANA_DETALLE> claseDetalle) throws IOException {
		super(claseObjeto, claseService, claseDetalle);
	}

}

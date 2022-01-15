package service.impl;

import java.util.List;

import objects.Articulo;
import service.IArticuloService;

public class ArticuloServiceImpl implements IArticuloService{

	@Override
	public Articulo dameArticuloFromDobleClickEnColumna(int idArticuloColumna, List<Articulo> listaArticulos) {
		Articulo retorno = null;
		for(Articulo a : listaArticulos) {
			if(a.getId() == idArticuloColumna)
				retorno = a;
		}
		
		return (retorno != null ? retorno : null);
	}
	
}

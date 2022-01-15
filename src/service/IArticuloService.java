package service;

import java.util.List;

import objects.Articulo;

/**
 * Interfaz para las acciones sobre los artículos
 * @author Sbita
 *
 */
public interface IArticuloService {
	
	public Articulo dameArticuloFromDobleClickEnColumna(int idArticuloColumna, List<Articulo> listaArticulos);
}

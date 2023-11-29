package uniandes.dpoo.taller2.procesamiento;

import java.util.ArrayList;


public class ProductoAjustado implements Producto {
	
	private Producto base;
	private ArrayList<Ingrediente> agregado;
	private ArrayList<Ingrediente> eliminado;
	
	
	public ProductoAjustado(Producto base) {
		this.agregado=new ArrayList<Ingrediente>();
		this.eliminado = new ArrayList<Ingrediente>();
		this.base = base;
	}
	public void añadirIngrediente(Ingrediente ingrediente) {
		agregado.add(ingrediente);
	}

	public void eliminarIngrediente(Ingrediente ingrediente) {
		eliminado.add(ingrediente);
		
	}
	public boolean sinIngredientesAgregados() {
		boolean siHayElementos=agregado.size()==0;
		return siHayElementos;
		
	}
	
	public boolean sinIngredientesQuitados() {
		boolean siHayElementos=eliminado.size()==0;
		return siHayElementos;
		
	}
	
	@Override
	public int getprecio() {
		// TODO Auto-generated method stub
		int precioBase = base.getprecio();
		int precioProducto = 0;
		for(int i=0;i<agregado.size();i++) {
			precioProducto =precioProducto +agregado.get(i).getCostoAdicional();
			
		}
		
		return  precioBase+precioProducto;
	}

	@Override
	public String getnombre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generarTextoFactura() {
		String factura=base.generarTextoFactura();
		if(sinIngredientesAgregados()==false) {
			factura=factura+"\n"+"Agregados";
			for (int i=0;i<agregado.size();i++) {
				factura=factura+"\n"+ agregado.get(i).getNombre()+" " +agregado.get(i).getCostoAdicional();			}
		}if(sinIngredientesQuitados()==false) {
			factura=factura+"\n"+"Eliminados";
			for (int i=0;i<eliminado.size();i++) {
				factura=factura+"\n"+ eliminado.get(i).getNombre();
				}
			
		}
		return factura;
	}
}

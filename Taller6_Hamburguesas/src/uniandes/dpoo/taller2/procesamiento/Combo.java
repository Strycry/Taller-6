package uniandes.dpoo.taller2.procesamiento;

import java.util.ArrayList;

public class Combo implements Producto {
	
	private double descuento;
	private String nombreCombo ;
	private ArrayList<Producto> itemsCombo;
	
	public Combo(double descuento, String nombreCombo) {
		this.descuento = descuento;
		this.nombreCombo = nombreCombo;
		this.itemsCombo = new ArrayList<Producto>();
	}
	
	
	public void agregarItemACombo(Producto itemCombo) {
		
		itemsCombo.add(itemCombo);
	}
	
	public ArrayList<Producto> getItemsCombo() {
		return itemsCombo;
	}

	@Override
	public int getprecio() {
		int precio =0;
		for (int i=0;i<itemsCombo.size();i++) {
			 precio = precio + itemsCombo.get(i).getprecio();	
		}
		int conDescuento = precio*(int)descuento/100;
		return precio - conDescuento ;
	}
		

	@Override
	public String getnombre() {
		// TODO Auto-generated method stub
		return nombreCombo;
	}

	@Override
	public String generarTextoFactura() {
		String factura = "Producto "+nombreCombo + " "+getprecio();
		return factura;
	}

	
	
	
	
}

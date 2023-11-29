package uniandes.dpoo.taller2.procesamiento;

public class ProductoMenu implements Producto {
	
	private String nombre;
	private int precioBase;
	
	public ProductoMenu(String nombre, int precioBase) {

		this.nombre = nombre;
		this.precioBase = precioBase;
	}

	@Override
	public int getprecio() {
		return precioBase;
	}

	@Override
	public String getnombre() {
		return nombre;
	}

	@Override
	public String generarTextoFactura() {
		String factura = "Producto "+nombre +" "+ precioBase;
		return factura;
	}
	
	
}

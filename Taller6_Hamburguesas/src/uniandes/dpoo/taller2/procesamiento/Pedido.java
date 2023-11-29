package uniandes.dpoo.taller2.procesamiento;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.crypto.interfaces.DHPrivateKey;

import uniandes.dpoo.taller2.pruebas.IngredienteRepetidoException;
import uniandes.dpoo.taller2.pruebas.PrecioExcedidoException;


public class Pedido {

	private int numeroPedidos ;
	private int idPedido;
	private String nombreCliente ;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;
	private int iva =19;
	
	
	public Pedido(String nombreCliente, String direccionCliente) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.itemsPedido = new ArrayList<Producto>();
		
	}

	public int getIdPedido() {
		return idPedido+1;
	}
	
	public ArrayList<Producto> getItemsPedido() {
		return itemsPedido;
	}

	public void agregarProducto(Producto nuevoitem) throws PrecioExcedidoException {
	    int valor = getPrecioNetoPedido();
	    if (valor + nuevoitem.getprecio() > 150000) {
	        throw new PrecioExcedidoException("Límite de precio excedido al agregar el ultimo producto");
	    } else {
	        itemsPedido.add(nuevoitem);
	    }
	}
	
	public void guardarFactura(File archivo) {
		try {
			archivo.createNewFile()	;
			FileWriter fWriter = new FileWriter(archivo);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			bWriter.write(generarTextoFactura());
			bWriter.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public String mostrarFactura() {
		return generarTextoFactura();
	}
	
	private int getPrecioNetoPedido() {
		int precio =0;
		for(int i =0;i<itemsPedido.size();i++) {
			precio += itemsPedido.get(i).getprecio();
		}
		return precio ;
		
	}
	
	private int getPrecioTotalPedido() {
		return getPrecioNetoPedido()+ getPrecioIvaPedido();	}
	
	private int getPrecioIvaPedido() {
		int precioIva = (getPrecioNetoPedido()*iva)/100;
		return precioIva;
		
	}
	
	public String generarTextoFactura() {
		String facturaString ="Id pedido: "+idPedido+"\n";
		facturaString = facturaString + "Nombre cliente: "+ nombreCliente+"\n";
		facturaString = facturaString + "Direccion cliente: "+ direccionCliente+"\n";
		facturaString = facturaString + "Items del pedido: "+"\n" ;
		for(int i=0;i<itemsPedido.size();i++) {
			
			facturaString =facturaString+"\n"+itemsPedido.get(i).generarTextoFactura();
		}
		facturaString = facturaString+"\n"+"Precio neto: "+getPrecioNetoPedido();
		facturaString = facturaString+"\n"+"Precio iva pedido : "+getPrecioIvaPedido();
		facturaString = facturaString+"\n"+"Precio total : "+getPrecioTotalPedido();
		return facturaString;
	}
	
	
}

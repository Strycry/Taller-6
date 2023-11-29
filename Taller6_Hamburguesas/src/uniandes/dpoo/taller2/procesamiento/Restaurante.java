package uniandes.dpoo.taller2.procesamiento;
import java.util.ArrayList;

import uniandes.dpoo.taller2.pruebas.IngredienteRepetidoException;
import uniandes.dpoo.taller2.pruebas.ProductoRepetidoException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Restaurante {
	
	private  ArrayList<Ingrediente> ingredientes;
	private  ArrayList<Pedido> pedidos;
	private Pedido pedidoEnCurso;
	private ArrayList<Producto> menuBase;
	private ArrayList<Combo> combos;

	
	public Restaurante() {
		this.pedidos = new ArrayList<>();
        this.menuBase = new ArrayList<>();
        this.ingredientes = new ArrayList<>();
        this.combos = new ArrayList<>();
	}
	
	public Restaurante(ArrayList<Ingrediente> ingredientes,ArrayList<Producto> menuBase,ArrayList<Combo> combos) {
		this.combos=combos;
		this.pedidos= new ArrayList<Pedido>();
		this.ingredientes=ingredientes;
		this.menuBase=menuBase;
		
	}
	
	public void iniciarPedido(String nombreCliente,String direccionCliente) {
		 pedidoEnCurso = new Pedido(direccionCliente,nombreCliente);	
	}
	public void cerrarYGuardarPedido() {
        if (pedidoEnCurso != null) {
            pedidos.add(pedidoEnCurso);
            File file = new File("./data/factura.txt");
            pedidoEnCurso.guardarFactura(file);
            pedidoEnCurso = null; // Indicar que no hay pedido en curso después de cerrar y guardar
        } else {
            System.out.println("No hay pedido en curso para cerrar y guardar.");
        }
    }
	
	public Pedido getPedidoEnCurso() {
		return pedidoEnCurso;
		
	}
	
	public ArrayList<Producto> getMenuBase(){
		return menuBase;
	}
	
	public ArrayList<Ingrediente> getIngredientes(){
		return ingredientes;
	}
	
	public ArrayList<Combo> getCombos(){
		return combos;
	}
	
	public Restaurante cargarInformacion(File archivoIngredientes, File archivoMenu, File archivoCombos) {
	    try {
	        cargarMenu(archivoMenu);
	        cargarCombos(archivoCombos);
	        cargarIngredientes(archivoIngredientes);
	        System.out.println("Información del restaurante cargada correctamente.");
	    } catch (ProductoRepetidoException e) {
	        System.out.println("Error al cargar información: " + e.getMessage());
	    } catch (IngredienteRepetidoException e) {
	        System.out.println("Error al cargar información: " + e.getMessage());
	    }

	    return this;
	}

	
	private void cargarCombos(File archivoCombos) {
		try
		{
			combos = LoaderRestaurante.leerInfoArchivoCombos(archivoCombos,menuBase);
			System.out.println("Se cargó Combos ");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo indicado no se encontró.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
	}
	
	private void cargarMenu(File archivoMenu) throws ProductoRepetidoException {
	    try {
	        menuBase = LoaderRestaurante.leerInfoArchivoProductosMenu(archivoMenu);
	        System.out.println("Se cargó el menú");
	    } catch (FileNotFoundException e) {
	        System.out.println("ERROR: el archivo indicado no se encontró.");
	    } catch (IOException e) {
	        System.out.println("ERROR: hubo un problema leyendo el archivo.");
	        System.out.println(e.getMessage());
	    }

	    // Verificar duplicados en el menú
	    for (int i = 0; i < menuBase.size(); i++) {
	        for (int j = i + 1; j < menuBase.size(); j++) {
	            if (menuBase.get(i).getnombre().equals(menuBase.get(j).getnombre())) {
	                throw new ProductoRepetidoException("Producto repetido en el menú: " + menuBase.get(i).getnombre());
	            }
	        }
	    }
	}

	private void cargarIngredientes(File archivoIngredientes) throws IngredienteRepetidoException {
	    try {
	        ingredientes = LoaderRestaurante.leerInfoArchivoIngredientes(archivoIngredientes);
	        System.out.println("Se cargaron los ingredientes");
	    } catch (FileNotFoundException e) {
	        System.out.println("ERROR: el archivo indicado no se encontró.");
	    } catch (IOException e) {
	        System.out.println("ERROR: hubo un problema leyendo el archivo.");
	        System.out.println(e.getMessage());
	    }

	    // Verificar duplicados en los ingredientes
	    for (int i = 0; i < ingredientes.size(); i++) {
	        for (int j = i + 1; j < ingredientes.size(); j++) {
	            if (ingredientes.get(i).getNombre().equals(ingredientes.get(j).getNombre())) {
	                throw new IngredienteRepetidoException("Ingrediente repetido: " + ingredientes.get(i).getNombre());
	            }
	        }
	    }
	}

	
	
	
	}


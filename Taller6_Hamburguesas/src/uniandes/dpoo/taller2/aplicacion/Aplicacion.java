package uniandes.dpoo.taller2.aplicacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Scanner;

import uniandes.dpoo.taller2.procesamiento.Pedido;
import uniandes.dpoo.taller2.procesamiento.Producto;
import uniandes.dpoo.taller2.procesamiento.ProductoAjustado;
import uniandes.dpoo.taller2.procesamiento.ProductoMenu;
import uniandes.dpoo.taller2.procesamiento.Restaurante;
import uniandes.dpoo.taller2.pruebas.PrecioExcedidoException;

public class Aplicacion {
	
	private Restaurante restaurante=new Restaurante();
	Scanner sc=new Scanner(System.in);
	
	
	public void ejecutarAplicacion() {
		cargarDatos();
		System.out.println("Bienvenido al corral\n");
		boolean continuar = true;
		while (continuar) {
			try {
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					mostrarElMenu();
				else if (opcion_seleccionada == 2) 
					iniciarpedido();
				else if (opcion_seleccionada == 3) 
					agregarElementoPedido();
				else if (opcion_seleccionada == 4) 
					cerrarPedido();
				else if (opcion_seleccionada == 5) 
					informacionPedidoId();
				else if (opcion_seleccionada ==6)
					continuar = false;
				
			}
			catch(NumberFormatException e){
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}
	
	public static void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Mostrar el menu");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Cerrar un pedido y guardar la factura");
		System.out.println("5.Consultar la información de un pedido dado su id");
		System.out.println("6. Salir de la aplicación\n");
	}
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}	
	
	private void cargarDatos(){
		File  fileIng= new File("./data/ingredientes.txt\\");
		File  fileCombo= new File("./data/combos.txt\\");
		File fileMenu = new File ("./data/menu.txt\\");
		restaurante=restaurante.cargarInformacion(fileIng,fileMenu ,fileCombo);
		System.out.println(restaurante.getMenuBase());
	}
	
	private void mostrarElMenu() {
		for (int i=0;i<restaurante.getMenuBase().size();i++) {
		      int precio =restaurante.getMenuBase().get(i).getprecio();
		      System.out.println(restaurante.getMenuBase().get(i).getnombre()+" "+precio);
		    }
		System.out.println("----Combos----");
		for (int z=0;z<restaurante.getCombos().size();z++) 
		{
			      int precio =restaurante.getCombos().get(z).getprecio();
			      System.out.println(z+restaurante.getMenuBase().size()+1+"."+restaurante.getCombos().get(z).getnombre()+" "+precio);
		}
	}
	
	private void iniciarpedido() {
		String nombreCliente=input("Ingrese su nombre");
		String direccionCliente=input("Ingrese su direccion");
		restaurante.iniciarPedido(nombreCliente, direccionCliente);
		System.out.println("Pedido iniciado");
		
	}
	
	private void agregarElementoPedido() {
	    boolean continua = true;
	    Pedido pedido = restaurante.getPedidoEnCurso();
	    while (continua) {
	        for (int i = 0; i < restaurante.getMenuBase().size(); i++) {
	            int precio = restaurante.getMenuBase().get(i).getprecio();
	            System.out.println(i + 1 + "." + restaurante.getMenuBase().get(i).getnombre() + " " + precio);
	        }
	        System.out.println("----Combos----");
	        for (int z = 0; z < restaurante.getCombos().size(); z++) {
	            int precio = restaurante.getCombos().get(z).getprecio();
	            System.out.println(z + restaurante.getMenuBase().size() + 1 + "." + restaurante.getCombos().get(z).getnombre() + " " + precio);
	        }

	        int opcionProducto = Integer.parseInt(input("Ingrese el número del producto que desea"));
	        System.out.println("Desea agregar un ingrediente?");
	        System.out.println("No Agregar(0)");
	        System.out.println("Agregar(1)");
	        String opcionIngrediente = sc.nextLine();
	        Producto product;

	        if (opcionProducto <= restaurante.getMenuBase().size()) {
	            product = restaurante.getMenuBase().get(opcionProducto - 1);
	        } else {
	            product = restaurante.getCombos().get(opcionProducto - restaurante.getMenuBase().size() - 1);
	        }

	        ProductoAjustado productoFinal = new ProductoAjustado(product);
	        productoFinal.getprecio();

	        try {
	            pedido.agregarProducto(productoFinal);

	            if (opcionIngrediente.equals("1")) {
	                boolean empezarCiclo = true;
	                while (empezarCiclo) {
	                    System.out.println("---Ingredientes---");
	                    for (int x = 0; x < restaurante.getIngredientes().size(); x++) {
	                        int precioIngrediente = restaurante.getIngredientes().get(x).getCostoAdicional();
	                        System.out.println(x + 1 + "." + restaurante.getIngredientes().get(x).getNombre() + " " + precioIngrediente);
	                    }

	                    opcionProducto = Integer.parseInt(input("Ingrese el número del ingrediente que desea agregar"));
	                    productoFinal.añadirIngrediente(restaurante.getIngredientes().get(opcionProducto - 1));
	                    String mas = input("Desea agregar algún ingrediente más?\nNo Agregar(0)\nAgregar(1)");

	                    if (mas.equals("0")) {
	                        empezarCiclo = false;
	                        String remover = input("Desea remover algún ingrediente?\nNo (0)\nSí(1)");

	                        if (remover.equals("1")) {
	                            boolean cicloRemover = true;
	                            while (cicloRemover) {
	                                for (int x = 0; x < restaurante.getIngredientes().size(); x++) {
	                                    int precioIngrediente = restaurante.getIngredientes().get(x).getCostoAdicional();
	                                    System.out.println(x + 1 + "." + restaurante.getIngredientes().get(x).getNombre() + " " + precioIngrediente);
	                                }

	                                opcionProducto = Integer.parseInt(input("Ingrese el número del ingrediente que desea remover"));
	                                productoFinal.eliminarIngrediente(restaurante.getIngredientes().get(opcionProducto - 1));
	                                mas = input("Desea eliminar algún ingrediente más?\nNo (0)\nSí(1)");

	                                if (mas.equals("0")) {
	                                    cicloRemover = false;
	                                    String acabarPedido = input("Desea algún producto más?\nNo (0)\nSí(1)");

	                                    if (acabarPedido.equals("0")) {
	                                        continua = false;
	                                    }
	                                }
	                            }
	                        } else {
	                            String acabarPedido = input("Desea algún producto más?\nNo (0)\nSí(1)");

	                            if (acabarPedido.equals("0")) {
	                                continua = false;
	                            }
	                        }
	                    }
	                }
	            } else {
	                String remover = input("Desea remover algún ingrediente?\nNo (0)\nSí(1)");

	                if (remover.equals("0")) {
	                    String preguntarOtroProducto = input("Desea otro producto?\nNo (0)\nSí(1)");

	                    if (preguntarOtroProducto.equals("0")) {
	                        continua = false;
	                    }
	                } else {
	                    boolean cicloRemover = true;
	                    while (cicloRemover) {
	                        for (int x = 0; x < restaurante.getIngredientes().size(); x++) {
	                            int precioIngrediente = restaurante.getIngredientes().get(x).getCostoAdicional();
	                            System.out.println(x + 1 + "." + restaurante.getIngredientes().get(x).getNombre() + " " + precioIngrediente);
	                        }

	                        opcionProducto = Integer.parseInt(input("Ingrese el número del ingrediente que desea remover"));
	                        productoFinal.eliminarIngrediente(restaurante.getIngredientes().get(opcionProducto - 1));
	                        String mas = input("Desea eliminar algún ingrediente más?\nNo (0)\nSí(1)");

	                        if (mas.equals("0")) {
	                            cicloRemover = false;
	                        }
	                    }
	                }
	            }
	        } catch (PrecioExcedidoException e) {
	            System.out.println("Error: " + e.getMessage());
	            break;
	        }
	    }
	}

	
	private void cerrarPedido() {
		Pedido pedido =restaurante.getPedidoEnCurso();
		restaurante.cerrarYGuardarPedido();
		System.out.println(pedido.mostrarFactura());
		
		
	
	}
	
	private void informacionPedidoId() {
		
	}
	
	public static void main(String[] args)
	{
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();
	}

}

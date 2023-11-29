package uniandes.dpoo.taller2.procesamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoaderRestaurante {
	
	public static ArrayList<Producto> leerInfoArchivoProductosMenu(File rutaArchivo) throws FileNotFoundException, IOException
	{
		
		ArrayList<Producto> productosMenu = new ArrayList<Producto>();
		
		BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
		String linea = br.readLine();  
		
		while (linea != null) 
		{
			
			String[] partes = linea.split(";");
			
			ProductoMenu nuevoProducto = new ProductoMenu(partes[0], Integer.parseInt(partes[1]));   
			productosMenu.add( nuevoProducto );

			linea = br.readLine(); 
		}
		br.close();
		return productosMenu;
	}
	public static ArrayList<Combo> leerInfoArchivoCombos(File rutaArchivo ,ArrayList<Producto> produc ) throws FileNotFoundException, IOException
	{
		
		ArrayList<Combo> combos = new ArrayList<Combo>();
		BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
		String linea = br.readLine();    
		while (linea != null) 
		{
			String[] partes = linea.split(";");
			double porcentaje = Double.parseDouble( partes[1].substring(0, partes[1].length()-1)); 

			Combo nuevoCombo = new Combo(porcentaje, partes[0]);  
			
			for (int i = 2; i < partes.length; i++)
			{
				for (int z=0;z<produc.size();z++) {
					if(partes[i].equals(produc.get(z).getnombre())) {
						int precio =produc.get(z).getprecio();
						ProductoMenu productoNew= new ProductoMenu(partes[i],precio);
						nuevoCombo.agregarItemACombo(productoNew);
						break;
						}	
				    }
				  
			}                                           

			combos.add(nuevoCombo);

			linea = br.readLine(); 
		}
		br.close();
		return combos;
	}
	
	public static ArrayList<Ingrediente> leerInfoArchivoIngredientes(File rutaArchivo)throws FileNotFoundException, IOException{
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
		String linea = br.readLine(); 
		while (linea != null) {
			String[] partes=linea.split(";");
			String nombre=partes[0];
			int costoAdicional=Integer.parseInt(partes[1]);
			Ingrediente ingrediente = new Ingrediente(nombre,costoAdicional);
			ingredientes.add( ingrediente );
			linea=br.readLine();
		}
		br.close();
		return ingredientes;
		
	}
}

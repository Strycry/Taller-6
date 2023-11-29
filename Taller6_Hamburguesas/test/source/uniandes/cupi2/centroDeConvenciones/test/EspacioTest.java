/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_centroDeConvenciones
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.centroDeConvenciones.test;

import uniandes.cupi2.centroDeConvenciones.mundo.Espacio;
import uniandes.cupi2.centroDeConvenciones.mundo.EspacioOcupadoException;
import uniandes.cupi2.centroDeConvenciones.mundo.Fecha;
import junit.framework.TestCase;

/**
 * Clase usada para verificar que los métodos de la clase Espacio están correctamente implementados.
 */
public class EspacioTest extends TestCase
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Espacio donde se harán las pruebas.
     */
    private Espacio espacio;

    // -------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------

    /**
     * Escenario 1: Construye un espacio sin eventos.
     */
    public void setupEscenario1( )
    {
        espacio = new Espacio( "Super auditorio", Espacio.TIPO_AUDITORIO, true, "Ruta", 300, 350000, 10, "Lina" );
    }

    /**
     * Escenario 2: Construye un espacio con un evento.
     */
    public void setupEscenario2( )
    {
        Fecha fecha = new Fecha( 2017, 10, 12, 8, 10 );
        espacio = new Espacio( "Prueba", Espacio.TIPO_SALA_COMPUTO, true, "ruta", 20, 350000, 10, "Lina" );
        try
        {
            espacio.agregarEvento( "Taller", "descripcion", 18, fecha, "imagen" );
        }
        catch( EspacioOcupadoException e )
        {
            fail( "No debería lanzar excepción." );
        }
    }

    /**
     * Prueba 1: Prueba el método constructor de la clase Espacio. <br>
     * <b>Métodos a probar:</b> <br>
     * Espacio<br>
     * darNombre<br>
     * darTipo<br>
     * tieneIntenet<br>
     * darRutaFoto<br>
     * darCapacidad<br>
     * darEventos<br>
     * <b> Casos de prueba: </b><br>
     * 1. El espacio fue creada y los valores de los atributos corresponden.
     */
    public void testEspacio( )
    {
        setupEscenario1( );
        assertEquals( "El nombre no es el esperado.", "Super auditorio", espacio.darNombre( ) );
        assertEquals( "El tipo no es el esperado.", Espacio.TIPO_AUDITORIO, espacio.darTipo( ) );
        assertTrue( "Debería tener internet.", espacio.tieneInternet( ) );
        assertEquals( "La ruta no es la esperada.", "Ruta", espacio.darRutaFoto( ) );
        assertEquals( "La capacidad del espacio no es el esperado.", 300, espacio.darCapacidad( ) );
        assertNotNull( "Debería tener una lista de eventos.", espacio.darEventos( ) );
        assertEquals( "La lista de eventos debería estar vacía.", 0, espacio.darEventos( ).size( ) );
    }

    /**
     * Prueba 2: Prueba el método agregarEvento de la clase Espacio. <br>
     * <b>Métodos a probar:</b> <br>
     * agregarEvento<br>
     * darEventos<br>
     * <b> Casos de prueba: </b><br>
     * 1. No existe ningún evento. <br>
     * 2. Existen eventos pero el evento a agregar no se cruza con ninguno evento existente. <br>
     * 3. Existen eventos y uno de los eventos tiene la misma fecha del evento a agregar. <br>
     * 4. Existen eventos y uno de los eventos se cruza con evento a agregar.
     */
    public void testAgregarEvento( )
    {
        setupEscenario1( );
        Fecha fecha = new Fecha( 2017, 12, 20, 8, 16 );

        // Caso de prueba 1
        try
        {
            espacio.agregarEvento( "Taller cupi2", "Taller sobre ", 30, fecha, "ruta" );
        }
        catch( EspacioOcupadoException e )
        {
            fail( "No debería lanzar excepción." );
        }

        // Caso de prueba 2
        Fecha fecha2 = new Fecha( 2017, 2, 2, 10, 12 );
        try
        {
            espacio.agregarEvento( "Tutoría apo2", "Tutorías de apo1 para estudiantes de apo2. ", 25, fecha2, "ruta2" );
            assertEquals( "Debería haber un evento adicional.", 2, espacio.darEventos( ).size( ) );
        }
        catch( EspacioOcupadoException e )
        {
            fail( "No debería lanzar excepción." );
        }

        fecha2 = new Fecha( 2017, 2, 2, 8, 9 );
        try
        {
            espacio.agregarEvento( "Charla cupi2", "Charla de inducción. ", 120, fecha2, "ruta3" );
            assertEquals( "Debería haber un evento adicional.", 3, espacio.darEventos( ).size( ) );
        }
        catch( EspacioOcupadoException e )
        {
            fail( "No debería lanzar excepción." );
        }

        // Caso de prueba 3
        try
        {
            espacio.agregarEvento( "Presentación", "Presentación sobre ...", 30, fecha, "ruta4" );
            fail( "Debería lanzar excepción" );
        }
        catch( EspacioOcupadoException e )
        {
            assertEquals( "No debería haber cambiado la cantidad de eventos.", 3, espacio.darEventos( ).size( ) );
        }

        // Caso de prueba 4
        fecha2 = new Fecha( 2017, 2, 2, 9, 11 );
        try
        {
            espacio.agregarEvento( "Clase Apo", "Clase", 24, fecha2, "ruta5" );
            fail( "Debería lanzar excepción" );
        }
        catch( EspacioOcupadoException e )
        {
            assertEquals( "No debería haber cambiado la cantidad de eventos.", 3, espacio.darEventos( ).size( ) );
        }

    }

    /**
     * Prueba 3: Prueba el método compararPorNombre de la clase Espacio. <br>
     * <b>Métodos a probar:</b> <br>
     * compararPorNombre<br>
     * <b> Casos de prueba: </b><br>
     * 1. Los dos espacios tienen el mismo nombre.<br>
     * 2. El espacio contra el cual se compara tiene un nombre lexicográficamente mayor. <br>
     * 3. El espacio contra el cual se compara tiene un nombre lexicográficamente menor. <br>
     */
    public void testCompararPorNombre( )
    {
        setupEscenario1( );

        // Caso de prueba 1
        Espacio espacio2 = new Espacio( "Super auditorio", Espacio.TIPO_SALA_COMPUTO, true, "ruta", 20, 350000, 10, "Lina" );
        int respuesta = espacio.compararPorNombre( espacio2 );
        assertEquals( "Los dos espacios tienen el mismo nombre.", 0, respuesta );

        // Caso de prueba 2
        Espacio espacio3 = new Espacio( "Super auditorio 1", Espacio.TIPO_SALA_COMPUTO, true, "ruta", 20, 350000, 10, "Lina" );
        respuesta = espacio.compararPorNombre( espacio3 );
        assertEquals( "El segundo espacio tiene un nombre lexicográficamente mayor.", -1, respuesta );

        // Caso de prueba 3
        Espacio espacio4 = new Espacio( "1 Super", Espacio.TIPO_SALA_COMPUTO, true, "ruta", 20, 350000, 10, "Lina" );
        respuesta = espacio.compararPorNombre( espacio4 );
        assertEquals( "El segundo espacio tiene un nombre lexicográficamente menor.", 1, respuesta );
    }

    /**
     * Prueba 4: Prueba el método compararPorCapacidad de la clase Espacio. <br>
     * <b>Métodos a probar:</b> <br>
     * Espacio<br>
     * compararPorCapacidad<br>
     * <b> Casos de prueba: </b><br>
     * 1. Los dos espacios tienen la misma capacidad. <br>
     * 2. El espacio contra el cual se compara tiene una capacidad mayor. <br>
     * 3. El espacio contra el cual se compara tiene una capacidad menor. <br>
     */
    public void testCompararPorCapacidad( )
    {
        setupEscenario1( );

        // Caso de prueba 1
        Espacio espacio2 = new Espacio( "Prueba", Espacio.TIPO_SALA_COMPUTO, true, "ruta", 300, 350000, 10, "Lina" );
        int respuesta = espacio.compararPorCapacidad( espacio2 );
        assertEquals( "Los dos espacios tienen la misma capacidad.", 0, respuesta );

        // Caso de prueba 2
        Espacio espacio3 = new Espacio( "Prueba", Espacio.TIPO_SALA_COMPUTO, true, "ruta", 400, 350000, 10, "Lina" );
        respuesta = espacio.compararPorCapacidad( espacio3 );
        assertEquals( "El segundo espacio tiene una capacidad mayor.", -1, respuesta );

        // Caso de prueba 3
        Espacio espacio4 = new Espacio( "Prueba", Espacio.TIPO_SALA_COMPUTO, true, "ruta", 10, 350000, 10, "Lina" );
        respuesta = espacio.compararPorCapacidad( espacio4 );
        assertEquals( "El segundo espacio tiene una capacidad menor.", 1, respuesta );
    }

    /**
     * Prueba 5: Prueba el método compararPorCantidadEventos de la clase Espacio. <br>
     * <b>Métodos a probar:</b> <br>
     * compararPorCantidadEventos<br>
     * <b> Casos de prueba: </b><br>
     * 1. Los dos espacios tienen la misma cantidad de eventos. <br>
     * 2. El espacio contra el cual se compara tiene una mayor cantidad de eventos. <br>
     * 3. El espacio contra el cual se compara tiene una menor cantidad de eventos.<br>
     */
    public void testCompararPorCantidadEventos( )
    {
        setupEscenario1( );

        Fecha fecha = new Fecha( 2017, 05, 15, 8, 10 );

        // Caso de prueba 1
        Espacio espacio2 = new Espacio( "Prueba", Espacio.TIPO_SALA_COMPUTO, true, "ruta", 100, 350000, 10, "Lina" );
        int respuesta = espacio.compararPorCantidadEventos( espacio2 );
        assertEquals( "Los dos espacios tienen la misma cantidad de eventos.", 0, respuesta );

        // Caso de prueba 2
        try
        {
            espacio2.agregarEvento( "Prueba", "descripcion", 100, fecha, "ruta" );
        }
        catch( EspacioOcupadoException e )
        {
            fail( "No debería lanzar excepción." );
        }
        respuesta = espacio.compararPorCantidadEventos( espacio2 );
        assertEquals( "El segundo espacio tiene una cantidad de eventos mayor.", -1, respuesta );

        // Caso de prueba 3
        Fecha fecha2 = new Fecha( 2017, 05, 15, 10, 12 );
        try
        {
            espacio.agregarEvento( "Prueba1", "descripcion", 100, fecha, "ruta" );
            espacio.agregarEvento( "Prueba2", "descripcion", 100, fecha2, "ruta" );
        }
        catch( EspacioOcupadoException e )
        {
            fail( "No debería lanzar excepción." );
        }

        respuesta = espacio.compararPorCantidadEventos( espacio2 );
        assertEquals( "El segundo espacio tiene una cantidad de eventos menor.", 1, respuesta );
    }

    /**
     * Prueba 6: Verifica que se busque correctamente un evento en la lista de eventos del espacio. <br>
     * <b>Métodos a probar:</b> <br>
     * tieneEvento<br>
     * 1. El evento a buscar existe en el espacio. <br>
     * 2. El evento a buscar no existe en el espacio. <br>
     */
    public void testTieneEvento( )
    {
        setupEscenario2( );

        // Caso de prueba 1
        boolean tieneEvento = espacio.tieneEvento( "Taller" );
        assertTrue( "El evento hace parte de la lista de eventos del espacio.", tieneEvento );

        // Caso de prueba 2
        tieneEvento = espacio.tieneEvento( "Workshop" );
        assertFalse( "El evento no hace parte de la lista de eventos del espacio.", tieneEvento );

    }

    /**
     * Prueba 7: Prueba el método buscarEventoFecha de la clase Espacio <br>
     * <b>Métodos a probar:</b> <br>
     * buscarEventoFecha<br>
     * 1. El espacio no tiene eventos. <br>
     * 1. El espacio tiene un evento que se cruza con la fecha buscada. <br>
     * 2. El espacio tiene eventos pero no tiene ningún evento que se cruce con la fecha buscada. <br>
     */
    public void testBuscarEventoFecha( )
    {
        setupEscenario1( );

        // Caso de prueba 1
        Fecha fecha = new Fecha( 2017, 10, 12, 8, 10 );
        boolean tieneEventoFecha = espacio.buscarEventoFecha( fecha );
        assertFalse( "El espacio no tiene un evento en la fecha dada.", tieneEventoFecha );

        // Caso de prueba 2
        try
        {
            espacio.agregarEvento( "Taller", "descripcion", 18, fecha, "imagen" );
        }
        catch( EspacioOcupadoException e )
        {
            fail( "No debería lanzar excepción." );
        }

        tieneEventoFecha = espacio.buscarEventoFecha( fecha );
        assertTrue( "El espacio tiene un evento en la fecha dada.", tieneEventoFecha );

        // Caso de prueba 3
        Fecha fecha2 = new Fecha( 2017, 11, 22, 8, 10 );
        tieneEventoFecha = espacio.buscarEventoFecha( fecha2 );
        assertFalse( "El espacio no tiene un evento en la fecha dada.", tieneEventoFecha );

    }
}

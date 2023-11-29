package uniandes.cupi2.CentroConvenciones.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import uniandes.cupi2.centroDeConvenciones.mundo.CentroDeConvenciones;
import uniandes.cupi2.centroDeConvenciones.mundo.Espacio;
import uniandes.cupi2.centroDeConvenciones.mundo.EspacioOcupadoException;


/**
 * Clase usada para verificar que los metodos de la clase CentroDeConvenciones estï¿½n correctamente implementados.
*/
public class CentroConvencionesTest
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la carga de datos.
     */
    public final static String PRUEBA = "./test/data/espacios.txt";

    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Centro de convenciones donde se harï¿½n las pruebas.
     */
    private CentroDeConvenciones centroConvenciones;

    // -------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------

    /**
     * Escenario de base: Construye un centro de convenciones sin espacios.
     */

    @Before
    public void setUp( ) throws Exception
    {
        try
        {
            centroConvenciones = new CentroDeConvenciones( "./otro.data" );
        }
        catch( Exception e )
        {
            fail( "No deberia lanzar excepcion.-" + e.getMessage( ) );
        }
    }

    public void iniciarCentroEspaciosBasicos()
    {
        boolean espacioAgregado = centroConvenciones.agregarEspacio( "Auditorio central", Espacio.TIPO_AUDITORIO, true, "Ruta Imagen", 450, 500000, 70, "Juan" );
        assertTrue( "Error al agregar el espacio", espacioAgregado );

        espacioAgregado = centroConvenciones.agregarEspacio( "Campo verde", Espacio.TIPO_AIRE_LIBRE, false, "Ruta Imagen", 1000, 2000000, 500, "Maria" );
        assertTrue( "Error al agregar el espacio", espacioAgregado );
        
        ArrayList<Espacio> espacios = centroConvenciones.darEspacios( );
        assertEquals( "El espacio no se agrego la lista de espacios", 2, espacios.size( ) );

    }
    
    /**
     * Prueba 1: Prueba el metodo constructor de la clase CentroDeConvenciones. <br>
     * <b>Metodos a probar:</b> <br>
     * CentroDeConvenciones<br>
     * darEspacios<br>
     * <b> Casos de prueba: </b><br>
     * 1. El centro de convenciones fue creado y los valores de los atributos corresponden.
     */
    @Test
    public void testCentroEspaciosVacios( )
    {
        assertNotNull( "La lista debio haberse inicializado.", centroConvenciones.darEspacios( ) );
        assertEquals( "La lista de espacios debería tener 0 espacios", 0, centroConvenciones.darEspacios( ).size( ) );
    }

    /**
     * Prueba 3: Prueba el metodo agregarEspacio de la clase CentroDeConvenciones. <br>
     * <b>Metodos a probar:</b> <br>
     * agregarEspacio<br>
     * darEspacios<br>
     * <b> Casos de prueba: </b><br>
     * 1. El centro de convenciones No tiene tiene espacios.
     */
    @Test
    public void testAgregarEspacio1( )
    {
        // Verificar que se haya ingresado el espacio
        boolean espacioAgregada = centroConvenciones.agregarEspacio( "Gran auditorio", Espacio.TIPO_AUDITORIO, true, "Ruta1", 350, 250000, 10, "Juan" );
        assertTrue( "Error al agregar el espacio", espacioAgregada );

        ArrayList<Espacio> espacios = centroConvenciones.darEspacios( );
        assertEquals( "El espacio no se agrego la lista de espacios", 1, espacios.size( ) );

        // Datos del espacio que se agrego en la lista
        Espacio espacio = espacios.get( 0 );
        assertEquals( "El nombre del espacio no es el esperado.", "Gran auditorio", espacio.darNombre( ) );
        assertEquals( "El tipo del espacio no es el esperado.", Espacio.TIPO_AUDITORIO, espacio.darTipo( ) );
        assertTrue( "El espacio deberï¿½a tener internet.", espacio.tieneInternet( ) );
        assertEquals( "La foto del espacio no es la esperada.", "Ruta1", espacio.darRutaFoto( ) );
        assertEquals( "La capacidad del espacio no es la esperada.", 350, espacio.darCapacidad( ) );
    }

    /**
     * Prueba 4: Prueba el metodo agregarEspacio de la clase CentroDeConvenciones. <br>
     * <b>Metodos a probar:</b> <br>
     * agregarEspacio<br>
     * <b> Casos de prueba: </b><br>
     * 1. Ya existe una espacio con el nombre dado.
     */
    @Test
    public void testAgregarEspacioError( )
    {
        iniciarCentroEspaciosBasicos();
        
        // Verificar que no se haya ingresado el espacio
        boolean espacioAgregado = centroConvenciones.agregarEspacio( "Auditorio central", Espacio.TIPO_AUDITORIO, true, "Ruta Imagen", 450, 500000, 70, "Juan" );
        assertFalse( "No debio haber registrado el espacio", espacioAgregado );
        assertEquals( "El numero de espacios del centroConvenciones no debio cambiar", 2, centroConvenciones.darEspacios( ).size( ) );
    }

    /**
     * Prueba 5: Prueba el metodo agregarEventoAEspacio de la clase CentroDeConvenciones. <br>
     * <b>Metodos a probar:</b> <br>
     * agregarEventoAEspacio<br>
     * <b> Casos de prueba: </b><br>
     * 1. Existe el espacio y No hay cruce con el nuevo evento <br>
     * 2. Existe el espacio y Ya existe un evento de cruce con el nuevo evento
     */
    @Test
    public void testAgregarEventoAEspacio1( )
    {
        iniciarCentroEspaciosBasicos();
        boolean eventoAgregado = false;

        // Caso de prueba 1
        try
        {
            eventoAgregado = centroConvenciones.agregarEventoAEspacio( "Auditorio central", "Evento 1", "Evento prueba 1", 30, 2017, 10, 21, 8, 16, "ruta imagen 1" );
            assertTrue( "Error: Debio haber registrado el evento al espacio", eventoAgregado );
        }
        catch( EspacioOcupadoException e )
        {
            // NO Deberia pasar por aqui
            fail( "No deberia lanzar excepcion." );
        }
        // Caso de prueba 2
        try
        {
            eventoAgregado = centroConvenciones.agregarEventoAEspacio( "Auditorio central", "Evento 2", "Evento prueba 2", 30, 2017, 10, 21, 10, 14, "ruta imagen 2" );
            assertFalse( "Error: NO Debio haber registrado el evento al espacio por cruce horario", eventoAgregado );
        }
        catch( EspacioOcupadoException e )
        {
            // SI Deberia pasar por aqui
            assertTrue( "Este mensaje NO se va utilizar", true );
        }
    }

    /**
     * Prueba 6: Prueba el metodo agregarEventoAEspacio de la clase CentroDeConvenciones. <br>
     * <b>Metodos a probar:</b> <br>
     * agregarEventoAEspacio<br>
     * <b> Casos de prueba: </b><br>
     * 1. NO Existe el espacio => No se puede agregar el evento <br>
     */
    @Test
    public void testAgregarEventoAEspacio2( )
    {
        iniciarCentroEspaciosBasicos();
        boolean eventoAgregado = false;

        // Caso de prueba 1
        try
        {
            eventoAgregado = centroConvenciones.agregarEventoAEspacio( "Piscina", "Evento 1", "Evento prueba 1", 30, 2017, 10, 21, 8, 16, "ruta imagen 1" );
            assertFalse( "Error: NO Debio haber registrado el evento al espacio", eventoAgregado );
        }
        catch( EspacioOcupadoException e )
        {
            // NO Deberia pasar por aqui
            fail( "No deberia lanzar excepcion." );
        }
    }
}

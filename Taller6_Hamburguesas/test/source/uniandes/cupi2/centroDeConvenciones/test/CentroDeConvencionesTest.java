/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_cupiViajes
 * Autor: Equipo Cupi2 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.centroDeConvenciones.test;

import java.util.ArrayList;

import uniandes.cupi2.centroDeConvenciones.mundo.CentroDeConvenciones;
import uniandes.cupi2.centroDeConvenciones.mundo.Espacio;
import uniandes.cupi2.centroDeConvenciones.mundo.EspacioOcupadoException;
import uniandes.cupi2.centroDeConvenciones.mundo.Fecha;
import uniandes.cupi2.centroDeConvenciones.mundo.FormatoArchivoException;
import uniandes.cupi2.centroDeConvenciones.mundo.PersistenciaException;
import junit.framework.TestCase;

/**
 * Clase usada para verificar que los m�todos de la clase CentroDeConvenciones est�n correctamente implementados.
 */
public class CentroDeConvencionesTest extends TestCase
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
     * Centro de convenciones donde se har�n las pruebas.
     */
    private CentroDeConvenciones centroConvenciones;

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Escenario 1: Construye un centro de convenciones sin espacios.
     */
    public void setupEscenario1( )
    {
        try
        {
            centroConvenciones = new CentroDeConvenciones( "./otro.data" );
        }
        catch( Exception e )
        {
            fail( "No deber�a lanzar excepci�n.-" + e.getMessage( ) );
        }

    }

    /**
     * Prueba 1: Prueba el m�todo constructor de la clase CentroDeConvenciones. <br>
     * <b>M�todos a probar:</b> <br>
     * CentroDeConvenciones<br>
     * darEspacios<br>
     * <b> Casos de prueba: </b><br>
     * 1. El centro de convenciones fue creado y los valores de los atributos corresponden.
     */
    public void testEspacio( )
    {
        setupEscenario1( );
        assertNotNull( "La lista debio haberse inicializado.", centroConvenciones.darEspacios( ) );
        assertEquals( "La lista de espacios deber�a tener 8.", 8, centroConvenciones.darEspacios( ).size( ) );
    }

    /**
     * Prueba 3: Prueba el m�todo agregarEspacio de la clase CentroDeConvenciones. <br>
     * <b>M�todos a probar:</b> <br>
     * agregarEspacio<br>
     * darEspacios<br>
     * <b> Casos de prueba: </b><br>
     * 1. El centro de convenciones ya tiene espacios.
     */
    public void testAgregarEspacio1( )
    {
        setupEscenario1( );

        // Verificar que se haya ingresado el espacio
        boolean espacioAgregada = centroConvenciones.agregarEspacio( "Gran auditorio", Espacio.TIPO_AUDITORIO, true, "Ruta1", 350, 250000, 10, "Juan" );
        assertTrue( "Error al agregar el espacio", espacioAgregada );

        ArrayList espacios = centroConvenciones.darEspacios( );
        assertEquals( "El espacio no se agreg� a la lista de espacios", 9, espacios.size( ) );

        // Datos del espacio que se agreg� en la lista
        Espacio espacio = ( Espacio )espacios.get( 8 );
        assertEquals( "El nombre del espacio no es el esperado.", "Gran auditorio", espacio.darNombre( ) );
        assertEquals( "El tipo del espacio no es el esperado.", Espacio.TIPO_AUDITORIO, espacio.darTipo( ) );
        assertTrue( "El espacio deber�a tener internet.", espacio.tieneInternet( ) );
        assertEquals( "La foto del espacio no es la esperada.", "Ruta1", espacio.darRutaFoto( ) );
        assertEquals( "La capacidad del espacio no es la esperada.", 350, espacio.darCapacidad( ) );
    }

    /**
     * Prueba 4: Prueba el m�todo agregarEspacio de la clase CentroDeConvenciones. <br>
     * <b>M�todos a probar:</b> <br>
     * agregarEspacio<br>
     * <b> Casos de prueba: </b><br>
     * 1. Ya existe una espacio con el nombre dado.
     */
    public void testAgregarEspacioError( )
    {
        setupEscenario1( );

        // Verificar que no se haya ingresado el espacio
        boolean espacioAgregado = centroConvenciones.agregarEspacio( "Gran sal�n", Espacio.TIPO_SALA_COMPUTO, true, "Ruta1", 20, 250000, 10, "Juan" );
        assertFalse( "No debi� haber registrado el espacio", espacioAgregado );
        assertEquals( "El n�mero de espacios del centroConvenciones no debi� cambiar", 8, centroConvenciones.darEspacios( ).size( ) );
    }

    /**
     * Prueba 5: Prueba el m�todo agregarEventoAEspacio de la clase CentroDeConvenciones. <br>
     * <b>M�todos a probar:</b> <br>
     * agregarEventoAEspacio<br>
     * <b> Casos de prueba: </b><br>
     * 1. No existe el espacio al que se desea agregar. <br>
     * 2. Existe el espacio. <br>
     * 3. Existe el espacio y tiene un evento que se cruza.
     */
    public void testAgregarEventoAEspacio( )
    {
        setupEscenario1( );
        boolean eventoAgregado = false;

        // Caso de prueba 1
        try
        {
            eventoAgregado = centroConvenciones.agregarEventoAEspacio( "Turing2", "Evento", "Evento prueba", 30, 2017, 10, 21, 8, 16, "ruta" );
            assertFalse( "No debi� haber registrado el evento", eventoAgregado );
        }
        catch( EspacioOcupadoException e )
        {
            // Deber�a pasar por aqu�
            fail( "No deber�a lanzar excepci�n." );

        }

        // Caso de prueba 2
        try
        {
            eventoAgregado = centroConvenciones.agregarEventoAEspacio( "Auditorio A", "Evento", "Evento prueba", 30, 2018, 10, 21, 8, 16, "ruta" );
        }
        catch( EspacioOcupadoException e )
        {
            fail( "No deber�a lanzar excepci�n." );
            assertTrue( "Debi� haber registrado el evento", eventoAgregado );
        }

        // Caso de prueba 2
        try
        {
            
            eventoAgregado = centroConvenciones.agregarEventoAEspacio( "Auditorio A", "Evento", "Evento prueba", 30, 2018, 10, 21, 8, 18, "ruta" );
            fail( "Deber�a lanzar excepci�n." );
        }
        catch( EspacioOcupadoException e )
        {
            //Deber�a pasar por ac�
        }

    }

    /**
     * Prueba 6: Prueba el m�todo ordenarPorNombre de la clase CentroDeConvenciones. <br>
     * <b>M�todos a probar:</b> <br>
     * ordenarPorNombre<br>
     * darEspacios<br>
     * <b> Casos de prueba: </b><br>
     * 1. Los espacios no se encuentran ordenados.<br>
     */
    public void testOrdenarPorNombre( )
    {
        setupEscenario1( );

        centroConvenciones.ordenarPorNombre( );
        ArrayList espacios = centroConvenciones.darEspacios( );

        for( int i = 1; i < espacios.size( ); i++ )
        {
            Espacio espacio1 = ( Espacio )espacios.get( i - 1 );
            Espacio espacio2 = ( Espacio )espacios.get( i );
            assertTrue( "Los espacios no quedaron ordenados correctamente", espacio1.compararPorNombre( espacio2 ) < 1 );
        }
    }

    /**
     * Prueba 7: Prueba el m�todo ordenarPorDificultad de la clase CentroDeConvenciones. <br>
     * <b>M�todos a probar:</b> <br>
     * ordenarPorDificultad<br>
     * darEspacios<br>
     * <b> Casos de prueba: </b><br>
     * 1. Los espacios no se encuentran ordenados.<br>
     */
    public void testOrdenarPorCapacidad( )
    {
        setupEscenario1( );

        centroConvenciones.ordenarPorCapacidad( );
        ArrayList espacios = centroConvenciones.darEspacios( );

        for( int i = 1; i < espacios.size( ); i++ )
        {
            Espacio espacio1 = ( Espacio )espacios.get( i - 1 );
            Espacio espacio2 = ( Espacio )espacios.get( i );
            assertTrue( "Los espacios no quedaron ordenados correctamente", espacio1.compararPorCapacidad( espacio2 ) < 1 );
        }
    }

    /**
     * Prueba 8: Prueba el m�todo ordenarPorCantidadEventos de la clase CentroDeConvenciones. <br>
     * <b>M�todos a probar:</b> <br>
     * ordenarPorCantidadEventos<br>
     * darEspacios<br>
     * <b> Casos de prueba: </b><br>
     * 1. Los espacios no se encuentran ordenados.<br>
     */
    public void testOrdenarPorCantidadEventos( )
    {
        setupEscenario1( );

        centroConvenciones.ordenarPorCantidadEventos( );
        ArrayList espacios = centroConvenciones.darEspacios( );

        for( int i = 1; i < espacios.size( ); i++ )
        {
            Espacio espacio1 = ( Espacio )espacios.get( i - 1 );
            Espacio espacio2 = ( Espacio )espacios.get( i );
            assertTrue( "Los espacios no quedaron ordenados correctamente", espacio1.compararPorCantidadEventos( espacio2 ) > -1 );
        }
    }

    /**
     * Prueba 9: Prueba el m�todo buscarEspacio de la clase CentroDeConvenciones. <br>
     * <b>M�todos a probar:</b> <br>
     * buscarEspacio<br>
     * <b> Casos de prueba: </b><br>
     * 1. Existe una espacio con el nombre dado.<br>
     * 2. No existe una espacio con el nombre dado.
     */
    public void testBuscarEspacio( )
    {
        setupEscenario1( );

        // Se busca un espacio que existe
        Espacio buscada = centroConvenciones.buscarEspacio( "Auditorio B" );
        assertNotNull( "Debi� haber encontrado una espacio con el nombre dado", buscada );

        // Se busca un espacio que no existe
        Espacio buscada2 = centroConvenciones.buscarEspacio( "Cupi2" );
        assertNull( "No deber�a encontrar una espacio con el nombre dado", buscada2 );
    }

    /**
     * Prueba 10: Prueba el m�todo buscarBinarioPorNombre de la clase CentroDeConvenciones. <br>
     * <b>M�todos a probar:</b> <br>
     * buscarBinarioPorNombe<br>
     * <b> Casos de prueba: </b><br>
     * 1. Existe un espacio con el nombre dado.<br>
     * 2. No existe un espacio con el nombre dado.
     */
    public void testBuscarBinarioPorNombre( )
    {
        setupEscenario1( );

        // Caso de prueba 1
        centroConvenciones.ordenarPorNombre( );
        int indice = centroConvenciones.buscarBinarioPorNombre( "Auditorio B" );
        assertTrue( "Debi� haber encontrado un espacio con el nombre dado.", indice > -1 );
        assertEquals( "El �ndice retornado no es el correcto.", 1, indice );

        // Caso de prueba 2
        int indice2 = centroConvenciones.buscarBinarioPorNombre( "Cupi2" );
        assertEquals( "No deber�a encontrar un espacio con el nombre dado.", -1, indice2 );
    }


    /**
     * Prueba 12: Prueba el m�todo buscarEspacioMenosUsado de la clase CentroDeConvenciones. <br>
     * <b>M�todos a probar:</b> <br>
     * buscarEspacioMasFacil<br>
     * <b> Casos de prueba: </b><br>
     * 1. El centro de convenciones tiene espacios<br>
     */
    public void testBuscarEspacioMenosUsado( )
    {
        setupEscenario1( );

        int indice = centroConvenciones.buscarEspacioMenosUsado( );
        assertEquals( "El espacio menos usado no es el correcto.", 1, indice );
    }


    /**
     * Prueba 14: Prueba el m�todo buscarEspacioMasDificil de la clase CentroDeConvenciones. <br>
     * <b>M�todos a probar:</b> <br>
     * buscarEspacioMasUsado<br>
     * <b> Casos de prueba: </b><br>
     * 1. El centro de convenciones tiene espacios.<br>
     */
    public void testBuscarEspacioMasUsado( )
    {
        setupEscenario1( );
        int indice = centroConvenciones.buscarEspacioMasUsado( );
        assertEquals( "El espacio m�s usado no es correcto.", 4, indice );
    }

    /**
     * Prueba 15: Prueba el m�todo buscarEspacioConEventosEnFecha de la clase CentroDeConvenciones. <br>
     * <b>M�todos a probar:</b> <br>
     * buscarEspacioConEventosEnFecha<br>
     * <b> Casos de prueba: </b><br>
     * 1. Ning�n espacio tiene eventos en la fecha dada.<br>
     * 2. Al menos un espacio tiene eventos en la fecha dada.
     */
    public void testBuscarEspacioConEventosEnFecha( )
    {
        setupEscenario1( );

        // Caso de prueba 1
        ArrayList espaciosEncontrados;

        espaciosEncontrados = centroConvenciones.buscarEspacioConEventosEnFecha( "2017/10/20" );
        assertEquals( "No deber�a retornar ning�n espacio.", 0, espaciosEncontrados.size( ) );

        // Caso de prueba 2
        try
        {
            centroConvenciones.agregarEventoAEspacio( "Auditorio A", "Workshop", "Taller cupi2", 30, 2017, 10, 20, 8, 16, "ruta" );
        }
        catch( EspacioOcupadoException e )
        {
            fail( "No deber�a lanzar excepci�n." );
        }

        espaciosEncontrados = centroConvenciones.buscarEspacioConEventosEnFecha( "2017/10/20" );
        assertEquals( "Deber�a retornar un espacio.", 1, espaciosEncontrados.size( ) );
    }

}

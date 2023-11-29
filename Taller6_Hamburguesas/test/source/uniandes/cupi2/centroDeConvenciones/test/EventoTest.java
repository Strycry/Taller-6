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

import junit.framework.TestCase;
import uniandes.cupi2.centroDeConvenciones.mundo.Evento;
import uniandes.cupi2.centroDeConvenciones.mundo.Fecha;

/**
 * Clase usada para verificar que los métodos de la clase Evento están correctamente implementados.
 */
public class EventoTest extends TestCase
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Evento donde se harán las pruebas.
     */
    private Evento evento;

    // -------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------

    /**
     * Escenario 1: Construye un evento.
     */
    public void setupEscenario1( )
    {
        Fecha fecha = new Fecha( 2017, 05, 13, 8, 18 );
        evento = new Evento( "CupiCongreso", "Primer congreso nacional de algorítmica y programación uniandina.", 300, fecha, "cupicongreso.png" );
    }

    /**
     * Prueba 1: Prueba el método constructor de la clase Evento. <br>
     * <b>Métodos a probar:</b> <br>
     * Evento<br>
     * darNombre<br>
     * darDescripcion<br>
     * darCantidadAsistentes<br>
     * darFecha<br>
     * darRutaImagen<br>
     * <b> Casos de prueba: </b><br>
     * 1. El evento fue creado y los valores de los atributos corresponden.
     */
    public void testEvento( )
    {
        setupEscenario1( );
        assertEquals( "El nombre no corresponde.", "CupiCongreso", evento.darNombre( ) );
        assertEquals( "La descripción no corresponde.", "Primer congreso nacional de algorítmica y programación uniandina.", evento.darDescripcion( ) );
        assertEquals( "La cantidad de asistentes no corresponde.", 300, evento.darCantidadAsistentes( ) );
        assertEquals( "La fecha no corresponde.", 2017, evento.darFecha( ).darAnio( ) );
        assertEquals( "La fecha no corresponde.", 5, evento.darFecha( ).darMes( ) );
        assertEquals( "La fecha no corresponde.", 13, evento.darFecha( ).darDia( ) );
        assertEquals( "La fecha no corresponde.", 8, evento.darFecha( ).darHoraInicio( ) );
        assertEquals( "La fecha no corresponde.", 18, evento.darFecha( ).darHoraFin( ) );
        assertEquals( "La ruta de la imagen de la publicidad no corresponde.", "cupicongreso.png", evento.darRutaImagenPublicidad( ) );
    }

}

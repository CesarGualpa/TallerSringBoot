package com.krakedev.asistencias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.asistencias.entidades.Estudiante;
import com.krakedev.asistencias.servicios.ServicioEstudiantes;

public class ServicioEstudiantesTest {

	@Test
	public void agregarEstudianteNuevoTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		Estudiante estudiante = new Estudiante("1714616123", "Juan", "Perez");

		Estudiante resultado = servicio.agregar(estudiante);

		assertEquals(estudiante, resultado);
		assertEquals(1, servicio.listar().size());
	}

	@Test
	public void agregarEstudianteDuplicadoTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		Estudiante estudiante1 = new Estudiante("1714616123", "Juan", "Perez");
		Estudiante estudiante2 = new Estudiante("1714616123", "Pedro", "Lopez");

		servicio.agregar(estudiante1);

		Estudiante resultado = servicio.agregar(estudiante2);

		assertNull(resultado);
		assertEquals(1, servicio.listar().size());
	}

	@Test
	public void buscarPorCedulaExistenteTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		Estudiante estudiante = new Estudiante("1714616123", "Juan", "Perez");

		servicio.agregar(estudiante);

		Estudiante resultado = servicio.buscarPorCedula("1714616123");

		assertEquals(estudiante, resultado);
		assertEquals("Juan", resultado.getNombre());
	}

	@Test
	public void buscarPorCedulaInexistenteTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		Estudiante resultado = servicio.buscarPorCedula("0000000000");

		assertNull(resultado);
	}

	@Test
	public void listarEstudiantesTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		Estudiante estudiante1 = new Estudiante("1714616123", "Juan", "Perez");
		Estudiante estudiante2 = new Estudiante("0923456789", "Maria", "Lopez");

		servicio.agregar(estudiante1);
		servicio.agregar(estudiante2);

		assertEquals(2, servicio.listar().size());
		assertTrue(servicio.listar().contains(estudiante1));
		assertTrue(servicio.listar().contains(estudiante2));
	}

	@Test
	public void actualizarEstudianteExistenteTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		Estudiante estudiante = new Estudiante("1714616123", "Juan", "Perez");

		servicio.agregar(estudiante);

		Estudiante nuevo = new Estudiante("9999999999", "Carlos", "Gomez");

		Estudiante resultado = servicio.actualizar("1714616123", nuevo);

		assertEquals("1714616123", resultado.getCedula());
		assertEquals("Carlos", resultado.getNombre());
		assertEquals("Gomez", resultado.getApellido());
	}

	@Test
	public void actualizarEstudianteInexistenteTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		Estudiante nuevo = new Estudiante("1714616123", "Carlos", "Gomez");

		Estudiante resultado = servicio.actualizar("1714616123", nuevo);

		assertNull(resultado);
	}

	@Test
	public void eliminarEstudianteExistenteTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		Estudiante estudiante = new Estudiante("1714616123", "Juan", "Perez");

		servicio.agregar(estudiante);

		boolean resultado = servicio.eliminar("1714616123");

		assertTrue(resultado);
		assertEquals(0, servicio.listar().size());
		assertNull(servicio.buscarPorCedula("1714616123"));
	}

	@Test
	public void eliminarEstudianteInexistenteTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		boolean resultado = servicio.eliminar("0000000000");

		assertFalse(resultado);
		assertEquals(0, servicio.listar().size());
	}
}
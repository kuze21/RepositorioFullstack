package com.example.proyectoperfulandia;

import com.example.proyectoperfulandia.model.Empleado;
import com.example.proyectoperfulandia.model.EnumRol;
import com.example.proyectoperfulandia.repository.EmpleadoRepository;
import com.example.proyectoperfulandia.services.EmpleadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmpleadoTests {

	@Mock
	@Autowired
	EmpleadoRepository empleadoRepository;

	@InjectMocks
	@MockitoBean
	EmpleadoService empleadoServiceMock;

	@Autowired
	MockMvc mockMvc;

	// Test unitario encargado de probar la función getEmpleados, utilizada para obtener una lista de todos los empleados
	@Test
	@DisplayName("Test listar todos los empleados")
	void testGetEmpleados(){
		Empleado prueba1 = new Empleado();
		prueba1.setId(123);
		prueba1.setNombre("Empleado Prueba 1");
		prueba1.setEmail("empleado@test.com");
		prueba1.setRut("12345678-9");
		prueba1.setPassword("password123");
		prueba1.setRol(EnumRol.EMPLEADO);

		Empleado prueba2 = new Empleado();
		prueba2.setId(124);
		prueba2.setNombre("Empleado Prueba 2");
		prueba2.setEmail("empleado@test.com");
		prueba2.setRut("98765432-1");
		prueba2.setPassword("password456");
		prueba2.setRol(EnumRol.EMPLEADO);
		List<Empleado> empleados = Arrays.asList(prueba1, prueba2);
		when(empleadoServiceMock.getEmpleados()).thenReturn(empleados);
		try{
			mockMvc.perform(get("/empleados"))
					.andExpect(status().isOk())
					.andExpect(content().string("Lista completa"));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	// Test unitario encargado de probar la función addEmpleado, utilizada para agregar un empleado
	@Test
	@DisplayName("Test agregar empleado")
	void testAddEmpleado(){
		try {
			Empleado prueba = new Empleado();
			prueba.setNombre("Empleado Prueba");
			prueba.setId(123);
			empleadoServiceMock.addEmpleado(prueba);
			assertNotNull(prueba);
			assertNotNull(empleadoServiceMock.getEmpleado(123));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	// Test unitario encargado de probar la función removeEmpleado, utilizada para eliminar un empleado
	@Test
	@DisplayName("Test elimar empleado")
	void testRemoveEmpleado(){
		try {
			Empleado prueba = new Empleado();
			prueba.setNombre("Empleado Prueba");
			prueba.setId(123);
			empleadoServiceMock.addEmpleado(prueba);
			empleadoServiceMock.removeEmpleado(123);
			assertNull(empleadoServiceMock.getEmpleado(123));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	// Test unitario encargado de probar la función updateEmpleado, utilizada para actualizar la información de un empleado
	@Test
	@DisplayName("Test actualizar empleado")
	void testUpdateEmpleado(){
		try {
			Empleado prueba = new Empleado();
			prueba.setNombre("Empleado Prueba");
			prueba.setId(123);
			prueba.setEmail("empleado@gmail.com");
			Empleado actualizacion = new Empleado();
			actualizacion.setNombre("Empleado Actualizado");
			actualizacion.setId(123);
			actualizacion.setEmail("actualizado@gmail.com");
			empleadoServiceMock.addEmpleado(prueba);
			assertNotNull(empleadoServiceMock.getEmpleado(123));
			empleadoServiceMock.updateEmpleado(123,actualizacion);
			assertEquals("Empleado Actualizado",empleadoServiceMock.getEmpleado(123).get().getNombre());
			assertEquals("actualizado@gmail.com",empleadoServiceMock.getEmpleado(123).get().getEmail());
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

}
package com.example.proyectoperfulandia;

import com.example.proyectoperfulandia.model.Empleado;
import com.example.proyectoperfulandia.model.EnumRol;
import com.example.proyectoperfulandia.model.Usuario;
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

	/*
	# Estructura @Test
	@Test
	void testFuncion(){
		# Se puede indicar un valor para suplantar y acotar el resultado que se espera de la funcion.
		when().thenReturn("");

		# Bloque try/catch permite probar un segmento o funcion y agarrar el error
		# mockMvc permite consultas HTTPMethod
		try{
			mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(content().string(""))
		}

		catch(Exception ex){
		 System.out.println(ex.getMessage());
		 fail();
		}
	}
	*/

	@Test
	@DisplayName("Test listar todos los empleados")
	void testGetEmpleados(){
		when(empleadoServiceMock.getEmpleados()).thenReturn("Lista completa");
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

	@Test
	@DisplayName("Test agregar empleado")
	void testAddEmpleado(){
		try {
			Empleado prueba = new Empleado();
			prueba.setNombre("Empleado Prueba");
			prueba.setId(123);
			empleadoServiceMock.addEmpleado(prueba);
			assertNotNull(prueba);
			assertNotNull(empleadoServiceMock.getEmpleadoID(123));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("Test elimar empleado")
	void testRemoveEmpleado(){
		try {
			Empleado prueba = new Empleado();
			prueba.setNombre("Empleado Prueba");
			prueba.setId(123);
			empleadoServiceMock.addEmpleado(prueba);
			empleadoServiceMock.removeEmpleado(123);
			assertNull(empleadoServiceMock.getEmpleadoID(123));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

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
			assertNotNull(empleadoServiceMock.getEmpleadoID(123));
			empleadoServiceMock.updateEmpleado(123,actualizacion);
			assertEquals("Empleado Actualizado",empleadoRepository.findById(123).get().getNombre());
			assertEquals("actualizado@gmail.com",empleadoRepository.findById(123).get().getEmail());
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

/*
	@Test
	@DisplayName("Rectificar precio producto")
	void testFindProduct(){
		Producto prueba = productoRepository.findById(1).get();
		assertNotNull(prueba);
		assertEquals(359990,prueba.getPrecio());
	}
*/

}
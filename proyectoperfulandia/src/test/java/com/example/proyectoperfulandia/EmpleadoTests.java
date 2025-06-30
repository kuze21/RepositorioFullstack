package com.example.proyectoperfulandia;

import com.example.proyectoperfulandia.model.Empleado;
import com.example.proyectoperfulandia.model.EnumRol;
import com.example.proyectoperfulandia.model.Usuario;
import com.example.proyectoperfulandia.repository.EmpleadoRepository;
import com.example.proyectoperfulandia.services.EmpleadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

	@Autowired
	EmpleadoRepository empleadoRepository;

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
			Empleado prueba = new Empleado(123456789,"11111111-1","Empleado Prueba", EnumRol.CLIENTE,"prueba@gmail.com","Claveprueba");
			empleadoServiceMock.addEmpleado(prueba);
			assertNotNull(prueba);
			assertNotNull(empleadoRepository.findById(123456789));
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
			Empleado prueba = new Empleado(123456789,"11111111-1","Empleado Prueba",EnumRol.CLIENTE,"prueba@gmail.com","Claveprueba");
			empleadoServiceMock.addEmpleado(prueba);
			empleadoRepository.deleteById(123456789);
			assertNull(empleadoRepository.findById(123456789));
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
			Empleado prueba = new Empleado(123456789,"11111111-1","Empleado Prueba",EnumRol.CLIENTE,"prueba@gmail.com","Claveprueba");
			Empleado actualizacion = new Empleado(123456788,"11111111-2","Empleado Actualizado",EnumRol.CLIENTE,"actualizado@gmail.com","Claveprueba");
			empleadoServiceMock.addEmpleado(prueba);
			assertNotNull(empleadoRepository.findById(123456789));
			empleadoServiceMock.updateEmpleado(123456789,actualizacion);
			assertEquals("Empleado Actualizado",empleadoRepository.findById(123456789).get().getNombre());
			assertEquals("actualizado@gmail.com",empleadoRepository.findById(123456789).get().getEmail());
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
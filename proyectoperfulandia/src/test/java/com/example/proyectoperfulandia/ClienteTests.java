package com.example.proyectoperfulandia;

import com.example.proyectoperfulandia.model.Cliente;
import com.example.proyectoperfulandia.model.EnumRol;
import com.example.proyectoperfulandia.model.Usuario;
import com.example.proyectoperfulandia.repository.ClienteRepository;
import com.example.proyectoperfulandia.services.ClienteService;
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
class ClienteTests {

	@Autowired
	ClienteRepository clienteRepository;

	@MockitoBean
	ClienteService clienteServiceMock;

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
	@DisplayName("Test listar todos los clientes")
	void testGetClientes(){
		when(clienteServiceMock.getClientes()).thenReturn("Lista completa");
		try{
			mockMvc.perform(get("/clientes"))
					.andExpect(status().isOk())
					.andExpect(content().string("Lista completa"));
		}

		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("Test agregar cliente")
	void testAddCliente(){
		try {
			Cliente prueba = new Cliente(123456789,"11111111-1","Cliente Prueba", EnumRol.CLIENTE,"prueba@gmail.com","Claveprueba");
			clienteServiceMock.addCliente(prueba);
			assertNotNull(prueba);
			assertNotNull(clienteRepository.findById(123456789));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("Test elimar cliente")
	void testRemoveCliente(){
		try {
			Cliente prueba = new Cliente(123456789,"11111111-1","Cliente Prueba",EnumRol.CLIENTE,"prueba@gmail.com","Claveprueba");
			clienteServiceMock.addCliente(prueba);
			clienteServiceMock.removeCliente(123456789);
			assertNull(clienteRepository.findById(123456789));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("Test actualizar cliente")
	void testActualizarCliente(){
		try {
			Cliente prueba = new Cliente(123456789,"11111111-1","Cliente Prueba",EnumRol.CLIENTE,"prueba@gmail.com","Claveprueba");
			Cliente actualizacion = new Cliente(123456788,"11111111-2","Cliente Actualizado",EnumRol.CLIENTE,"actualizado@gmail.com","Claveprueba");
			clienteServiceMock.addCliente(prueba);
			assertNotNull(clienteRepository.findById(123456789));
			clienteServiceMock.updateCliente(123456789,actualizacion);
			assertEquals("Cliente Actualizado",clienteRepository.findById(123456789).get().getNombre());
			assertEquals("actualizado@gmail.com",clienteRepository.findById(123456789).get().getEmail());
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
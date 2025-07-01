package com.example.proyectoperfulandia;

import com.example.proyectoperfulandia.model.Cliente;
import com.example.proyectoperfulandia.model.EnumRol;
import com.example.proyectoperfulandia.model.Usuario;
import com.example.proyectoperfulandia.repository.ClienteRepository;
import com.example.proyectoperfulandia.services.ClienteService;
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
class ClienteTests {

	@Mock
	@Autowired
	ClienteRepository clienteRepository;

	@InjectMocks
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
			Cliente prueba = new Cliente();
			prueba.setNombre("Cliente Prueba");
			prueba.setId(123);
			clienteServiceMock.addCliente(prueba);
			assertNotNull(prueba);
			assertNotNull(clienteServiceMock.getClienteID(123));
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
			Cliente prueba = new Cliente();
			prueba.setNombre("Cliente Prueba");
			prueba.setId(123);
			clienteServiceMock.addCliente(prueba);
			clienteServiceMock.removeCliente(123);
			assertNull(clienteServiceMock.getClienteID(123));
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
			Cliente prueba = new Cliente();
			prueba.setNombre("Cliente Prueba");
			prueba.setId(123);
			prueba.setEmail("cliente@gmail.com");
			Cliente actualizacion = new Cliente();
			actualizacion.setNombre("Cliente Actualizado");
			actualizacion.setId(123);
			actualizacion.setEmail("actualizado@gmail.com");
			clienteServiceMock.addCliente(prueba);
			assertNotNull(clienteServiceMock.getClienteID(123));
			clienteServiceMock.updateCliente(123,actualizacion);
			assertEquals("Cliente Actualizado",clienteRepository.findById(123).get().getNombre());
			assertEquals("actualizado@gmail.com",clienteRepository.findById(123).get().getEmail());
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
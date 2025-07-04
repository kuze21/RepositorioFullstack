package com.example.proyectoperfulandia;

import com.example.proyectoperfulandia.model.Cliente;
import com.example.proyectoperfulandia.model.EnumRol;
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

import java.util.Arrays;
import java.util.List;

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

	// Test unitario encargado de probar la función getClientes, utilizada para obtener una lista de todos los clientes
	@Test
	@DisplayName("Test listar todos los clientes")
	void testGetClientes(){
		Cliente prueba1 = new Cliente();
		prueba1.setId(123);
		prueba1.setNombre("Cliente Prueba 1");
		prueba1.setEmail("cliente@test.com");
		prueba1.setRut("12345678-9");
		prueba1.setPassword("password123");
		prueba1.setRol(EnumRol.CLIENTE);

		Cliente prueba2 = new Cliente();
		prueba2.setId(124);
		prueba2.setNombre("Cliente Prueba 2");
		prueba2.setEmail("cliente@test.com");
		prueba2.setRut("98765432-1");
		prueba2.setPassword("password456");
		prueba2.setRol(EnumRol.CLIENTE);
		List<Cliente> clientes = Arrays.asList(prueba1, prueba2);
		when(clienteServiceMock.getClientes()).thenReturn(clientes);
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

	// Test unitario encargado de probar la función addCliente, utilizada para agregar un cliente
	@Test
	@DisplayName("Test agregar cliente")
	void testAddCliente(){
		try {
			Cliente prueba = new Cliente();
			prueba.setNombre("Cliente Prueba");
			prueba.setId(123);
			clienteServiceMock.addCliente(prueba);
			assertNotNull(prueba);
			assertNotNull(clienteServiceMock.getCliente(123));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	// Test unitario encargado de probar la función removeCliente, utilizada para eliminar un cliente
	@Test
	@DisplayName("Test elimar cliente")
	void testRemoveCliente(){
		try {
			Cliente prueba = new Cliente();
			prueba.setNombre("Cliente Prueba");
			prueba.setId(123);
			clienteServiceMock.addCliente(prueba);
			clienteServiceMock.removeCliente(123);
			assertNull(clienteServiceMock.getCliente(123));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	// Test unitario encargado de probar la función updateCliente, utilizada para actualizar la información de un cliente
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
			assertNotNull(clienteServiceMock.getCliente(123));
			clienteServiceMock.updateCliente(123,actualizacion);
			assertEquals("Cliente Actualizado",clienteServiceMock.getCliente(123).get().getNombre());
			assertEquals("actualizado@gmail.com",clienteServiceMock.getCliente(123).get().getEmail());
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

}
package com.example.proyectoperfulandia;

import com.example.proyectoperfulandia.model.Cliente;
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
	/*
	@Test
	@DisplayName("Test Listar Productos")
	void testListarProductos(){
		when(productoServiceMock.listarProducto()).thenReturn("Lista completa");
		try{
			mockMvc.perform(get("/productos"))
					.andExpect(status().isOk())
					.andExpect(content().string("Lista completa"));
		}

		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}

	}

	@Test
	@DisplayName("Rectificar precio producto")
	void testFindProduct(){
		Producto prueba = productoRepository.findById(1).get();
		assertNotNull(prueba);
		assertEquals(359990,prueba.getPrecio());
	}
*/


}
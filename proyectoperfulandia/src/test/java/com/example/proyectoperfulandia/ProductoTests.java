package com.example.proyectoperfulandia;

import com.example.proyectoperfulandia.model.EnumRol;
import com.example.proyectoperfulandia.model.Producto;
import com.example.proyectoperfulandia.model.Usuario;
import com.example.proyectoperfulandia.repository.ProductoRepository;
import com.example.proyectoperfulandia.services.ProductoService;
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
class  ProductoTests {

	@Autowired
	ProductoRepository productoRepository;
	@MockitoBean
	ProductoService productoServiceMock;
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
	@DisplayName("Test listar productos")
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
	void testFindProducto(){
		Producto prueba = productoRepository.findById(1).get();
		assertNotNull(prueba);
		assertEquals(359990,prueba.getPrecio());
	}

	@Test
	@DisplayName("Test agregar producto")
	void testAddProducto(){
		try {
			Producto prueba = new Producto();
			productoServiceMock.agregarProducto(prueba);
			assertNotNull(prueba);
			assertNotNull(productoRepository.findById(123456789));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("Test elimar producto")
	void testRemoveProducto(){
		try {
			Producto prueba = new Producto();
			productoServiceMock.agregarProducto(prueba);
			productoServiceMock.eliminarProducto(123456789);
			assertNull(productoRepository.findById(123456789));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("Test actualizar producto")
	void testUpdateProducto(){
		try {
			Producto prueba = new Producto();
			Producto actualizacion = new Producto();
			productoServiceMock.agregarProducto(prueba);
			assertNotNull(productoRepository.findById(123456789));
			productoServiceMock.actualizarProducto(123456789,actualizacion);
			assertEquals("Producto Actualizado",productoRepository.findById(123456789).get().getNombre());

		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

}
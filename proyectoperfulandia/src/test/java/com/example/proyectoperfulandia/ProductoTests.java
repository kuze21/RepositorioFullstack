package com.example.proyectoperfulandia;

import com.example.proyectoperfulandia.model.Producto;
import com.example.proyectoperfulandia.repository.ProductoRepository;
import com.example.proyectoperfulandia.services.ProductoService;
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
class  ProductoTests {

	@Mock
	@Autowired
	ProductoRepository productoRepository;

	@InjectMocks
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
	void testGetProductos(){
		Producto prueba1 = new Producto();

		Producto prueba2 = new Producto();
		List<Producto> productos = Arrays.asList(prueba1, prueba2);
		when(productoServiceMock.getProductos()).thenReturn(productos);
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
			productoServiceMock.addProducto(prueba);
			prueba.setNombre("Producto Prueba");
			prueba.setId(123);
			assertNotNull(prueba);
			assertNotNull(productoServiceMock.getProducto(123));
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
			productoServiceMock.addProducto(prueba);
			prueba.setNombre("Producto Prueba");
			prueba.setId(123);
			productoServiceMock.removeProducto(123);
			assertNull(productoServiceMock.getProducto(123));
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
			prueba.setNombre("Producto Prueba");
			prueba.setId(123);
			Producto actualizacion = new Producto();
			actualizacion.setNombre("Producto Actualizado");
			actualizacion.setId(123);
			productoServiceMock.addProducto(prueba);
			assertNotNull(productoServiceMock.getProducto(123));
			productoServiceMock.updateProducto(123,actualizacion);
			assertEquals("Producto Actualizado",productoServiceMock.getProducto(123).get().getNombre());

		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

}
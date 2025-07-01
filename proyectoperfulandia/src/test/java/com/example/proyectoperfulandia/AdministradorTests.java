package com.example.proyectoperfulandia;

import com.example.proyectoperfulandia.model.Administrador;
import com.example.proyectoperfulandia.model.EnumRol;
import com.example.proyectoperfulandia.model.Usuario;
import com.example.proyectoperfulandia.repository.AdministradorRepository;
import com.example.proyectoperfulandia.services.AdministradorService;
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
class AdministradorTests {

	@Autowired
	AdministradorRepository administradorRepository;

	@MockitoBean
	AdministradorService administradorServiceMock;

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
	@DisplayName("Test listar todos los administradores")
	void testGetAdmins(){
		when(administradorServiceMock.getAdmins()).thenReturn("Lista completa");
		try{
			mockMvc.perform(get("/administradores"))
					.andExpect(status().isOk())
					.andExpect(content().string("Lista completa"));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("Test agregar administrador")
	void testAddAdmin(){
		try {
			Administrador prueba = new Administrador(123456789,"11111111-1","Administrador Prueba", EnumRol.ADMIN,"prueba@gmail.com","Claveprueba");
			administradorServiceMock.addAdmin(prueba);
			assertNotNull(prueba);
			assertNotNull(administradorRepository.findById(123456789));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("Test elimar administrador")
	void testRemoveAdmin(){
		try {
			Administrador prueba = new Administrador(123456789,"11111111-1","Administrador Prueba",EnumRol.ADMIN,"prueba@gmail.com","Claveprueba");
			administradorServiceMock.addAdmin(prueba);
			administradorServiceMock.removeAdmin(123456789);
			assertNull(administradorRepository.findById(123456789));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("Test actualizar administrador")
	void testUpdateAdmin(){
		try {
			Administrador prueba = new Administrador(123456789,"11111111-1","administrador Prueba",EnumRol.ADMIN,"prueba@gmail.com","Claveprueba");
			Administrador actualizacion = new Administrador(123456788,"11111111-2","administrador Actualizado",EnumRol.ADMIN,"actualizado@gmail.com","Claveprueba");
			administradorServiceMock.addAdmin(prueba);
			assertNotNull(administradorRepository.findById(123456789));
			administradorServiceMock.updateAdmin(123456789,actualizacion);
			assertEquals("Administrador Actualizado",administradorRepository.findById(123456789).get().getNombre());
			assertEquals("actualizado@gmail.com",administradorRepository.findById(123456789).get().getEmail());
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
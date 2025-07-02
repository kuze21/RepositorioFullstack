package com.example.proyectoperfulandia;

import com.example.proyectoperfulandia.model.EnumRol;
import com.example.proyectoperfulandia.model.Usuario;
import com.example.proyectoperfulandia.repository.UsuarioRepository;
import com.example.proyectoperfulandia.services.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioTests {

	@Mock
	@Autowired
	UsuarioRepository usuarioRepository;

	@InjectMocks
	@MockitoBean
	UsuarioService usuarioServiceMock;

	@Autowired
	MockMvc mockMvc;
	@Autowired
	private MockMvcTester mockMvcTester;

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
	@DisplayName("Test listar todos los usuarios")
	void testGetUsuarios(){
		when(usuarioServiceMock.getUsuarios()).thenReturn("Lista completa");
		try{
			mockMvc.perform(get("/usuarios"))
					.andExpect(status().isOk())
					.andExpect(content().string("Lista completa"));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("Test comprobar usuario")
	void testGetUsuario(){
		Usuario prueba = new Usuario(123,"11111111-1","Usuario Prueba",EnumRol.CLIENTE,"prueba@gmail.com","Claveprueba");
		usuarioServiceMock.addUsuario(prueba);
		when(usuarioServiceMock.getUsuario(123)).thenReturn("Valores usuario");
		try{
			mockMvc.perform(get("/usuario"))
					.andExpect(status().isOk())
					.andExpect(content().string("Valores usuario"));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();

		}
	}
	// estos 3 de pruebas probablemente no esten bien
	@Test
	@DisplayName("Test agregar usuario")
	void testAddUsuario(){
		try {
			Usuario prueba = new Usuario(123,"11111111-1","Usuario Prueba",EnumRol.CLIENTE,"prueba@gmail.com","Claveprueba");
			usuarioServiceMock.addUsuario(prueba);
			assertNotNull(prueba);
			assertNotNull(usuarioServiceMock.getUsuario(123));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("Test elimar usuario")
	void testRemoveUsuario(){
		try {
			Usuario prueba = new Usuario(123,"11111111-1","Usuario Prueba",EnumRol.CLIENTE,"prueba@gmail.com","Claveprueba");
			usuarioServiceMock.addUsuario(prueba);
			usuarioServiceMock.removeUsuario(123);
			assertNull(usuarioServiceMock.getUsuario(123));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

	@Test
	@DisplayName("Test actualizar usuario")
	void testUpdateUsuario(){
		try {
			Usuario prueba = new Usuario(123,"11111111-1","Usuario Prueba",EnumRol.CLIENTE,"prueba@gmail.com","Claveprueba");
			Usuario actualizacion = new Usuario(123,"11111111-2","Usuario Actualizado",EnumRol.CLIENTE,"actualizado@gmail.com","Claveprueba");
			usuarioServiceMock.addUsuario(prueba);
			assertNotNull(usuarioServiceMock.getUsuario(123));
			usuarioServiceMock.updateUsuario(123,actualizacion);
			assertEquals("Usuario Actualizado",usuarioServiceMock.getUsuario(123).get().getNombre());
			assertEquals("actualizado@gmail.com",usuarioServiceMock.getUsuario(123).get().getEmail());
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

}
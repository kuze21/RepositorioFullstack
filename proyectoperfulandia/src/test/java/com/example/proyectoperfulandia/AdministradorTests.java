package com.example.proyectoperfulandia;

import com.example.proyectoperfulandia.model.Administrador;
import com.example.proyectoperfulandia.model.EnumRol;
import com.example.proyectoperfulandia.model.Usuario;
import com.example.proyectoperfulandia.repository.AdministradorRepository;
import com.example.proyectoperfulandia.services.AdministradorService;
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
class AdministradorTests {

	@Mock
	@Autowired
	AdministradorRepository administradorRepository;

	@InjectMocks
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
			Administrador prueba = new Administrador();
			prueba.setNombre("Administrador Prueba");
			prueba.setId(123);
			administradorServiceMock.addAdmin(prueba);
			assertNotNull(prueba);
			assertNotNull(administradorServiceMock.getAdmin(123));
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
			Administrador prueba = new Administrador();
			prueba.setNombre("Administrador Prueba");
			prueba.setId(123);
			administradorServiceMock.addAdmin(prueba);
			administradorServiceMock.removeAdmin(123);
			assertNull(administradorServiceMock.getAdmin(123));
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
			Administrador prueba = new Administrador();
			prueba.setNombre("Administrador Prueba");
			prueba.setId(123);
			prueba.setEmail("administrador@gmail.com");
			Administrador actualizacion = new Administrador();
			actualizacion.setNombre("Administrador Actualizado");
			actualizacion.setId(123);
			actualizacion.setEmail("actualizado@gmail.com");
			administradorServiceMock.addAdmin(prueba);
			assertNotNull(administradorServiceMock.getAdmin(123));
			administradorServiceMock.updateAdmin(123,actualizacion);
			assertEquals("Administrador Actualizado",administradorServiceMock.getAdmin(123).get().getNombre());
			assertEquals("actualizado@gmail.com",administradorServiceMock.getAdmin(123).get().getEmail());
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			fail();
		}
	}

}
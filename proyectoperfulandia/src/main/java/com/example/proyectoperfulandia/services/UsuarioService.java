package com.example.proyectoperfulandia.services;

import com.example.proyectoperfulandia.model.Usuario;
import com.example.proyectoperfulandia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public String getUsuarios(){
        return usuarioRepository.getUsuarios();
    }

    public String addUsuario(Usuario usuario){
        return usuarioRepository.addUsuario(usuario);
    }

    public String getUsuario(int id){
        return usuarioRepository.getUsuario(id);
    }

    public String removeUsuario(int id){
        return usuarioRepository.removeUsuario(id);
    }

    public String updateUsuario(int id, Usuario usuario){
        return usuarioRepository.updateUsuario(id,usuario);
    }

}

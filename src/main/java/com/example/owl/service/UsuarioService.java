package com.example.owl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.owl.model.Usuario;
import com.example.owl.repository.UsuarioRepository;

@Service
public class UsuarioService {

@Autowired
private UsuarioRepository usuarioRepository;


public Optional<Usuario> searchById(Long id) {
    return usuarioRepository.findById(id);
}

public List<Usuario> searchAll(){
    return usuarioRepository.findAll();
}

public Usuario createUser(Usuario usuario) {
    return usuarioRepository.save(usuario);
}

public void updateUser(Long id, Usuario usuario){

}

public void deleteUser(Long id){
    if (usuarioRepository.existsById(id)) {
        usuarioRepository.deleteById(id);
    }
}

}

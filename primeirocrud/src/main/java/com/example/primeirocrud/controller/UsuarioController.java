package com.example.primeirocrud.controller;

import com.example.primeirocrud.model.Usuario;
import com.example.primeirocrud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable Integer id){
        return usuarioRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuarioPorId(@PathVariable Integer id, @RequestBody Usuario usuarioAtualizado){
        return usuarioRepository.findById(id).map(usuario ->  {
            usuario.setNome(usuario.getNome());
            usuario.setEmail(usuario.getEmail());
            return usuarioRepository.save(usuario);
        }).orElse(null);
    }


    @DeleteMapping("/{id}")
    public void deletarUsuarioPorId(@PathVariable Integer id){
        usuarioRepository.deleteById(id);
    }


}

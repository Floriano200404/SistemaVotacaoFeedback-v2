package br.edu.ifro.calama.votacaofeedback.repository;


import br.edu.ifro.calama.votacaofeedback.model.Usuario;

public class UsuarioRepository {

    public Usuario buscarPorLoginESenha(String login, String senha) {
        if ("admin".equals(login) && "123".equals(senha)) {
            return new Usuario(1, login, senha, "Administrador", "ADMIN");
        }
        return null;
    }

    public boolean autenticar(String login, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
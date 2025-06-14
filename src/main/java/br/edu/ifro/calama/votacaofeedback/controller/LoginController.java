/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.controller;

import br.edu.ifro.calama.votacaofeedback.repository.UsuarioRepository;

/**
 *
 * @author Aluno
 */

public class LoginController {
    public boolean realizarLogin(String login, String senha) {
        return new UsuarioRepository().autenticar(login, senha);
    }
}

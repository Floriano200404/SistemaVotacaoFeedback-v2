/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.controller;

import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.repository.UsuarioRepository;
import br.edu.ifro.calama.votacaofeedback.service.EmailService;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Athos
 */
public class RecuperacaoSenhaController {
    
    public boolean iniciarRecuperacao(String email) throws Exception {
        UsuarioRepository usuarioRepo = new UsuarioRepository();
        Usuario usuario = usuarioRepo.buscarPorEmail(email);

        if (usuario == null) {
            return false;
        }

        String token = String.format("%06d", new Random().nextInt(999999));
        
        long tempoExpiracao = System.currentTimeMillis() + (15 * 60 * 1000);
        Date dataExpiracao = new Date(tempoExpiracao);

        usuarioRepo.salvarToken(usuario.getId(), token, dataExpiracao);
        
        EmailService emailService = new EmailService();
        emailService.enviarEmailDeRecuperacao(usuario.getEmail(), token);
        
        return true;
    }
    
    public boolean verificarToken(String email, String tokenDigitado) throws Exception {
        UsuarioRepository usuarioRepo = new UsuarioRepository();
        Usuario usuario = usuarioRepo.buscarPorEmail(email);

        System.out.println("\n--- INICIANDO DEBUG DA VERIFICAÇÃO DE TOKEN ---");

        if (usuario == null || usuario.getToken() == null || usuario.getTokenExpiracao() == null) {
            System.out.println("[DEBUG] Falha: Usuário ou token no banco é nulo.");
            return false;
        }

        String tokenDoBanco = usuario.getToken();
        System.out.println("[DEBUG] Token Digitado: '" + tokenDigitado + "'");
        System.out.println("[DEBUG] Token do Banco:  '" + tokenDoBanco + "'");
        boolean tokensCoincidem = tokenDigitado.equals(tokenDoBanco);
        System.out.println("[DEBUG] >> Os tokens coincidem? " + tokensCoincidem);

        Date agora = new Date();
        Date dataDeExpiracao = usuario.getTokenExpiracao();
        System.out.println("[DEBUG] Hora Atual:      " + agora);
        System.out.println("[DEBUG] Expira Em:       " + dataDeExpiracao);
        boolean naoExpirou = agora.before(dataDeExpiracao);
        System.out.println("[DEBUG] >> O token não expirou? " + naoExpirou);

        boolean resultadoFinal = tokensCoincidem && naoExpirou;
        System.out.println("[DEBUG] >> RESULTADO FINAL DA VALIDAÇÃO: " + resultadoFinal);
        System.out.println("------------------------------------------------\n");

        return resultadoFinal;
}

    public boolean redefinirSenha(String email, String novaSenha) throws Exception {
        UsuarioRepository usuarioRepo = new UsuarioRepository();
        Usuario usuario = usuarioRepo.buscarPorEmail(email);

        if (usuario == null) {
            return false;
        }

        usuarioRepo.atualizarSenha(usuario.getId(), novaSenha);
        return true;
    }
    
    public boolean isSenhaAnterior(String email, String novaSenha) throws Exception {
        UsuarioRepository usuarioRepo = new UsuarioRepository();
        return usuarioRepo.verificarSenhaAnterior(email, novaSenha);
    }
}


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

        // Se o usuário não existir, a operação falha.
        if (usuario == null) {
            return false;
        }

        // Gera um token numérico de 6 dígitos
        String token = String.format("%06d", new Random().nextInt(999999));
        
        // Define a expiração para 15 minutos a partir de agora
        long tempoExpiracao = System.currentTimeMillis() + (15 * 60 * 1000); // 15 min
        Date dataExpiracao = new Date(tempoExpiracao);

        // Salva o token e a data de expiração no banco
        usuarioRepo.salvarToken(usuario.getId(), token, dataExpiracao);
        
        // "Envia" o e-mail
        EmailService emailService = new EmailService();
        emailService.enviarEmailDeRecuperacao(usuario.getEmail(), token);
        
        // Se chegou até aqui, a operação foi um sucesso.
        return true;
    }
}

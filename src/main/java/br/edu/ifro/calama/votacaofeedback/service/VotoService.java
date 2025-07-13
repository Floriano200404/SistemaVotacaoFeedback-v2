package br.edu.ifro.calama.votacaofeedback.service;

// ... seus imports ...
import br.edu.ifro.calama.votacaofeedback.model.OpcaoVoto;
import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.model.Voto;
import br.edu.ifro.calama.votacaofeedback.repository.VotoRepository;

public class VotoService {

    private final VotoRepository votoRepository;

    public VotoService() {
        this.votoRepository = new VotoRepository();
    }

    public void registrarVoto(Votacao votacao, OpcaoVoto opcaoSelecionada, Usuario usuario) throws Exception {
        System.out.println("--- 2. VotoService.registrarVoto chamado ---");

        boolean jaVotou = votoRepository.usuarioJaVotou(usuario.getId(), votacao.getIdVotacao());

        if (jaVotou) {
            System.out.println("--- ERRO: Usuário já votou! ---");
            throw new Exception("Você já votou nesta eleição!");
        } else {
            System.out.println("--- 3. Verificação 'usuarioJaVotou' passou. Tentando salvar... ---");
            Voto novoVoto = new Voto();
            novoVoto.setIdUsuario(usuario.getId());
            novoVoto.setIdVotacao(votacao.getIdVotacao());
            novoVoto.setIdOpcao(opcaoSelecionada.getIdOpcaoVoto());

            votoRepository.salvar(novoVoto);
        }
    }
}
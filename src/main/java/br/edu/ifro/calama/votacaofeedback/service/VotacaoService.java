package br.edu.ifro.calama.votacaofeedback.service;

// Imports necessários
import br.edu.ifro.calama.votacaofeedback.model.Usuario;
import br.edu.ifro.calama.votacaofeedback.model.Votacao;
import br.edu.ifro.calama.votacaofeedback.repository.VotacaoRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe contém a lógica de negócio para o gerenciamento de votações.
 * Ela atua como uma ponte entre a View (telas) e o Repository (banco de dados).
 */
public class VotacaoService {

    private VotacaoRepository votacaoRepository;


    public VotacaoService() {
        this.votacaoRepository = new VotacaoRepository();
    }

   
    public void aprovarVotacao(int idVotacao) {
        try {
            // Chama o método do repositório para fazer o UPDATE no banco
            votacaoRepository.atualizarStatus(idVotacao, "APROVADA");
            System.out.println("SERVIÇO: Votação " + idVotacao + " aprovada com sucesso.");
        } catch (Exception e) {
            System.err.println("ERRO NO SERVIÇO ao aprovar votação: " + e.getMessage());
            e.printStackTrace();
            // Aqui você poderia lançar uma exceção customizada ou tratar o erro
        }
    }


    public void reprovarVotacao(int idVotacao) {
        try {
            // Chama o mesmo método do repositório, mas com um status diferente
            votacaoRepository.atualizarStatus(idVotacao, "REPROVADA");
            System.out.println("SERVIÇO: Votação " + idVotacao + " reprovada com sucesso.");
        } catch (Exception e) {
            System.err.println("ERRO NO SERVIÇO ao reprovar votação: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    public List<Votacao> buscarAtivasPorUsuario(Usuario usuario) throws Exception {
    
    return votacaoRepository.buscarAtivasPorUsuario(usuario);
}

   
    public List<Votacao> getVotacoesParaAprovar() {
        try {
            return votacaoRepository.buscarPendentes();
        } catch (Exception e) {
            System.err.println("ERRO NO SERVIÇO ao buscar votações pendentes: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
        }
    }
}
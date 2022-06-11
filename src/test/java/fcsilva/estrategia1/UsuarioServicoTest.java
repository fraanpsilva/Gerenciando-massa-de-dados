package fcsilva.estrategia1;


import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.service.UsuarioService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // ordem de execução dos testes

public class UsuarioServicoTest {

    private UsuarioService servico = new UsuarioService();

    @Test
    public void CaseTest1_inserirConta() throws Exception {
        Usuario user = new Usuario("Usuario 1", "user@gmail.com", "pwd");
        servico.salvar(user);


    }

    @Test
    public void CaseTest2_consultarConta(){

    }

    @Test
    public void CaseTest3_editarConta(){

    }

    @Test
    public void CaseTest4_excluirConta(){

    }
}



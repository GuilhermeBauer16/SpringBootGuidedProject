package med.voll.api.doMain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.doMain.endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    private String telefone;

    private boolean ativo;
    private String cpf;

    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dadosPaciente) {
        this.ativo = true;
        this.nome = dadosPaciente.nome();
        this.email = dadosPaciente.email();
        this.telefone = dadosPaciente.telefone();
        this.cpf = dadosPaciente.cpf();
        this.endereco = new Endereco(dadosPaciente.endereco());
    }

    public void excluir() {
        this.ativo = false;
    }

    public void atualizaImformacoes(DadosAtualizarPaciente atualizarPaciente) {

        if (atualizarPaciente.nome() != null) {
            this.nome = atualizarPaciente.nome();
        }
        if (atualizarPaciente.telefone() != null) {
            this.telefone = atualizarPaciente.telefone();
        }
        if (atualizarPaciente.endereco() != null) {
            endereco.atualizaEndereco(atualizarPaciente.endereco());
        }

    }
}

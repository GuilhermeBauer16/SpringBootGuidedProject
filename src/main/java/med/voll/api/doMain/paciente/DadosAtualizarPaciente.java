package med.voll.api.doMain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.doMain.endereco.DadosEndereco;

public record DadosAtualizarPaciente(

        @NotNull
        Long id ,
        String nome ,
        String telefone ,
        DadosEndereco endereco
) {
}

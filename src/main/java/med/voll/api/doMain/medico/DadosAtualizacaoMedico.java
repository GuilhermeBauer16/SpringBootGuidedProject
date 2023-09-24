package med.voll.api.doMain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.doMain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {

}

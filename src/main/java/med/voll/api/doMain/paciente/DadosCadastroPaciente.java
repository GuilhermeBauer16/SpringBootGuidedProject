package med.voll.api.doMain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.doMain.endereco.DadosEndereco;

public record DadosCadastroPaciente(

        @NotBlank
        String nome,

        @NotBlank
        String telefone,

        @NotBlank
//        @CPF
        @Pattern(regexp = "\\d{11}")
        String cpf,

        @NotBlank
        @Email
        String email,

        @NotNull
        @Valid
        DadosEndereco endereco

) {
}

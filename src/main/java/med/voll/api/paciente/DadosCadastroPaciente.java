package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.endereco.Endereco;
import org.hibernate.validator.constraints.br.CPF;

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
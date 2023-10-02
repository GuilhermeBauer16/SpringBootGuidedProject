package med.voll.api.doMain.consulta.agendamento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.doMain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @Future
        @NotNull
        LocalDateTime data ,
        Especialidade especialidade
) {
}

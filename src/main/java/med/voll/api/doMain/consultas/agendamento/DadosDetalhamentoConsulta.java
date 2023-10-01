package med.voll.api.doMain.consultas.agendamento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.doMain.consultas.Consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id , Long idMedico, Long idPaciente, LocalDateTime data) {
    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId() , consulta.getMedico().getId() , consulta.getPaciente().getId() ,consulta.getData());
    }
}

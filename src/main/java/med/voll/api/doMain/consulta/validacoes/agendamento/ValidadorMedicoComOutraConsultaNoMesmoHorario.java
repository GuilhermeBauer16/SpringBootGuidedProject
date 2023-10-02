package med.voll.api.doMain.consulta.validacoes.agendamento;

import med.voll.api.doMain.ValidacaoException;
import med.voll.api.doMain.consulta.ConsultaRepository;
import med.voll.api.doMain.consulta.agendamento.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamendoDeConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;

    public  void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico() , dados.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario){
            throw new ValidacaoException("Médico já possui outra consulta agendada ness mesmo horário");
        }
    }


}

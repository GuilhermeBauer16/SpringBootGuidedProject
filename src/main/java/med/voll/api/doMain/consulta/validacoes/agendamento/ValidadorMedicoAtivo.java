package med.voll.api.doMain.consulta.validacoes.agendamento;

import med.voll.api.doMain.ValidacaoException;
import med.voll.api.doMain.consulta.agendamento.DadosAgendamentoConsulta;
import med.voll.api.doMain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamendoDeConsulta {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados){
//        escolha medico opcional

        if(dados.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());

        if(!medicoEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluido");
        }

    }
}

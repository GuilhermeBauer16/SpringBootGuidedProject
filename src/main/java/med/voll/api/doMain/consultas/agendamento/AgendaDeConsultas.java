package med.voll.api.doMain.consultas.agendamento;

import med.voll.api.doMain.ValidacaoException;
import med.voll.api.doMain.consultas.Consulta;
import med.voll.api.doMain.consultas.ConsultaRepository;
import med.voll.api.doMain.consultas.cancelamento.DadosCancelamentoConsulta;
import med.voll.api.doMain.consultas.validacoes.agendamento.ValidadorAgendamendoDeConsulta;
import med.voll.api.doMain.consultas.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.voll.api.doMain.medico.Medico;
import med.voll.api.doMain.medico.MedicoRepository;
import med.voll.api.doMain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository ;


    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;
    @Autowired

    private List<ValidadorAgendamendoDeConsulta> validadores ;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("ID do paciente não encontrado!");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }



        validadores.forEach( v -> v.validar(dados));
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);

        if (medico == null){
            throw new ValidacaoException("Nao existe nenhum medico disponivel nesta data!");
        }
        var consulta = new Consulta(null , medico , paciente , dados.data() ,null);
        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);

    }

    public void cancelar(DadosCancelamentoConsulta dados){
        if(!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id imformado da consulta não existe");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {

        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }


        if ( dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatoria quando o médico não for imformado");
        }



        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade() , dados.data());
    }
}

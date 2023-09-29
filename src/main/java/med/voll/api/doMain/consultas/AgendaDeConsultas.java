package med.voll.api.doMain.consultas;

import med.voll.api.doMain.ValidacaoException;
import med.voll.api.doMain.medico.Medico;
import med.voll.api.doMain.medico.MedicoRepository;
import med.voll.api.doMain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired

    private PacienteRepository pacienteRepository ;
    public void agendar(DadosAgendamentoConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("ID do paciente não encontrado!");
        }

        if(dados.idMedico() != null &&  medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("ID do médico não encontrado!");
        }
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null , medico , paciente , dados.data());
        consultaRepository.save(consulta);

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

package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doMain.consultas.agendamento.AgendaDeConsultas;
import med.voll.api.doMain.consultas.agendamento.DadosAgendamentoConsulta;
import med.voll.api.doMain.consultas.agendamento.DadosDetalhamentoConsulta;
import med.voll.api.doMain.consultas.cancelamento.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;
    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados){
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}

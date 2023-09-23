package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository repository;

    @PostMapping
    @Transactional

    public ResponseEntity cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dadosPaciente, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dadosPaciente);
        repository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));


    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listagemPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByativoTrue(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarPaciente(@RequestBody @Valid DadosAtualizarPaciente atualizarPaciente) {

        var paciente = repository.getReferenceById(atualizarPaciente.id());
        paciente.atualizaImformacoes(atualizarPaciente);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));


    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarPaciente(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhamentoPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
}

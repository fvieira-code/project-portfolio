package br.com.project.portfolio.controller;

import br.com.project.portfolio.model.Pessoa;
import br.com.project.portfolio.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository;

	@GetMapping("/pessoas")
	public ResponseEntity<List<Pessoa>> getAllPessoas(@RequestParam(required = false) String nome) {
		try {
			List<Pessoa> pessoas = new ArrayList<Pessoa>();

			if (nome == null)
				pessoaRepository.findAll().forEach(pessoas::add);
			else
				pessoaRepository.findByNome(nome).forEach(pessoas::add);

			if (pessoas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(pessoas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/pessoas/{id}")
	public ResponseEntity<Pessoa> getPessoaById(@PathVariable("id") long id) {
		Optional<Pessoa> pessoaData = pessoaRepository.findById(id);

		if (pessoaData.isPresent()) {
			return new ResponseEntity<>(pessoaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/pessoas")
	public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
		try {
			Pessoa newPessoa = pessoaRepository
					.save(new Pessoa(pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getCpf(), pessoa.getFuncionario()));
			return new ResponseEntity<>(newPessoa, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/pessoas/{id}")
	public ResponseEntity<Pessoa> updatePessoa(@PathVariable("id") long id, @RequestBody Pessoa pessoa) {
		Optional<Pessoa> pessoaData = pessoaRepository.findById(id);

		if (pessoaData.isPresent()) {
			Pessoa newPessoa = pessoaData.get();
			newPessoa.setNome(pessoa.getNome());
			newPessoa.setDataNascimento(pessoa.getDataNascimento());
			newPessoa.setCpf(pessoa.getCpf());
			newPessoa.setFuncionario(pessoa.getFuncionario());

			return new ResponseEntity<>(pessoaRepository.save(newPessoa), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/pessoas/{id}")
	public ResponseEntity<HttpStatus> deletePessoa(@PathVariable("id") long id) {
		try {
			pessoaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/pessoas")
	public ResponseEntity<HttpStatus> deleteAllPessoas() {
		try {
			pessoaRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/pessoas/funcionario")
	public ResponseEntity<List<Pessoa>> findByFuncionario() {
		try {
			List<Pessoa> pessoas = pessoaRepository.findByFuncionario(true);

			if (pessoas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(pessoas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

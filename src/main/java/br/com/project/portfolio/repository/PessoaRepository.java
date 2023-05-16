package br.com.project.portfolio.repository;

import br.com.project.portfolio.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

  List<Pessoa> findByFuncionario(boolean funcionario);

  List<Pessoa> findByNome(String nome);

}

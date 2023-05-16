package br.com.project.portfolio.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pessoa")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "datanascimento")
	private Date dataNascimento;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "funcionario")
	private Boolean funcionario;

	public Pessoa(String nome, Date dataNascimento, String cpf, Boolean funcionario) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.funcionario = funcionario;
	}
}

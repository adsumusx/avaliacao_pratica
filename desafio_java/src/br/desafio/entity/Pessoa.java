package br.desafio.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tb_pessoa")
@NamedQuery(name="Pessoa.findAll", query="SELECT p FROM Pessoa p")
public class Pessoa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(name="nome_pessoa", nullable=true)
	private String nomePessoa;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy" ,locale = "pt-BR", timezone = "Brazil/East")
	@Column(name="dt_cadastro", nullable=true)
	private Date dtCadastro;
	
	@Column(name="cadastrante", nullable=true)
	private String cadastrante;
	
	@Column(name="documento", nullable=true)
	private String documento;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy" ,locale = "pt-BR", timezone = "Brazil/East")
	@Column(name="dt_nascimento", nullable=true)
	private Date dtNascimento;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy" ,locale = "pt-BR", timezone = "Brazil/East")
	@Column(name="dt_termino", nullable=true)
	private Date dtTermino;
	
	@Column(name="nm_pai", nullable=true)
	private String nmPai;
	
	@Column(name="nm_mae", nullable=true)
	private String nmMae;	
	
	@Column(name="tp_pessoa", nullable=true)
	private String tpPessoa;
	
	@Column(name="login_operador", nullable=true)
	private String loginOperador;
	
//	@Column(name="valor_dotacao", nullable=true)
//	private String valorDotacao;
	
	@Column(name="nr_sei", nullable=true)
	private String nrSEI;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

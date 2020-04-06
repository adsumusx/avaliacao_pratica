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
@Table(name = "tb_operador")
@NamedQuery(name="Operador.findAll", query="SELECT o FROM Operador o")
public class Operador implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(name="nome_operador", nullable=true)
	private String nomeOperador;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy" ,locale = "pt-BR", timezone = "Brazil/East")
	@Column(name="dt_cadastro", nullable=true)
	private Date dtCadastro;
	
	@Column(name="login_operador", nullable=true)
	private String loginOperador;
	
	@Column(name="senha_operador", nullable=true)
	private String senhaOperador;
	
	@Column(name="perfil_operador", nullable=true)
	private String perfilOperador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeOperador() {
		return nomeOperador;
	}

	public void setNomeOperador(String nomeOperador) {
		this.nomeOperador = nomeOperador;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getLoginOperador() {
		return loginOperador;
	}

	public void setLoginOperador(String loginOperador) {
		this.loginOperador = loginOperador;
	}

	public String getPerfilOperador() {
		return perfilOperador;
	}

	public void setPerfilOperador(String perfilOperador) {
		this.perfilOperador = perfilOperador;
	}

	public String getSenhaOperador() {
		return senhaOperador;
	}

	public void setSenhaOperador(String senhaOperador) {
		this.senhaOperador = senhaOperador;
	}

	

}

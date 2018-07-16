package br.com.bmsti.produto.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProdutoDTO {
	
	private Integer id;
	private String nome;
	private String descricao;
	private Float valorUnitario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotNull(message = "Campo não pode ser vazio!")
	@Size(min = 5, max = 100, message = "Campo deve conter entre 5 a 100 caracteres!")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull(message = "Campo não pode ser vazio!")
	@Size(min = 10, message = "Campo deve conter mais de 10 caracteres!")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	@NotNull(message = "Campo não pode ser vazio!")
	public Float getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@Override
	public String toString() {
		return "ProdutoDTO [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", valorUnitario="
				+ valorUnitario + "]";
	}

}

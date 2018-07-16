package br.com.bmsti.produto.response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

	private T dado;
	private List<String> erros;

	public Response() {
	}

	public T getDado() {
		return dado;
	}

	public void setDado(T dado) {
		this.dado = dado;
	}

	public List<String> getErros() {
		if (this.erros == null) {
			this.erros = new ArrayList<String>();
		}
		return erros;
	}

	public void setErrors(List<String> errors) {
		this.erros = errors;
	}

}

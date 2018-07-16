package br.com.bmsti.produto.service;

import java.util.List;
import java.util.Optional;

import br.com.bmsti.produto.entity.Produto;

public interface ProdutoService {
		
	Produto inserir(Produto produto);
	
	Produto atualizar(Produto produto);
	
	Produto obterPorId(Integer id);
	
	Optional<Produto> obterPorNome(String nome);
	
	List<Produto> obterTodos();
	
	void excluir(Integer id);
	
	boolean produtoExiste(Produto produto);

}

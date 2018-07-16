package br.com.bmsti.produto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bmsti.produto.entity.Produto;
import br.com.bmsti.produto.repository.ProdutoRepository;
import br.com.bmsti.produto.service.ProdutoService;

@Service("produtoService")
@Transactional
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Produto inserir(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public Produto atualizar(Produto produto) {
		return this.inserir(produto);
	}

	@Override
	public Produto obterPorId(Integer id) {
		return produtoRepository.findOne(id);
	}
	
	@Override
	public Optional<Produto> obterPorNome(String nome) {
		return produtoRepository.findByNome(nome);
	}

	@Override
	public List<Produto> obterTodos() {
		return produtoRepository.findAll();
	}

	@Override
	public void excluir(Integer id) {
		produtoRepository.delete(id);
	}

	@Override
	public boolean produtoExiste(Produto produto) {		
		return this.obterPorId(produto.getId()) != null;
	}
	
}

package br.com.bmsti.produto.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bmsti.produto.dto.ProdutoDTO;
import br.com.bmsti.produto.entity.Produto;
import br.com.bmsti.produto.response.Response;
import br.com.bmsti.produto.service.ProdutoService;

/**
 * 
 * @author angelobms
 *
 */
@RestController
@RequestMapping("/api/v1/produto/")
public class ProdutoController {

	public static final Logger log = LoggerFactory.getLogger(ProdutoController.class);

	@Autowired
	private ProdutoService produtoService;

	/**
	 * Cadastrar produto.
	 * 
	 * @param produtoDTO
	 * @param result
	 * @return response
	 */
	@PostMapping
	public ResponseEntity<Response<Produto>> cadastrar(@Valid @RequestBody ProdutoDTO produtoDTO,
			BindingResult result) {

		log.info("Cadastro: {}", produtoDTO.toString());
		Response<Produto> response = new Response<>();
		try {

			Produto produto = this.converterDTO(produtoDTO);

			if (result.hasErrors()) {
				log.error("Erro ao validar dados de cadastro do Produto: {}", result.getAllErrors());
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}

			Produto produtoCadastrado = (Produto) this.produtoService.inserir(produto);
			response.setDado(produtoCadastrado);

		} catch (Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * Alterar produto.
	 * 
	 * @param produtoDTO
	 * @param result
	 * @return response
	 */
	@PutMapping(value = "{id}")
	public ResponseEntity<?> alterar(@RequestBody ProdutoDTO produtoDTO, BindingResult result) {

		log.info("Alterando: {}", produtoDTO.toString());
		Response<Produto> response = new Response<>();

		try {

			Produto produto = converterDTO(produtoDTO);
			this.validarAlterar(produto, result);

			if (result.hasErrors()) {
				log.error("Erro ao validar dados para alterar o Produto: {}", result.getAllErrors());
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return new ResponseEntity<Object>(response.getErros(), HttpStatus.NOT_FOUND);
			}

			Produto produtoAtual = this.produtoService.obterPorId(produto.getId());
			produtoAtual.setNome(produto.getNome());
			produtoAtual.setDescricao(produto.getDescricao());
			produtoAtual.setValorUnitario(produto.getValorUnitario());

			Produto produtoAlterado = this.produtoService.atualizar(produtoAtual);
			response.setDado(produtoAlterado);
			
			return ResponseEntity.ok(response.getDado());

		} catch (Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
	}

	/**
	 * Valida se dados necessarios para a alteração do Produto foi informado.
	 * 
	 * @param produto
	 */
	private void validarAlterar(Produto produto, BindingResult result) {
		if (produto.getId() == null) {
			result.addError(new ObjectError("Produto", "ID não informado"));
			return;
		}
	}

	/**
	 * Recuperar produto pelo ID.
	 * 
	 * @param id
	 * @return response
	 */
	@GetMapping(value = "{id}")
	public ResponseEntity<?> obterPorId(@PathVariable("id") Integer id) {

		log.info("Recuperando Produto com ID: {}", id);
		Response<Produto> response = new Response<>();

		try {

			Produto produto = this.produtoService.obterPorId(id);
			if (produto == null) {
				return new ResponseEntity<Object>("Produto com ID {} não encontrado: " + id, HttpStatus.NOT_FOUND);
			}
			
			response.setDado(produto);

		} catch (Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response.getDado());
	}

	/**
	 * Recuperar todos os produtos.
	 * 
	 * @return ResponseEntity
	 */
	@GetMapping()
	public ResponseEntity<?> obterTodos() {
		
		log.info("Recuperando todos os produtos");
		Response<List<Produto>> response = new Response<>();
		
		try {
			
			List<Produto> produtos = this.produtoService.obterTodos();
			if (produtos.isEmpty()) {				
				return new ResponseEntity<Object>("Não existe produtos cadastrados", HttpStatus.NO_CONTENT);
			}
			
			response.setDado(produtos);
			
		} catch (Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response.getDado());
	}

	/**
	 * Excluir produto pelo ID.
	 * 
	 * @param id
	 * @return ResponseEntity
	 */
	@DeleteMapping(value = "{id}")
	public ResponseEntity<?> excluir(@PathVariable("id") Integer id) {
	
		log.info("Recuperando e excluindo produto com ID {}", id);
		Response<Produto> response = new Response<>();

		try {

			Produto produto = this.produtoService.obterPorId(id);
			if (produto == null) {
				return new ResponseEntity<Object>("Produto com ID {} não encontrado: " + id, HttpStatus.NOT_FOUND);
			}
			
			response.setDado(produto);
			this.produtoService.excluir(id);			
			
		} catch (Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}		
		
		return ResponseEntity.ok(response.getDado());		
	}
	
	/**
	 * Converte DTO para a entidade Produto
	 * 
	 * @param produtoDTO
	 * @return produto
	 */
	private Produto converterDTO(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		produto.setId(produtoDTO.getId());
		produto.setNome(produtoDTO.getNome());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setValorUnitario(produtoDTO.getValorUnitario());

		return produto;
	}

}

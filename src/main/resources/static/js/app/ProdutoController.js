'use strict';
 
angular.module('appProduto').controller('ProdutoController',
    ['ProdutoService', '$scope',  function( ProdutoService, $scope) {
 
        var produtoCtrl = this;
        produtoCtrl.produto = {};
        produtoCtrl.produtos = [];
 
        produtoCtrl.submit = submit;
        produtoCtrl.obterTodosProdutos = obterTodosProdutos;
        produtoCtrl.criarProduto = criarProduto;
        produtoCtrl.atualizarProduto = atualizarProduto;
        produtoCtrl.removerProduto = removerProduto;
        produtoCtrl.alterarProduto = alterarProduto;
        produtoCtrl.reset = reset;
 
        produtoCtrl.mensagemSucesso = '';
        produtoCtrl.mensagemErro = '';
        produtoCtrl.done = false;
 
        produtoCtrl.somenteNumeros = /^\d+([.]\d+)?$/;
 
        function submit() {
            console.log('Enviando');
            if (produtoCtrl.produto.id === undefined || produtoCtrl.produto.id === null) {
                console.log('Salvado novo produto', produtoCtrl.produto);
                criarProduto(produtoCtrl.produto);
            } else {
                atualizarProduto(produtoCtrl.produto, produtoCtrl.produto.id);
                console.log('Produto com ID ' + produtoCtrl.produto.id + 'atualizado.');
            }
        }
 
        function criarProduto(produto) {
            console.log('Preste a criar o produto');
            ProdutoService.criarProduto(produto)
                .then(
                    function (response) {
                        console.log('Produto criado com sucesso');
                        produtoCtrl.mensagemSucesso = 'Produto criado com sucesso';
                        produtoCtrl.mensagemErro = '';
                        produtoCtrl.done = true;
                        produtoCtrl.produto = {};
                        $scope.produtoForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Erro ao criar produto');
                        self.mensagemErro = 'Erro ao criar produto: ' + errResponse.data.errorMessage;
                        self.mensagemSucesso = '';
                    }
                );
        }
 
        function atualizarProduto(produto, id){
            console.log('Preste a atualizar o produto');
            ProdutoService.atualizarProduto(produto, id)
                .then(
                    function (response){
                        console.log('Produto atualizado com sucesso');
                        produtoCtrl.mensagemSucesso = 'Produto atualizado com sucesso';
                        produtoCtrl.mensagemErro = '';
                        produtoCtrl.done = true;                        
                        $scope.produtoForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Erro ao atualizar produto');
                        produtoCtrl.mensagemErro = 'Erro ao atualizar produto' + errResponse.data;
                        produtoCtrl.mensagemSucesso = '';
                    }
                );
        }
 
        function removerProduto(id){
            console.log('Preste a remover produto com ID: ' + id);
            ProdutoService.removerProduto(id)
                .then(
                    function(){
                        console.log('Produto ' + id + ' removido com sucesso');
                    },
                    function(errResponse){
                        console.error('Erro ao remover produto com ID: ' + id + ', Erro:' + errResponse.data);
                    }
                );
        }
 
        function obterTodosProdutos(){
            return ProdutoService.obterTodosProdutos();
        }
 
        function alterarProduto(id) {
            produtoCtrl.mensagemSucesso = '';
            produtoCtrl.mensagemErro = '';
            ProdutoService.obterProduto(id).then(            		
                function (produto) {
                	
                	console.log(produto);
                    produtoCtrl.produto = produto;
                },
                function (errResponse) {
                    console.error('Erro ao obter produto com:' + id + ', Erro:' + errResponse.data);
                }
            );
        }
        
        function reset(){
        	produtoCtrl.mensagemSucesso = '';
            produtoCtrl.mensagemErro = '';
        	produtoCtrl.produto = {};
            $scope.produtoForm.$setPristine(); //reset form
        }
    }
    ]);
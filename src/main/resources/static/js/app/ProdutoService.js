'use strict';
 
angular.module('appProduto').factory('ProdutoService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
        		carregarTodosProdutos: carregarTodosProdutos,
                obterTodosProdutos: obterTodosProdutos,
                obterProduto: obterProduto,
                criarProduto: criarProduto,
                atualizarProduto: atualizarProduto,
                removerProduto: removerProduto
            };
 
            return factory;
 
            function carregarTodosProdutos() {
                console.log('Carregando todos produtos');
                var produtos = null;
                
                var deferred = $q.defer();
                $http.get(urls.PRODUTO_API)
                    .then(
                        function (response) {
                            console.log('Todos os produtos recuperados com sucesso');                                                                                                              
                            $localStorage.produtos = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.log('Erro enquanto carregando produtos');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function obterTodosProdutos(){
                return $localStorage.produtos;
            }
 
            function obterProduto(id) {
                console.log('Recuperando produto com ID: ' + id);
                var deferred = $q.defer();
                $http.get(urls.PRODUTO_API + id)
                    .then(
                        function (response) {
                            console.log('Produto com ID ' + id + ' recuperado com sucesso.');                                                     
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.log('Erro ao carregar o produto com o id: ' + id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function criarProduto(produto) {
                console.log('Criando produto');
                var deferred = $q.defer();
                $http.post(urls.PRODUTO_API, produto)
                    .then(
                        function (response) {
                        	carregarTodosProdutos();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.log('Erro ao criar o produto: ' + errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function atualizarProduto(produto, id) {
                console.log('Atualizando produto com ID: ' + id);
                var deferred = $q.defer();
                $http.put(urls.PRODUTO_API + id, produto)
                    .then(
                        function (response) {
                        	carregarTodosProdutos();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.log('Erro ao atualizar produto com ID:' + id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function removerProduto(id) {
                console.log('Removendo produto com ID: ' + id);
                var deferred = $q.defer();
                $http.delete(urls.PRODUTO_API + id)
                    .then(
                        function (response) {
                        	carregarTodosProdutos();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.log('Erro ao remover produto com ID: ' + id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
        }
    ]);
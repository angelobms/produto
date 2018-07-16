var app = angular.module('appProduto',['ui.router','ngStorage']);
 
app.constant('urls', {
    BASE: 'http://localhost:8080/AppProduto',
    PRODUTO_API : 'http://localhost:8080/AppProduto/api/v1/produto/'
});
 
app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
 
        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'parcial/lista',
                controller:'ProdutoController',
                controllerAs:'ctrl',
                resolve: {
                    produtos: function ($q, ProdutoService) {
                        console.log('Caregando todos os usuários');
                        var deferred = $q.defer();
                        ProdutoService.carregarTodosProdutos().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);
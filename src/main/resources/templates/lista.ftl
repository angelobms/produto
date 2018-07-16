<div class="container">  	
  	<div class="jumbotron jumbotron-fluid">
      <div class="container text-center">
          <h1>Cadastro Produto</h1>
      </div>
  	</div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Produto </span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.mensagemSucesso">{{ctrl.mensagemSucesso}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.mensagemErro">{{ctrl.mensagemErro}}</div>
                <form ng-submit="ctrl.submit()" name="produtoForm" class="form-horizontal">
                    <input type="hidden" ng-model="ctrl.produto.id" />
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="nome">Nome</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.produto.nome" id="nome" class="form-control input-sm" placeholder="Digite o nome" required ng-minlength="5"/>
                            </div>
                        </div>
                    </div>
 
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="descricao">Descrição</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.produto.descricao" id="descricao" class="form-control input-sm" placeholder="Digite a descrição." required ng-minlength="10"/>
                            </div>
                        </div>
                    </div>                                    
     
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="valor_unitario">Valor Unitário</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.produto.valorUnitario" id="valor_unitario" class="form-control input-sm" placeholder="Digite o valor unitário." required ng-pattern="ctrl.somenteNumeros"/>
                            </div>
                        </div>
                    </div>
 
                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit"  value="{{!ctrl.produto.id ? 'Cadastrar' : 'Atualizar'}}" class="btn btn-primary btn-sm" ng-disabled="produtoForm.$invalid || produtoForm.$pristine">
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-sm" ng-disabled="produtoForm.$pristine">Limpar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>    
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Lista de Produtos </span></div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Descrição</th>                        
                        <th>Valor Unitário</th>
                        <th width="100"></th>
                        <th width="100"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="p in ctrl.obterTodosProdutos()">
                        <td>{{p.id}}</td>
                        <td>{{p.nome}}</td>
                        <td>{{p.descricao}}</td>                        
                        <td>{{p.valorUnitario}}</td>
                        <td><button type="button" ng-click="ctrl.alterarProduto(p.id)" class="btn btn-success custom-width">Alterar</button></td>
		                <td><button type="button" ng-click="ctrl.removerProduto(p.id)" class="btn btn-danger custom-width">Excluir</button></td>
                    </tr>
                    </tbody>
                </table>      
            </div>
        </div>
    </div>
</div>
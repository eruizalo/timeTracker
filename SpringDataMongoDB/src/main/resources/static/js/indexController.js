var app = angular.module('TimeTracker', [ "ngResource" ]);

app.controller('indexController', [ '$scope', '$http',
                                     
	function($scope, $http) {
		$scope.getListaEmpleados = function() {
			$http.get('/getEmpleados').success(function(data) {
				$scope.listaEmpleados = data;
			});
		}
} ]);
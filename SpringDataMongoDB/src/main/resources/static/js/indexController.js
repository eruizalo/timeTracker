var app = angular.module('TimeTracker', [ "ngResource" ]);

app.controller('indexController', [ '$scope', '$http',
                                     
	function($scope, $http) {
		
		$scope.numProyectos = -1;
		$scope.numTareasPro = -1;
		$scope.numEmpleados = -1;
		$scope.numClientes  = -1;
		
		$scope.getOverview = function() {
			$http.get('/getNumProyectos').success(function(data) {
				$scope.numProyectos = data;
			});
			$http.get('/getNumTareas').success(function(data) {
				$scope.numTareasPro = data;
			});
			$http.get('/getNumEmpleados').success(function(data) {
				$scope.numEmpleados = data;
			});
			$http.get('/getNumClientes').success(function(data) {
				$scope.numClientes = data;
			});
		};
} ]);
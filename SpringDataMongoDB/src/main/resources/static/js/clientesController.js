var app = angular.module('TimeTracker', [ "ngResource" ]);

app.controller('clientesController', [ '$scope', '$http',
                                     
	function($scope, $http) {
	
		$scope.empleadoLogueado = 'No one logged in';
		
		$http.get('/getEmpleadoLogueado').success(function(data) {
			$scope.empleado = data;
		});
		
		$scope.getClientes = function() {
			$http.get('/getClientes').success(function(data) {
				$scope.clientes = data;
			});
		};
		
		$scope.getFecha = function (data){
			if (data == null){
				return '-';
			}
			var fecha = new Date(data).toLocaleDateString();
			return fecha;
		};
		
} ]);
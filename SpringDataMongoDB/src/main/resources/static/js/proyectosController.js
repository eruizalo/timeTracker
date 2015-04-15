var app = angular.module('TimeTracker', [ "ngResource" ]);

app.controller('proyectosController', [ '$scope', '$http',
                                     
	function($scope, $http) {
	
		$scope.empleadoLogueado = 'No one logged in';
		
		$http.get('/getEmpleadoLogueado').success(function(data) {
			$scope.empleado = data;
		});
		
		$scope.getProyectos = function() {
			$http.get('/getProyectos').success(function(data) {
				$scope.proyectos = data;
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
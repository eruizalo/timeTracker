var app = angular.module('TimeTracker', [ "ngResource" ]);

app.controller('generarFacturaController', [ '$scope', '$http',
                                     
	function($scope, $http) {
		
		$http.get('/getEmpleadoLogueado').success(function(data) {
			$scope.empleado = data;
		});
		
		$http.get('/getCliente').success(function(data) {
			$scope.cliente = data;
		});
		
		$http.get('/getProyectosCliente').success(function(data) {
			$scope.proyectosCliente = data;
			console.log(data);
		});
		
		$http.get('/getPerfiles').success(function(data) {
			$scope.listaPerfiles = data;
		});
		
		$scope.getPerfil = function (data){
			var perfil = 'No encontrado';
			var i = 0;
			if($scope.listaPerfiles != null) {
				while (i<$scope.listaPerfiles.length){
					if(data === $scope.listaPerfiles[i].id){
						perfil = $scope.listaPerfiles[i].nombre;
					}
					i++;
				}
			}
			
			return perfil;
		};
} ]);
var app = angular.module('TimeTracker', [ "ngResource" ]);

app.controller('clienteController', [ '$scope', '$http',
                                     
	function($scope, $http) {
		
		$http.get('/getEmpleadoLogueado').success(function(data) {
			$scope.empleado = data;
		});
		
		$scope.getCliente = function() {
			$http.get('/getCliente').success(function(data) {
				$scope.cliente = data;
				$scope.cliente.fechaInicio = $scope.getFecha($scope.cliente.fechaInicio);
				$scope.cliente.fechaUltimoProyecto = $scope.getFecha($scope.cliente.fechaUltimoProyecto);
			});
		};
		
		$scope.getFecha = function (data){
			if (data == null){
				return '-';
			}
			var fecha = new Date(data).toLocaleDateString();
			return fecha;
		};
		
		$http.get('/getPerfiles').success(function(data) {
			$scope.listaPerfiles = data;
		});
		
		$scope.getPerfil = function (data){
			var perfil = 'No encontrado';
			var i = 0;
			
			while (i<$scope.listaPerfiles.length){
				if(data === $scope.listaPerfiles[i].id){
					perfil = $scope.listaPerfiles[i].nombre;
				}
				i++;
			}
			
			return perfil;
		};
} ]);
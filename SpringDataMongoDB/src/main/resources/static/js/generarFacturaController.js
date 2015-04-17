var app = angular.module('TimeTracker', [ "ngResource" ]);

app.controller('generarFacturaController', [ '$scope', '$http',
                                     
	function($scope, $http) {
		
		$scope.totalFactura = 0;
		
		$http.get('/getEmpleadoLogueado').success(function(data) {
			$scope.empleado = data;
		});
		
		$http.get('/getCliente').success(function(data) {
			$scope.cliente = data;
		});
		
		$http.get('/getProyectosCliente').success(function(data) {
			$scope.proyectosCliente = data;
			
			if(data != undefined) {
				var i = 0, i2 = 0;
				while (i < data.length){
					i2 = 0;
					while (i2 < data[i].listaTarifasProyecto.length){
						$scope.totalFactura = $scope.totalFactura + 
							(data[i].listaTarifasProyecto[i2].numPerfiles * data[i].listaTarifasProyecto[i2].tarifa);
						i2++;
					}
					i++;
				}
			}
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
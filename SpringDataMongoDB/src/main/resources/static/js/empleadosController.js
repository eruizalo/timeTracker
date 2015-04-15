var app = angular.module('TimeTracker', [ "ngResource" ]);

app.controller('empleadosController', [ '$scope', '$http',
                                     
	function($scope, $http) {
		
		$scope.listaEmpleados = null;
		$scope.listaPerfiles = null;
		
		$http.get('/getPerfiles').success(function(data) {
			$scope.listaPerfiles = data;
		});
		$http.get('/getEmpleados').success(function(data) {
			$scope.listaEmpleados = data;
		});
		$scope.getFecha = function (data){
			var fecha = new Date(data).toLocaleDateString();
			return fecha;
		};
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
		
		$scope.getProyecto = function (data){
			if (null != data) {
				$http.get('/getProyecto?id=' + data).success(function(resp) {
					console.log(resp.nombre);
					return resp.nombre;
				});
			}
		};
	}
]);

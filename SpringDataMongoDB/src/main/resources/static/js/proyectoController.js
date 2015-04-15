var app = angular.module('TimeTracker', [ "ngResource" ]);

app.controller('proyectoController', [ '$scope', '$http',
                                     
	function($scope, $http) {
	
		$scope.empleadoLogueado = 'No one logged in';
		
		$http.get('/getEmpleadoLogueado').success(function(data) {
			data.fechaIncorporacion = new Date(data.fechaIncorporacion).toLocaleDateString();
			$scope.empleado = data;
			$http.get('/getNombrePerfil?id=' + $scope.empleado.perfil).success(function(data) {
				$scope.perfilEmpleado = data;
			});
		});
		
		$scope.proyecto;
		$scope.listaTareas;
		$scope.listaTarifasProyecto = 'Not implemented';
		$scope.listaTareasImputadas;
		
		$scope.listaPerfiles;
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
		
		$scope.getProyecto = function() {
			$http.get('/getProyecto?id=1').success(function(data) {
				$scope.proyecto = data;
				$scope.fechaInicio = $scope.getFecha(data.fechaInicio);
				$scope.fechaFin    = $scope.getFecha(data.fechaFin);
				$http.get('/getListaTareas?id='+$scope.proyecto.id).success(function(lista) {
					$scope.listaTareas = lista;
					
				});
				$http.get('/getImputaciones?id='+$scope.proyecto.id).success(function(listaImputaciones) {
					$scope.listaTareasImputadas = listaImputaciones;
					console.log(listaImputaciones.length);
					
				});
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
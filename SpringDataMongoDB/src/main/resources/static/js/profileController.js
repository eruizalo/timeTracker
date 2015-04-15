var app = angular.module('TimeTracker', [ "ngResource" ]);

app.controller('profileController', [ '$scope', '$http',
                                     
	function($scope, $http) {
		$scope.getEmpleado = function() {
			$http.get('/getEmpleado?id=1').success(function(data) {
			data.fechaIncorporacion = new Date(data.fechaIncorporacion).toLocaleDateString();
				$scope.empleado = data;
				$http.get('/getNombrePerfil?id=' + $scope.empleado.perfil).success(function(data) {
					$scope.perfilEmpleado = data;
				});
			});
		}
} ]);
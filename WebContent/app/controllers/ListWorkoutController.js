(function(w) { 'use strict';

var gymApp = angular.module('gymApp.workout');

gymApp.controller('ListWorkoutController', ['$scope','$http',ListWorkoutController]);

function ListWorkoutController($scope,$http) {

	$http.get('http://localhost:9000/workouts').then(function(res) {
		$scope.workouts = res.data;
		console.log($scope.workouts);
	});	

}

})(window);
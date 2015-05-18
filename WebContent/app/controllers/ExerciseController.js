(function(w) { 'use strict';


var exercise = angular.module('gymApp.exercise');

exercise.controller('ExerciseController', ['$scope','$http','globals',ExerciseController]);

function ExerciseController($scope,$http,globals) {

	$scope.groups = [];

	$http.get( globals.url + 'groups' , {} ).then(function(response) {

		$scope.groups = response.data;
		console.log($scope.groups);
	});	
}

})(window);
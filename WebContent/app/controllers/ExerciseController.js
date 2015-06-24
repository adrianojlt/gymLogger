(function(w) { 'use strict';


//var exercise = angular.module('gymApp.exercise',[]);
var exercise = angular.module('gymApp.exercise',[]);

exercise.controller('ExerciseController', ['$scope','$http','$routeParams','content','globals',ExerciseController]);

function ExerciseController($scope,$http,$routeParams,content,globals) {

	//console.log('ExerciseController');

	$scope.groups = [];
	$scope.actions = {};

	$http.get( globals.url + 'groups' , {} ).then(function(response) {
		$scope.groups = response.data;
	});	

	$scope.actions.isActive = function(id) {
		//console.log(id);
		return parseInt($routeParams.groupID) === id;
	};

	$scope.click = function() {
		console.log('click');
	};

	$scope.isGroupPage = function() {
		return $routeParams.groupID;
	};

	if ( content ) {

	}
	else {

	}
}

})(window);
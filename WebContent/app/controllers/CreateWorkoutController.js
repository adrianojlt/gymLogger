(function(w) { 'use strict';

var gymApp = angular.module('gymApp.workout');

gymApp.controller('CreateWorkoutController', ['$scope','$window','$http',CreateWorkoutController]);


function CreateWorkoutController($scope,$window,$http) {
	
	var self = this;

	$scope.dateOptions = { 
		formatYear: 'yy',
		startingDay: 1
	};

	$scope.dateStart = new Date().getTime();

	$scope.open = function($event) {
		
    	$event.preventDefault();
    	$event.stopPropagation();
    	$scope.opened = true;
  	};

	$http.get('create.json').then(function(res) {
		$scope.musclegroups = res.data;
	});

	$scope.workout = [
		{ 
			repetitions: [{weight:"",rep:""}],
			selectedGroup: null,
			selectedExercise: null
		}
	];

	$scope.getMuscleGroups = function() {};

	$scope.muscleGroupChanged = function(exercise) {

		console.log('selectedGroup: ',exercise.selectedGroup);
		console.log('exercise: ',exercise);

		if ( exercise.selectedGroup == undefined ) 		exercise.selectedGroup = null;
		if ( exercise.selectedExercise == undefined ) 	exercise.selectedExercise = null;
	};

	$scope.exerciseChanged = function(selectedExercise) {
		//console.log('selectedExercise: ',selectedExercise);
	};

	$scope.getExercises = function(indice) {
		//console.log(indice);
		return [
			{"id":"1","name":"power squats"},
			{"id":"2","name":"front squats"}
		];
	};

	$scope.addExerciseInput = function(indice) {
		$scope.workout.splice( indice + 1 , 0 , {repetitions:[{weight:"",rep:""}]} );
	};

	$scope.removeExerciseInput = function(indice) {
		if ( $scope.workout.length > 1 ) $scope.workout.splice(indice,1);
	};

	$scope.addRepetitionInput = function(indice) {
		$scope.workout[indice].repetitions.push({});
	};

	$scope.removeRepetitionInput = function(indice,$index,a) {
		if ( $scope.workout[indice].repetitions.length > 1 ) $scope.workout[indice].repetitions.splice($index,1);
	};

	$scope.save = function() {
		console.log($scope.workout[0].repetitions);
		console.log($scope.repetitions);
	};

	$scope.cancel = function() {
		console.log('cancel');
		console.log($scope.workout);
	};
}

})(window);
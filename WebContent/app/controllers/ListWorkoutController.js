(function(w) { 'use strict';

// 'get' module
var gymApp = angular.module('gymApp.workout');

gymApp.controller('ListWorkoutController', ['$scope','$http','globals',ListWorkoutController]);

function ListWorkoutController($scope,$http,globals) {


	$http.get( globals.url + 'workouts' ).then(function(res) {
		$scope.workouts = res.data;
	});	

	(function() {

	})();

	$scope.numberOfGroups = function(workout) {
		return "X";
	};

	$scope.totalRepetitions = function(workout) {
		return workout.repetitions.length;
	};

	$scope.groupNames = function(workout,index) {

		//console.log(index);

		var groups = [];
		var counts = []; 

		workout.repetitions.forEach(function(rep) {

			if ( groups.indexOf(rep.exercise.muscleGroup.name) === -1 ) { // array doesnt contain the 'name' 
				groups.push(rep.exercise.muscleGroup.name);
				counts[groups.indexOf(rep.exercise.muscleGroup.name)] = 1; 
			}
			else 
				counts[groups.indexOf(rep.exercise.muscleGroup.name)]++; 
		});

		//console.log(groups);
		//console.log(counts);

		var i; var names = "";

		for ( i = 0 ; i < groups.length ; i++ ) {
			if ( i === 1 ) names = names+"/";
			names = names+groups[i];
		}

		$scope.workouts[index].groups = groups;
		$scope.workouts[index].counts = counts;

		return names;
	};
}

})(window);
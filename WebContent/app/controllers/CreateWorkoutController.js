(function(w) { 'use strict';

var gymApp = angular.module('gymApp.workout');

gymApp.controller('CreateWorkoutController', ['$scope','$window','$http','$filter',CreateWorkoutController]);

function CreateWorkoutController($scope,$window,$http,$filter) {
	
	var self = this;

	$scope.dateOptions = { 
		formatYear: 'yy',
		startingDay: 1
	};

	//$scope.dateStart = new Date().getTime();
	
	$scope.dateStart = {};
	$scope.hourStart = {};
	$scope.dateEnd = {};
	$scope.hourEnd = {};

	$scope.dateStart.open = function($event) {
		console.log($event);
    	$event.preventDefault();
    	$event.stopPropagation();
    	$scope.dateStart.opened = true;
  	};

	$scope.dateEnd.open = function($event) {
    	$event.preventDefault();
    	$event.stopPropagation();
    	$scope.dateEnd.opened = true;
  	};


	$http.get('http://localhost:9000/groups').then(function(res) {
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

		//console.log('selectedGroup: ',exercise.selectedGroup);
		//console.log('exercise: ',exercise);

		if ( exercise.selectedGroup == undefined ) {
		 	exercise.selectedGroup = null;
			exercise.selectedExercise = null;
		 }
		if ( exercise.selectedExercise == undefined ) 	
			exercise.selectedExercise = null;
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
	
	$scope.dateStart.change = function() {
		//$scope.dateEnd.data = new Date($filter('date')($scope.dateStart.data,"yyyy-MM-dd"));
		$scope.dateEnd.data = $filter('date')($scope.dateStart.data,"yyyy-MM-dd");
		//$scope.dateEnd.data = new Date($scope.dateStart.data);
		
	};

	$scope.dateEnd.change = function() {
		//console.log($scope.workout);
	};
	
	$scope.hourStart.change = function() {
		
	};

	$scope.hourEnd.change = function() {
		
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

	
	$scope.save = function(valid) {
		
		$scope.wkt = {}; $scope.wkt.repetitions = [];

		$scope.wkt.start = $scope.dateStart.data;
		$scope.wkt.start.setHours($scope.hourStart.data.getHours());
		$scope.wkt.start.setMinutes($scope.hourStart.data.getMinutes());
		$scope.wkt.end = new Date($scope.dateEnd.data);
		$scope.wkt.end.setHours($scope.hourEnd.data.getHours());
		$scope.wkt.end.setMinutes($scope.hourEnd.data.getMinutes());
		
		$scope.workout.forEach(function(entry) {

			var exerciseID = entry.selectedExercise.id;

			entry.repetitions.forEach(function(repetition) {

				$scope.wkt.repetitions.push(
					{
						weight:repetition.weight,
						num:repetition.rep,
						exercice: {
							id: exerciseID
						}
					}
				);
			});
		});

		//console.log($scope.wkt);
		//return;
		
		$http.post('http://localhost:9000/workouts',$scope.wkt).success(function(data, status, headers, config) {
			console.log(status);
		}).error(function(data, status, headers, config) { });
	};
	
	$scope.cancel = function() {
		//console.log($scope.workout[0].repetitions);
		console.log($scope.wkt.start);
		console.log($scope.wkt.end);
		//console.log(new Date($scope.dateEnd.data));
		//$window.dat = new Date($scope.dateEnd.data);
		//$window.tim = $scope.hourEnd.data;
	};
}

})(window);
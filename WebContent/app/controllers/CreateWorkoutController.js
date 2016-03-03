(function(w) { 'use strict';

// 'get' module
var gymApp = angular.module('gymApp.workout.create');

// 'set' module
//var gymApp = angular.module('gymApp.workout.create',['ui.bootstrap','ui.bootstrap.datepicker','smart-table']);

gymApp.controller('CreateWorkoutController', ['$scope','$window','$location','$http','$filter','globals',CreateWorkoutController]);

function CreateWorkoutController($scope,$window,$location,$http,$filter,globals) {

	console.log(globals);

	var self = this;

	$scope.dateOptions = { 
		formatYear: 'yy',
		startingDay: 1
	};

	//$scope.dateStart = new Date().getTime();
	
	$scope.listPath = '/workout/list'; 
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


	$http.get( globals.url + 'groups' , { headers: { exercises: true } } ).then(function(res) {
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
		//console.log($scope.hourStart.data);
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

	self.buildWorkout = function() {

		$scope.wkt = {}; 
		$scope.wkt.repetitions = [];

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
						exercise: {
							id: exerciseID
						}
					}
				);
			});
		});
	}
	
	$scope.save = function(valid) {

		self.buildWorkout();
		
		$http.post( globals.url + 'workouts' , $scope.wkt ).success(function(data, status, headers, config) {
			$location.path( $scope.listPath );
		}).error(function(data, status, headers, config) { });
	};
	
	$scope.cancel = function() {
		//$location.path( $scope.listPath );
		self.buildWorkout();
		console.log($scope.wkt);
	};
}

})(window);
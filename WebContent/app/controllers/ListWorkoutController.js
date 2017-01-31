(function(w) { 'use strict';

// 'get' module
var gymApp = angular.module('gymApp.workout.list');

// 'set' module
//var gymApp = angular.module('gymApp.workout.list',[]);

gymApp.controller('ListWorkoutController', ['$scope','$http','globals',ListWorkoutController]);
gymApp.controller('EditWorkoutController', ['$scope','$http','$routeParams','globals',EditWorkoutController]);
gymApp.controller('DeleteWorkoutController', ['$scope','$http','$routeParams','globals',DeleteWorkoutController]);

gymApp.directive('editworkout', ['$location',EditWorkout]);
gymApp.directive('workoutExercises', ['$location',WorkoutExercises]);
//gymApp.directive('collapseWorkout', ['$location',CollapseWorkout]);

function ListWorkoutController($scope,$http,globals) {

	$scope.actions = {};

	//$http.get( globals.url + 'workouts' ).then(function(res) {$scope.workouts = res.data; });

	(function() {

	})();

	$scope.actions.remove = function($event) {
		console.log($event);
		//$event.preventDefault();
	};

	$scope.totalRepetitions = function(workout) {
		return 0;
		return workout.repetitions.length;
	};

	$scope.trainingTotalTime = function(workout) {

		//var a = 1377005400000; //2013-07-20 15:30
		//var b = 1377783900000; //2013-07-29 15:45 
		var a = workout.start;
		var b = workout.end;

		var dateA = new Date(a);
		var dateB = new Date(b);

		var dayRelativeDifference = dateB.getHours()*60 + dateB.getMinutes() - dateA.getHours()*60 - dateA.getMinutes();
		var absoluteDifference    = (b-a)/60

		return absoluteDifference;
	};


	$scope.groupNames = function(workout,index) {

		var names = "";

		workout.groups.forEach(function(group,idx) {
			names = names + group.name;
			if ( !idx ) names = names + " ";
		});

		return names;

		// old code ... 
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

	$scope.callServer = function callServer(tableState) {

		$scope.isLoading = true;

    	var start = tableState.pagination.start 	|| 0;  	// This is NOT the page number, but the index of item in the list that you want to use to display the table.
    	var count = tableState.pagination.number 	|| 10;	// Number of entries showed per page.

    	var headers = { Range: start + '-' + count }
    	var paramts = { offset: start , limit: count }
    	//console.log(headers,'headers');

    	$http.get( globals.url + 'workouts' , { headers: headers , params: paramts } ).then( function(res) {

			$scope.workouts = res.data;

			var itemsLength = res.headers('Content-Range');
    		var totalItems = itemsLength.substring( itemsLength.indexOf('/') + 1 , itemsLength.length )

			tableState.pagination.numberOfPages = Math.ceil(totalItems / count);
		});	
	};
}

function EditWorkoutController($scope,$http,$routeParams,globals) {

	$scope.workout = {id:$routeParams.workoutID};

	$scope.listPath = '/workout/list'; 
	$scope.dateOptions = { formatYear: 'yy', startingDay: 1 };
	$scope.actions = {};1
	$scope.dateStart = {};
	$scope.hourStart = {};
	$scope.dateEnd = {};
	$scope.hourEnd = {};

	$scope.createForm = { $invalid: false };

	$scope.dateStart.open = function($event) {
    	$event.preventDefault();
    	$event.stopPropagation();
    	$scope.dateStart.opened = true;
  	};

	$scope.dateEnd.open = function($event) {
    	$event.preventDefault();
    	$event.stopPropagation();
    	$scope.dateEnd.opened = true;
  	};

    $http.get( globals.url + 'workouts/' + $scope.workout.id ).then( function(res) {

    	$scope.workout.data = res.data;
    	$scope.createForm.$invalid = false;

    	$scope.dateStart.data = $scope.workout.data.start;
    	$scope.dateEnd.data = $scope.workout.data.end;
    	$scope.hourStart.data = new Date($scope.workout.data.start);
    	$scope.hourEnd.data = new Date($scope.workout.data.end);

    },function(err) { } );

    $scope.actions.save = function() {

    };

    $scope.actions.cancel = function() {
    	console.log($scope.workout);
    };
}

function DeleteWorkoutController($scope,$http,$routeParams,globals) {

}

function EditWorkout() {

	return {

		restrict: 'AE',
		template: 'cenas e cenadas',

    	link: function($scope, element, attrs, ctrl) {
			ctrl.init(element);
    	},

		controller: function($scope) {

			var self = this;

			this.init = function(elem) {
				//console.log(elem);
			};
		}
	}
}

function WorkoutExercises() {

	return {

		restrict: 'AE',

    	link: function($scope, element, attrs, ctrl) {
			console.log('link');
    	},

		controller: function($scope) {

			var self = this;

			self.init = function(elem) {

			};
		}
	}
}

})(window);
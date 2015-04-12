(function(w) { 'use strict';

// 'get' module
var gymApp = angular.module('gymApp.workout');

gymApp.controller('ListWorkoutController', ['$scope','$http','globals',ListWorkoutController]);

function ListWorkoutController($scope,$http,globals) {


	//$http.get( globals.url + 'workouts' ).then(function(res) {$scope.workouts = res.data; });

	(function() {

	})();

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

    	var headers = { Range : start + '-' + count }

    	$http.get( globals.url + 'workouts' , { headers: headers } ).then(function(res) {

			$scope.workouts = res.data;

			tableState.pagination.numberOfPages = 10;
		});	

		var pagination = tableState.pagination;
		console.log(pagination);

	};
}

})(window);
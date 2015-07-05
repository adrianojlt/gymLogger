(function(w) { 'use strict';

var app = angular.module('gymApp', 
	[
	 	'gymApp.workout',
	 	'gymApp.workout.list',
	 	'gymApp.workout.create',
	 	'gymApp.exercise',
	 	'gymApp.calendar',
	 	//'ui.router'
	 	'ngRoute'
	]
);

app.controller('MainController', function( $scope , $location ) {
	$scope.isActive = function (viewLocation) {
        return (viewLocation === $location.path().substring(0,viewLocation.length));
    };
});

// 'set' module ... create a module with the following dependencies ...
angular.module('gymApp.workout',[]);
angular.module('gymApp.workout.create',['ui.bootstrap','ui.bootstrap.datepicker','smart-table']);
angular.module('gymApp.workout.list',[]);
//angular.module('gymApp.exercise',[]);
//angular.module('gymApp.calendar',['ui.calendar']);

})(window);
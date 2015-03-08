(function(w) { 'use strict';

var app = angular.module('gymApp', 
	[
	 	'gymApp.workout',
	 	//'ui.router'
	 	'ngRoute'
	]
);

app.controller('MainController', function( $scope , $location ) {
	$scope.isActive = function (viewLocation) {
        return (viewLocation === $location.path().substring(0,viewLocation.length));
    };
});

angular.module('gymApp.workout',['ui.bootstrap','ui.bootstrap.datepicker']);

})(window);
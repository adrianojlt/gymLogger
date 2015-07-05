(function(w) { 'use strict';


//var exercise = angular.module('gymApp.exercise',[]);
var exercise = angular.module('gymApp.exercise',[]);

exercise.controller('ExerciseController', ['$scope','$http','$routeParams','oneGroup','globals','MuscleGroups',ExerciseController]);
exercise.directive('contentPage', ['$location',ContentPage]);

exercise.factory('MuscleGroups',['$http','globals',function($http,globals) {

	self = this;

	var dataFactory = {groups:{}};
	var promise;
	
	dataFactory.groups.getEvent = function(event) {

		if ( !promise ) {
		 	promise = $http.get( globals.url + 'groups', event ).error(function() { // this is optional
            	promise = false;
           	});
		}

		return promise;
	};

	dataFactory.group = function(id) {
		return $http.get( globals.url + 'groups/' + id , {} );
	}

	return dataFactory;
}]);

function ExerciseController($scope,$http,$routeParams,oneGroup,globals,MuscleGroups) {

	var self = this;

	$scope.groups = [];
	$scope.group = {};
	$scope.actions = {};

	// call groups only one time ...
	MuscleGroups.groups.getEvent().success(function(data) {
		$scope.groups = data;
	}).error(function(error) { } );

	if ( oneGroup ) {
		MuscleGroups.group($routeParams.groupID).success(function(response) {
			$scope.group = response;
		})
	}

	$scope.actions.isActive = function(id) {
		return parseInt($routeParams.groupID) === id;
	};

	$scope.actions.changeGroup = function(id) {

		return;

		// this doesnt work ... $scope is not refreshed ...
		$http.get( globals.url + 'groups/' + id , {} ).then( function(response) {
			$scope.group = response.data;
			$scope.$apply(function() {
				console.log('apply');
			});
		}, function(err) { } );	
	};

	$scope.isGroupPage = function() {
		return $routeParams.groupID;
	};

	$scope.test = function() {

	};

	$scope.$watch('group',function(newVal,oldVal){
		//console.log(newVal, oldVal);
	});
}

function ContentPage() {

	return {

		restrict: 'AE',

    	link: function($scope, element, attrs, ctrl) {
			//console.log('link: contentPage');
    	},

		controller: function($scope) {

			var self = this;

			self.init = function(elem) {

			};
		}
	}
}

})(window);
(function(w) { 'use strict';


//var calendar = angular.module('gymApp.calendar');
var calendar = angular.module('gymApp.calendar',['ui.calendar']);

calendar.controller('CalendarController', ['$scope','$http',CalendarController]);

function CalendarController($scope,$http) {
	
	$scope.eventSources = [];
	$scope.uiConfig = {
		calendar : {
			height : 450,
			editable : true,
			header : {
				left : 'month basicWeek basicDay agendaWeek agendaDay',
				center : 'title',
				right : 'today prev,next'
			},
			dayClick : $scope.alertEventOnClick,
			eventDrop : $scope.alertOnDrop,
			eventResize : $scope.alertOnResize
		}
	};

	$scope.tmp = function() {
		console.log('tmp');
	};
}

})(window);
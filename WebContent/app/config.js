(function(w) { 'use strict';

// ui-router
//app.config(function($stateProvider,$urlRouterProvider) {

var app = angular.module('gymApp'); 

// ngRoute
app.config(['$routeProvider', function($routeProvider) {
	
        var views = '/app/views/';
        //views = '/gymlogger/' + views; // ... for apache tomcat
        
        $routeProvider

        // Home
        .when("/", {
            controller: 'DashboardController',
            templateUrl: views + "dashboard.htm"
        })

        // WORKOUTS
        .when("/workout/list", {
            controller: 'ListWorkoutController',
            templateUrl: views + "workout/list.htm"
        })
        .when("/workout/create", {
            controller: 'CreateWorkoutController',
            templateUrl: views + "workout/create.htm"
        })
        
        // else 404
        .otherwise({
            templateUrl: views + "404.htm", 
        	controller: function($window) {

        	}
        });
}]);

})(window);
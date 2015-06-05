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
    
    // EXERCISES
    .when("/exercise", {
        controller: 'ExerciseController',
        templateUrl: views + "exercise/exercises.htm"
    })

    // CALENDAR
    .when("/calendar", {
        controller: 'CalendarController',
        templateUrl: views + "calendar/calendar.htm"
    })
    
    // else 404
    .otherwise({
        templateUrl: views + "404.htm", 
    	controller: function($window) {

    	}
    });
}]);

app.config(['$httpProvider',function($httpProvider) {

    $httpProvider.interceptors.push(function() {

        var myInterceptor = {
            request: function(config) {
                //config.url = 'http://localhost:9009' + config.url;
                //conf = config;
                console.log(config.url);
                return config;
            }
        };

        return myInterceptor;
    });

}]);

app.constant('globals', { url:'http://localhost:9009/'} );



})(window);
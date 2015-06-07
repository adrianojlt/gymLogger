(function(w) { 'use strict';

// ui-router
//app.config(function($stateProvider,$urlRouterProvider) {

var app = angular.module('gymApp'); 

app.constant('globals', { 
    url:'http://localhost:9009/'
});

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

//app.config(['$stateProvider',function($stateProvider) {
    //$stateProvider
//}]);

app.config(['$httpProvider',function($httpProvider) {

    $httpProvider.interceptors.push(['$q','$location', function($q,$location) {

        var myInterceptor = {

            request: function(config) {
                return config;
            },

            responseError: function(response) {

                if  ( response.status === 401 || response.status === 403 ) {
                    //$location.path('/login');
                }

                return $q.reject(response);
            }
        };

        //console.log(myInterceptor);

        return myInterceptor;
    }]);
}]);

})(window);
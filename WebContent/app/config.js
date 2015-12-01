(function(w) { 'use strict';

// ui-router
//app.config(function($stateProvider,$urlRouterProvider) {

var app = angular.module('gymApp'); 

var ngroute;

app.constant('globals', { 
    url:'http://localhost:9009/api/'
    //url:'http://localhost:8080/gymlogger/rest/'
    //url:'http://localhost:8080/gymlogger/restlet/'
});

// ngRoute
app.config(['$routeProvider', function($routeProvider) {
	
    var views = '/app/views/';
    views = '/gymlogger/' + views; // ... for apache tomcat
    
    //ngroute(null);
    //return;

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
    .when("/workout/edit/:workoutID", {
        controller: 'EditWorkoutController',
        templateUrl: views + "workout/edit.htm"
    })
    .when("/workout/delete/:workoutID", {
        controller: 'DeleteWorkoutController',
        templateUrl: views + "workout/delete.htm"
    })
    
    // EXERCISES
    .when("/exercises/", {
        controller: 'ExerciseController',
        templateUrl: views + "exercise/exercises.htm",
        resolve: { oneGroup: function () { return false; } }
    })
    .when("/exercises/group/:groupID/", {
        controller: 'ExerciseController',
        templateUrl: views + "exercise/exercises.htm",
        resolve: { oneGroup: function () { return true; } }
    })

    // CALENDAR
    .when("/calendar/", {
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

ngroute = function($routeProvider) {

    return;
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
};

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
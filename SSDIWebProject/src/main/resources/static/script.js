/**
 * Created by Jacob on 2/24/2017.
 */
(function () {
    'use strict';
    var app = angular.module('renting', ['ngRoute']);
    // Set up routing
    app.config(['$locationProvider', function ($locationProvider) {
        $locationProvider.hashPrefix('');
    }]);
    app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when("/", {
                templateUrl: "landingPage.html"
            })
            .when("/registration", {
                templateUrl: "registration.html",
                controller: "registrationController"
            })
            .when("/login", {
                templateUrl: "login.html",
                controller: "loginController"
            })
            .otherwise({template: "<p>We're sorry, something seems to have gone wrong.</p>"});
    }]);
    // Set up the login controller
    app.controller('loginController', ['$scope', '$http', function ($scope, $http) {
        $scope.title = 'Sign In';
        $scope.user = {
            email: '',
            password: ''
        };
        $scope.loginError = false;
        $scope.results = [];
        $scope.login = function () {
            $http.post('http://localhost:8080/users/login', {params: $scope.user})
                .success(function () {
                    $window.location.href = '/userHomepage.html';
                    $cookies.put('login', true);
                    $cookies.put('user', $scope.user.email);
                })
                .error(function () {
                    $scope.loginError = true;
                });
        };
    }]);
    // Set up the registration controller
    app.controller('registrationController', ['$scope', function ($scope) {
        $scope.title = 'Create Your Account';
        $scope.user = {
            firstName: "",
            lastName: "",
            email: "",
            password: "",
            confirmPassword: ""
        };
    }]);
})();

// angular.module('demo', [])
//     .controller('Hello', function ($scope, $http) {
//         $http.get('http://localhost:8080/students')
//             .then(function (response) {
//                 $scope.students = response.data;
//             });
//     });
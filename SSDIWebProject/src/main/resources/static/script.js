/**
 * Created by Jacob on 2/24/2017.
 */
(function () {
    'use strict';
    var app = angular.module('renting', ['ngRoute']);
    // Set up routing
    app.config(['$locationProvider', function ($locationProvider) {
        $locationProvider.hashPrefix('');
        $locationProvider.html5Mode(true);
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
            .when("/userHome", {
                templateUrl: "userHomepage.html",
                controller: "getProductAllController"
            })
            .otherwise({template: "<p>We're sorry, something seems to have gone wrong.</p>"});
    }]);
    // Set up the login controller
    app.controller('loginController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
        $scope.title = 'Sign In';
        $scope.user = {
            email: '',
            password: '',
            firstName: '',
            lastName: ''
        };
        $scope.loginError = false;
        $scope.results = [];
        $scope.login = function () {
            $http.post('/users/login', $scope.user)
                .then(function (data, status, headers, config) {
                    console.log(data);
                    if (data.data.response === "Login Unsuccessful") {
                        $scope.loginError = true;
                    } else {
                        $location.path('/userHome');
                        //$window.location.href = '/userHomepage.html';
                    }
                });
            // .success(function (data, status, headers, config) {
            //     if (data.response === "Login Unsuccessful") {
            //         $scope.loginError = true;
            //     } else {
            //         $window.location.href = '/userHomepage.html';
            //     }
            // })
            // .error(function () {
            //     $scope.loginError = true;
            // });
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
    // Set up the user homepage controller
    app.controller('getProductAllController', function ($scope, $http) {
        $http.get('/products')
            .then(function (response) {
                $scope.allProducts = response.data;
            });
    });
})();

// angular.module('demo', [])
//     .controller('Hello', function ($scope, $http) {
//         $http.get('http://localhost:8080/students')
//             .then(function (response) {
//                 $scope.students = response.data;
//             });
//     });
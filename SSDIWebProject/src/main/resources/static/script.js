/**
 * Created by Jacob on 2/24/2017.
 */
(function () {
    'use strict';
    var app = angular.module('renting', ['ngRoute', 'ngCookies']);
    // Set up routing
    app.config(['$locationProvider', function ($locationProvider) {
        $locationProvider.hashPrefix('');
        $locationProvider.html5Mode(true);
    }]);
    app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when("/", {
                templateUrl: "landingPage.html",
                controller: 'landingController'
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
            .when('/logout', {
                templateUrl: 'logout.html',
                controller: 'logoutController'
            })
            .otherwise({template: "<p>We're sorry, something seems to have gone wrong.</p>"});
    }]);
    app.controller('landingController', ['$cookies', '$location', function ($cookies, $location) {
        if ($cookies.get("loggedIn") === 'true') {
            $location.path('/userHome');
        }
    }]);
    // Set up the login controller
    app.controller('loginController', ['$scope', '$http', '$location', '$cookies', function ($scope, $http, $location, $cookies) {
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
                .then(function (response) {
                    //console.log(data);
                    if (response.data.response === "Login Unsuccessful") {
                        $scope.loginError = true;
                    } else {
                        $cookies.put('loggedIn', 'true');
                        $cookies.putObject('userInfo', $scope.user);
                        $location.path('/userHome');
                        //$window.location.href = '/userHomepage.html';
                    }
                });
        };
    }]);
    // Set up the registration controller
    app.controller('registrationController', ['$scope', '$http', '$location', '$cookies', function ($scope, $http, $location, $cookies) {
        $scope.title = 'Create Your Account';
        $scope.user = {
            firstName: "",
            lastName: "",
            email: "",
            password: "",
            confirmPassword: ""
        };
        $scope.duplicateEmail = false;
        $scope.registrationError = false;
        $scope.register = function () {
            $http.post('/users/adduser', $scope.user)
                .then(function (response) {
                    console.log(response);
                    if (response.data.response === 'User successfully added') {
                        $cookies.put('loggedIn', 'true');
                        $cookies.putObject('userInfo', $scope.user);
                        $location.path('/userHome');
                    } else if (response.data.response === 'Email id already exists') {
                        $scope.duplicateEmail = true;
                    } else if (response.data.response === '') {
                        $scope.registrationError = true;
                    }
                });
        };
    }]);
    // Set up the user homepage controller
    app.controller('getProductAllController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {
        if ($cookies.get('loggedIn') !== 'true') {
            $scope.hidePage = true;
        }
        $scope.searchTerm = '';
        $http.get('/products')
            .then(function (response) {
                $scope.allProducts = response.data;
            });
        $http.get('/products/getallcategories')
            .then(function (response) {
                $scope.categories = response.data;
            });
        $scope.search = function () {
            $http.post('/products/search', $scope.searchTerm)
                .then(function (response) {
                    $scope.allProducts = response.data;
                });
        };
    }]);
    app.controller('logoutController', ['$scope', '$http', '$cookies', '$location', function ($scope, $http, $cookies, $location) {
        $http.post('/users/logout', $cookies.getObject('userInfo'))
            .then(function (response) {
                console.log(response.data);
                $cookies.putObject('userInfo', null);
                $cookies.put('loggedIn', 'false');
                $location.path('/');
            });
    }]);
})();

// angular.module('demo', [])
//     .controller('Hello', function ($scope, $http) {
//         $http.get('http://localhost:8080/students')
//             .then(function (response) {
//                 $scope.students = response.data;
//             });
//     });
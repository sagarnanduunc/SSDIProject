/**
 * Created by Jacob on 3/20/2017.
 */
angular.module('login', ['ngCookies'])
    .controller('loginController', ['$scope', '$cookies', '$http', function ($scope, $cookies, $http) {
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
/**
 * Created by Jacob on 2/24/2017.
 */
angular.module('demo', [])
    .controller('Hello', function ($scope, $http) {
        $http.get('http://localhost:8080/students')
            .then(function (response) {
                $scope.students = response.data;
            });
    });
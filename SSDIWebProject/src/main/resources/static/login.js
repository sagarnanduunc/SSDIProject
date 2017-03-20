/**
 * Created by Jacob on 3/20/2017.
 */
angular.module('login', [])
    .controller('loginController', ['$scope', function ($scope) {
        $scope.user = {
            email: '',
            password: ''
        };
    }]);
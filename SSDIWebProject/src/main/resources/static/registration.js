/**
 * Created by Jacob on 3/19/2017.
 */
angular.module('registration', [])
    .controller('registrationController', ['$scope', function ($scope) {
        $scope.user = {
            firstName: "",
            lastName: "",
            email: "",
            password: "",
            confirmPassword: ""
        };
    }]);
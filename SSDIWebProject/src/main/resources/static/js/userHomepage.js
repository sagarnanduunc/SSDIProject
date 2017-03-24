/**
 * Created by Admin on 3/21/2017.
 */
angular.module('userHome', [])
    .controller('getProductAllController', function ($scope, $http) {
        $http.get('http://localhost:8080/products')
            .then(function (response) {
                $scope.allProducts = response.data;

            });

    });
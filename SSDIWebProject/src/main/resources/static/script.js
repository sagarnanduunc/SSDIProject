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
            .when('/productHome', {
                templateUrl: 'productPage.html',
                controller: 'productController'
            })
            .when('/addProduct', {
                templateUrl: 'addProduct.html',
                controller: 'addProductController'
            })
            .when('/selectAddress', {
                templateUrl: 'selectAddress.html',
                controller: 'selectAddressController'
            })
            .when('/selectCheckoutAddress', {
                templateUrl: 'selectCheckoutAddress.html',
                controller: 'selectCheckoutAddressController'
            })
            .when('/addAddress', {
                templateUrl: 'addAddress.html',
                controller: 'addAddressController'
            })
            .when('/addCheckoutAddress', {
                templateUrl: 'addCheckoutAddress.html',
                controller: 'addCheckoutAddressController'
            })
            .when('/addBankInfo', {
                templateUrl: 'addBankDetails.html',
                controller: 'addBankInfoController'
            })
            .when('/selectBankInfo', {
                templateUrl: 'selectBank.html',
                controller: 'selectBankController'
            })
            .when('/selectPayment', {
                templateUrl: 'selectPayment.html',
                controller: 'selectPaymentController'
            })
            .when('/addPayment', {
                templateUrl: 'addPayment.html',
                controller: 'addPaymentController'
            })
            .when('/myCart', {
                templateUrl: 'cart.html',
                controller: 'cartController'
            })
            .when('/checkout', {
                templateUrl: 'checkout.html',
                controller: 'checkoutController'
            })
            .otherwise({template: "<p>We're sorry, something seems to have gone wrong.</p>"});
    }]);
    app.controller('selectPaymentController', ['$scope', '$http', '$location', '$cookies', function ($scope, $http, $location, $cookies) {
        $scope.buttonChoice;
        $scope.newId;
        $http.get('/users/paymentinfo')
            .then(function (response) {
                $scope.payments = response.data;
            });
        console.log($scope.payments)
        $scope.register = function () {
            $scope.cart = $cookies.getObject('userCart');
            //console.log();
        };
    }]);

    app.controller('cartController', ['$scope', '$http', '$cookies', '$location', function ($scope, $http, $cookies, $location) {
        $scope.cartItems = [];
        $http.get('/cart/getcart')
            .then(function (response) {
                $scope.cartItems = response.data;
                $cookies.putObject('userCart', $scope.cartItems);
            });
    }]);

    app.controller('checkoutController', ['$scope', '$http', '$cookies', '$location', function ($scope, $http, $cookies, $location) {
        $scope.checkoutItems = [];
        $http.get('/cart/checkoutcart')
            .then(function (response) {
                $scope.checkoutItems = response.data;
                $scope.totalPrice = 0;
                for (var i = 0; i < $scope.checkoutItems.length; i++) {
                    $scope.checkoutItems[i].startDate = $filter('date')($scope.checkoutItems[i].startDate, "yyyy-MM-dd");
                    $scope.checkoutItems[i].startDate = $filter('date')($scope.checkoutItems[i].endDate, "yyyy-MM-dd");
                    // $scope.checkoutItems[i].startDate = '';
                    // $scope.checkoutItems[i].endDate = '';
                    $scope.totalPrice = $scope.totalPrice + response.data[i].price;
                }
            });
        $scope.deleteItem = function (productName, productPrice) {
            for (var i = 0; i < $scope.checkoutItems.length; i++) {
                if ($scope.checkoutItems[i].name === productName) {
                    $scope.checkoutItems.splice(i, 1);
                    $scope.totalPrice = $scope.totalPrice - productPrice;
                }
            }
        };
        $scope.checkout = function () {
            $cookies.putObject('userCheckoutItems', $scope.checkoutItems);
            console.log($scope.checkoutItems);
            $location.path('/selectCheckoutAddress');

        };

    }]);

    app.controller('addPaymentController', ['$scope', '$http', '$location', '$cookies', '$locale', function ($scope, $http, $location, $cookies, $locale) {
        $scope.payment = {
            card_no: '',
            expmonth: '',
            expyear: '',
            name: '',
            zip: '',
            security_code: ''
        };
        $scope.regexCardNo = '/^[0-9]+$/';
        $scope.currentYear = new Date().getFullYear();
        $scope.currentMonth = new Date().getMonth() + 1;
        $scope.months = $locale.DATETIME_FORMATS.MONTH;
        console.log($scope.currentYear);
        $scope.years = [];
        for (var i = 0; i < 14; i++) {
            $scope.years.push($scope.currentYear + i);
        }
        $scope.register = function () {
            console.log("In function");
            $http.post('/users/addpaymentinfo', $scope.payment)
                .then(function (response) {
                    console.log(response);
                    $location.path('/selectPayment');
                });
        };
    }]);

    app.controller('selectBankController', ['$scope', '$http', '$location', '$cookies', function ($scope, $http, $location, $cookies) {
        $scope.buttonChoice;
        $scope.newId;
        $http.get('/users/bankinfo')
            .then(function (response) {
                $scope.banks = response.data;
            });
        //console.log($scope.banks)
        $scope.register = function () {
            console.log("Done")
        };
    }]);

    app.controller('selectAddressController', ['$scope', '$http', '$location', '$cookies', function ($scope, $http, $location, $cookies) {
        $scope.buttonChoice;
        $scope.newId;
        $http.get('/users/address')
            .then(function (response) {
                $scope.addresses = response.data;
            });
        $scope.register = function () {
            $location.path('/selectBankInfo');
        };
    }]);

    app.controller('selectCheckoutAddressController', ['$scope', '$http', '$location', '$cookies', function ($scope, $http, $location, $cookies) {
        $scope.buttonChoice;
        $scope.newId;
        $http.get('/users/address')
            .then(function (response) {
                $scope.addresses = response.data;
            });
        $scope.register = function () {
            $cookies.putObject('userCheckoutAddress', $scope.addresses);
            $location.path('/payment');
        };
    }]);

    app.controller('addBankInfoController', ['$scope', '$http', '$location', '$cookies', function ($scope, $http, $location, $cookies) {
        $scope.bank = {
            bankName: '',
            accountNumber: '',
            accountHolderName: '',
            routingNumber: ''
        };
        $scope.register = function () {
            console.log("In function");
            console.log($scope.bank);
            $http.post('/users/addbankinfo', $scope.bank)
                .then(function (response) {
                    console.log(response);
                    $location.path('/userHome');
                });
        };
    }]);
    app.controller('addAddressController', ['$scope', '$http', '$location', '$cookies', function ($scope, $http, $location, $cookies) {
        $scope.address = {
            streetAddress: '',
            apartment: '',
            city: '',
            state: '',
            zip: ''
        };
        $scope.states = ["NC"];
        $scope.register = function () {
            $http.post('/users/addaddress', $scope.address)
                .then(function (response) {
                    console.log(response);
                    $location.path('/selectAddress');
                });
        };
    }]);

    app.controller('addProductController', ['$scope', '$http', '$location', '$cookies', function ($scope, $http, $location, $cookies) {
        $scope.product = {
            name: '',
            description: '',
            price: '',
            category: ''
        };
        $http.get('/products/getallcategories')
            .then(function (response) {
                $scope.categories = response.data;
            });
        $scope.addProduct = function () {
            var file = $scope.myFile;
            console.log('file is ');
            console.dir(file);
            var uploadUrl = "/products/addproduct";
            var fd = new FormData();
            fd.append('file', file);
            fd.append('name', angular.toJson($scope.product.name, true));
            fd.append('description', angular.toJson($scope.product.description, true));
            fd.append('price', angular.toJson($scope.product.price, true));
            fd.append('category', angular.toJson($scope.product.category, true));
            //fd.append('user',angular.toJson($scope.user,true));
            //onsole.log('Socpe of user'+$scope.user);
            $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            }).then(function (success) {
                console.log('success');
                $location.path('/selectAddress');
            });
        };

    }]);
    app.controller('landingController', ['$cookies', '$location', function ($cookies, $location) {
        if ($cookies.get("loggedIn") === 'true') {
            $location.path('/userHome');
        }
    }]);

    app.controller('productController', ['$scope', '$cookies', '$http', function ($scope, $cookies, $http) {
        $scope.product = $cookies.getObject("currentProduct");
        if ($scope.product.photoLink.length === 0) {
            $scope.product.photoLink = "null";
        }
        $scope.successfulAdd = false;
        $scope.inCart = false;
        $scope.cartError = false;
        $scope.addToCart = function () {
            var product = {};
            for (var prop in $scope.product) {
                if (prop !== 'photoLink') {
                    product[prop] = $scope.product[prop];
                }
            }
            console.log(product);
            $http.post('/cart/addproduct', product)
                .then(function (response) {
                    if (response.data.response === "Product added to cart") {
                        $scope.successfulAdd = true;
                    } else if (response.data.response === "Product already in cart") {
                        $scope.inCart = true;
                    } else {
                        $scope.cartError = true;
                    }
                });
        };
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
    app.controller('getProductAllController', ['$scope', '$http', '$cookies', '$location', function ($scope, $http, $cookies, $location) {
        if ($cookies.get('loggedIn') !== 'true') {
            $scope.hidePage = true;
        }
        $scope.searchTerm = '';
        $scope.useCategories = {};
        $scope.priceFloor = '';
        $scope.priceCeiling = '';
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
        $scope.ProductPass = function (arg) {
            $cookies.putObject("currentProduct", arg);
            //$location.path = "/productHome";
            console.log(arg);
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
    app.filter('filterByCategory', function () {
        return function (input, categories) {
            var output = [];
            angular.forEach(input, function (product) {
                if (categories[product.category] === true) {
                    output.push(product);
                }
            });
            return output;
        }
    });
    app.filter('filterByPrice', function () {
        return function (input, priceFloor, priceCeiling) {
            var output = [];
            if (angular.isNumber(priceFloor) && priceFloor >= 0) {
                if (angular.isNumber(priceCeiling) && priceCeiling >= 0) {
                    if (priceFloor > priceCeiling) {
                        angular.forEach(input, function (product) {
                            output.push(product);
                        });
                    } else {
                        angular.forEach(input, function (product) {
                            if (product.price >= priceFloor && product.price <= priceCeiling) {
                                output.push(product);
                            }
                        });
                    }
                } else {
                    angular.forEach(input, function (product) {
                        if (product.price >= priceFloor) {
                            output.push(product);
                        }
                    });
                }
            } else {
                if (angular.isNumber(priceCeiling) && priceCeiling >= 0) {
                    angular.forEach(input, function (product) {
                        if (product.price <= priceCeiling) {
                            output.push(product);
                        }
                    });
                } else {
                    angular.forEach(input, function (product) {
                        output.push(product);
                    });
                }
            }
            return output;
        }
    });
    app.directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function () {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }]);

    app.service('fileUpload', ['$http', function ($http) {
        this.uploadFileToUrl = function (file) {
            var fd = new FormData();
            fd.append('file', file);

            $http.post("/products/uploadimage", fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function () {
            });
        }
    }]);


})();

// angular.module('demo', [])
//     .controller('Hello', function ($scope, $http) {
//         $http.get('http://localhost:8080/students')
//             .then(function (response) {
//                 $scope.students = response.data;
//             });
//     });
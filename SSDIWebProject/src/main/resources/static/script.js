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
            .when('/history', {
                templateUrl: 'history.html',
                controller: 'historyController'
            })
            .otherwise({template: "<p>We're sorry, something seems to have gone wrong.</p>"});
    }]);

    app.controller('historyController', ['$scope', '$http', '$location', '$cookies', '$locale', function ($scope, $http, $location, $cookies, $locale) {
        $scope.rentedProductsList = [];
        $scope.productsRentedOutList = [];
        $http.get('/products')
            .then(function (response) {
                $scope.allProducts = response.data;
                console.log($scope.allProducts);
                $http.get('/users/rentedproducts')
                    .then(function (response) {
                        var rentedProducts = response.data;
                        $scope.allProducts.map(function (product) {
                            rentedProducts.map(function (rentedProduct) {
                                if (product.id === rentedProduct.product_id) {
                                    $scope.rentedProductsList.push(product);
                                }
                            });
                        });
                        // $scope.rentedProducts = response.data;
                        // console.log($scope.rentedProducts);
                        // for (var i = 0; i < $scope.allProducts.length; i++) {
                        //     for (var j = 0; j < $scope.rentedProducts.length; j++) {
                        //         if ($scope.allProducts[i].id === $scope.rentedProducts[j].product_id) {
                        //             $scope.rentedProductsList.push($scope.allProducts[i]);
                        //         }
                        //     }
                        // }
                    });
                $http.get('/users/productsrentedout')
                    .then(function (response) {
                        var productsRentedOut = response.data;
                        $scope.allProducts.map(function (product) {
                            productsRentedOut.map(function (rentedProduct) {
                                if (product.id === rentedProduct.product_id) {
                                    $scope.productsRentedOutList.push(product);
                                }
                            });
                        });
                        // $scope.productsRentedOut = response.data;
                        // console.log($scope.productsRentedOut.length);
                        // for (var i = 0; i < $scope.allProducts.length; i++) {
                        //     console.log("Hello");
                        //     for (var j = 0; j < $scope.productsRentedOut.length; j++) {
                        //         console.log("Hello");
                        //         if ($scope.allProducts[i].id === $scope.productsRentedOut[j].product_id) {
                        //             $scope.productsRentedOutList.push($scope.allProducts[i]);
                        //         }
                        //     }
                        // }
                    });
            });

        console.log($scope.rentedProductsList);
        console.log($scope.productsRentedOutList);
        //console.log($scope.rentedProductsList.get(0));
        //console.log($scope.productsRentedOutList.get(0));
    }]);

    app.controller('selectPaymentController', ['$scope', '$http', '$location', '$cookies', function ($scope, $http, $location, $cookies) {
        $scope.buttonChoice = {
            id: '',
            card_no: '',
            expmonth: '',
            expyear: '',
            name: '',
            zip: '',
            security_code: ''
        };
        $scope.newId;
        $http.get('/users/paymentinfo')
            .then(function (response) {
                $scope.payments = response.data;
            });
        console.log($scope.payments);
        $scope.register = function () {
            //console.log("In function");
            $scope.products = $cookies.getObject('userCheckoutItems');
            $scope.address = $cookies.getObject('userCheckoutAddress');
            //console.log($scope.address.addressId);
            $scope.transactions = [];
            $scope.products.map(function (product) {
                var transaction = {
                    product_id: '',
                    payment_id: '',
                    address_id: '',
                    email_rentee: '',
                    start_date: '',
                    end_date: ''
                };
                transaction.product_id = product.id;
                transaction.payment_id = $scope.buttonChoice.id;
                transaction.address_id = $scope.address.addressId;
                transaction.email_rentee = product.email;
                transaction.start_date = product.startDate;
                transaction.end_date = product.endDate;
                $scope.transactions.push(transaction);
            });
            // for (var i = 0; i < $scope.products.length; i++) {
            //     $scope.transaction = {
            //         product_id: '',
            //         payment_id: '',
            //         address_id: '',
            //         email_rentee: '',
            //         start_date: '',
            //         end_date: ''
            //     };
            //     $scope.transaction.product_id = $scope.products[i].id;
            //     $scope.transaction.payment_id = $scope.buttonChoice.id;
            //     $scope.transaction.address_id = $scope.address.addressId;
            //     $scope.transaction.email_rentee = $scope.products[i].email;
            //     $scope.transaction.start_date = $scope.products[i].startDate;
            //     $scope.transaction.end_date = $scope.products[i].endDate;
            //     //
            //     // var fd = new FormData();
            //     // fd.append('product_id',$scope.products[i].id);
            //     // fd.append('payment_id',$scope.buttonChoice.id);
            //     // fd.append('address_id',$scope.address.id);
            //     // fd.append('email_rentee',$scope.products[i].email);
            //     // fd.append('start_date',$scope.products[i].startDate);
            //     // fd.append('end_date',$scope.products[i].endDate);
            //     $scope.transactions.push($scope.transaction);
            // }
            console.log($scope.transactions);
            $http.post('/users/addtransactioninfo', $scope.transactions)
                .then(function (response) {
                    console.log(response);
                    $location.path('/');
                    $scope.products.map(function (product) {
                        var p = {};
                        for (var prop in product) {
                            if (prop !== 'photoLink') {
                                p[prop] = product[prop];
                            }
                        }
                        $http.post('/cart/removeproduct', p)
                            .then(function (response) {
                            });
                    });
                    // for (var product in $scope.products) {
                    //     $http.post('/cart/removeproduct', product)
                    //         .then(function (response) {
                    //         });
                    // }
                });
            //console.log();
        };
    }]);

    app.controller('cartController', ['$scope', '$http', '$cookies', '$location', function ($scope, $http, $cookies, $location) {
        $scope.cartItems = [];
        $scope.deleteSuccess = false;
        $scope.deleteFail = false;
        $http.get('/cart/getcart')
            .then(function (response) {
                $scope.cartItems = response.data;
                $cookies.putObject('userCart', $scope.cartItems);
            });
        $scope.removeProduct = function (product) {
            var p = {};
            for (var prop in product) {
                if (prop !== 'photoLink') {
                    p[prop] = product[prop];
                }
            }
            $http.post('/cart/removeproduct', p)
                .then(function (response) {
                    if (response.data.response === 'Product successfully removed') {
                        $scope.deleteSuccess = true;
                        $scope.deleteFail = false;
                        var ndx = $scope.cartItems.indexOf(product);
                        if (ndx > -1) {
                            $scope.cartItems.splice(ndx, 1);
                        }
                    } else {
                        $scope.deleteFail = true;
                        $scope.deleteSuccess = false;
                    }
                });
        };
    }]);

    app.controller('checkoutController', ['$scope', '$http', '$cookies', '$location', function ($scope, $http, $cookies, $location) {
        $scope.checkoutItems = [];
        $http.get('/cart/checkoutcart')
            .then(function (response) {
                $scope.checkoutItems = response.data;
                $scope.totalPrice = 0;
                $scope.date = new Date();
                $scope.FromDate = $scope.date.getFullYear() + '-' + ('0' + ($scope.date.getMonth() + 1)).slice(-2) + '-' + ('0' + $scope.date.getDate()).slice(-2);
                console.log($scope.FromDate);
                for (var i = 0; i < $scope.checkoutItems.length; i++) {
                    // $scope.checkoutItems[i].startDate = $filter('date')($scope.checkoutItems[i].startDate, "MM-dd-yyyy");
                    // $scope.checkoutItems[i].endDate = $filter('date')($scope.checkoutItems[i].endDate, "MM-dd-yyyy");
                    $scope.checkoutItems[i].startDate = new Date();
                    $scope.checkoutItems[i].endDate = new Date();
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
            for (var i = 0; i < $scope.checkoutItems.length; i++) {
                $scope.checkoutItems[i].startDate = $scope.checkoutItems[i].startDate.getFullYear() + '-' + ('0' + ($scope.checkoutItems[i].startDate.getMonth() + 1)).slice(-2) + '-' + ('0' + $scope.checkoutItems[i].startDate.getDate()).slice(-2);
                $scope.checkoutItems[i].endDate = $scope.checkoutItems[i].endDate.getFullYear() + '-' + ('0' + ($scope.checkoutItems[i].endDate.getMonth() + 1)).slice(-2) + '-' + ('0' + $scope.checkoutItems[i].endDate.getDate()).slice(-2);
            }
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
        $scope.buttonChoice = {
            addressId: '',
            streetAddress: '',
            apartment: '',
            city: '',
            state: '',
            zip: ''
        };
        $scope.newId;
        $http.get('/users/address')
            .then(function (response) {
                $scope.addresses = response.data;
            });
        $scope.register = function () {
            $cookies.putObject('userCheckoutAddress', $scope.buttonChoice);
            console.log($scope.buttonChoice.addressId);
            $location.path('/selectPayment');
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

    app.controller('addCheckoutAddressController', ['$scope', '$http', '$location', '$cookies', function ($scope, $http, $location, $cookies) {
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
                    $location.path('/selectCheckoutAddress');
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
        $scope.reviewList = [];
        $scope.review = '';
        $scope.successfulAdd = false;
        $scope.inCart = false;
        $scope.cartError = false;
        $scope.reviewError = false;
        $scope.ratingError = false;
        $scope.myRating = -1;
        $scope.avgRating = -1;
        var getReviews = function () {
            $http.post('/products/getreviews', $scope.product.id)
                .then(function (response) {
                    $scope.reviewList = response.data;
                });
        };
        var getMyRating = function () {
            var product = {};
            for (var prop in $scope.product) {
                if (prop !== 'photoLink') {
                    product[prop] = $scope.product[prop];
                }
            }
            $http.post('/products/myrating', product)
                .then(function (response) {
                    $scope.myRating = response.data;
                })
        };
        var getAvgRating = function () {
            var product = {};
            for (var prop in $scope.product) {
                if (prop !== 'photoLink') {
                    product[prop] = $scope.product[prop];
                }
            }
            $http.post('/products/averagerating', product)
                .then(function (response) {
                    $scope.avgRating = response.data;
                });
        };
        $scope.postReview = function () {
            if ($scope.review !== '') {
                var review = {
                    description: $scope.review,
                    productId: $scope.product.id
                };
                $http.post('/products/addreview', review)
                    .then(function (response) {
                        if (response.data.response === 'Review successfully added') {
                            $scope.reviewError = false;
                            getReviews();
                        } else {
                            $scope.reviewError = true;
                        }
                    });
            }
        };
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
        $scope.addRating = function (rating) {
            var product = {};
            for (var prop in $scope.product) {
                if (prop !== 'photoLink') {
                    product[prop] = $scope.product[prop];
                }
            }
            product.rating = rating;
            $http.post('/products/addrating', product)
                .then(function (response) {
                    if (response.data.response === "") {
                        $scope.ratingError = false;
                        getMyRating();
                        getAvgRating();
                    } else {
                        $scope.ratingError = true;
                    }
                });
        };
        getReviews();
        getMyRating();
        getAvgRating();
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
        $http.get('/products/getavailableproducts')
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
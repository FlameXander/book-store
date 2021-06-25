(function ($localStorage) {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'about/about.html',
                controller: 'aboutController'
            })
            .when('/admin', {
                templateUrl: 'admin/admin_panel.html',
                controller: 'adminPanelController'
            })
            .when('/books', {
                templateUrl: 'store/store.html',
                controller: 'booksController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/order_confirmation', {
                templateUrl: 'order_confirmation/order_confirmation.html',
                controller: 'orderConfirmationController'
            })
            .when('/order_result/:orderId', {
                templateUrl: 'order_result/order_result.html',
                controller: 'orderResultController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'ordersController'
            })
            .otherwise({
                redirectTo: '/'
            });

        // $httpProvider.interceptors.push(function ($q, $location) {
        //     return {
        //         'responseError': function (rejection, $localStorage, $http) {
        //             var defer = $q.defer();
        //             if (rejection.status == 401 || rejection.status == 403) {
        //                 console.log('error: 401-403');
        //                 $location.path('/auth');
        //                 if (!(localStorage.getItem("localUser") === null)) {
        //                     delete $localStorage.bookStoreUser;
        //                     $http.defaults.headers.common.Authorization = '';
        //                 }
        //                 console.log(rejection.data);
        //                 var answer = JSON.parse(rejection.data);
        //                 console.log(answer);
        //                 // window.alert(answer.message);
        //             }
        //             defer.reject(rejection);
        //             return defer.promise;
        //         }
        //     };
        // });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.bookStoreUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.bookStoreUser.token;
        }

        if ($localStorage.bookStoreCartId) {
        } else {
            const contextPath = 'http://localhost:8189/store';
            $http({
                url: contextPath + '/api/v1/cart/generate',
                method: 'GET'
            }).then(function (response) {
                $localStorage.bookStoreCartId = response.data.value;
            });
        }
    }
})();

angular.module('app').controller('indexController', function ($rootScope, $scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/store';

    $scope.mergeCarts = function () {
        $http({
            url: contextPath + '/api/v1/cart/merge',
            method: 'GET',
            params: {
                'cartId': $localStorage.bookStoreCartId
            }
        }).then(function (response) {
        });
    }

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.bookStoreUser = {username: $scope.user.username, token: response.data.token};

                    $scope.mergeCarts();

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.bookStoreUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.bookStoreUser) {
            return true;
        } else {
            return false;
        }
    };
});
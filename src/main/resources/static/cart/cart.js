angular.module('app').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/store';

    $scope.cartContentRequest = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET',
            params: {
                cartName: $localStorage.bookStoreCartId
            }
        }).then(function (response) {
            $scope.cart = response.data;
        });
    };

    $scope.decrementItem = function (bookId) {
        $http({
            url: contextPath + '/api/v1/cart/dec',
            method: 'GET',
            params: {
                bookId: bookId,
                cartName: $localStorage.bookStoreCartId
            }
        }).then(function (response) {
            $scope.cartContentRequest();
        });
    };

    $scope.removeItem = function (bookId) {
        $http({
            url: contextPath + '/api/v1/cart/remove',
            method: 'GET',
            params: {
                bookId: bookId,
                cartName: $localStorage.bookStoreCartId
            }
        }).then(function (response) {
            $scope.cartContentRequest();
        });
    };

    $scope.incrementItem = function (bookId) {
        $http({
            url: contextPath + '/api/v1/cart/add',
            method: 'GET',
            params: {
                bookId: bookId,
                cartName: $localStorage.bookStoreCartId
            }
        }).then(function (response) {
            $scope.cartContentRequest();
        });
    };

    $scope.goToOrderSubmit = function () {
        $location.path('/order_confirmation');
    }

    $scope.cartContentRequest();
});
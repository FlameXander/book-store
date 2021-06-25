angular.module('app').controller('orderConfirmationController', function ($scope, $http, $location, $localStorage) {
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

    $scope.submitOrder = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'POST',
            params: {
                address: $scope.order_info.address
            }
        }).then(function (response) {
            $location.path('/order_result/' + response.data.id);
        });
    }

    $scope.cartContentRequest();
});
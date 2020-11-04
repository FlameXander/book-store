angular.module('app').controller('adminPanelController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/store';

    $scope.submitCreateNewBook = function () {
        $http.post(contextPath + '/api/v1/books', $scope.newBook)
            .then(function (response) {
                alert("Книга добавлена");
            });
    };
});
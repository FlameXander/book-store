angular.module('app').controller('booksController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/store';

    let selectedGenres = [];

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/books',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                genres_ids: selectedGenres,
                p: pageIndex
            }
        }).then(function (response) {
            $scope.BooksPage = response.data;
            console.log($scope.BooksPage);
            $scope.PaginationArray = $scope.generatePagesIndexes(1, $scope.BooksPage.totalPages);
        });
    };

    $scope.addToCart = function (bookId) {
        $http({
            url: contextPath + '/api/v1/cart/add',
            method: 'GET',
            params: {
                bookId: bookId,
                cartName: $localStorage.bookStoreCartId
            }
        }).then(function (response) {
        });
    }

    $scope.getGenresList = function () {
        $http({
            url: contextPath + '/api/v1/genres',
            method: 'GET'
        }).then(function (response) {
            $scope.GenresList = response.data;
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.clickGenre = function (genreId, selected) {
        var idx = selectedGenres.indexOf(genreId);
        if (idx > -1) {
            selectedGenres.splice(idx, 1);
        } else {
            selectedGenres.push(genreId);
        }
    }

    $scope.fillTable();
    $scope.getGenresList();
});
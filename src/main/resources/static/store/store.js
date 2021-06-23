angular.module('app').controller('booksController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/store';

    let selectedGenres = [];

    $scope.fillTable = function (pageIndex = 1) {
        if ($scope.xxx != null) {
            console.log($scope.xxx);
        }
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
            $scope.PaginationArray = $scope.generatePagesIndexes(1, $scope.BooksPage.totalPages);
        });
    };

    $scope.addToCartFunction = function (book) {
        $http({
            url: contextPath + '/api/v1/cart/add/' + book.id,
            method: "GET"
        }).then(function (response) {
            console.log('added');
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

    $scope.addToCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            console.log('ok');
        });
    }

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
        }
        else {
            selectedGenres.push(genreId);
        }
    }

    $scope.fillTable();
    $scope.getGenresList();
});
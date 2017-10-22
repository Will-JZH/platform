'use strict';

/**
 * UserController
 */
var UserController = function($scope, $http) {
	$scope.userName = "";
	$scope.password = "";
	
	$scope.login = function() {
		$http({
			method : "POST",
			url : "users/loginUser",
			data : {
				userName : $scope.userName,
				password : $scope.password
			}
		}).success(function (data, status) {
			window.open('mainhtml/main.html', data);
		}).error(function (data, status){
			alert("username or password error");
		});
	};
	
	$scope.register = function() {
		alert("submit");
		
	}
};
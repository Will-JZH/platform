'use strict';

/**
 * UserController
 */
var UserController = function($scope, $http, $location) {
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
			if (data != "" && data != null) {
				var str = "userID=" + data["userID"] + "&userName=" + data["userName"] + "&password=" + data["password"] + "&email=" + data["email"] + "&phone=" + data["phone"] + "&address=" + data["address"] + "&authority=" + data["authority"];
				window.open('users/layout.html?' + str, '_self');
			} else {
				alert("username or password error");
			}
			
		}).error(function (data, status){
			alert("username or password error");
		});
	};
	
	$scope.userName="";
	$scope.password="";
	$scope.email="";
	$scope.phone="";
	$scope.address="";
	$scope.authority=0;
	
	$scope.register = function() {
		$http({
			method : "POST",
			url : "users/addUser",
			data : {
				userName : $scope.userName,
				password : $scope.password,
				email : $scope.email,
				phone : $scope.phone,
				address : $scope.address,
				authority : $scope.authority
			}
		}).success(function (data, status) {
			
		}).error(function (data, status){
			alert("add user error");
		});
		
	}
};
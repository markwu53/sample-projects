$(document).ready(function() {
	//$("body").append($("<h1>").append("Welcome Spring Boot from tomcat"));
});

angular.module("myapp", []).controller("mycontroller", function($scope, $http) {
	$http({ url: "drivers" }).success(function(json) {
		$scope.rows = json;
	});
});
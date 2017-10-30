'use strict';

var AngularSpringmvcMybatis = {};

var App = angular.module('AngularSpringmvcMybatis', ['AngularSpringmvcMybatis.filters', 'AngularSpringmvcMybatis.services', 'AngularSpringmvcMybatis.directives']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
    .when('/main', {
        templateUrl: 'users/layout.html',
        controller: MainController
    })
    .when('/', {
        templateUrl: 'index.html',
        controller: UserController
    })
    .otherwise({redirectTo: '/'});
}]);

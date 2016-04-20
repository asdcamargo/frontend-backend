/**
 * 
 */
var app = angular.module('crl-front', ['ui.router', 'ngFileUpload', 'ngLoadingSpinner']);
var baseUrl = 'components/';

app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/app/index.html");
    $stateProvider.state('add-member', {
            url: "/add-crl",
            templateUrl : baseUrl + 'crls/add-crl.html',
    		controller : 'AddCrlController'
    })
    .state('members', {
        url: "/crls",
        templateUrl : baseUrl + 'crls/crls.html',
		controller : 'CrlsController'
    })
    .state('home', {
        url: "/home",
        templateUrl : '/app/home.html',
    })
   
 });


var isMemberEmpty = function (obj){
    	for(var prop in obj) {
 	        if(obj.hasOwnProperty(prop))
 	            return false;
        }
    return true;
};

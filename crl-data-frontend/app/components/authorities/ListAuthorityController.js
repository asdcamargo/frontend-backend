/**
 * 
 */
app.controller('MembersController', ['$scope', 'membersService', function($scope, membersService) {
	
    //var c= 0;
//	$interval(function(){
//		membersService.getMembers().then(function (historico) {
//	        console.log(historico);
//	    	$scope.membersList = historico;
//		});
//		c++;
//		console.log(c);
//	},3000);
    $scope.getAll = membersService.getMembers().then(function (historico) {
        console.log(historico);
    	$scope.membersList = historico; 
    });
    
    
    $scope.$watch(function(scope) { return scope.getAll },
            function(scope) {getAll = membersService.getMembers().then(function (historico) {
                console.log(historico);
            	$scope.membersList = historico; 
            });}
           );
    
  
}]);


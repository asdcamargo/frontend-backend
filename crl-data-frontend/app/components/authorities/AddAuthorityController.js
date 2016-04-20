/**
 * 
 */
app.controller('AddMembersController', ['$scope', 'membersService', function($scope, membersService) {
	$scope.member = {};
	$scope.save = function (member) {
        membersService.save(member).then(function (errors) {
	        $scope.errors = errors;
	    	if (errors['status'] == 200)
	    	$scope.member = {};
	    });
    };
    
    //var c= 0;
//	$interval(function(){
//		membersService.getMembers().then(function (historico) {
//	        console.log(historico);
//	    	$scope.membersList = historico;
//		});
//		c++;
//		console.log(c);
//	},3000);
  
}]);


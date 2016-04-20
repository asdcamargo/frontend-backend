/**
 * 
 */
app.controller('CrlsController', ['$scope', '$interval','configService', 'crlsService', function($scope, $interval, configService, crlsService) {
	//$interval(function(){
	//	lcrsService.getLcrs().then(function (lcrs) {
	//        $scope.lcrsList = lcrs;
	//	});
	//},3000);
	$scope.getAll = crlsService.getLcrs().then(function (lcrs) {
        //as we are running locally this sleep is just to simulate some delay to fetch the data
        sleep(3000);
            lcrs.forEach(function(lcr) { lcr['url'] = configService.urlServico()  + '/rest/crls/file/' + lcr.id; });
            $scope.lcrsList = lcrs;
    });

    function sleep(milliseconds) {
        var start = new Date().getTime();
        for (var i = 0; i < 1e7; i++) {
            if ((new Date().getTime() - start) > milliseconds){
                break;
            }
        }
    }
}]);





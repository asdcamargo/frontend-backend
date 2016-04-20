/**
 * 
 */
app.controller('AddCrlController', ['$scope', 'crlsService', function($scope, crlsService) {
	$scope.crl = {};

   $scope.save = function (crl, files) {
        crlsService.save(crl, files[0]).then(function (errors) {
            $scope.errors = errors;
            if (errors['status'] == 200) {
                $scope.crl = {};
                files = [];
                $scope.message_principal = 'Success!';
                $scope.message = 'CRL was saved successfully.';
                $scope.concluido = true;
                $scope.msg_type = 'info';
            }
        });
    };
}]);


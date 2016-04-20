/**
 * 
 */
app
		.factory(
				'membersService',
				['$http', '$q', function($http, $q) {
					$http.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
					return {
						getMembers : function() {

							var deferred = $q.defer();

							$http.get('/rest/lcrs').success(
									function(data, status, headers, config) {
										deferred.resolve(data);
									}).error(function(data, status) {
								deferred.resolve(data);
							});
							return deferred.promise;
						},
						save : function(member) {
							var deferred = $q.defer();
							$http.post('/rest/save', member)
									.success(function(response) {
										var status = {
											'status' : 200
										};
										deferred.resolve(status);
									}).error(function(data, status) {
										data['status'] = status;
										deferred.resolve(data);
									});
							return deferred.promise;
						}
					};
				}]);

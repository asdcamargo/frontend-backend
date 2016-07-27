/**
 * 
 */

app
		.factory(
				'crlsService',
				['$http', '$q', 'configService', function($http, $q, configService) {
					var url = configService.url();
					$http.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
					return {
						getLcrs : function() {

							var req = {
								method: 'GET',
								url: url + '/rest/crls',
							}
							var deferred = $q.defer();

							$http(req).success(
									function(data, status, headers, config) {
										deferred.resolve(data);
									}).error(function(data, status) {
								deferred.resolve(data);
							});
							return deferred.promise;
						},
						save : function(crl, file) {
							var deferred = $q.defer();

							var formData = new FormData();

							formData.append("file", file);
							formData.append('data', new Blob([JSON.stringify(crl)], {
								type: "application/json"
							}));
							var req = {
								headers: {
									"Content-Type": undefined
								},
								method: 'POST',
								url: url + '/rest/crls',
								data: formData
							}
							$http(req)
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

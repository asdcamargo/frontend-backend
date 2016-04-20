/**
 * Created by andre.camargo on 03/07/2015.
 */

app
    .factory(
    'configService',
    ['$location', function($location) {
        var urlServico = $location.protocol() + "://" + $location.host() + ':8080';
        return {
            urlServico : function() {
                return urlServico;
            }
        };
    }]);

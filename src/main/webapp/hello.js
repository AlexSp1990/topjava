/**
 * Created by AlexS on 20.03.2016.
 */
function Hello($scope, $http) {
    $http.get('http://rest-service.guides.spring.io/greeting').
    success(function(data) {
        $scope.greeting = data;
    });
}
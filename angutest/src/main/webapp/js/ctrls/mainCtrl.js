/**
 * Created with IntelliJ IDEA.
 * User: luke
 * Date: 8/11/13
 * Time: 6:47 PM
 * To change this template use File | Settings | File Templates.
 */

function ListExpansesCtrl($scope, $http) {

    $http.get("api/expense").success(function data(data){
        $scope.expanses = data;

    })

    $scope.expansesOrder = 'name';
}

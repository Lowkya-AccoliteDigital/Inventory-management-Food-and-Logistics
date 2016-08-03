


angular.module('app', [])
        .controller('myCtrl', function ($scope, $http) {
        	$scope.showTable = false;
        	$scope.itemName='';
        	$scope.typeId = 0;
        	$scope.itemType = '';
        	$scope.itemSubType='';

         	$scope.saveMessage = function(id, data) {
         
         
                    $http({
                        method : 'POST',
                        url : 'http://localhost:8080/AngularjsJAXRSCRUDExample/rest/messages/'+ id+ '/'+data
                   
                    }).then( _success, _error );
                };

  $scope.addType = function(type, subType) { 		
      $http({
            method : 'GET',
            url : 'http://localhost:8080/ifsm/rest/addItemType/'+ type+'/' + subType 
        }).then(function successCallback(response) {
       
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
        $scope.itemName='';
	}

   $scope.addItem = function(name, id) { 		
      $http({
            method : 'GET',
            url : 'http://localhost:8080/ifsm/rest/addItems/'+ name+'/' + id 
        }).then(function successCallback(response) {
       
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
        $scope.itemName='';
	}

	$scope.removeItem = function(name) { 		
	      $http({
	            method : 'GET',
	            url : 'http://localhost:8080/ifsm/rest/removeItems/'+ name 
	        }).then(function successCallback(response) {
	       
	        }, function errorCallback(response) {
	            console.log(response.statusText);
	        });
	        $scope.itemName='';
		}
 	$scope.allItems = function() {
 		$scope.showTable = true;
      $http({
            method : 'GET',
            url : 'http://localhost:8080/ifsm/rest/item/1'
        }).then(function successCallback(response) {
            $scope.items = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
	}

	$scope.items = [{itemID:2, itemName:'pen', visiblity:0, typeID:1},
		{itemID:3, itemName:'color', visiblity:0, typeID:1}];
	
});
        

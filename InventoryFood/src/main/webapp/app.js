


angular.module('app', [])
        .controller('myCtrl', function ($scope, $http) {
        	$scope.showTable = false;
        	$scope.showSummary=false;
        	$scope.itemName='';
        	$scope.typeId = 0;
        	$scope.itemType = '';
        	$scope.itemSubType='';
        	$scope.fromDate='';
        	$scope.toDate='';
         	
  $scope.addType = function(type, subType) { 		
      $http({
            method : 'GET',
            url : 'http://localhost:8081/InventoryFood/rest/addItemType/'+ type+'/' + subType 
        }).then(function successCallback(response) {
       
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
        $scope.itemName='';
	}

   $scope.addItem = function(name, id) { 		
      $http({
            method : 'GET',
            url : 'http://localhost:8081/InventoryFood/rest/addItem/'+ name+'/' + id 
        }).then(function successCallback(response) {
       
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
        $scope.itemName='';
	}

	$scope.removeItem = function(name) { 		
	      $http({
	            method : 'GET',
	            url : 'http://localhost:8081/InventoryFood/rest/removeItem/'+ name 
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
            url : 'http://localhost:8081/InventoryFood/rest/items/1'
        }).then(function successCallback(response) {
            $scope.items = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
	}

/*	$scope.items = [{itemID:2, itemName:'pen', visiblity:0, typeID:1},
		{itemID:3, itemName:'color', visiblity:0, typeID:1}];*/
	$scope.viewSummary=function(fromDate,toDate){
		$scope.showSummary=true;
	 $http({
         method : 'GET',
         url : "http://localhost:8081/InventoryFood/rest/summary/'"+fromDate+"'/'"+toDate+"'"
     }).then(function successCallback(response) {
         $scope.summary = response.data;
     }, function errorCallback(response) {
         console.log(response.statusText);
     });
}
	$timeout(emailExpiry,86400000);
	
	function emailExpiry() {
		$http({
	         method : 'GET',
	         url : "http://localhost:8081/InventoryFood/rest/email/expiry"
	})
	}
	});
      

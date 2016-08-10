


angular.module('app', ['ui.router','ui.bootstrap', 'ngSanitize',  'ngAnimate', 'ngMaterial', 'ngMessages', 'material.svgAssetsCache'])
        .controller('myCtrl',function ($scope, $http, $uibModal,$interval) {
         $scope.showTable = false;
        

        //  $scope.email=profile.getEmail();
         $scope.mail = localStorage.getItem("email");
            $scope.brand;
            $scope.quantity;
            $scope.doe;
            $scope.dop;
        $scope.selectedCity = "Bangalore";
        $scope.cities = ["Bangalore","Hyderabad","Delhi"];
        $scope.filePath;
    
    $scope.doeF ;
    $scope.dopF ;

    $scope.fromDt =  new Date('01/01/2000');
    $scope.toDt = new Date();
    $scope.dt = new Date();

$scope.collection ={itemName:'', typeId:'', subItemids:'', quantity:'', unit:'null',dateOfPurchase:'', dateOfExpiry:'', location:$scope.selectedCity}


$scope.formatDate = function(dt) {
    var day;
    var zero = '0';
    var ten = "10";
    day = dt.getDate().toString();
    if(+day < +ten) {
        day = zero.concat(day);
    }
    var month =  dt.getMonth().toString();
    month = +month+1+"";
    if(+month < +ten) {

        month = zero.concat(month);
    }

    var year =  dt.getFullYear().toString();   
    var finalDate = year.concat('-');
    finalDate = finalDate.concat(month);
    finalDate = finalDate.concat('-');
    finalDate = finalDate.concat(day);
    return finalDate;
}

$scope.showSummary = function() {
    var frm = $scope.formatDate($scope.fromDt);
    var todate = $scope.formatDate($scope.toDt);
    $http({
            method : 'GET',
            url : 'http://localhost:8081/InventoryFood/rest/summary/'+ frm+'/' + todate 
        }).then(function successCallback(response) {
            var temp = angular.copy(response.data);
            for(var i = 0; i < temp.length; i++) {
                if(temp[i].remaining == 0) {
                    temp[i].remaining = temp[i].total;
                }
            }
            $scope.items = temp;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
}



 $scope.addCollection = function() {
        var str = '';
        for (var i = 0; i < $scope.items.length; i++) {
            
            if ($scope.arr[i] == 'YES') {
                str=str.concat($scope.items[i].itemId);
                str=str.concat(',');
            }
        }
        var len = str.length;
        str = str.substring(0, len-1);

$scope.doe = $scope.formatDate($scope.doeF);
$scope.dop = $scope.formatDate($scope.dopF);
$scope.collection.subItemids = str;
$scope.collection.dateOfPurchase = $scope.dop;
$scope.collection.dateOfExpiry = $scope.doe;


         $http({
                    method : 'POST',
                    url : 'http://localhost:8081/InventoryFood/rest/addItemCollection',
                    data: $scope.collection
                }).then(function successCallback(response) {
                $scope.allItems();s
                }, function errorCallback(response) {
                    console.log(response.statusText);
                });
              

    }
            $scope.initiallizeArr = function() {
                $scope.arr = new Array($scope.items.length);
            }
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
/*to add quantity to an item*/
   $scope.addItemQty = function(item, qty, doeff, dopff) {  
        item.quantity = qty;
        item.dateOfExpiry = $scope.formatDate(doeff);
        item.dateOfPurchase = $scope.formatDate(dopff);
      $http({
            method : 'POST',
            url : 'http://localhost:8081/InventoryFood/rest/addItemQuantity',
            data: item
        }).then(function successCallback(response) {
            $scope.allItems();
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
       
 }

/*to reduce quantity of item*/

   $scope.reduceItemQty = function(item, qty) {    
        
       var id = item.itemId;

       var temp=[];
         /*var temp = [{brand:"being human",
dateOfExpiry:"2016-09-09",
dateOfPurchase:"2016-08-08",
itemId:146,
itemName:"pawan MS",
location:"Delhi",
quantity:10,
typeid:96,
unit:"null"}];*/
            var ansArr = [];
            for (var i = 0; i < $scope.originalItems.length; i++) {
                if ($scope.originalItems[i].itemId == id) {
                    temp.push($scope.originalItems[i]);
                    
                }
            }
            var min = '9999-99-99';
            var minIndex = 0;
            while(qty > 0) {
                for (var i = 0; i < temp.length; i++) {
                    if(temp[i].dateOfExpiry < min) {
                        min = temp[i].dateOfExpiry;
                        minIndex = i;
                    }
                }
                if (qty > temp[minIndex].quantity) {
                    qty = qty - temp[minIndex].quantity;
                     ansArr[ansArr.length] = angular.copy(temp[minIndex]);

                } else {
                    temp[minIndex].quantity = qty;
                    
                    ansArr[ansArr.length] = angular.copy(temp[minIndex]);

                    qty = 0;
                }
                 
                 temp[minIndex].dateOfExpiry = '9999-99-99';
                 min = '9999-99-99';
            }
      $http({
            method : 'POST',
            url : 'http://localhost:8081/InventoryFood/rest/removeItemQuantity',
            data: ansArr
        }).then(function successCallback(response) {
            $scope.allItems();
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
       
    }
/*to delete an item */
 $scope.removeItem = function(id) {   
       $http({
             method : 'GET',

             url : 'http://localhost:8081/InventoryFood/rest/removeItemCollection/'+ id
         }).then(function successCallback(response) {
                $scope.allItems();
         }, function errorCallback(response) {
             console.log(response.statusText);
         });
        
  }


$scope.newItem = {itemName:'', typeId:'', brand:'', quantity:0, unit:'', dateOfPurchase:'', dateOfExpiry:'', location: $scope.selectedCity,visibility:1};
$scope.addItem = function(item) {  
    item.dateOfExpiry = $scope.formatDate($scope.doeF);
    item.dateOfPurchase = $scope.formatDate($scope.dopF);
       $http({
             method : 'POST',
             url : 'http://localhost:8081/InventoryFood/rest/addItem',
             data: item
         }).then(function successCallback(response) {
                $scope.allItems();
         }, function errorCallback(response) {
             console.log(response.statusText);
         });
  }

$scope.addItemFile = function(fp) {
        $http({
             method : 'GET',
             url : 'http://localhost:8081/InventoryFood/rest/fileUpload?file='+fp
             
         }).then(function successCallback(response) {
                $scope.allItems();
         }, function errorCallback(response) {
             console.log(response.statusText);
         });
}

  $scope.allItems = function() {
   $scope.showTable = true;
      $http({
            method : 'GET',
            url : 'http://localhost:8081/InventoryFood/rest/items'
        }).then(function successCallback(response) {
           
            $scope.items = angular.copy(response.data);
             $scope.originalItems = [];
            var temp = [];
            
               for(var i = 0; i < response.data.length; i++ ) {
                if ( response.data[i].location == $scope.selectedCity) {
                    $scope.originalItems.push(response.data[i]);
                }
            }
          
            for (var i = 0; i < $scope.items.length; i++) {
                var flag = 0;
                for (var j = 0; j < temp.length; j++) {
                    if ($scope.items[i].itemId == temp[j].itemId  && $scope.items[i].location == $scope.selectedCity)  {
                        if($scope.items[i].dateOfExpiry < temp[j].dateOfExpiry) {
                            temp[j].dateOfExpiry = $scope.items[i].dateOfExpiry;
                        }

                        temp[j].quantity += $scope.items[i].quantity;
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    temp.push($scope.items[i]);
                }
            }


         
            $scope.items = temp;

        }, function errorCallback(response) {
            console.log(response.statusText);
        });
 }


    //email expiry
    $interval(emailExpiry,180000);
 
 function emailExpiry() {
  $http({
          method : 'GET',
          url : "http://localhost:8081/InventoryFood/rest/email/expiry"
 })
 }
$scope.printDiv = function(divName) {
     var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.body.innerHTML;

     document.body.innerHTML = printContents;

     window.print();

     document.body.innerHTML = originalContents;
};

$scope.selectedType='Stationary';
$scope.getTypes = function() {

    $http({
             method : 'GET',
             
             url : 'http://localhost:8081/InventoryFood/rest/type'
         }).then(function successCallback(response) {
                $scope.types = response.data;
         }, function errorCallback(response) {
             console.log(response.statusText);
    });
};

 
 $scope.typeChange = function() {
     $http({
             method : 'GET',
             
             url : 'http://localhost:8081/InventoryFood/rest/summary/' + $scope.selectedType
         }).then(function successCallback(response) {
                $scope.items = response.data;
         }, function errorCallback(response) {
             console.log(response.statusText);
    });
 }


 $scope.showItemUser = function() {
     $http({
             method : 'GET',
             
             url : 'http://localhost:8081/InventoryFood/rest/users/'+ $scope.selectedCity
         }).then(function successCallback(response) {
                $scope.items = response.data;
         }, function errorCallback(response) {
             console.log(response.statusText);
    });
 }







$scope.onLoad = function() {
    gapi.load('auth2', function() {
      gapi.auth2.init();
    });
  }

 $scope.signOut = function() {
   var auth2 = gapi.auth2.getAuthInstance();
   console.log(auth2);
   auth2.signOut().then(function () {
    
  console.log('User signed out.');
     //location.href="http://localhost:8081/InventoryFood/firstpage.html";
   });
   
   
   
 }
 


 

$scope.SignIn = function (googleUser) {
 
   var id_token = googleUser.getAuthResponse().id_token;
   var profile = googleUser.getBasicProfile();
   console.log('Email: ' + profile.getEmail());
   var email=profile.getEmail();
 //  window.location = "";
  if(profile.getEmail()=="pawan.prakash305@gmail.com") {
    
  
  window.location = "http://localhost:8081/InventoryFood/index.html";
  
  }
   else if(profile.getEmail()=="pawan.prakash@accoliteindia.com") {


    window.location = "http://localhost:8081/InventoryFood/index.html";
   
}
  
  else{
   
   $scope.signOut();

  
   location.href="http://localhost:8081/emailnotification/firstpage.html";
   
  }
 }






 $scope.items = [{itemID:2, itemName:'pen', visiblity:0, typeID:1},
  {itemID:3, itemName:'color', visiblity:0, typeID:1}];
 
});
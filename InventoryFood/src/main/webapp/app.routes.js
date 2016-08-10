(function () {
    'use strict';

    angular
        .module('app')
        .config(config);

    config.$inject = ['$stateProvider', '$urlRouterProvider'];

    function config($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('/rest/admin');

        $stateProvider
            .state('admin', {
                url: '/rest/admin',
                templateUrl: 'admin.html',
                controller:'myCtrl'
       
            })
            .state('user', {
                url: '/rest/user',
                templateUrl: 'user.html',
                controller:'myCtrl'
       
            })

            .state('summary', {
                url: '/rest/summary',
                templateUrl: 'summary.html',
                controller:'myCtrl'
       
            })
            .state('about', {
                url: '/rest/about',
                templateUrl: 'aboutus.html',
                controller:'myCtrl'
       
            });

    }

})();


angular.module("sv.AccountServices", ["ngResource"]);
(function(angular) {
  var AccountFactory = function($resource) {
    return $resource('/accountsAPI/:id', {
      id: '@id'
    }, {
      update: {
        method: "PUT"
      },
      remove: {
        method: "DELETE"
      }
    });
  };
  
  AccountFactory.$inject = ['$resource'];
  angular.module("sv.AccountServices").factory("Account", AccountFactory);
}(angular));
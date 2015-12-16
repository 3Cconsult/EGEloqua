angular.module("sv.ContactServices", ["ngResource"]);
(function(angular) {
  var ContactFactory = function($resource) {
    return $resource('/contactsAPI/:id', {
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
  
  ContactFactory.$inject = ['$resource'];
  angular.module("sv.ContactServices").factory("Contact", ContactFactory);
}(angular));
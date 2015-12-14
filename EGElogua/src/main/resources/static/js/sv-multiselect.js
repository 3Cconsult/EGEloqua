var app_directives = angular.module('sv.multiselect', []);


app_directives.directive('svMultiselect', function(){
   return {
       restrict: 'E',
       scope:{           
            aItems: '=info'
       },
       template: "<div class='btn-group' data-ng-class='{open: open}'>"+
        "<button class='btn btn-default'><span class='icon glyphicon glyphicon-th-list'></span></button>"+
		
                "<button class='btn btn-default dropdown-toggle' data-ng-click='open=!open'><span class='caret'></span></button>"+
                "<ul class='dropdown-menu' aria-labelledby='dropdownMenu'>" + 
                    "<li><a data-ng-click='selectAll()'><i class='glyphicon glyphicon-ok-sign'></i>  Check All</a></li>" +
                    "<li><a data-ng-click='deselectAll();'><i class='glyphicon glyphicon-remove-sign'></i>  Uncheck All</a></li>" +                    
                    "<li class='divider'></li>" +
                    "<li data-ng-repeat='item in aItems'> <a data-ng-click='setSelectedItem(item)'><i data-ng-class='isChecked(item)'></i>{{item.title}}</a></li>" +                                        
                "</ul>" +
            "</div>" ,
       controller: function($scope){
            $scope.selectAll = function () {
				_.forEach($scope.aItems,function(item,key){
					item.show = true;
				});
            };            
            $scope.deselectAll = function() {
				_.forEach($scope.aItems,function(item,key){
					item.show = false;
				});
            };
            $scope.setSelectedItem = function(option){
				option.show = !option.show ;
            };
            $scope.isChecked = function (option) {   
				if(option.show){
					return 'glyphicon glyphicon-ok pull-right';
				}else{
					return false;
				}
            };                                 
       }
   } 
});
	
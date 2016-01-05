var app_directives = angular.module('sv.picklist', []);


app_directives.directive('svPicklist', function(){
   return {
       restrict: 'E',
       scope:{    
			aItems:'=info'
			,parRecord: '=row'
			,parFieldName: '=parFieldName'
			,val:'=val'
			,tab1:'=tab1'
       },
       template: "<div class='btn-group' data-ng-class='{open: open}'>"+
					"<input type='text' name='accPick' ng-model='val' class='editable-input form-control input-sm' ng-change='onCellChange(val)'/>"+
					"<ul class='dropdown-menu' aria-labelledby='dropdownMenu' style='overflow-y:scroll; height:120px;'>" + 
						"<li data-ng-repeat='item in aShown'> <a data-ng-click='onClickItem(item)'>{{item.name}}</a></li>" +                                        
					"</ul>" +
				"</div>" ,
       controller: function($scope){
			$scope.aShown = [];
			if($scope.val == null){
				$scope.val = "";
			}
			$scope.prevval = $scope.val;
			
			$scope.pickRecord = function (option){
				$scope.parRecord.accountName = option.name;
				$scope.parRecord.accountId = option.id;
				$scope.tab1.onCellChange($scope.parRecord,'accountName');
				$scope.tab1.onCellChange($scope.parRecord,'accountId');
			};
			$scope.openPickList = function(){
				$scope.open = true;
			};
			
			$scope.closePickList = function(){
				$scope.open = false;
			};
			
			$scope.onClickItem = function(option){
				$scope.pickRecord(option);
				$scope.closePickList();
			};
			$scope.onCellChange = function(val){
//				if($scope.prevval == null){
//					$scope.prevval=angular.copy($scope.val);
//				}
				var bFindStrict = true;
				if($scope.prevval.indexOf($scope.val)==0){//если буквы удаляются из строки, то точное совпадение не ищем
					bFindStrict = false;
				}
				$scope.prevval = $scope.val;
				$scope.aShown = [];
				var i;
				for(i=0;i<$scope.aItems.length;i++){
					var item = $scope.aItems[i];
					if(bFindStrict && item.name.toUpperCase() == val.toUpperCase()){
						$scope.aShown = [];
						$scope.aShown.push(item);
						break;
					}
					if(item.name.toUpperCase().indexOf(val.toUpperCase())==0){
						$scope.aShown.push(item);
					}
				}
				if(bFindStrict && $scope.aShown.length==1){
					$scope.pickRecord($scope.aShown[0]);
					$scope.closePickList();
				}else{
					$scope.openPickList();
				}
				
			};
       }
   } 
});
	
angular.module("myApp", ["ngResource", "ngTable", 'ngAnimate', 'ui.bootstrap', 'sv.multiselect', "sv.ContactServices","sv.AccountServices"]);

(function () {
	"use strict";
	var dataset = [];
	var accounts = [];

	angular.module("myApp").controller("contactTableController", contactTableController);
	contactTableController.$inject = ["NgTableParams", "Contact","Account"];
	function contactTableController(NgTableParams, Contact, Account) {
		var self = this;
		var originalData = angular.copy(dataset);
		self.cols = [
			{
				field : "action",
				title : "Control",
				dataType : "command",
				show : true,
				width : '10%'
			},	{
				field : "id",
				title : "ID",
				filter : {
					id : "text"
				},
				sortable : "id",
				dataType : "text",
				show : true,
				isReadOnly : true,
				width : '4%'
			}, {
				field : "firstName",
				title : "firstName",
				filter : {
					firstName : "text"
				},
				sortable : "firstName",
				dataType : "text",
				show : true,
				isReadOnly : false,
				width : '10%'
			}, {
				field : "lastName",
				title : "lastName",
				filter : {
					lastName : "text"
				},
				sortable : "lastName",
				dataType : "text",
				show : true,
				isReadOnly : false,
				width : '20%'
			}, {
				field : "emailAddress",
				title : "emailAddress",
				filter : {
					emailAddress : "text"
				},
				sortable : "emailAddress",
				dataType : "text",
				show : true,
				isReadOnly : false,
				width : '20%'
			}, {
				field : "accountName",
				title : "accountName",
				filter : {
					accountName : "pick"
				},
				sortable : "accountName",
				dataType : "pick",
				show : true,
				isReadOnly : false,
				width : '10%'
			}, {
				field : "accountId",
				title : "accountId",
				filter : {
					accountId : "text"
				},
				sortable : "accountId",
				dataType : "text",
				show : true,
				isReadOnly : true,
				width : '10%'				
			}, {
				field : "address1",
				title : "Address",
				filter : {
					address1 : "text"
				},
				sortable : "address1",
				dataType : "text",
				show : true,
				isReadOnly : false,
				width : '20%'
			}, {
				field : "city",
				title : "Сity",
				filter : {
					city : "text"
				},
				sortable : "city",
				dataType : "text",
				show : true,
				isReadOnly : false,
				width : '10%'
			}, {
				field : "postalCode",
				title : "Postal Code",
				filter : {
					postalCode : "text"
				},
				sortable : "postalCode",
				dataType : "text",
				show : true,
				isReadOnly : false,
				width : '8%'
			}, {
				field : "businessPhone",
				title : "Business Phone",
				filter : {
					businessPhone : "text"
				},
				sortable : "businessPhone",
				dataType : "text",
				show : true,
				isReadOnly : false,
				width : '8%'
			}
		];
		
		self.tableParams = new NgTableParams({
				count : 5
			}, {
				counts : [5, 20, 100],
				dataset : angular.copy(dataset)
			});
		self.accounts = accounts;
		self.aDelete = [];

		self.add = add;
		self.cancelChanges = cancelChanges;
		self.del = del;
		self.hasChanges = hasChanges;
		self.saveChanges = saveChanges;

		self.cancel = cancel;
		self.del = del;
		self.save = save;
		self.getContacts = getContacts;
		self.uploadContacts = uploadContacts;
		self.selectAccount = selectAccount;
		function selectAccount(row,rowForm,fieldName){
			var acc = _.find(self.accounts, function (item) {
				return item.name === row.accountName;
			});
			row.accountId = acc.id;
		}
		function add() {
			self.isEditing = true;
			self.isAdding = true;
			self.tableParams.settings().dataset.unshift( new Contact({
				name : "",
				isInsert : true
			}));
			// we need to ensure the user sees the new row we've just added.
			// it seems a poor but reliable choice to remove sorting and move them to the first page
			// where we know that our new item was added to
			self.tableParams.sorting({});
			self.tableParams.page(1);
			self.tableParams.reload();
		}
		function getContacts() {
			Contact.query(function (response) {
				var resp = response ? response : [];
				self.tableParams.settings().dataset = resp;
				self.dataset = resp;
				originalData = angular.copy(resp);
				self.aDelete = [];
				self.isDirty = false;
				self.tableParams.reload();
			});
			Account.query(function (response) {
				var resp = response ? response : [];
				self.accounts = resp;
			});

		}
		function uploadContacts() {
			self.saveChanges();		
			_.each(self.aDelete, function (item) {
				item.$delete();
			});
			_.each(self.tableParams.settings().dataset, function (item) {
				if (item.isDirty) {
					if (item.isInsert) {
						item.name = 'test ' + item.name;
						item.$save();

					} else {
						item.$update();
					}
				}
			});

		}

		function cancelChanges() {
			resetTableStatus();
			var currentPage = self.tableParams.page();
			self.tableParams.settings({
				dataset : angular.copy(originalData)
			});
			// keep the user on the current page when we can
			if (!self.isAdding) {
				self.tableParams.page(currentPage);
			}
		}

		function del(row) {
			var acc = _.find(originalData, function (item) {
					return row.id === item.id;
				});
			self.aDelete.push(acc);

			_.remove(self.tableParams.settings().dataset, function (item) {
				return row.id === item.id;
			});
			self.tableTracker.untrack(row);
			self.tableParams.reload().then(function (data) {
				if (data.length === 0 && self.tableParams.total() > 0) {
					self.tableParams.page(self.tableParams.page() - 1);
					self.tableParams.reload();
				}
			});
		}
		function resetRow(row, rowForm) {
			row.isEditing = false;
			rowForm.$setPristine();
			self.tableTracker.untrack(row);
			return _.find(originalData, function (r) {
				var ret = (r.id === row.id);
				var aa = 0;
				return ret;
			});
		}

		function save(row, rowForm) {
			var originalRow = resetRow(row, rowForm);
			angular.extend(originalRow, row);
		}

		function cancel(row, rowForm) {
			var originalRow = resetRow(row, rowForm);
			angular.extend(row, originalRow);
		}

		function hasChanges() {
			if (self.tableForm.$dirty)
				self.isDirty = true;
			return self.isDirty || self.aDelete.length > 0;
		}

		function resetTableStatus() {
			self.isEditing = false;
			self.isAdding = false;
			self.aDelete = [];
			self.tableTracker.reset();
			self.tableForm.$setPristine();
		}

		function saveChanges() {
			resetTableStatus();
			var currentPage = self.tableParams.page();
			originalData = angular.copy(self.tableParams.settings().dataset);
		}
	}
})();

(function () {
	"use strict";

	angular.module("myApp").run(configureDefaults);
	configureDefaults.$inject = ["ngTableDefaults"];

	function configureDefaults(ngTableDefaults) {
		ngTableDefaults.params.count = 5;
		ngTableDefaults.settings.counts = [];
	}
})();

/**********
The following directives are necessary in order to track dirty state and validity of the rows
in the table as the user pages within the grid
------------------------
 */

(function () {
	angular.module("myApp").directive("trackedTable", trackedTable);

	trackedTable.$inject = [];

	function trackedTable() {
		return {
			restrict : "A",
			priority : -1,
			require : "ngForm",
			controller : trackedTableController
		};
	}

	trackedTableController.$inject = ["$scope", "$parse", "$attrs", "$element"];

	function trackedTableController($scope, $parse, $attrs, $element) {
		var self = this;
		var tableForm = $element.controller("form");
		var dirtyCellsByRow = [];
		var invalidCellsByRow = [];

		init();

		////////

		function init() {
			var setter = $parse($attrs.trackedTable).assign;
			setter($scope, self);
			$scope.$on("$destroy", function () {
				setter(null);
			});

			self.reset = reset;
			self.isCellDirty = isCellDirty;
			self.setCellDirty = setCellDirty;
			self.setCellInvalid = setCellInvalid;
			self.untrack = untrack;
		}

		function getCellsForRow(row, cellsByRow) {
			return _.find(cellsByRow, function (entry) {
				return entry.row === row;
			})
		}

		function isCellDirty(row, cell) {
			var rowCells = getCellsForRow(row, dirtyCellsByRow);
			return rowCells && rowCells.cells.indexOf(cell) !== -1;
		}

		function reset() {
			dirtyCellsByRow = [];
			invalidCellsByRow = [];
			setInvalid(false);
		}

		function setCellDirty(row, cell, isDirty) {
			row.isDirty = true;
			setCellStatus(row, cell, isDirty, dirtyCellsByRow);
		}

		function setCellInvalid(row, cell, isInvalid) {
			setCellStatus(row, cell, isInvalid, invalidCellsByRow);
			setInvalid(invalidCellsByRow.length > 0);
		}

		function setCellStatus(row, cell, value, cellsByRow) {
			var rowCells = getCellsForRow(row, cellsByRow);
			if (!rowCells && !value) {
				return;
			}

			if (value) {
				if (!rowCells) {
					rowCells = {
						row : row,
						cells : []
					};
					cellsByRow.push(rowCells);
				}
				if (rowCells.cells.indexOf(cell) === -1) {
					rowCells.cells.push(cell);
				}
			} else {
				_.remove(rowCells.cells, function (item) {
					return cell === item;
				});
				if (rowCells.cells.length === 0) {
					_.remove(cellsByRow, function (item) {
						return rowCells === item;
					});
				}
			}
		}

		function setInvalid(isInvalid) {
			self.$invalid = isInvalid;
			self.$valid = !isInvalid;
		}

		function untrack(row) {
			_.remove(invalidCellsByRow, function (item) {
				return item.row === row;
			});
			_.remove(dirtyCellsByRow, function (item) {
				return item.row === row;
			});
			setInvalid(invalidCellsByRow.length > 0);
		}
	}
})();

(function () {
	angular.module("myApp").directive("trackedTableRow", trackedTableRow);

	trackedTableRow.$inject = [];

	function trackedTableRow() {
		return {
			restrict : "A",
			priority : -1,
			require : ["^trackedTable", "ngForm"],
			controller : trackedTableRowController
		};
	}

	trackedTableRowController.$inject = ["$attrs", "$element", "$parse", "$scope"];

	function trackedTableRowController($attrs, $element, $parse, $scope) {
		var self = this;
		var row = $parse($attrs.trackedTableRow)($scope);
		var rowFormCtrl = $element.controller("form");
		var trackedTableCtrl = $element.controller("trackedTable");

		self.isCellDirty = isCellDirty;
		self.setCellDirty = setCellDirty;
		self.setCellInvalid = setCellInvalid;

		function isCellDirty(cell) {
			return trackedTableCtrl.isCellDirty(row, cell);
		}

		function setCellDirty(cell, isDirty) {
			trackedTableCtrl.setCellDirty(row, cell, isDirty)
		}

		function setCellInvalid(cell, isInvalid) {
			trackedTableCtrl.setCellInvalid(row, cell, isInvalid)
		}
	}
})();

(function () {
	angular.module("myApp").directive("trackedTableCell", trackedTableCell);

	trackedTableCell.$inject = [];

	function trackedTableCell() {
		return {
			restrict : "A",
			priority : -1,
			scope : true,
			require : ["^trackedTableRow", "ngForm"],
			controller : trackedTableCellController
		};
	}

	trackedTableCellController.$inject = ["$attrs", "$element", "$scope"];

	function trackedTableCellController($attrs, $element, $scope) {
		var self = this;
		var cellFormCtrl = $element.controller("form");
		var cellName = cellFormCtrl.$name;
		var trackedTableRowCtrl = $element.controller("trackedTableRow");

		if (trackedTableRowCtrl.isCellDirty(cellName)) {
			cellFormCtrl.$setDirty();
		} else {
			cellFormCtrl.$setPristine();
		}
		// note: we don't have to force setting validaty as angular will run validations
		// when we page back to a row that contains invalid data

		$scope.$watch(function () {
			return cellFormCtrl.$dirty;
		}, function (newValue, oldValue) {
			if (newValue === oldValue)
				return;

			trackedTableRowCtrl.setCellDirty(cellName, newValue);
		});

		$scope.$watch(function () {
			return cellFormCtrl.$invalid;
		}, function (newValue, oldValue) {
			if (newValue === oldValue)
				return;

			trackedTableRowCtrl.setCellInvalid(cellName, newValue);
		});
	}
})();

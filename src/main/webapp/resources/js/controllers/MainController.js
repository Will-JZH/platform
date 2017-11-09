'use strict';

/**
 * 
 */

var MainController = function($scope, $http) {
	var url = window.location.href;
	var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
	var paraObj = {};
	var j;
	for (var i = 0; j = paraString[i]; i++) {
		paraObj[j.substring(0, j.indexOf("="))] = j.substring(
				j.indexOf("=") + 1, j.length);
	}
	console.log(paraObj);

	$scope.authority = paraObj["authority"];

	// ********************产品发布为服务*****************//
//	$scope.partCount = 1;
//	$scope.countUp = function() {
//		if ($scope.partCount < 7) {
//			$scope.partCount += 1;
//		}
//	}
//	$scope.countDown = function() {
//		if ($scope.partCount > 1) {
//			$scope.partCount -= 1;
//		}
//	}

	$scope.savePublish0 = function() {
		var productContent = "";
		if (typeof ($scope.part1) != "undefined") {
			productContent = $scope.part1;
		}
		if (typeof ($scope.part2) != "undefined") {
			productContent += "+" + $scope.part2;
		}
		if (typeof ($scope.part3) != "undefined") {
			productContent += "+" + $scope.part3;
		}
		if (typeof ($scope.part4) != "undefined") {
			productContent += "+" + $scope.part4;
		}
		if (typeof ($scope.part5) != "undefined") {
			productContent += "+" + $scope.part5;
		}
		if (typeof ($scope.part6) != "undefined") {
			productContent += "+" + $scope.part6;
		}
		if (typeof ($scope.part7) != "undefined") {
			productContent += "+" + $scope.part7;
		}
		if (typeof ($scope.part78) != "undefined") {
			productContent += "+" + $scope.part8;
		}

		$http(
				{
					method : "POST",
					url : "/AngularSpringmvcMybatis/product/addProduct",
					data : {
						productName : $scope.machineTypePublish + "("
								+ $scope.machineVersion + ")",
						userID : paraObj["userID"],
						productDescript : $scope.serviceDescribe,
						inventory : $scope.inventory,
						price : $scope.price,
						productContent : productContent
					}
				}).success(function(data, status) {
			alert("success..." + status);
		}).error(function(data, status) {
			alert("error..." + status);
		});
	}

	$scope.clearPublish0 = function() {
		$scope.machineTypePublish = "";
		$scope.machineVersion = "";
		$scope.serviceDescribe = "";
		$scope.inventory = "";
		$scope.price = "";
		$scope.part1 = "";
		$scope.part2 = "";
		$scope.part3 = "";
		$scope.part4 = "";
		$scope.part5 = "";
		$scope.part6 = "";
		$scope.part7 = "";
		$scope.part8 = "";
	}

	// ********************加工能力发布为服务*****************//
	$scope.savePublish1 = function() {
		$http({
			method : "POST",
			url : "/AngularSpringmvcMybatis/resource/addServiceResource",
			data : {
				resourceName : $scope.serviceObj + $scope.serviceType,
				userID : paraObj["userID"],
				category : 1,
				description : $scope.serviceDescription,
				procTime : $scope.procTime,
				fee : $scope.fee,
				resourceContent : $scope.resourceContent
			}
		}).success(function(data, status) {
			$scope.publishResourceCal();
		}).error(function(data, status) {
			alert("error..." + status);
		});
	}

	$scope.publishResourceCal = function() {
		$http({
			method : "POST",
			url : "/AngularSpringmvcMybatis/cal/addResourceCal",
			data : {
				resourceName : $scope.serviceObj + $scope.serviceType,
				userID : paraObj["userID"],
				startTime : $scope.startTime,
				endTime : $scope.endTime
			}
		}).success(function(data, status) {
			alert("success..." + status);
		}).error(function(data, status) {
			alert("error..." + status);
		});
	}

	$scope.clearPublish1 = function() {
		$scope.serviceType = "";
		$scope.serviceObj = "";
		$scope.serviceDescription = "";
		$scope.procTime = "";
		$scope.fee = "";
		$scope.resourceContent = "";
		$scope.startTime = "";
		$scope.endTime = "";
	}

	// 存放传入的wsdl url路径
	$scope.wsdlPath = "";

	$scope.uploadFile = function() {
		var formData = new FormData();
		formData.append("file", file.files[0]);
		$http.post('main/newDocument', formData, {
			transformRequest : function(data, headersGetterFunction) {
				return data;
			},
			headers : {
				'Content-Type' : undefined
			}
		}).success(function(data, status) {
			console.log(data);
			console.log("Success ... " + status);
			alert("文件上传成功！");
		}).error(function(data, status) {
			console.log("Error ... " + status);
			console.log(data);
			alert("上传出错..." + status);
		});
	};

	$scope.clearPublish = function() {
		$scope.enterpriseName = "";
		$scope.machineTypePublish = "";
		$scope.machineVersion = "";
		$scope.partType = "";
		$scope.shapeType = "";
		$scope.technologyType = "";
		$scope.size = "";
		$scope.serviceDescribe = "";
		$scope.startTime = "";
		$scope.endTime = "";
	}

	$scope.enterprise = "";
	$scope.machine = "";
	$scope.machineType = "";

	// 获取RDF4J中已有的内容
	$scope.getInfoFromRDF4J = function() {
		$http.get("main/getInfoFromRDF4J").success(function(data) {
			$scope.enterprises = data[0];
			$scope.machines = data[1];
			$scope.machineTypes = data[2];
		});
	}
	$scope.getInfoFromRDF4J();

	// 删除服务
	$scope.deleteService = function() {

	}
	// 取消删除
	$scope.cancelDelete = function() {
		$scope.enterprise = "";
		$scope.machine = "";
		$scope.machineType = "";
	}

	// ********************服务组合*********************//
	$scope.isProduct = true;
	$scope.publishProductDemand = function() {
		if ($scope.productVersion != undefined && $scope.productVersion != '') {
			$http.get("/AngularSpringmvcMybatis/product/getProductByNameAndVersion/" + $scope.productName + "(" + $scope.productVersion + ")").success( function(data) {
				$scope.isProduct = true;
				console.log(data);
				$scope.messages = data;
				if(!$scope.messages){
					$http.get("/AngularSpringmvcMybatis/product/getProductByName/" + $scope.productName).success(function(data) {
						$scope.isProduct = true;
						console.log(data);
						$scope.messages = data;
						if (!$scope.messages) {
							$http.get("/AngularSpringmvcMybatis/resource/getServiceResourceByName/" + $scope.productName).success(function(data) {
								$scope.isProduct = false;
								console.log(data);
								$scope.serviceMessages = data;
							})
						}
					})
				}
			})
		}
		else {
			$http.get("/AngularSpringmvcMybatis/product/getProductByName/" + $scope.productName).success(function(data) {
				$scope.isProduct = true;
				console.log(data);
				$scope.messages = data;
				console.log($scope.messages.productName);
				if ($scope.messages.length == 0) {
					$http.get("/AngularSpringmvcMybatis/resource/getServiceResourceByName/" + $scope.productName).success(function(data) {
						$scope.isProduct = false;
						console.log(data);
						$scope.serviceMessages = data;
						console.log("serviceMessages : " + $scope.serviceMessages);
					})
				}
			})
			
		}
	}
	
	
	
	$scope.messages = "";
	$scope.serviceName = "";
	$scope.searchType = "服务名称";
	$scope.getServiceInfo = function(page) {
		// $http({
		// method: "POST",
		// url:"main/serviceSearch",
		// data : {
		// serviceName : $scope.serviceSearch,
		// serviceType : $scope.searchType
		// }
		// }).success(function(data){
		// $scope.atomicTotalPage = Math.ceil(data.length / 7);
		// $scope.messages = data.splice((page - 1) * 7, page * 7);
		// $scope.atomicCurrentPage = page;
		// });
		$http.get("atomic/searchByServiceName/" + $scope.serviceName).success(
				function(data) {
					$scope.atomicTotalPage = Math.ceil(data.length / 7);
					$scope.messages = data.splice((page - 1) * 7, page * 7);
					$scope.atomicCurrentPage = page;
				});

	};

	// 翻页设置
	// 上一页
	$scope.upPage = function(page) {
		if (page > 1) {
			$scope.getServiceInfo(page - 1)
		}
	}
	// 下一页
	$scope.downPage = function(page) {
		if (page < $scope.atomicTotalPage) {
			$scope.getServiceInfo(page + 1);
		}
	}

	// 存放选中的服务
	$scope.services = new Array();
	$scope.compositeServiceName = "";
	$scope.selectService = function(serviceName) {
		console.log("ss");
		console.log(serviceName);
		$scope.services.push(serviceName);
	};

	// 将服务组合 生成owl文档
	$scope.compositeService = function() {
		console.log($scope.services)
		$http({
			method : "POST",
			url : "main/compositeService",
			data : {
				services : $scope.services,
				compositeServiceName : $scope.compositeServiceName
			}
		}).success(function(data, status) {

			alert("服务组合成功，可在服务评价栏查看组合服务。");
			$scope.getCompositeServiceInfo();
		}).error(function(data, status) {
			alert("error..." + status);
		});
	};

	// *******************服务评价******************//
	// 翻页设置
	// 上一页
	$scope.goPreviousPage = function(page) {
		if (page > 1) {
			$scope.getCompositeServiceInfo(page - 1)
		}
	}
	// 下一页
	$scope.goNextPage = function(page) {
		if (page < $scope.totalPage) {
			$scope.getCompositeServiceInfo(page + 1);
		}
	}

	// 点击仿真按钮触发事件
	$scope.doSimulation = function(compositeServiceName) {
		$http.post("main/doSimulation", {
			"name" : compositeServiceName
		}).success(function(data) {
			$scope.getSimulationInfo();
			alert("仿真需要时间，请稍后查看！");
		});
	};

	// 得到已完成仿真的服务
	$scope.simulationFinished = function() {
		$http.get("main/simulationFinished").success(function(data) {
			$scope.simeds = data;
		});
	};
	$scope.simulationFinished();

	// 得到评价结果
	$scope.getEvaluationResult = function(name) {
		$http({
			method : "POST",
			url : "main/evaluation",
			data : {
				name : name
			}
		}).success(function(data, status) {
			$scope.evaluationResult = data;
		}).error(function(data, status) {
			alert("error..." + status);
		});
	};

	// 得到待仿真的服务
	$scope.getSimulationInfo = function() {
		$http.get("main/getSimulationInfo").success(function(data) {
			$scope.sims = data;
		});
	};
	$scope.getSimulationInfo();
	// 得到组合服务列表中的内容
	$scope.getCompositeServiceInfo = function(page) {
		$http.get("main/getCompositeServiceInfo").success(function(data) {
			$scope.totalPage = Math.ceil(data.length / 7);
			$scope.compositeServices = data.splice((page - 1) * 7, page * 7);
			$scope.currentPage = page;
		});
	};
	$scope.getCompositeServiceInfo(1);

	// 删除组合服务
	$scope.cancelService = function(id) {
		console.log(id);
		$http({
			method : "POST",
			url : "main/cancelService",
			data : {
				dataId : id
			}
		}).success(function(data, status) {
			alert("该组合服务已删除！");
		}).error(function(data, status) {
			alert("error..." + status);
		});
	};
}
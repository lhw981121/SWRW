//城市选择脚本

if(typeof(selectTrue)=="undefined"){//判断是否定义了变量selectTrue
	var selectTrue = ['','','']
}
var select1 = document.getElementById('citySelect1');
var select2 = document.getElementById('citySelect2');
var select3 = document.getElementById('citySelect3');
loopProvince(citys)

function loopProvince(arr){
	var provinceIndex,cityIndex,areaIndex;
	// 遍历 省
	for (var i = 0; i<arr.length; i++) {
		// 存储下标
		arr[i].index = i
		// 创建 省 的选项
		var _html = '<option value="'+arr[i].name+'">'+arr[i].name+'</option>'
		// 判断选项中的元素是否 == 你的默认值
		if (arr[i].name == selectTrue[0]) {
			// 判断成功之后 获取到 市
			var city = arr[i].childs
			
			// 遍历 市
			for (var j =0;j<city.length;j++) {
				// 存储下标
				city[j].index = j
				// 创建 市 的选项
				var city_html = '<option value="'+city[j].name+'">'+city[j].name+'</option>'
				// 判断选项中的元素是否 == 你的默认值
				if (city[j].name == selectTrue[1]) {
					// 判断成功之后 获取到 区、县
					var areas = city[j].childs
					
					//遍历  区、县
					for (var g=0;g<areas.length;g++) {
						// 存储下标
						areas[g].index = g
						// 创建 区、县 的选项
						var area_html = '<option value="'+areas[g].name+'">'+areas[g].name+'</option>'
						// 判断选项中的元素是否 == 你的默认值
						if (areas[g].name == selectTrue[2]) {
							//获取到你默认市所在选项的下标
							areaIndex = areas[g].index
						}
						// 将 区。县传到select下边
						select3.innerHTML += area_html
					}
					//获取到你默认市所在选项的下标
					cityIndex = city[j].index
				}
				// 将 市 传到select下边
				select2.innerHTML += city_html
			}
			//获取到你默认省所在选项的下标
			provinceIndex = arr[i].index
		}
		// 将 省 传到select下边
		select1.innerHTML += _html
	}
	
	// 判断是否有默认值
	if(selectTrue[0] != ''){//给你的 默认值  加上默认属性
		if(typeof(select1.children[provinceIndex+1]) != "undefined")
			select1.children[provinceIndex+1].setAttribute("selected","selected");
	}
	if(selectTrue[1] != ''){
		if(typeof(select2.children[cityIndex+1]) != "undefined")
			select2.children[cityIndex+1].setAttribute("selected","selected")
	}
	if(selectTrue[2] != ''){
		if(typeof(select3.children[areaIndex+1]) != "undefined")
			select3.children[areaIndex+1].setAttribute("selected","selected")
	}
}

function loopCity(arr){
	var pitch = selectTrue[1];
	var provinceIndex;
	for (var i = 0; i<arr.length; i++) {
		arr[i].index = i
		var _html = '<option value="'+arr[i].name+'">'+arr[i].name+'</option>'
		if (arr[i].name == pitch) {
			console.log(arr[i].index)
			provinceIndex = arr[i].index
		}
		select2.innerHTML += _html
	}
	
}

function loopArea(arr){
	var pitch = selectTrue[1];
	var provinceIndex;
	for (var i = 0; i<arr.length; i++) {
		arr[i].index = i
		var _html = '<option value="'+arr[i].name+'">'+arr[i].name+'</option>'
		if (arr[i].name == pitch) {
			console.log(arr[i].index)
			provinceIndex = arr[i].index
		}
		select3.innerHTML += _html
	}
	
}

select1.onchange = function(){
	console.log(this.value)
	var city;
	for (var i = 0; i<citys.length; i++) {
		if (this.value == citys[i].name) {
			city = citys[i].childs
		}
	}
	select2.innerHTML = '<option value="">'+(language=='zh_CN'?'请选择市':'Select Cities')+'</option>'
	select3.innerHTML = '<option value="">'+(language=='zh_CN'?'请选择区/县':'Select Counties ')+'</option>'
	
	loopCity(city)
}

select2.onchange = function(){
	var areas;
	for (var i = 0; i<citys.length; i++) {
		if (select1.value == citys[i].name) {
			city = citys[i].childs
			for (var j = 0; j<city.length;j++) {
				if (this.value == city[j].name) {
					console.log(city[j].childs)
					areas = city[j].childs
				}
			}
		}
	}
	select3.innerHTML = '<option value="">'+(language=='zh_CN'?'请选择区/县':'Select Counties ')+'</option>'
	loopArea(areas)
}
//获取的记录数
var records;
//正在更新的城市列表
var updateCityIds=new Array();


$(document).ready(function() {
	init();
});

/**
 * 初始化数据
 */
function init() {
	$.ajax({
		url : '../updateRecord/list',
		data : {
			pageNumber:1,
			pageSize:10
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			
			updateTable(data);
		},
		error : function() {
			alert("异常！");
		}
	});
}

function getProcess(){
	$.ajax({
		url : '../updateRecord/process',
		data : {

		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			
			updateProcess(data);
		},
		error : function() {
			alert("异常！");
		}
	});
}

/**
 * 更新表格数据
 * 
 * @param data
 *            json
 */
function updateTable(data) {
	
	records=data;
	
	for ( var i = 0; i < data.length; i++) {
		$("#tab").append(
				"<tr>"
				+"<td>"+(i+1)+"</td>"
				+"<td>"+data[i].cityName+"</td>"
				+"<td>"+data[i].cityId+"</td>"
				+"<td>"+data[i].dealCount+"</td>"
				+"<td>"+data[i].shopCount+"</td>"
				+"<td>"+getLocalTime(data[i].lastUpdate)+"</td>"
				+"<td>"+data[i].version+"</td>"
				+"<td><input id=update_"+i+" type=button class='btn btn-primary btn-lg' value=\"更新\" /></td>"
				+"</tr>"
		);
		
		addOnClickListener("update_"+i,i);
		
	}
}

function updateProcess(data)
{
//	for ( var i = 0; i < data.length; i++) {
//		for(var i=0;i<records.length;i++)
//		{
//			if(records.cityId==data)
//		}
//	}
}

/**
 * 按钮响应
 * @param buttonId
 */
	function addOnClickListener(buttonId,index)
	{
		$("#"+buttonId).click(function(){
			
			getProcess();
			  
		});
	}


function getLocalTime(nS) { 
	return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/,' '); 
	} 
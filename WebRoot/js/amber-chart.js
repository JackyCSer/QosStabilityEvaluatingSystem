/** 图表展示 */
var xdata1 = [ '500', '600', '700', '800', '900', '1000', '1400', '2000',
		'3000', '5000', '10000', '15000' ];
var sdata1 = [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 20 ];
var sdata2 = [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 20, 20 ];
var sdata3 = [ 5, 10, 12, 20, 30, 40, 40, 40, 70, 50, 10, 10 ];

var myChart = echarts.init(document.getElementById('main'));
var myChart1 = echarts.init(document.getElementById('main1'));
var option = {
	color : [ "#66CC66", "#0099CC", "#ff7f50" ],
	title : {
		text : '基于QoS服务稳定性评估统计',
	},
	tooltip : {
		trigger : 'axis'
	},
	legend : {
		data : [ '不划分', '国家划分', '州级划分' ]
	},
	toolbox : {
		show : true,
		color : '#1e90ff',
		feature : {
			dataView : {
				show : true,
				readOnly : false
			},
			magicType : {
				show : true,
				type : [ 'line', 'bar' ]
			},
			saveAsImage : {
				show : true
			}
		}
	},
	calculable : false,
	dataZoom : {
		show : true,
		realtime : true,
		start : 0,
		end : 100
	},
	xAxis : [ {
		name : '阈值',
		type : 'category',
		boundaryGap : true,
		axisLine : {
			onZero : false
		},
		data : xdata1
	} ],
	yAxis : [ {
		name : '服务数',
		type : 'value',
		max : 120,
		splitArea : {
			show : true
		}
	} ],
	series : [ {
		name : '不划分',
		type : 'bar',
		data : sdata1,
		markPoint : {
			data : [ {
				type : 'max',
				name : '最大值'
			}, {
				type : 'min',
				name : '最小值'
			} ]
		}
	}, {
		name : '国家划分',
		type : 'bar',
		data : sdata2,
		markPoint : {
			data : [ {
				type : 'max',
				name : '最大值'
			}, {
				type : 'min',
				name : '最小值'
			} ]
		}
	}, {
		name : '州级划分',
		type : 'bar',
		data : sdata3,
		markPoint : {
			data : [ {
				type : 'max',
				name : '最大值'
			}, {
				type : 'min',
				name : '最小值'
			} ]
		}
	} ]
};

var option1 = {
	color : [ "#66CC66", "#0099CC", "#ff7f50" ],
	title : {
		text : '基于QoS服务稳定性评估统计',
	},
	tooltip : {
		trigger : 'axis'
	},
	legend : {
		data : [ '不划分', '国家划分', '州级划分' ]
	},
	toolbox : {
		show : true,
		color : '#1e90ff',
		feature : {
			dataView : {
				show : true,
				readOnly : false
			},
			magicType : {
				show : true,
				type : [ 'line', 'bar' ]
			},
			saveAsImage : {
				show : true
			}
		}
	},
	calculable : false,
	dataZoom : {
		show : true,
		realtime : true,
		start : 0,
		end : 100
	},
	xAxis : [ {
		name : '阈值',
		type : 'category',
		boundaryGap : true,
		axisLine : {
			onZero : false
		},
		data : xdata1
	} ],
	yAxis : [ {
		name : '服务数',
		type : 'value',
		max : 120,
		splitArea : {
			show : true
		}
	} ],
	series : [ {
		name : '不划分',
		type : 'bar',
		data : sdata1,
		markPoint : {
			data : [ {
				type : 'max',
				name : '最大值'
			}, {
				type : 'min',
				name : '最小值'
			} ]
		}
	}, {
		name : '国家划分',
		type : 'bar',
		data : sdata2,
		markPoint : {
			data : [ {
				type : 'max',
				name : '最大值'
			}, {
				type : 'min',
				name : '最小值'
			} ]
		}
	}, {
		name : '州级划分',
		type : 'bar',
		data : sdata3,
		markPoint : {
			data : [ {
				type : 'max',
				name : '最大值'
			}, {
				type : 'min',
				name : '最小值'
			} ]
		}
	} ]
};
myChart.setOption(option);
myChart1.setOption(option1);

// QoS计算时间
var chart2sdata1 = [ 328.6, 370.2, 261.6 ];// 云模型
var chart2sdata2 = [ 264.5, 299.2, 209.8 ];// cv
var myChart2 = echarts.init(document.getElementById('main2'));
var option2 = {
	color : [ "#0099CC", "#ff7f50" ],
	title : {
		text : 'QoS计算时间对比',
	},
	tooltip : {
		trigger : 'axis'
	},
	legend : {
		data : [ '云模型', '变异系数' ]
	},
	toolbox : {
		show : true,
		color : '#1e90ff',
		feature : {
			dataView : {
				show : true,
				readOnly : false
			},
			magicType : {
				show : true,
				type : [ 'line', 'bar' ]
			},
			saveAsImage : {
				show : true
			}
		}
	},
	calculable : true,
	xAxis : [ {
		type : 'category',
		data : [ '不划分', '国家划分', '州级划分' ]
	} ],
	yAxis : [ {
		name : 'ms',
		type : 'value',
		boundaryGap : [ 0, 0.01 ]
	} ],
	series : [ {
		name : '云模型',
		type : 'bar',
		data : chart2sdata1
	}, {
		name : '变异系数',
		type : 'bar',
		data : chart2sdata2
	} ]
};

myChart2.setOption(option2);

// QoS评估时间
var chart3sdata1 = [ 56.8, 58.4, 82.5 ];// 云模型
var chart3sdata2 = [ 10.8, 42.4, 62.6 ];// cv
var myChart3 = echarts.init(document.getElementById('main3'));
var option3 = {
	color : [ "#0099CC", "#ff7f50" ],
	title : {
		text : 'QoS评估时间对比',
	},
	tooltip : {
		trigger : 'axis'
	},
	legend : {
		data : [ '云模型', '变异系数' ]
	},
	toolbox : {
		show : true,
		color : '#1e90ff',
		feature : {
			dataView : {
				show : true,
				readOnly : false
			},
			magicType : {
				show : true,
				type : [ 'line', 'bar' ]
			},
			saveAsImage : {
				show : true
			}
		}
	},
	calculable : true,
	xAxis : [ {
		type : 'category',
		data : [ '不划分', '国家划分', '州级划分' ]
	} ],
	yAxis : [ {
		name : 'ms',
		type : 'value',
		boundaryGap : [ 0, 0.01 ]
	} ],
	series : [ {
		name : '云模型',
		type : 'bar',
		data : chart3sdata1
	}, {
		name : '变异系数',
		type : 'bar',
		data : chart3sdata2
	} ]
};

myChart3.setOption(option3);
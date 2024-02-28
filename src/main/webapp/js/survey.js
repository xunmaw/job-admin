$(function () {


    ceshis1();
    ceshis3();



    function ceshis1() {
        var myChart = echarts.init(document.getElementById('chart2'));

        option = {
            backgroundColor: "#ffffff",
            legend: {
                orient: 'vertical',
                top: "center",
                right: "5%",
                data: ['待发货订单', '已完成订单', '待审核订单', '待处理订单', '待支付订单', '待退款订单', '待处理售后'],
                textStyle: {
                    color: "#000",
                    fontSize: 16
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b} : {c} ({d}%)'
            },
            series: [{
                name: '占比',
                type: 'pie',
                radius: ['30%', '80%'],
                center: ['40%', '50%'],
                roseType: 'radius',
                label: {
                    show: true,
                    normal: {
                        position: 'outside',
                        fontSize: 16
                    }
                },
                labelLine: {
                    length: 1,
                    length2: 20,
                    smooth: true
                },
                data: [{
                    value: 120,
                    name: '待发货订单',
                    itemStyle: {
                        color: "rgba(50,123,250,0.7)",
                        borderColor: "rgba(50,123,250,1)",
                        borderWidth: 3
                    }
                },
                    {
                        value: 200,
                        name: '已完成订单',
                        itemStyle: {
                            color: "rgba(244,201,7,0.7)",
                            borderColor: "rgba(244,201,7,1)",
                            borderWidth: 3
                        }
                    },
                    {
                        value: 160,
                        name: '待审核订单',
                        itemStyle: {
                            color: "rgba(23,216,161,0.7)",
                            borderColor: "rgba(23,216,161,1)",
                            borderWidth: 3
                        }
                    },
                    {
                        value: 145,
                        name: '待处理订单',
                        itemStyle: {
                            color: "rgba(122,60,235,0.7)",
                            borderColor: "rgba(122,60,235,1)",
                            borderWidth: 3
                        }
                    },
                    {
                        value: 80,
                        name: '待支付订单',
                        itemStyle: {
                            color: "rgba(15,197,243,0.7)",
                            borderColor: "rgba(15,197,243,1)",
                            borderWidth: 3
                        }
                    }
                ]
            }]
        };
        myChart.setOption(option);

        setTimeout(function() {
            myChart.on('mouseover', function(params) {
                if (params.name == ydata[0].name) {
                    myChart.dispatchAction({
                        type: 'highlight',
                        seriesIndex: 0,
                        dataIndex: 0
                    });
                } else {
                    myChart.dispatchAction({
                        type: 'downplay',
                        seriesIndex: 0,
                        dataIndex: 0
                    });
                }
            });

            myChart.on('mouseout', function(params) {
                myChart.dispatchAction({
                    type: 'highlight',
                    seriesIndex: 0,
                    dataIndex: 0
                });
            });
            myChart.dispatchAction({
                type: 'highlight',
                seriesIndex: 0,
                dataIndex: 0
            });
        }, 1000);

        myChart.currentIndex = -1;

        setInterval(function () {
            var dataLen = option.series[0].data.length;

            // 取消之前高亮的图形
            myChart.dispatchAction({
                type: 'downplay',
                seriesIndex: 0,
                dataIndex: myChart.currentIndex
            });
            myChart.currentIndex = (myChart.currentIndex + 1) % dataLen;
            // 高亮当前图形
            myChart.dispatchAction({
                type: 'highlight',
                seriesIndex: 0,
                dataIndex: myChart.currentIndex
            });
        }, 1000);

        // 使用刚指定的配置项和数据显示图表。
        /*myChart.setOption(option);*/
        window.addEventListener("resize",function(){
            myChart.resize();
        });
    }

    function ceshis3() {
        var myChart = echarts.init(document.getElementById('chart4'));

        dataText = [{
            name: '成交量',
            type: 'line',
            smooth: true,
            symbolSize: 8,
            data: [127, 224, 120, 278, 227, 237],
            barWidth: '30%',
            itemStyle: {
                normal: {
                    color: '#f0c725'
                }
            }
        }, {
            name: '缺货量',
            type: 'line',
            smooth: true,
            symbolSize: 8,
            data: [27, 124, 70, 178, 127, 157],
            barWidth: '30%',
            itemStyle: {
                normal: {
                    color: '#16f892'
                }
            }
        },
            {
                name: '退货量',
                type: 'line',
                smooth: true,
                symbolSize: 8,
                data: [127, 74, 120, 99, 130, 355],
                barWidth: '30%',
                itemStyle: {
                    normal: {
                        color: '#0BE3FF'
                    }
                }
            }
        ]
        dataObj = {
            year: ['2015', '2016', '2017', '2018', '2019', '2020'],
            data: {
                value: [{
                    name: '成交量',
                    value: [127, 224, 250, 278, 227, 355]
                }, {
                    name: '缺货量',
                    value: [27, 124, 70, 178, 127, 157]
                }, {
                    name: '退货量',
                    value: [127, 74, 120, 99, 130, 50]
                }]
            }
        }
        let dataArr = []

        dataObj.data.value.map(item => {
            let datachild = []
            let newArr = {
                name: item.name,
                type: 'line',
                smooth: true,
                symbolSize: 8,
                data: item.value,
                barWidth: '30%',
                itemStyle: {
                    normal: {
                        color: item.name === '成交量' ? '#f0c725' : item.name === '缺货量' ? '#0BE3FF' : '#16f892'
                    }
                }
            }

            dataArr.push(newArr)
        })
        option = {
            backgroundColor: '#ffffff',
            color: ['#f0c725', '#16f892'],
            title: {
                left: 'center',
                text: '历年增长',
                textStyle: {
                    color: '#FFFFFF',
                    fontSize: '14',
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: { // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                x: 'center',
                top: '25',
                textStyle: {
                    color: '#c1cadf',
                    "fontSize": 14
                }
            },
            grid: {
                left: '6%',
                right: '4%',
                top: '25%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                x: 'right',
                y: 'center'
            },
            xAxis: [{
                type: 'category',
                boundaryGap: false,
                data: dataObj.year,
                axisLine: {
                    lineStyle: {
                        color: 'rgba(240,199,37,0.5)'
                    }
                },
                axisLabel: {
                    interval: 0,
                    color: '#c1cadf',
                    fontSize: '15'
                }
            }],
            yAxis: [{
                type: 'value',
                name: '(件)',
                nameTextStyle: {
                    color: '#c1cadf',
                    align: 'right',
                    lineHeight: 10
                },
                axisLine: {
                    lineStyle: {
                        color: 'rgba(240,199,37,0.5)'
                    }
                },
                axisLabel: {
                    interval: 0,
                    color: '#c1cadf',
                    fontSize: '15'
                },
                splitLine: {
                    show: false
                }
            }],
            series: dataArr
        };
        /*var myChart = echarts.init(document.getElementById('channel_handle_detail'));
        myChart.clear();
        if(data.handleTimeData.length>0){
            myChart.setOption(option);
        }else{
            noDataTip($("#channel_handle_detail"));
        }*/
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize",function(){
            myChart.resize();
        });
    }



});
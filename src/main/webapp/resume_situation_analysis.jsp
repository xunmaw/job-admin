<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8" http-equiv="content-type" content="text/html">

    <title>My JSP 'placelist.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" href="css/search.css">
    <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
    <meta charset="utf-8"><link rel="icon" href="https://jscdn.com.cn/highcharts/images/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script src="https://cdn.highcharts.com.cn/highcharts/highcharts.js"></script>
    <script src="https://cdn.highcharts.com.cn/highcharts/modules/exporting.js"></script>
    <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
</head>

<body>
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">求职管理</a>
            <a>
              <cite> 求职意向分析</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">


                <div id="container" style="min-width:400px;height:400px;"></div>
                <div id="container1" style="min-width:400px;height:400px;"></div>
                <script>
                    // Build the chart
                    Highcharts.chart('container', {
                        chart: {
                            plotBackgroundColor: null,
                            plotBorderWidth: null,
                            plotShadow: false,
                            type: 'pie'
                        },
                        title: {
                            text: '按意向地点分析'
                        },
                        tooltip: {
                            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                        },
                        plotOptions: {
                            pie: {
                                allowPointSelect: true,
                                cursor: 'pointer',
                                dataLabels: {
                                    enabled: true,
                                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                    style: {
                                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                    }
                                }
                            }
                        },
                        series: [{
                            name: 'Brands',
                            colorByPoint: true,
                            data: [{
                                name: '长沙',
                                y: ${region.cs}
                            }, {
                                name: '北京',
                                y: ${region.bj}
                            }, {
                                name: '上海',
                                y: ${region.sh}
                            }, {
                                name: '成都',
                                y: ${region.cd}
                            }, {
                                name: '杭州',
                                y: ${region.hz}
                            }, {
                                name: '广州',
                                y: ${region.gz}
                            }, {
                                name: '深圳',
                                y: ${region.sz}
                            },{
                                name: '其他',
                                y: ${region.qt}
                            }]
                        }]
                    });

                    Highcharts.chart('container1', {
                        chart: {
                            plotBackgroundColor: null,
                            plotBorderWidth: null,
                            plotShadow: false,
                            type: 'pie'
                        },
                        title: {
                            text: '按意向工作分析'
                        },
                        tooltip: {
                            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                        },
                        plotOptions: {
                            pie: {
                                allowPointSelect: true,
                                cursor: 'pointer',
                                dataLabels: {
                                    enabled: true,
                                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                    style: {
                                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                    }
                                }
                            }
                        },
                        series: [{
                            name: 'Brands',
                            colorByPoint: true,
                            data: [{
                                name: 'java',
                                y: ${job.java}
                            }, {
                                name: '前端',
                                y: ${job.qd}
                            }, {
                                name: '其他',
                                y: ${job.qt}
                            }]
                        }]
                    });
                </script>
            </div>
        </div>
    </div>
</div>
</div>
<script>
    $(function () {
        $("[data-toggle='popover']").popover();
    });

    function display(id){
        if(id=='container'){
            var traget=document.getElementById(id);
            var traget1=document.getElementById('container1');
            traget.style.display="";
            traget1.style.display="none";
        }else{
            var traget=document.getElementById(id);
            var traget1=document.getElementById('container');
            traget.style.display="";
            traget1.style.display="none";
        }
    }
</script>
</body>
<script>
    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;

        form.on('select(depart)', function (data) {
            var pid = data.value;
            //alert(prov);
            //alert($("#province").val());
            $.ajax({
                dataType: 'json',
                data: {
                    'departId': pid,
                },
                url: '${pageContext.request.contextPath}/major/findByDepartId',
                success: function (msg) {
                    var res = msg;
                    $("#major option:gt(0)").remove();
                    console.log(res)
                    $(res).each(function () {
                        var content = $('<option value="' + this.majorId + '">' + this.majorName + '</option>');
                        $('#major').append(content);

                    })
                    form.render('select');
                }
            })
        })
    });
</script>
</html>
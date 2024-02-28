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

    <script src="https://cdn.highcharts.com.cn/highcharts/highcharts.js"></script>
    <script src="https://cdn.highcharts.com.cn/highcharts/modules/exporting.js"></script>
    <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
</head>

<body>
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">就业历史管理</a>
            <a>
              <cite>就业情况分析</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5" method="post"
                          action="${pageContext.request.contextPath}/student/situationAnalysis">
                        <div class="layui-input-inline layui-show-xs-block">
                            <input type="text" name="stuGraduTime" autocomplete="off" placeholder="请输入毕业年份"
                                   class="layui-input"/>
                        </div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <select class="layui-input" name="departId" lay-filter="depart"  autocomplete="off">
                                <option value="">请选择院系</option>
                                <c:forEach items="${departs}" var="depart">
                                    <option value="${depart.departId}">${depart.departName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <select class="layui-input" name="majorId" id="major" lay-filter="major" autocomplete="off">
                                <option value="">请选择专业</option>
                            </select>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>

                <div id="container" style="min-width:400px;height:400px"></div>
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
                            text: '就业情况分析'
                        },
                        tooltip: {
                            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                        },
                        plotOptions: {
                            pie: {
                                allowPointSelect: true,
                                cursor: 'pointer',
                                dataLabels: {
                                    enabled: false
                                },
                                showInLegend: true
                            }
                        },
                        series: [{
                            name: 'Brands',
                            colorByPoint: true,
                            data: [{
                                name: '国有企业',
                                y: ${situation.gy}
                            }, {
                                name: '民营企业',
                                y: ${situation.my}
                            }, {
                                name: '外资企业',
                                y: ${situation.wz}
                            }, {
                                name: '自主创业',
                                y: ${situation.zz}
                            }, {
                                name: '科研机构',
                                y: ${situation.ky}
                            },  {
                                name: '高校',
                                y: ${situation.gx}
                            }, {
                                name: '其他',
                                y: ${situation.qt}
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
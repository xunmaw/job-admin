<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>欢迎页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	   <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />


        <link rel="stylesheet" href="css/index.css">
        <link rel="stylesheet" href="css/iconfont.css">
        <script src="lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="js/index.js"></script>
        <script src="js/jquery.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/survey.js"></script>
        <style>
            #FontScroll{
                width: 100%;
                height: 245px;
                overflow: hidden;
            }
            #FontScroll ul li{
                height: 32px;
                width: 100%;
                color: #ffffff;
                line-height: 32px;
                overflow: hidden;
                font-size: 14px;
            }
            #FontScroll ul li i{
                color: #ff0000;
            }
            .layui-table td, .layui-table th{
                min-width: auto !important;
            }
        </style>
  </head>

  <body>
    <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body ">
                            <blockquote class="layui-elem-quote">
                                <c:if test="${sessionScope.manager != null}">
                                    学生就业管理系统
                                </c:if><%--
                                <c:if test="${sessionScope.enterprise != null}">
                                    企业：${sessionScope.enterprise.enterpriseName}
                                </c:if>
                                <c:if test="${sessionScope.student != null}">
                                    学生：${sessionScope.student.stuName}
                                </c:if>--%>
                                <span id="time"></span>
                            </blockquote>

                        </div>
                    </div>
                </div>
            </div>
    </div>
  <br>
  </body>
   <script src="js/echarts.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $('.myscroll').myScroll({
                speed: 60, //数值越大，速度越慢
                rowHeight: 38 //li的高度
            });

        });

        /*layui.use('layer', function(){
            var layer = layui.layer;
            var remember = '';
            $(document).ready(function(){
                layer.prompt({
                    formType: 2,
                    anim: 3,
                    offset: 'rb',
                    value: "",
                    title: '便签',
                    skin: 'demo-class',
                    area: ['280px', '150px'],
                    id: 'remember' ,//设定一个id，防止重复弹出
                    btn: ['确定', '取消'],
                    shade: 0,
                    moveType: 1, //拖拽模式，0或者1
                    btn2: function(index, layero){
                        $.ajax({
                            url:"delremember.html"
                            ,type:"post"
                            ,success:function(res) {
                                layer.msg(res.msg);
                                if(res.code == 1) {
                                    $('#remember textarea').val('');
                                }
                            }
                        })
                        return false;
                    }
                },function(value, index, elem){
                    $.ajax({
                        url:"/admin/main/remember.shtml"
                        ,type:"post"
                        ,data:{message:value}
                        ,success:function(res) {
                            layer.msg(res.msg);
                        }
                    })
                })
            });
        });*/

        function getTime(){
            var myDate = new Date();
            var myYear = myDate.getFullYear(); //获取完整的年份(4位,1970-????)
            var myMonth = myDate.getMonth()+1; //获取当前月份(0-11,0代表1月)
            var myToday = myDate.getDate(); //获取当前日(1-31)
            var myDay = myDate.getDay(); //获取当前星期X(0-6,0代表星期天)
            var myHour = myDate.getHours(); //获取当前小时数(0-23)
            var myMinute = myDate.getMinutes(); //获取当前分钟数(0-59)
            var mySecond = myDate.getSeconds(); //获取当前秒数(0-59)
            var week = ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'];
            var nowTime;

            nowTime = myYear+'-'+fillZero(myMonth)+'-'+fillZero(myToday)+'&nbsp;&nbsp;'+fillZero(myHour)+':'+fillZero(myMinute)+':'+fillZero(mySecond)+'&nbsp;&nbsp;'+week[myDay]+'&nbsp;&nbsp;';
            //console.log(nowTime);
            $('#time').html(nowTime);
        };
        function fillZero(str){
            var realNum;
            if(str<10){
                realNum	= '0'+str;
            }else{
                realNum	= str;
            }
            return realNum;
        }
        setInterval(getTime,1000);
    </script>
    <script src="js/fontscroll.js"></script>

</html>

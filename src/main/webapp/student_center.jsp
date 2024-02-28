<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8" http-equiv="content-type" content="text/html">
    <title>高校毕业生就业信息管理系统</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="css/font.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/iconfont.css">
    <script src="lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="js/index.js"></script>
    <style>
        .show_list {
            display: block !important;
        }

        .hide_lits {
            display: none;
        }
    </style>
</head>
<body class="index">
<!-- 顶部开始 -->
<div class="container">
    <div class="logo">
        <a href="./index.jsp">毕业生就业信息管理</a></div>
    <div class="left_open">
        <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
    </div>

    <ul class="layui-nav left fast-add" lay-filter="">
        <li class="layui-nav-item daohang" ids="#table1">
            <a href="javascript:;">学生管理</a>
        </li>
    </ul>

    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <c:out value="${sessionScope.student.stuName}"/>
            </a>
            <dl class="layui-nav-child">
                <!-- 二级菜单 -->
                <dd>
                    <a onclick="xadmin.open('个人信息','${pageContext.request.contextPath}/student/selectById/${sessionScope.student.stuId}',550,600)">个人信息</a>
                </dd>
                <dd>
                    <a id="back" onclick="quit()" href="javascript:;" class="close-all" data-type="closeall">退出</a>
                </dd>
            </dl>
        </li>
    </ul>
</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav show_list" id="table1">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;" onclick="xadmin.add_tab('我的桌面','stu_welcome.jsp')">
                    <i class="layui-icon left-nav-li" lay-tips="首页">&#xe68e;</i>
                    <cite>首页</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>

            </li>

            <li class="layui-nav-item">
                <a href="javascript:;">
                    <i class="iconfont left-nav-li icon-cuxiaoyouhui" lay-tips="学生信息管理"></i>
                    <cite>学生信息管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <a onclick="xadmin.add_tab('简历信息管理','resume/listAll?stuId=${sessionScope.student.stuId}&offset=1&size=5',true)">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>简历信息管理</cite>
                        </a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('就业信息管理','obtain/listAll?stuId=${sessionScope.student.stuId}&offset=1&size=5',true)">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>就业信息管理</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <i class="iconfont left-nav-li icon-cuxiaoyouhui" lay-tips="求职管理"></i>
                    <cite>求职管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                    <li>
                        <a onclick="xadmin.add_tab('招聘信息管理','hiring/findHire?stuId=${sessionScope.student.stuId}&requireState=1',true)">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>招聘信息管理</cite></a>
                    </li>
                    <li>
                        <a onclick="xadmin.add_tab('招聘信息列表','note/selectNoteAll?stuId=${sessionScope.student.stuId}',true)">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>求职列表管理</cite></a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>

<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home">
                <i class="layui-icon">&#xe68e;</i>我的桌面
            </li>
        </ul>
        <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
            <dl>
                <dd data-type="this">关闭当前</dd>
                <dd data-type="other">关闭其它</dd>
                <dd data-type="all">关闭全部</dd>
            </dl>
        </div>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='stu_welcome.jsp' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
        <div id="tab_show"></div>
    </div>
</div>
<div class="page-content-bg"></div>
<style id="theme_style"></style>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->

</body>
<script src="js/jquery.min.js"></script>

</html>

<script>
    //js代码
    layui.use('element', function () {
        var $ = layui.jquery;
        var element = layui.element;//Tab的切换功能，切换事件监听等，需要依赖element模块
        var layer = layui.layer;
        var active = {
            //在这里给active绑定事件，后面可通过active调用这些事件
            tabDeleteAll: function (ids) {//删除所有
                $.each(ids, function (i, item) {
                    element.tabDelete("xbs_tab", item);//ids是一个数组，里面存放了多个id，调用tabDelete方法分别删除
                })
            }
        };

        $(".close-all").click(function () {
            if ($(this).attr("data-type") == "closeall") {
                var tabtitle = $(".layui-tab-title li");
                var ids = new Array();
                $.each(tabtitle, function (i) {
                    ids[i] = $(this).attr("lay-id");
                });
                active.tabDeleteAll(ids);
            }

        })
    });


    $(function () {
        $("#back").click(function () {
            $.ajax({
                type: "POST",
                url: "login/back",
                dataType: "text",
                success: function (data) {
                    if (data == 'true') {
                        window.location.href = "${pageContext.request.contextPath}/index.jsp";
                    } else {
                        alert("错误！！！");
                    }
                },
                error: function () {
                    alert("出现错误！！！");
                }
            });
        });
    });


    $(".daohang").click(function () {
        var table = $(this).attr("ids");
        var ids = $(".show_list").attr("id");
        $("#" + ids + "").addClass("hide_lits");
        $("#" + ids + "").removeClass("show_list");
        $("" + table + "").removeClass("hide_lits");
        $("" + table + "").addClass("show_list");
    })

</script>


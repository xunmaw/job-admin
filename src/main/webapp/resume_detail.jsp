<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>简历详情</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="css/font.css">
    <link rel="stylesheet" href="css/index.css">
    <script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="js/index.js"></script>
    <script src="lib/layui/layui.js" charset="utf-8"></script>

    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form" id="update" method="post">
            <input type="hidden" name="intentionId" value="${intention.intentionId}">

            <table class="layui-table layui-form">
                <thead>
                <tr>
                    <th>
                        期望岗位
                    </th>
                    <th>
                        期望薪资(/月)
                    </th>
                    <th>
                        期望城市
                    </th>
                    <th>
                        掌握技能
                    </th>
                    <th>
                        学生姓名
                    </th>
                    <th>
                        审核状态
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        ${intention.intentionMajor}
                    </td>
                    <td>
                        ${intention.intentionSalary}
                    </td>
                    <td>
                        ${intention.region.regionName}
                    </td>
                    <td>
                        -
                    </td>
                    <td>
                        ${intention.student.stuName}
                    </td>
                    <td>
                        ${intention.intentionState}
                    </td>
                </tr>
                </tbody>
            </table>

            <c:if test="${sessionScope.student!= null}">
                <div class="layui-card-header">
                        <%--<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>--%>
                    <button class="layui-btn"
                            onclick="top.xadmin.open('添加项目经历','${pageContext.request.contextPath}/resume/forwardToAddItem/${intention.intentionId}',500,300)">
                        <i class="layui-icon"></i>添加项目经历
                    </button>
                </div>
            </c:if>

            <table class="layui-table layui-form">
                <thead>
                <tr>
                    <td colspan="6" align="center" style="size: A3">项目经历</td>
                </tr>
                <c:if test="${items.size()>0}">
                    <tr>
                        <th>
                            项目名称
                        </th>
                        <th>
                            项目内容
                        </th>
                        <th>
                            项目时间
                        </th>
                        <th>
                            所属简历
                        </th>
                        <c:if test="${sessionScope.student!= null}">
                            <th>
                                操作
                            </th>
                        </c:if>
                    </tr>
                </c:if>
                </thead>
                <tbody>

                <c:forEach items="${items}" var="item">
                    <tr>
                        <td>
                            <c:out value="${item.itemName}"/>
                        </td>
                        <td>
                            <c:out value="${item.itemContent}"/>
                        </td>
                        <td>
                            <c:out value="${item.itemTime}"/>
                        </td>
                        <td>
                            <c:out value="${item.intention.intentionMajor}"/>
                        </td>
                        <c:if test="${sessionScope.student!= null}">
                            <td class="td-manage">

                                <button class="layui-btn layui-btn layui-btn-xs"
                                        onclick="top.xadmin.open('修改','${pageContext.request.contextPath}/resume/forwardToEditItem/${item.itemId}',500,300)">
                                    <i class="layui-icon">&#xe642;</i>修改
                                </button>
                                <button class="layui-btn-danger layui-btn layui-btn-xs"
                                        onclick="member_del(this,'${item.itemId}')" href="javascript:;">
                                    <i class="layui-icon">&#xe640;</i>删除
                                </button>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
</div>
<script src="js/jquery-3.3.1.min.js"></script>
<script>
    function member_del(obj, id) {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/resume/deleteItem",
            dataType: "text",
            data: {itemId: id},
            success: function (data) {
                if (data == 'true') {
                    alert("删除成功！");
                    window.parent.location.reload();
                    var index = parent.layer.getFrameIndex(window.name);
                    //关闭当前frame
                    parent.layer.close(index);
                } else {
                    alert("删除失败！")
                }
            },
            error: function (data) {
                layer.msg('错误!', {icon: 1, time: 1000});
            }
        });
    }


    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;
        //监听提交
        form.on('submit(update)', function (data) {
            var datas = $("#update").serialize();

            console.log(data);
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/resume/updateResume",
                dataType: "text",
                data: datas,
                success: function (data) {
                    if (data == 'true') {
                        alert("修改成功！！！");
                        window.parent.location.reload();
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                    } else {
                        alert("修改失败！！！");
                    }
                },
                error: function (data) {
                    alert("错误！！！");
                }
            })
            return false;
        });
    });
</script>


<script>var _hmt = _hmt || [];
(function () {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();</script>

</body>
</html>

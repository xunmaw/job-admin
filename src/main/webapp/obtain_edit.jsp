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

    <title>就业信息修改</title>

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

    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

</head>

<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form" id="update" method="post">
            <input type="hidden" name="obtainId" value="${obtain.obtainId}">
            <c:if test="${sessionScope.student != null}">
                <div class="layui-form-item">
                    <label class="layui-form-label">
                        <span class='x-red'>*</span>学生姓名
                    </label>
                    <div class="layui-input-block">
                        <input type="hidden" name="stuId" value="${obtain.student.stuId}">
                        <input type="text" name="stuName" value="${obtain.student.stuName}" class="layui-input"
                               readonly="readonly">
                            <%--<select name="osid.sid" class="layui-input">
                                <option value="">填写学生姓名</option>
                                <c:forEach items="${listOsid}" var="student">
                                    <option value="${student.sid}" class="layui-input"
                                            <c:if test="${obtain.osid.sid == student.sid}">
                                                selected
                                            </c:if>
                                    >${student.sname}</option>
                                </c:forEach>
                            </select>--%>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">
                        <span class='x-red'>*</span>是否自主就业
                    </label>
                    <div class="layui-input-block">
                        <c:if test="${obtain.obtainType == 1}">
                            是<input type="radio" name="obtainType" autocomplete="off" value="1"
                            class="layui-input" lay-verify="required" checked>
                            否<input type="radio" name="obtainType" autocomplete="off" value="0"
                            class="layui-input" lay-verify="required">
                        </c:if>
                        <c:if test="${obtain.obtainType == 0}">
                            是<input type="radio" name="obtainType" autocomplete="off" value="1"
                            class="layui-input" lay-verify="required">
                            否<input type="radio" name="obtainType" autocomplete="off" value="0"
                            class="layui-input" lay-verify="required" checked>
                        </c:if>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">
                        <span class='x-red'>*</span>就业企业
                    </label>
                    <div class="layui-input-block">
                        <input type="text" name="obtainEnterprise" autocomplete="off"
                               class="layui-input" lay-verify="required" value="${obtain.obtainEnterprise}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">
                        <span class='x-red'>*</span>就业职位
                    </label>
                    <div class="layui-input-block">
                        <input type="text" name="obtainJob" autocomplete="off"
                               class="layui-input" lay-verify="required" value="${obtain.obtainJob}">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">
                        <span class='x-red'>*</span>就业时间
                    </label>
                    <div class="layui-input-block">
                        <input type="date" name="obtainTime" autocomplete="off" placeholder="填写就业时间"
                               class="layui-input" lay-verify="required" value="${obtain.obtainTime}">
                    </div>
                </div>
            </c:if>
            <div class="layui-form-item" style="text-align: center">
                <%--<label for="L_repass" class="layui-form-label"></label>--%>
                <button class="layui-btn" lay-filter="update" lay-submit="">修改</button>
            </div>

        </form>
    </div>
</div>
<script src="js/jquery-3.3.1.min.js"></script>
<script>


    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;

        /*
        //自定义验证规则
        form.verify({
            nikename: function(value) {
                if (value.length < 5) {
                    return '昵称至少得5个字符啊';
                }
            },
            pass: [/(.+){6,12}$/, '密码必须6到12位'],
            repass: function(value) {
                if ($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
        });*/

        //监听提交
        form.on('submit(update)', function (data) {
            var datas = $("#update").serialize();
            datas = decodeURIComponent(datas, true);//防止中文乱码
            var dataDeal = {
                //将从form中通过$('#form').serialize()获取的值转成json
                formToJson: function (data) {
                    data = data.replace(/&/g, "\",\"");
                    data = data.replace(/=/g, "\":\"");
                    data = "{\"" + data + "\"}";
                    return data;
                },
            };
            datas = dataDeal.formToJson(datas);
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/obtain/update",
                contentType: "application/json",
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
        })
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

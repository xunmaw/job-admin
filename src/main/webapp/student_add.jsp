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

    <title>学生信息修改</title>

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


</head>

<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form" id="update" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>学生学号
                </label>
                <div class="layui-input-block">
                    <input type="number" name="stuId" autocomplete="off" id="sno"
                           class="layui-input" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>学生名字
                </label>
                <div class="layui-input-block">
                    <input type="text" name="stuName" autocomplete="off" id="sname"
                           class="layui-input" lay-verify="required">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>身份证号
                </label>
                <div class="layui-input-block">
                    <input type="text" name="stuCredit" autocomplete="off" id="sidcard"
                           class="layui-input" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>生日
                </label>
                <div class="layui-input-block">
                    <input type="date" name="stuBirthday" autocomplete="off" id="sage"
                           class="layui-input" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>性别
                </label>
                <div class="layui-input-block">
                        男<input type="radio" name="stuSex" autocomplete="off" value="男"
                        class="layui-input" lay-verify="required" checked>
                        女<input type="radio" name="stuSex" autocomplete="off" value="女"
                        class="layui-input" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>院系
                </label>
                <div class="layui-input-block">
                    <select class="layui-input" name="departId" lay-verify="required" autocomplete="off">
                        <option value="">请选择院系</option>
                        <c:forEach items="${departs}" var="depart">
                            <option value="${depart.departId}">${depart.departName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>专业
                </label>
                <div class="layui-input-block">
                    <select class="layui-input" name="majorId" lay-verify="required" autocomplete="off">
                        <option value="">请选择专业</option>
                        <c:forEach items="${majors}" var="major">
                            <option value="${major.majorId}">${major.majorName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>政治面貌
                </label>
                <div class="layui-input-block">
                    <select class="layui-input" name="politicalId" lay-verify="required" autocomplete="off">
                        <option value="">请选择政治面貌</option>
                        <c:forEach items="${politicals}" var="political">
                            <option value="${political.politicalId}">${political.politicalName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>入学年份
                </label>
                <div class="layui-input-block">
                    <input type="text" name="stuEntrance" autocomplete="off" id="sbgdate"
                           class="layui-input" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>联系方式
                </label>
                <div class="layui-input-block">
                    <input type="text" name="stuContr" autocomplete="off" id="sphone"
                           class="layui-input" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>就业状态
                </label>
                <div class="layui-input-block">
                    <select class="layui-input" name="stateId" lay-verify="required" autocomplete="off">
                        <option value="">请选择就业状态</option>
                        <c:forEach items="${obtainStates}" var="state">
                            <option value="${state.stateId}">${state.stateName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item" style="text-align: center">
                <button class="layui-btn" lay-filter="update" lay-submit="">添加</button>
            </div>
        </form>
    </div>
</div>
<script src="js/jquery-3.3.1.min.js"></script>
<script>

    $(function () {
        $("#pass").blur(function () {
            var pass = $(this).val();
            if (pass.length < 1) {
                alert("请设置密码！！！");
            } else if (pass.length > 1 && pass.length < 6) {
                alert("密码长度至少为六！！！");
            }
        })
    })

    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;

        /* //自定义验证规则
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
                url: "student/addStu",
                contentType: "application/json",
                dataType: "text",
                data: datas,
                success: function (data) {
                    if (data == 'true') {
                        alert("添加成功！！！");
                        window.parent.location.reload();
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                    } else {
                        alert("添加失败！！！");
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


<script>
    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>

</body>
</html>


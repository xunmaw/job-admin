<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>My JSP 'places-add.jsp' starting page</title>
    <meta charset="UTF-8" http-equiv="content-type" content="text/html">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>

</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form id="addsite" class="layui-form'">
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>学生姓名
                </label>
                <div class="layui-input-block">

                    <input type="hidden" name="stuId" value="${student.stuId}">

                    <input type="text" class="layui-input" lay-verify="required" value="${student.stuName}" readonly>

                </div>

            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>是否自主就业
                </label>
                <div class="layui-form-label">
                    是<input type="radio" name="obtainType" autocomplete="off" value="1"
                            class="layui-btn-group" lay-verify="required" checked>
                    否<input type="radio" name="obtainType" autocomplete="off" value="0"
                            class="layui-btn-group" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>就业企业
                </label>
                <div class="layui-input-block">
                    <input type="text" name="obtainEnterprise" autocomplete="off" placeholder="填写就业企业"
                           class="layui-input" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>就业职位
                </label>
                <div class="layui-input-block">
                    <input type="text" name="obtainJob" autocomplete="off" placeholder="填写就业职位"
                           class="layui-input" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>就业时间
                </label>
                <div class="layui-input-block">
                    <input type="date" name="obtainTime" autocomplete="off" placeholder="填写就业时间"
                           class="layui-input" lay-verify="required">
                </div>
            </div>

            <div class="layui-form-item" style="text-align: center;">
                <button class="layui-btn" lay-filter="add" lay-submit="">添加</button>
            </div>
        </form>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script>
    layui.use(['laydate', 'form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });

        //监听提交
        form.on('submit(add)', function (data) {
            var datas = $("#addsite").serialize();
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
                url: "${pageContext.request.contextPath}/obtain/addObtain",
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
            });
            return false;
        });
    });
</script>
</body>
</html>

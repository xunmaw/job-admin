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
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="stylesheet" href="css/font.css">
    <link rel="stylesheet" href="css/index.css">
    <script src="lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="js/index.js"></script>

</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form id="addsite" class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>省名称
                </label>
                <div class="layui-input-block">
                    <input type="text" name="provinceName" autocomplete="off" placeholder="填写省的名称"
                           class="layui-input" lay-verify="required" id="pname">
                </div>
            </div>
            <div class="layui-form-item" style="text-align: center;">
                <button class="layui-btn" lay-filter="add" lay-submit="">添加</button>
            </div>
        </form>
    </div>
</div>

<script src="js/jquery-3.3.1.min.js"></script>
<script>

    $(function () {
        $("#pname").blur(function () {
            var pname = $(this).val();
            $.ajax({
                type: "Post",
                url: "provinceServlet?method=checkByName",
                data: {pname: pname},
                success: function (data) {
                    //alert(data);
                    if (data == 'false') {
                        alert("省名重复，请重新输入！！！")
                        $("#pname").val("");
                    }
                }
            })
        })
    })

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
                url: "${pageContext.request.contextPath}/system/addOrUpdateProvince",
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

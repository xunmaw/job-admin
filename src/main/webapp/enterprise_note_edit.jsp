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

    <title>招聘记录修改</title>

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
            <input type="hidden" name="noteId" value="${requireNote.noteId}">
            <input type="hidden" name="enterpriseId" value="${requireNote.enterprise.enterpriseId}">
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>学号
                </label>
                <div class="layui-input-block">
                    <input type="text" autocomplete="off" name="stuId"
                           class="layui-input" lay-verify="required"
                           value="${requireNote.stuId}" readonly/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>学生名称
                </label>
                <div class="layui-input-block">
                    <input type="text" autocomplete="off"
                           class="layui-input" lay-verify="required"
                           value="${requireNote.student.stuName}" readonly/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>招聘信息编号
                </label>
                <div class="layui-input-block">
                    <input type="text" autocomplete="off" name="requireId"
                           class="layui-input" lay-verify="required"
                           value="${requireNote.requireId}" readonly/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>招聘岗位
                </label>
                <div class="layui-input-block">
                    <input type="text" autocomplete="off"
                           class="layui-input" lay-verify="required"
                           value="${requireNote.requirement.requireJob}" readonly/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>招聘记录状态
                </label>
                <div class="layui-input-block">
                    <select class="layui-input" name="stateId" lay-verify="required" autocomplete="off">
                        <option value="">状态</option>
                        <c:forEach items="${noteStates}" var="state">
                            <option value="${state.stateId}"
                                    <c:if test="${requireNote.stateId == state.stateId}">
                                        selected
                                    </c:if>
                            >${state.stateName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item" style="text-align: center">
                <%--<label for="L_repass" class="layui-form-label"></label>--%>
                <button class="layui-btn" lay-filter="update" lay-submit="">修改</button>
            </div>
        </form>
    </div>
</div>
<script src="js/jquery-3.3.1.min.js"></script>
<script>

    $(function () {
        $("#nflag").blur(function () {
            var nflag = $(this).val();
            $.ajax({
                type: "Post",
                url: "provinceServlet?method=checkByName",
                data: {pname: nflag},
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

    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;

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
            //alert(JSON.stringify(datas));
            console.log(data);
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/note/update",
                contentType: "application/json",
                dataType: "text",
                data: datas,
                success: function (data) {
                    if (data == 'true') {
                        alert("修改成功！！！")
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

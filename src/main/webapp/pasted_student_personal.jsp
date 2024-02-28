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

    <title>毕业生信息修改</title>

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
            <%--<input type="hidden" name="sid" value="${requestScope.student.stuId}">--%>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>学生学号
                </label>
                <div class="layui-input-block">
                    <input type="text" name="stuId" autocomplete="off" id="sno" readonly
                           class="layui-input" lay-verify="required" value="${student.stuId}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>学生名字
                </label>
                <div class="layui-input-block">
                    <input type="text" name="stuName" autocomplete="off" id="sname" readonly="readonly"
                           class="layui-input" lay-verify="required" value="${student.stuName}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>生日
                </label>
                <div class="layui-input-block">
                    <input type="text" name="stuBirthday" autocomplete="off" id="sage" readonly="readonly"
                           class="layui-input" lay-verify="required" value="${student.stuBirthday}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>性别
                </label>
                <div class="layui-input-block">
                    <c:if test="${student.stuSex == '男'}">
                        男<input type="radio" name="stuSex" autocomplete="off" value="男"
                        class="layui-input" lay-verify="required" checked disabled>
                        女<input type="radio" name="stuSex" autocomplete="off" value="女"
                        class="layui-input" lay-verify="required" disabled>
                    </c:if>
                    <c:if test="${student.stuSex == '女'}">
                        男<input type="radio" name="stuSex" autocomplete="off" value="男"
                        class="layui-input" lay-verify="required" disabled>
                        女<input type="radio" name="stuSex" autocomplete="off" value="女"
                        class="layui-input" lay-verify="required" checked disabled>
                    </c:if>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>院系
                </label>
                <div class="layui-input-block">
                    <select class="layui-input" name="departId" lay-verify="required" autocomplete="off" disabled>
                        <option value="">请选择院系</option>
                        <c:forEach items="${departs}" var="depart">
                            <option value="${student.departId}"
                                    <c:if test="${student.departId == depart.departId}">
                                        selected
                                    </c:if>
                            >${depart.departName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>专业
                </label>
                <div class="layui-input-block">
                    <select class="layui-input" name="majorId" lay-verify="required" autocomplete="off" disabled>
                        <option value="">请选择专业</option>
                        <c:forEach items="${majors}" var="major">
                            <option value="${student.majorId}"
                                    <c:if test="${student.majorId == major.majorId}">
                                        selected
                                    </c:if>
                            >${major.majorName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>毕业年份
                </label>
                <div class="layui-input-block">
                    <input type="text" name="stuGraduTime" autocomplete="off" id="sEndate" readonly
                           class="layui-input" lay-verify="required" value="${student.stuGraduTime}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>毕业去向
                </label>
                <div class="layui-input-block">
                    <select class="layui-input" name="placeId" lay-verify="required" autocomplete="off" disabled>
                        <option value="">请选择毕业去向</option>
                        <c:forEach items="${places}" var="place">
                            <option value="${place.placeId}"
                                    <c:if test="${student.placeId == place.placeId}">
                                        selected
                                    </c:if>
                            >${place.placeName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <%--<div class="layui-form-item" style="text-align: center">
                &lt;%&ndash;<label for="L_repass" class="layui-form-label"></label>&ndash;%&gt;
                <button class="layui-btn" lay-filter="update" lay-submit="">修改</button>
            </div>--%>
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
                url: "student/update",
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

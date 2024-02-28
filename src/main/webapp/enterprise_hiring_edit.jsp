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

    <title>招聘修改</title>

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
                    <span class='x-red'>*</span>招聘企业
                </label>
                <div class="layui-input-block">
                    <input type="text" autocomplete="off"
                           class="layui-input" lay-verify="required"
                           value="${sessionScope.enterprise.enterpriseName}" readonly/>
                </div>
            </div>
            <input type="hidden" name="enterpriseId" value="${sessionScope.enterprise.enterpriseId}">
            <input type="hidden" name="requireId" value="${requirement.requireId}">
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>招聘岗位
                </label>
                <div class="layui-input-block">
                    <input type="text" name="requireJob" autocomplete="off" placeholder="填写招聘岗位"
                           class="layui-input" lay-verify="required" id="hname"
                           value="${requirement.requireJob}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>招聘专业
                </label>
                <div class="layui-input-block">
                    <select class="layui-input" name="majorId" lay-verify="required" autocomplete="off">
                        <option value="">请选择专业</option>
                        <c:forEach items="${majors}" var="major">
                            <option value="${major.majorId}"
                                    <c:if test="${major.majorId == requirement.majorId}">
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
                    <span class='x-red'>*</span>招聘人数
                </label>
                <div class="layui-input-block">
                    <input type="number" name="requireCount" autocomplete="off" placeholder="填写招聘人数"
                           class="layui-input" lay-verify="required" id="hmajor"
                           value="${requirement.requireCount}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>工作地点
                </label>
                <div class="layui-input-block">
                    <select class="layui-input" name="regionId" lay-verify="required" autocomplete="off">
                        <option value="">请选择地点</option>
                        <c:forEach items="${regions}" var="region">
                            <option value="${region.regionId}"
                                    <c:if test="${requirement.regionId == region.regionId}">
                                        selected
                                    </c:if>
                            >${region.regionName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>薪资待遇(/月)
                </label>
                <div class="layui-input-block">
                    <input type="number" name="requireSalary" autocomplete="off" placeholder="薪资"
                           class="layui-input" lay-verify="required" id="hprice"
                           value="${requirement.requireSalary}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>招聘要求
                </label>
                <div class="layui-input-block">
                    <textarea name="requireOther" autocomplete="off" placeholder="招聘要求"
                              class="layui-input" lay-verify="required"
                              id="hetime">${requirement.requireOther}</textarea>
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
        $("#cname").blur(function () {
            var cname = $(this).val();
            $.ajax({
                type: "Post",
                url: "cityServlet?method=checkByName",
                data: {cname: cname},
                success: function (data) {
                    //alert(data);
                    if (data == 'false') {
                        alert("城市名重复，请重新输入！！！")
                        $("#cname").val("");
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
            //alert(JSON.stringify(datas));
            console.log(data);
            $.ajax({
                type: "POST",
                url: "hiring/update",
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

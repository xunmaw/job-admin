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
        <form id="addsite" class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>招聘企业
                </label>
                <div class="layui-input-block">
                    <input type="text" autocomplete="off"
                           class="layui-input" lay-verify="required" value="${sessionScope.enterprise.enterpriseName}"
                           readonly/>
                </div>
            </div>
            <input type="hidden" name="enterpriseId" value="${sessionScope.enterprise.enterpriseId}">
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>招聘岗位
                </label>
                <div class="layui-input-block">
                    <input type="text" name="requireJob" autocomplete="off" placeholder="填写招聘岗位"
                           class="layui-input" lay-verify="required" id="hname">
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
                            <option value="${major.majorId}">${major.majorName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>招聘人数
                </label>
                <div class="layui-input-block">
                    <input type="text" name="requireCount" autocomplete="off" placeholder="填写招聘人数"
                           class="layui-input" lay-verify="required" id="hmajor">
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
                            <option value="${region.regionId}">${region.regionName}</option>
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
                           class="layui-input" lay-verify="required" id="hprice">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>招聘要求
                </label>
                <div class="layui-input-block">
                    <textarea name="requireOther" autocomplete="off" placeholder="招聘要求"
                              class="layui-input" lay-verify="required" id="hetime"></textarea>
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

    /* $(function () {
         $("#cname").blur(function () {
             var cname = $(this).val();
             $.ajax({
                 type:"Post",
                 url:"cityServlet?method=checkByName",
                 data:{cname:cname},
                 success:function (data) {
                     //alert(data);
                     if(data == 'false'){
                         alert("城市名重复，请重新输入！！！")
                         $("#cname").val("");
                     }
                 }
             })
         })
     })*/

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
                url: "${pageContext.request.contextPath}/hiring/addHire",
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

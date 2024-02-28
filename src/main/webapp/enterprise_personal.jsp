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

    <title>企业修改</title>

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
            <input type="hidden" name="enterpriseNo" value="${enterprise.enterpriseNo}">
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>企业代码
                </label>
                <div class="layui-input-block">
                    <input type="text" name="enterpriseId" autocomplete="off" readonly
                    class="layui-input" lay-verify="required" value="${enterprise.enterpriseId}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>企业名称
                </label>
                <div class="layui-input-block">
                    <input type="text" name="enterpriseName" autocomplete="off"
                           class="layui-input" lay-verify="required" value="${enterprise.enterpriseName}" readonly>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>企业所在地
                </label>
                <div class="layui-input-block">
                    <select class="layui-input" lay-filter="prov" id="region" lay-verify="required"
                            autocomplete="off" disabled>
                        <option value="">请选择城市</option>
                        <c:forEach items="${regions}" var="region">
                            <option value="${region.regionId}"
                                    <c:if test="${enterprise.regionId == region.regionId}">
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
                    <span class='x-red'>*</span>企业类型
                </label>
                <div class="layui-input-block">
                    <select class="layui-input" lay-filter="prov" id="type" lay-verify="required"
                            autocomplete="off" disabled>
                        <option value="">请选择类型</option>
                        <c:forEach items="${types}" var="type">
                            <option value="${type.typeId}"
                                    <c:if test="${enterprise.typeId == type.typeId}">
                                        selected
                                    </c:if>
                            >${type.typeName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>联系人
                </label>
                <div class="layui-input-block">
                    <input type="text" name="contactName" autocomplete="off"
                           class="layui-input" lay-verify="required" value="${enterprise.contactName}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>电话号码
                </label>
                <div class="layui-input-block">
                    <input type="text" name="contactTel" autocomplete="off"
                           class="layui-input" lay-verify="required" value="${enterprise.contactTel}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>邮箱
                </label>
                <div class="layui-input-block">
                    <input type="email" name="contactEmail" autocomplete="off"
                           class="layui-input" lay-verify="required" value="${enterprise.contactEmail}">
                </div>
            </div>
            <%--<div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>企业状态
                </label>
                <div class="layui-input-block">
                    <input type="hidden" name="eflag" value="${enterprise.eflag}"/>
                    <c:if test="${enterprise.eflag == 0}">
                        未审核
                    </c:if>
                    <c:if test="${enterprise.eflag == 1}">
                        已通过
                    </c:if>
                </div>
            </div>--%>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>企业密码
                </label>
                <div class="layui-input-block">
                    <input type="text" name="enterprisePass" autocomplete="off" id="pass" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"
                           class="layui-input" lay-verify="required" value="${enterprise.enterprisePass}">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span class='x-red'>*</span>企业简介
                </label>
                <div class="layui-input-block">
                    <textarea name="enterpriseIntro" autocomplete="off"
                              class="layui-input" lay-verify="required">${enterprise.enterpriseIntro}</textarea>
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
        $("#pass").blur(function () {
            var pass = $(this).val();
            if (pass.length<1){
                alert("请设置密码！！！");
            } else if (pass.length>1&&pass.length<6){
                alert("密码长度至少为六！！！");
            }
        })
    })

    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;

        form.on('select(prov)', function (data) {
            var pid = data.value;
            $.ajax({
                dataType: 'json',
                data: {
                    'pid': pid,
                },
                url: 'city/selectCityByPId',
                success: function (msg) {
                    var res = msg.data;

                    console.log(res)
                    $('#ecid').empty().append("<option>请选择城市</option>");
                    $(res).each(function () {
                        var content = $('<option value="' + this.cid + '">' + this.cname + '</option>');
                        $('#ecid').append(content);


                    })
                    form.render('select');

                }
            })
        })
        /* form.on('select(prov)',
             function (){
                 var prov=$("#province").val();
                 $.ajax({
                     type:"post",
                     url:"city/selectCityByPId",
                     data:{pid: prov},
                     success:function (res){
                         $("#ecid").empty();
                         $("#ecid").append("<option>---请选择---</option>")
                         alert(res.cityList[i].cname);
                         for (var i=0;i<res.cityList.length;i++){

                             $("#ecid").append("<option value='"+res.cityList[i].cid+"'>"+res.cityList[i].cname+"</option>");
                         }
                         form.render('select');
                     }
                 });

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
            console.log(data);
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/enterprise/update",
                contentType: "application/json",
                dataType: "text",
                data: datas,
                success: function (data) {
                    if (data == 'true') {
                        alert("修改成功！")
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

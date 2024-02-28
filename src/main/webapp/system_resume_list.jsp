<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8" http-equiv="content-type" content="text/html">

    <title>My JSP 'placelist.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" href="css/search.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
</head>

<body>
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">学生信息管理</a>
            <a>
              <cite>简历信息管理</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5" method="post" action="${pageContext.request.contextPath}/system/showAllResume">
                        <input type="hidden" name="intentionState" value="0">
                        <div class="layui-input-inline layui-show-xs-block">
                            <input type="text" name="intentionMajor" autocomplete="off" placeholder="请输入岗位"
                                   class="layui-input"/>
                        </div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <select name="regionId">
                                <option value="">请选择城市</option>
                                <c:forEach items="${regions}" var="region">
                                    <option value="${region.regionId}">${region.regionName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn" lay-submit="" lay-filter="sreach"><i
                                    class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>

                <div class="layui-card-body layui-table-body layui-table-main">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>
                                编号
                            </th>
                            <th>
                                学号
                            </th>
                            <th>
                                学生姓名
                            </th>
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
                                操作
                            </th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pager.datas}" var="intention">
                            <tr>
                                <td>
                                    <c:out value="${intention.intentionId}"/>
                                </td>
                                <td>
                                    <c:out value="${intention.student.stuId}"/>
                                </td>
                                <td>
                                    <c:out value="${intention.student.stuName}"/>
                                </td>
                                <td>
                                    <c:out value="${intention.intentionMajor}"/>
                                </td>
                                <td>
                                    <c:out value="${intention.intentionSalary}"/>
                                </td>
                                <td>
                                    <c:out value="${intention.region.regionName}"/>
                                </td>
                                <td onclick="xadmin.open('技能详情','${pageContext.request.contextPath}/resume/showDetail/${intention.intentionId}',500,500)">
                                    <a>查看</a>
                                </td>
                                <td class="td-manage">
                                    <button class="layui-btn layui-btn layui-btn-xs"
                                            onclick="xadmin.open('查看详细信息','${pageContext.request.contextPath}/manager/getItemByResumeId/${intention.intentionId}',700,500)">
                                        <i class="layui-icon">&#xe642;</i>查看详细信息
                                    </button>
                                    <c:if test="${sessionScope.manager != null&&sessionScope.enterprise==null}">
                                        <button class="layui-btn layui-btn layui-btn-xs"
                                                onclick="xadmin.open('审核','${pageContext.request.contextPath}/manager/forwardToChangResumeState/${intention.intentionId}',450,200)" >
                                            <i class="layui-icon">&#xe642;</i>审核</button>

                                    <button class="layui-btn-danger layui-btn layui-btn-xs"
                                            onclick="member_del(this,'${intention.intentionId}')" href="javascript:;" >
                                        <i class="layui-icon">&#xe640;</i>删除</button>
                                    </c:if>
                                </td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div align="center">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <li><a>共${pager.totalPage}页${pager.totalCount}条</a></li>
                                <%--首页--%>
                                <li>
                                    <a href="${pageContext.request.contextPath}/enterprise/showAllResume?intentionState=${intentionState}&intentionMajor=${intentionMajor}&regionId=${regionId}&offset=1&size=${pager.size}">首页</a>
                                </li>
                                <%--上一页--%>
                                <c:if test="${pager.offset<=1}">
                                    <li class="disabled"><a href="#">&laquo;</a></li>
                                </c:if>
                                <c:if test="${pager.offset>1}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/enterprise/showAllResume?intentionState=${intentionState}&intentionMajor=${intentionMajor}&regionId=${regionId}&offset=${pager.offset-1}&size=${pager.size}">&laquo;</a>
                                    </li>
                                </c:if>
                                <%--分页按钮--%>
                                <c:forEach begin="${begin}" end="${end}" var="i">
                                    <c:if test="${pager.offset==i}">
                                        <li class="active">
                                            <a href="${pageContext.request.contextPath}/enterprise/showAllResume?intentionState=${intentionState}&intentionMajor=${intentionMajor}&regionId=${regionId}&offset=${i}&size=${pager.size}">${i}</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${pager.offset!=i}">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/enterprise/showAllResume?intentionState=${intentionState}&intentionMajor=${intentionMajor}&regionId=${regionId}&offset=${i}&size=${pager.size}">${i}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                                <%--下一页--%>
                                <c:if test="${pager.offset>=pager.totalPage}">
                                    <li class="disabled"><a href="#">&raquo;</a></li>
                                </c:if>
                                <c:if test="${pager.offset<pager.totalPage}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/enterprise/showAllResume?intentionState=${intentionState}&intentionMajor=${intentionMajor}&regionId=${regionId}&offset=${pager.offset+1}&size=${pager.size}">&raquo;</a>
                                    </li>
                                </c:if>
                                <%--尾页--%>
                                <li>
                                    <a href="${pageContext.request.contextPath}/enterprise/showAllResume?intentionState=${intentionState}&intentionMajor=${intentionMajor}&regionId=${regionId}&offset=${pager.totalPage}&size=${pager.size}">尾页</a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>

    layui.use(['laydate', 'form'], function () {
        var laydate = layui.laydate;
        var form = layui.form;


        // 监听全选
        form.on('checkbox(checkall)', function (data) {

            if (data.elem.checked) {
                $('tbody input').prop('checked', true);
            } else {
                $('tbody input').prop('checked', false);
            }
            form.render('checkbox');
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });


    });


    /*用户-删除*/
    function member_del(obj, id) {
        layer.confirm('确认要删除吗？', {icon: 3, title: '提示信息'}, function (index) {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/resume/deleteResume",
                dataType: "text",
                data: {intentionId: id},
                success: function (data) {
                    if (data == 'true') {
                        //发异步删除数据
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!', {icon: 1, time: 1000});
                        window.parent.location.reload();
                    } else {
                        layer.msg('删除失败!', {icon: 1, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg('错误!', {icon: 1, time: 1000});
                }
            });
        });
    }


    function delAll(argument) {
        var ids = [];

        // 获取选中的id
        $('tbody input').each(function (index, el) {
            if ($(this).prop('checked')) {
                ids.push($(this).val())
            }
        });

        layer.confirm('确认要删除吗？' + ids.toString(), {icon: 3, title: '提示信息'}, function (index) {
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
    }
</script>
</html>
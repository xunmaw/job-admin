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
              <cite>就业信息管理</cite></a>
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

                    <%--<form class="layui-form layui-col-space5">
                        <div class="layui-input-inline layui-show-xs-block">
                            <select name="contrller">
                                <option value=""></option>
                                <option value="0"></option>
                                <option value="1"></option>
                            </select>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn" lay-submit="" lay-filter="sreach"><i
                                    class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>--%>
                </div>
                <c:if test="${sessionScope.student != null }">
                    <div class="layui-card-header">
                            <%--<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>--%>
                        <button class="layui-btn"
                                onclick="xadmin.open('添加就业信息','${pageContext.request.contextPath}/obtain/forwardToAddObtain/${sessionScope.student.stuId}',500,300)">
                            <i class="layui-icon"></i>添加
                        </button>
                    </div>
                </c:if>
                <div class="layui-card-body layui-table-body layui-table-main">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <%--<th>
                                <input type="checkbox" lay-filter="checkall" name="" lay-skin="primary">
                            </th>--%>
                            <th>
                                学号
                            </th>
                            <th>
                                学生姓名
                            </th>
                            <th>
                                是否自主就业
                            </th>
                            <th>
                                就业企业
                            </th>
                            <th>
                                就业职位
                            </th>
                            <th>
                                就业时间
                            </th>
                            <th>
                                操作
                            </th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pager.datas}" var="obtain">
                            <tr>
                                <td>
                                    <c:out value="${obtain.student.stuId}"/>
                                </td>
                                <td>
                                    <c:out value="${obtain.student.stuName}"/>
                                </td>
                                <td>
                                    <c:if test="${obtain.obtainType ==1}">
                                        自主就业
                                    </c:if>
                                    <c:if test="${obtain.obtainType ==0}">
                                        非自主就业
                                    </c:if>
                                </td>
                                <td>
                                    <c:out value="${obtain.obtainEnterprise}"/>
                                </td>
                                <td>
                                    <c:out value="${obtain.obtainJob}"/>
                                </td>
                                <td>
                                    <c:out value="${obtain.obtainTime}"/>
                                </td>
                                    <%--<td>
                                        <c:if test="${obtain.oflag ==0}">未审核</c:if>
                                        <c:if test="${obtain.oflag ==1}">已通过</c:if>
                                        <c:if test="${obtain.oflag ==2}">未通过</c:if>
                                    </td>--%>
                                <td class="td-manage">
                                    <c:if test="${sessionScope.student != null}">
                                        <button class="layui-btn layui-btn layui-btn-xs"
                                                onclick="xadmin.open('修改','${pageContext.request.contextPath}/obtain/forwardToUpdate/${obtain.obtainId}',700,500)">
                                            <i class="layui-icon">&#xe642;</i>修改
                                        </button>
                                    </c:if>
                                        <%--<c:if test="${sessionScope.university != null}">
                                            <button class="layui-btn layui-btn layui-btn-xs"
                                                onclick="xadmin.open('审核','${pageContext.request.contextPath}/obtain/getOsid?method=edit&oid=${obtain.oid}',450,200)" >
                                            <i class="layui-icon">&#xe642;</i>审核</button>
                                        </c:if>--%>
                                    <button class="layui-btn-danger layui-btn layui-btn-xs"
                                            onclick="member_del(this,'${obtain.obtainId}')" href="javascript:;">
                                        <i class="layui-icon">&#xe640;</i>删除
                                    </button>
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
                                    <a href="${pageContext.request.contextPath}/obtain/listAll?stuId=${sessionScope.student.stuId}&offset=1&size=${pager.size}">首页</a>
                                </li>
                                <%--上一页--%>
                                <c:if test="${pager.offset<=1}">
                                    <li class="disabled"><a href="#">&laquo;</a></li>
                                </c:if>
                                <c:if test="${pager.offset>1}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/obtain/listAll?stuId=${sessionScope.student.stuId}&offset=${pager.offset-1}&size=${pager.size}">&laquo;</a>
                                    </li>
                                </c:if>
                                <%--分页按钮--%>
                                <c:forEach begin="${begin}" end="${end}" var="i">
                                    <c:if test="${pager.offset==i}">
                                        <li class="active">
                                            <a href="${pageContext.request.contextPath}/obtain/listAll?stuId=${sessionScope.student.stuId}&offset=${i}&size=${pager.size}">${i}</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${pager.offset!=i}">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/obtain/listAll?stuId=${sessionScope.student.stuId}&offset=${i}&size=${pager.size}">${i}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                                <%--下一页--%>
                                <c:if test="${pager.offset>=pager.totalPage}">
                                    <li class="disabled"><a href="#">&raquo;</a></li>
                                </c:if>
                                <c:if test="${pager.offset<pager.totalPage}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/obtain/listAll?stuId=${sessionScope.student.stuId}&offset=${pager.offset+1}&size=${pager.size}">&raquo;</a>
                                    </li>
                                </c:if>
                                <%--尾页--%>
                                <li>
                                    <a href="${pageContext.request.contextPath}/obtain/listAll?stuId=${sessionScope.student.stuId}&offset=${pager.totalPage}&size=${pager.size}">尾页</a>
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
                url: "${pageContext.request.contextPath}/obtain/delete",
                dataType: "text",
                data: {obtainId: id},
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
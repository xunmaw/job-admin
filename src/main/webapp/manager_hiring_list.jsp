<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8" http-equiv="content-type" content="text/html">

    <title>My JSP 'placelist.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>

    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">招聘信息管理</a>
            <a>
              <cite>招聘信息管理</cite></a>
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
                    <form class="layui-form layui-col-space5" method="post"
                          action="${pageContext.request.contextPath}/hiring/showAllHire">
                        <div class="layui-input-inline layui-show-xs-block">
                            <input type="text" name="requireJob" autocomplete="off" placeholder="请输入企业代码"
                                   class="layui-input"/>
                        </div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <select name="requireState">
                                <option value="">请选择审核状态</option>
                                <option value="0">未审核</option>
                                <option value="1">已通过</option>
                                <option value="2">未通过</option>
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
                                企业代码
                            </th>
                            <th>
                                企业名称
                            </th>
                            <th>
                                招聘岗位
                            </th>

                            <th>
                                招聘专业
                            </th>
                            <th>
                                招聘人数
                            </th>
                            <th>
                                工作地点
                            </th>
                            <th>
                                薪资待遇
                            </th>
                            <th>
                                招聘要求
                            </th>
                            <th>
                                审核状态
                            </th>
                            <th colspan="3">
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pager.datas}" var="require">
                            <tr>
                                <td>
                                    <c:out value="${require.requireId}"/>
                                </td>
                                <td>
                                    <c:out value="${require.enterprise.enterpriseId}"/>
                                </td>
                                <td>
                                    <c:out value="${require.enterprise.enterpriseName}"/>
                                </td>
                                <td>
                                    <c:out value="${require.requireJob}"/>
                                </td>

                                <td>
                                    <c:out value="${require.major.majorName}"/>
                                </td>
                                <td>
                                    <c:out value="${require.requireCount}"/>
                                </td>
                                <td>
                                    <c:out value="${require.region.regionName}"/>
                                </td>
                                <td>
                                    <c:out value="${require.requireSalary}"/>
                                </td>
                                <td>
                                    <c:out value="${require.requireOther}"/>
                                </td>
                                <td>
                                    <c:if test="${require.requireState == 0}">
                                        未审核
                                    </c:if>
                                    <c:if test="${require.requireState == 1}">
                                        已通过
                                    </c:if>
                                    <c:if test="${require.requireState == 2}">
                                        未通过
                                    </c:if>
                                </td>
                                <td class="td-manage">
                                    <button class="layui-btn layui-btn layui-btn-xs"
                                            onclick="xadmin.open('审核','${pageContext.request.contextPath}/manager/forwardToChangRequireState/${require.requireId}',450,200)" >
                                        <i class="layui-icon">&#xe642;</i>审核
                                    </button>
                                    <button class="layui-btn-danger layui-btn layui-btn-xs"
                                            onclick="member_del(this,'${require.requireId}')" href="javascript:;">
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
                                    <a href="${pageContext.request.contextPath}/hiring/showAllHire?enterpriseId=${enterpriseId}&requireState=${requireState}&offset=1&size=${pager.size}">首页</a>
                                </li>
                                <%--上一页--%>
                                <c:if test="${pager.offset<=1}">
                                    <li class="disabled"><a href="#">&laquo;</a></li>
                                </c:if>
                                <c:if test="${pager.offset>1}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/hiring/showAllHire?enterpriseId=${enterpriseId}&requireState=${requireState}&offset=${pager.offset-1}&size=${pager.size}">&laquo;</a>
                                    </li>
                                </c:if>
                                <%--分页按钮--%>
                                <c:forEach begin="${begin}" end="${end}" var="i">
                                    <c:if test="${pager.offset==i}">
                                        <li class="active">
                                            <a href="${pageContext.request.contextPath}/hiring/showAllHire?enterpriseId=${enterpriseId}&requireState=${requireState}&offset=${i}&size=${pager.size}">${i}</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${pager.offset!=i}">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/hiring/showAllHire?enterpriseId=${enterpriseId}&requireState=${requireState}&offset=${i}&size=${pager.size}">${i}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                                <%--下一页--%>
                                <c:if test="${pager.offset>=pager.totalPage}">
                                    <li class="disabled"><a href="#">&raquo;</a></li>
                                </c:if>
                                <c:if test="${pager.offset<pager.totalPage}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/hiring/showAllHire?enterpriseId=${enterpriseId}&requireState=${requireState}&offset=${pager.offset+1}&size=${pager.size}">&raquo;</a>
                                    </li>
                                </c:if>
                                <%--尾页--%>
                                <li>
                                    <a href="${pageContext.request.contextPath}/hiring/showAllHire?enterpriseId=${enterpriseId}&requireState=${requireState}&offset=${pager.totalPage}&size=${pager.size}">尾页</a>
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
                url: "${pageContext.request.contextPath}/hiring/delete",
                dataType: "text",
                data: {requireId: id},
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

    function member_shenqing(obj, id) {
        layer.confirm('确认要申请吗？', {icon: 3, title: '提示信息'}, function (index) {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/hiring/selectHiringById1",
                dataType: "text",
                data: {hid: id},
                success: function (data) {
                    if (data == 'true') {
                        //发异步删除数据

                        // $(obj).parents("tr").remove();

                        layer.msg('已申请!', {icon: 1, time: 1000});

                    } else {
                        layer.msg('申请失败!', {icon: 1, time: 1000});
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
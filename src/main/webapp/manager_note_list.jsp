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
              <cite>招聘信息记录</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5" method="post"
                          action="${pageContext.request.contextPath}/note/showAllNote">
                        <div class="layui-input-inline layui-show-xs-block">
                            <input type="text" name="enterpriseId" autocomplete="off" placeholder="请输入企业代码"
                                   class="layui-input"/>
                        </div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <select name="stateId">
                                <option value="">请选择记录状态</option>
                                <option value="0">未面试</option>
                                <option value="1">已通过</option>
                                <option value="2">未通过</option>
                            </select>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
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
                                学生名称
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
                                申请时间
                            </th>
                            <th>
                                记录状态
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pager.datas}" var="note">
                            <tr>
                                <td>
                                    <c:out value="${note.noteId}"/>
                                </td>
                                <td>
                                    <c:out value="${note.stuId}"/>
                                </td>
                                <td>
                                    <c:out value="${note.student.stuName}"/>
                                </td>
                                <td>
                                    <c:out value="${note.requirement.enterpriseId}"/>
                                </td>
                                <td>
                                    <c:out value="${note.requirement.enterprise.enterpriseName}"/>
                                </td>
                                <td>
                                    <c:out value="${note.requirement.requireJob}"/>
                                </td>
                                <td>
                                    <c:out value="${note.noteDay}"/>
                                </td>
                                <td >
                                    <c:if test="${note.stateId  == 0}">
                                        未面试
                                    </c:if>
                                    <c:if test="${note.stateId  == 1}">
                                        已通过
                                    </c:if>
                                    <c:if test="${note.stateId  == 2}">
                                        未通过
                                    </c:if>
                                    <%--<c:if test="${note.noteState  == 3}">
                                        未入职
                                    </c:if>
                                    <c:if test="${note.noteState  == 4}">
                                        已入职
                                    </c:if>--%>
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
                                    <a href="${pageContext.request.contextPath}/note/showAllNote?enterpriseId=${enterpriseId}&stateId=${stateId}&offset=1&size=${pager.size}">首页</a>
                                </li>
                                <%--上一页--%>
                                <c:if test="${pager.offset<=1}">
                                    <li class="disabled"><a href="#">&laquo;</a></li>
                                </c:if>
                                <c:if test="${pager.offset>1}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/note/showAllNote?enterpriseId=${enterpriseId}&stateId=${stateId}&offset=${pager.offset-1}&size=${pager.size}">&laquo;</a>
                                    </li>
                                </c:if>
                                <%--分页按钮--%>
                                <c:forEach begin="${begin}" end="${end}" var="i">
                                    <c:if test="${pager.offset==i}">
                                        <li class="active">
                                            <a href="${pageContext.request.contextPath}/note/showAllNote?enterpriseId=${enterpriseId}&stateId=${stateId}&offset=${i}&size=${pager.size}">${i}</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${pager.offset!=i}">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/note/showAllNote?enterpriseId=${enterpriseId}&stateId=${stateId}&offset=${i}&size=${pager.size}">${i}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                                <%--下一页--%>
                                <c:if test="${pager.offset>=pager.totalPage}">
                                    <li class="disabled"><a href="#">&raquo;</a></li>
                                </c:if>
                                <c:if test="${pager.offset<pager.totalPage}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/note/showAllNote?enterpriseId=${enterpriseId}&stateId=${stateId}&offset=${pager.offset+1}&size=${pager.size}">&raquo;</a>
                                    </li>
                                </c:if>
                                <%--尾页--%>
                                <li>
                                    <a href="${pageContext.request.contextPath}/note/showAllNote?enterpriseId=${enterpriseId}&stateId=${stateId}&offset=${pager.totalPage}&size=${pager.size}">尾页</a>
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
    layui.use(['laydate','form'], function(){
        var laydate = layui.laydate;
        var  form = layui.form;


        // 监听全选
        form.on('checkbox(checkall)', function(data){

            if(data.elem.checked){
                $('tbody input').prop('checked',true);
            }else{
                $('tbody input').prop('checked',false);
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
    function member_del(obj,id){
        layer.confirm('确认要删除吗？',{icon:3,title:'提示信息'},function(index){
            $.ajax({
                type:"POST",
                url:"${pageContext.request.contextPath}/note/delete",
                dataType:"text",
                data: {noteId: id},
                success:function (data){
                    if(data == 'true'){
                        //发异步删除数据
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!',{icon:1,time:1000});
                        window.parent.location.reload();
                    }else {
                        layer.msg('删除失败!',{icon:1,time:1000});
                    }
                },
                error:function (data){
                    layer.msg('错误!',{icon:1,time:1000});
                }
            });
        });
    }



    function delAll (argument) {
        var ids = [];

        // 获取选中的id
        $('tbody input').each(function(index, el) {
            if($(this).prop('checked')){
                ids.push($(this).val())
            }
        });

        layer.confirm('确认要删除吗？'+ids.toString(),{icon:3,title:'提示信息'},function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
    }
</script>
</html>
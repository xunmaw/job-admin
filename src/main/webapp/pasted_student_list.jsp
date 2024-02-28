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
            <a href="">就业历史管理</a>
            <a>
              <cite>毕业生信息管理</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i>
    </a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5" method="post"
                          action="${pageContext.request.contextPath}/student/showAllPastedStudent">
                        <div class="layui-input-inline layui-show-xs-block">
                            <input type="text" name="stuId" autocomplete="off" placeholder="请输入学号"
                                   class="layui-input"/>
                        </div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <input type="text" name="stuName" autocomplete="off" placeholder="请输入姓名"
                                   class="layui-input"/>
                        </div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <select name="majorId">
                                <option value="">请选择专业</option>
                                <c:forEach items="${majors}" var="major">
                                    <option value="${major.majorId}">${major.majorName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <input type="text" name="stuGraduTime" autocomplete="off" placeholder="请输入毕业年份"
                                   class="layui-input"/>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn" lay-submit="" lay-filter="sreach"><i
                                    class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>
                <c:if test="${sessionScope.manager!=null&&sessionScope.enterprise==null}">
                    <div class="layui-card-header">
                            <%--<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>--%>
                        <button class="layui-btn"
                                onclick="xadmin.open('添加毕业生','${pageContext.request.contextPath}/student/forwardToAddPastedStu',500,550)">
                            <i class="layui-icon"></i>添加
                        </button>
                        <button class="layui-btn" id="importExcel" onclick="importExcel();"><i class="layui-icon"></i>导入
                        </button>
                    </div>
                </c:if>
                <div class="layui-card-body layui-table-body layui-table-main">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>
                                学号
                            </th>
                            <th>
                                学生姓名
                            </th>
                            <th>
                                年龄
                            </th>
                            <th>
                                性别
                            </th>
                            <th>
                                院系
                            </th>
                            <th>
                                专业
                            </th>
                            <th>
                                毕业年份
                            </th>
                            <th>
                                毕业去向
                            </th>
                            <th>
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pager.datas}" var="student">
                            <tr>
                                <td>
                                    <c:out value="${student.stuId}"/>
                                </td>
                                <td>
                                    <c:out value="${student.stuName}"/>
                                </td>
                                <td>
                                    <c:out value="${student.stuAge}"/>
                                </td>
                                <td>
                                    <c:out value="${student.stuSex}"/>
                                </td>
                                <td>
                                    <c:out value="${student.depart.departName}"/>
                                </td>
                                <td>
                                    <c:out value="${student.major.majorName}"/>
                                </td>
                                <td>
                                    <c:out value="${student.stuGraduTime}"/>
                                </td>
                                <td>
                                    <c:out value="${student.graduatePlace.placeName}"/>
                                </td>
                                <td class="td-manage">
                                    <button class="layui-btn layui-btn layui-btn-xs"
                                            onclick="xadmin.open('查看详细信息','${pageContext.request.contextPath}/student/selectPastedStuById?stuId=${student.stuId}&flag=0',700,500)">
                                        <i class="layui-icon">&#xe642;</i>查看详细信息
                                    </button>
                                    <c:if test="${sessionScope.manager!=null&&sessionScope.enterprise==null}">
                                        <button class="layui-btn layui-btn layui-btn-xs"
                                                onclick="xadmin.open('修改','${pageContext.request.contextPath}/student/selectPastedStuById?stuId=${student.stuId}&flag=1',700,500)">
                                            <i class="layui-icon">&#xe642;</i>修改
                                        </button>
                                        <button class="layui-btn-danger layui-btn layui-btn-xs"
                                                onclick="member_del(this,'${student.stuId}')" href="javascript:;">
                                            <i class="layui-icon">&#xe640;</i>删除
                                        </button>
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
                                    <a href="${pageContext.request.contextPath}/enterprise/showAllStudent?stuId=${stuId}&stuEntrance=${stuEntrance}&stateId=${stateId}&majorId=${majorId}&offset=1&size=5">首页</a>
                                </li>
                                <%--上一页--%>
                                <c:if test="${pager.offset<=1}">
                                    <li class="disabled"><a href="#">&laquo;</a></li>
                                </c:if>
                                <c:if test="${pager.offset>1}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/enterprise/showAllStudent?stuId=${stuId}&stuEntrance=${stuEntrance}&stateId=${stateId}&majorId=${majorId}&offset=${pager.offset-1}&size=${pager.size}">&laquo;</a>
                                    </li>
                                </c:if>
                                <%--分页按钮--%>
                                <c:forEach begin="${begin}" end="${end}" var="i">
                                    <c:if test="${pager.offset==i}">
                                        <li class="active">
                                            <a href="${pageContext.request.contextPath}/enterprise/showAllStudent?stuId=${stuId}&stuEntrance=${stuEntrance}&stateId=${stateId}&majorId=${majorId}&offset=${i}&size=${pager.size}">${i}</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${pager.offset!=i}">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/enterprise/showAllStudent?stuId=${stuId}&stuEntrance=${stuEntrance}&stateId=${stateId}&majorId=${majorId}&offset=${i}&size=${pager.size}">${i}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                                <%--下一页--%>
                                <c:if test="${pager.offset>=pager.totalPage}">
                                    <li class="disabled"><a href="#">&raquo;</a></li>
                                </c:if>
                                <c:if test="${pager.offset<pager.totalPage}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/enterprise/showAllStudent?stuId=${stuId}&stuEntrance=${stuEntrance}&stateId=${stateId}&majorId=${majorId}&offset=${pager.offset+1}&size=${pager.size}">&raquo;</a>
                                    </li>
                                </c:if>
                                <%--尾页--%>
                                <li>
                                    <a href="${pageContext.request.contextPath}/enterprise/showAllStudent?stuId=${stuId}&stuEntrance=${stuEntrance}&stateId=${stateId}&majorId=${majorId}&offset=${pager.totalPage}&size=${pager.size}">尾页</a>
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

    // 导入
    function importExcel() {
        $('#importExcel').after('<input type="file" id="load_xls" name="file" style="display:none" onchange ="uploadFile()">');
        $("#load_xls").click();
    }

    // 上传文件
    function uploadFile() {
        var myForm = new FormData();
        myForm.append('file', $('#load_xls')[0].files[0]);
        $.ajax({
            url: "${pageContext.request.contextPath}/import/importPastedStuExcel",
            type: "POST",
            data: myForm,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data) {
                    alert("导入成功！");
                    //window.this.location.reload();
                    window.parent.location.reload();
                } else {
                    alert("导入失败！");
                }
            },
            error: function (data) {
                console.log(data)
            }
        });
    }

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
                url: "${pageContext.request.contextPath}/student/deleteStu",
                dataType: "text",
                data: {stuId: id},
                success: function (data) {
                    if (data == 'true') {
                        //发异步删除数据
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!', {icon: 1, time: 1000});

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
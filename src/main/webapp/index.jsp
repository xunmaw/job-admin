<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <%--<style>
        body{background:url(images/background.jpg) top left;
            background-size:100%;}
    </style>--%>
</head>
<body>
<div id="wrapper" class="login-page">
    <div id="login_form" class="form">
        <form class="register-form" id="register">
            <h2>企业注册</h2>
            <input type="text" placeholder="企业名称" id="r_user_name" name="ename" required/>
            <input type="password" placeholder="密码" id="r_password" name="epwd" required/>
            <input type="text" placeholder="电子邮件" id="r_email" name="eemail" onblur="ischeckemail()" />
            <select style="width: 100%;font-size: 14px;background: #f2f2f2;border: 0;margin: 0 0 15px;
                            padding: 15px;box-sizing: border-box;outline: 0;" name="" lay-filter="province" required="" id="province">
                <option value="">请选择所在省份</option>
            </select>
            <select style="width: 100%;font-size: 14px;background: #f2f2f2;border: 0;margin: 0 0 15px;
                            padding: 15px;box-sizing: border-box;outline: 0;" name="ecid.cid" lay-filter="city" required="" id="ecid">
                <option value="">请选择所在城市</option>
            </select>
            <input type="file" placeholder="营业执照" id="elicense" name="elicense" required/>

            <button id="create">创建账户</button>
            <p class="message">已经有了一个账户? <a href="#">立刻登录</a></p>
        </form>
        <form class="login-form">
            <h2>登录</h2>
            <input type="text" placeholder="学号/编号" id="user_name" required/>
            <input type="password" placeholder="密码" id="password" required/>
            <select style="width: 100%;font-size: 14px;background: #f2f2f2;border: 0;margin: 0 0 15px;
                            padding: 15px;box-sizing: border-box;outline: 0;" name="flag" required="" id="flag">
                <option value="">请选择角色</option>
                <option value="0">管理员</option>
                <option value="1">企业</option>
                <option value="2">学生</option>
            </select>
            <button id="login">登　录</button>
            <%--<p class="message">还没有账户? <a href="#">立刻创建</a></p>--%>
        </form>
    </div>
</div>

<script src="js/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
        $.ajax({
            type:"post",
            url:"province/selectProvinceAllLayui",
            dataType:"JSON",
            success:function (res) {
                //alert(res.provinceList[0].pid);
                $("#province").empty();
                $('#province').append("<option value=''>请选择所在省份</option>");
                for(var i=0;i<res.provinceList.length;i++){
                    $("#province").append("<option value='"+res.provinceList[i].pid+"'>"+res.provinceList[i].pname+"</option>");
                }
            }
        });
        $("#province").change(function (){
            var val = $(this).val();
            $.ajax({
                type:"post",
                url:"city/selectCityByPIdOfLogin",
                data:{pid:val},
                dataType:"JSON",
                success:function (res) {
                    $("#ecid").empty();
                    $('#ecid').append("<option value=''>请选择所在城市</option>");
                    for(var i=0;i<res.cityList.length;i++){
                        $("#ecid").append("<option value='"+res.cityList[i].cid+"'>"+res.cityList[i].cname+"</option>");
                    }
                }
            });
        })
    })


    function check_login()
    {
        var no=$("#user_name").val();
        var pwd=$("#password").val();
        var flag=$("#flag").val();
        if (flag==""){
            alert("请选择角色！！！")
        }else{
            $.ajax({
                type:"POST",
                url: "login/login",
                dataType:"text",
                data:{no:no,pwd:pwd,flag:flag},
                success: function(data){
                    //alert(data)
                    if(data =="0"){
                        $(location).attr('href','manager_center.jsp');
                    }else if (data=="1"){
                        $(location).attr('href','enterprise_center.jsp');
                    }else if (data=="2"){
                        $(location).attr('href','student_center.jsp');
                    }else {
                        alert("用户名或密码错误...");
                        $("#login_form").removeClass('shake_effect');
                        setTimeout(function()
                        {
                            $("#login_form").addClass('shake_effect')
                        },1);
                    }
                },
                error:function (data){
                    alert("check_login错误...")
                }
            });
        }

    }
    function ischeckemail(){
        var email = document.getElementById("r_email").value;
        if(email != "") {
            var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
            //调用正则验证test()函数
            isok= reg.test(email);
            if(!isok) {
                alert("邮箱格式不正确，请重新输入！");
                $("#r_email").val('');
                document.getElementById("r_email").focus();
                return false;
            }
        };
    }
    function check_register(){
        var name = $("#r_user_name").val();
        var pwd = $("#r_password").val();
        var email = $("#r_email").val();
        var cid = $("#ecid").val();
        var img =  document.getElementById('elicense').files[0];
        var formDate = new FormData();
        formDate.append('ename',name);
        formDate.append('epwd',pwd);
        formDate.append('eemail',email);
        formDate.append('ecid.cid',cid);
        formDate.append('file',img);

        $.ajax({
            type:"POST",
            url: "login/register",
            dataType:"text",
            data:formDate,
            contentType: false,
            processData: false,
            success: function(data){
                //alert(data)
                if(data =='0'){
                    alert("注册成功...");
                    //window.location.href='index.jsp';
                    $(location).attr('href', 'login.jsp');
                } else {
                    alert("注册失败...");
                    $("#login_form").removeClass('shake_effect');
                    setTimeout(function()
                    {
                        $("#login_form").addClass('shake_effect')
                    },1);
                }
            },
            error:function (data){
                alert("check_register错误...")
            }
        });
    }
    $(function(){
        $("#create").click(function(){
            check_register();
            return false;
        })
        $("#login").click(function(){
            check_login();
            return false;
        })
        $('.message a').click(function () {
            $('form').animate({
                height: 'toggle',
                opacity: 'toggle'
            }, 'slow');
        });
    })
</script>
</body>
</html>
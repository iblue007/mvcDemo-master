<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/17
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user_ADD</title>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>

</head>
<body>
<!-- action="/orderShop/user/insert" method="post" -->
  <form id="formId">

      <%--用户名：<input type="text" name="userName">--%>
          昵称：<input type="text" name="userName"></br>
      电话：<input type="text" name="mobile"></br>
          邮箱：<input type="text" name="email"></br>

      <input type="button" value="提交"  id="submit"/>
  </form>
  <script >

      $("#submit").click(function(){

          var data = new Object();
          data.userName = $("#formId input[name='userName']").val();
          data.mobile = $("#formId input[name='mobile']").val();
          data.email = $("#formId input[name='email']").val();

          console.log(data);
          $.ajax(
              {
                  url:"/orderShop/user/insert",
                  contentType:"application/json;utf-8",
                  dataType:"json",
                  data:JSON.stringify(data),
                  type:"post",
                  success:function(response){
                      console.log(response);
                      debugger;
                  },
                  error:function(response){
                      console.log(response);
                      debugger;
                  }
              }
          );
      });


  </script>
</body>
</html>

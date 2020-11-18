<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function deletUser(id) {
            if (confirm("确定要删除该数据吗？")) {
            location.href=" ${pageContext.request.contextPath}/deletUserServlet?id="+id;
        }

        }
        window.onload = function () {

                document.getElementById("delSeleted").onclick = function(){
                    if (confirm("确定要删除该数据吗？")) {
                        var flag = false;
                        var cbs = document.getElementsByName("uid");
                        for (var i = 0; i <cbs.length ; i++) {
                            if (cbs[i].checked){
                                flag = true;
                                break;
                            }
                        }
                        if (!flag){
                            alert("未选中删除用户，请重新操作！");
                        }
                        if (flag){
                            document.getElementById("form").submit();
                        }

                    }
                }
                //获取第一个cb
            document.getElementById("firstCb").onclick = function () {
                    var cbs = document.getElementsByName("uid");
                    //遍历
                for (var i = 0; i <cbs.length ; i++) {
                        cbs[i].checked = this.checked;
                }
            }


        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float: left">
        <form class="form-inline"action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" name="name"  value="${condition.name[0]}" class="form-control" id="exampleInputName2" >
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">籍贯</label>
                <input type="text" name="address" value="${condition.address[0]}" class="form-control" id="exampleInputAddress2" >
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="email" name="text" value="${condition.email[0]}" class="form-control" id="exampleInputEmail2">
                <button type="submit" class="btn btn-default">查询</button>
            </div>
        </form>
    </div>
    <div style="float: right;margin: 6px">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSeleted">删除联系人</a>
    </div>
    <form action="${pageContext.request.contextPath}/delSelectedServlet" method="post" id="form">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="firstCb"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pb.list}" var="data" varStatus="s">
        <tr>
            <td><input type="checkbox" name="uid" value="${data.id}"></td>
            <td>${s.count}</td>
            <td>${data.name}</td>
            <td>${data.gender}</td>
            <td>${data.age}</td>
            <td>${data.address}</td>
            <td>${data.qq}</td>
            <td>${data.email}</td>
            <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${data.id}">修改</a>&nbsp;
                <a class="btn btn-default btn-sm" href="javascript:deletUser(${data.id});">删除</a>
            </td>
        </tr>
        </c:forEach>
<%--        <tr>--%>
<%--            <td>2</td>--%>
<%--            <td>张三</td>--%>
<%--            <td>男</td>--%>
<%--            <td>20</td>--%>
<%--            <td>广东</td>--%>
<%--            <td>44444222</td>--%>
<%--            <td>zs@qq.com</td>--%>
<%--            <td><a class="btn btn-default btn-sm" href="update.html">修改</a>&nbsp;<a class="btn btn-default btn-sm" href="">删除</a></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>3</td>--%>
<%--            <td>张三</td>--%>
<%--            <td>男</td>--%>
<%--            <td>20</td>--%>
<%--            <td>广东</td>--%>
<%--            <td>44444222</td>--%>
<%--            <td>zs@qq.com</td>--%>
<%--            <td><a class="btn btn-default btn-sm" href="update.html">修改</a>&nbsp;<a class="btn btn-default btn-sm" href="">删除</a></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>4</td>--%>
<%--            <td>张三</td>--%>
<%--            <td>男</td>--%>
<%--            <td>20</td>--%>
<%--            <td>广东</td>--%>
<%--            <td>44444222</td>--%>
<%--            <td>zs@qq.com</td>--%>
<%--            <td><a class="btn btn-default btn-sm" href="update.html">修改</a>&nbsp;<a class="btn btn-default btn-sm" href="">删除</a></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>5</td>--%>
<%--            <td>张三</td>--%>
<%--            <td>男</td>--%>
<%--            <td>20</td>--%>
<%--            <td>广东</td>--%>
<%--            <td>44444222</td>--%>
<%--            <td>zs@qq.com</td>--%>
<%--            <td><a class="btn btn-default btn-sm" href="update.html">修改</a>&nbsp;<a class="btn btn-default btn-sm" href="">删除</a></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td colspan="8" align="center"><a class="btn btn-primary" href="add.html">添加联系人</a></td>--%>
<%--        </tr>--%>
    </table>
    </form>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${pb.currentPage == 1}">
                <li class="disabled">
                    </c:if>
              <c:if test="${pb.currentPage != 1}">
                <li>
                    </c:if>
                    <a  href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage-1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>



          <c:forEach begin="1" end="${pb.totalPage}" var="i">

             <c:if test="${pb.currentPage == i}">
                 <li class="active"><a  href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
             </c:if>
              <c:if test="${pb.currentPage != i}">
                  <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
              </c:if>
          </c:forEach>

               <c:if test="${pb.currentPage>=pb.totalPage}">
                <li class="disabled">
               </c:if>
<%--                <c:if test="${pb.currentPage}<${pb.totalPage}">--%>
                    <c:if test="${pb.currentPage<pb.totalPage}">
                <li>
                    </c:if>
<%--                <li>--%>
                <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${(pb.currentPage>=pb.totalPage)?pb.currentPage:pb.currentPage+1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <span style="font-size: 25px;margin-left: 6px">共${pb.totalCount}条记录，${pb.totalPage}页</span>
        </ul>
    </nav>
</div>
</body>
</html>

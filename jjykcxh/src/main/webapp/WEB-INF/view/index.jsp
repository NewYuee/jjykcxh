<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="./static/js/jquery-1.8.2.min.js"></script>
</head>
<body>
<table>
    <tr>
        <td>id</td>
        <td>name</td>
        <td>password</td>
    </tr>
</table>
</body>
<script>
    $(function () {
        displayUsers();
    });

    function displayUsers() {
        $.ajax({
            url:"/jjykcxh/user/list",
            type:"GET",
            success:function (result) {
                var list=result.other.userList;
                $("#con").empty();
                $.each(list,function (index,item) {
                    var uid=$("<td></td>").append(item.id);
                    var uname=$("<td></td>").append(item.name);
                    var upwd=$("<td></td>").append(item.password);
                    $("<tr></tr>").append(uid)
                        .append(uname)
                        .append(upwd)
                        .appendTo("table");
                });
            }
        });

    }
</script>
</html>

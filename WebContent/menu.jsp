<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:if test="${empty user}">
  <c:redirect url="/index.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
<p>${fn:escapeXml(user.name)}さん、こんにちは</p>

<p><a href="select.jsp">検索</a></p>
<p><a href="insert.jsp">登録</a></p>
<p><a href="update.jsp">更新</a></p>
<p><a href="delete.jsp">削除</a></p>
<form action="logout" method="post">
  <input type="submit" value="ログアウト">
</form>
</body>
</html>

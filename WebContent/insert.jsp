<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
<p>登録情報を入力してください<br>
<span class="required"></span>は必須です</p>

<c:if test="${not empty errmsg}">
  <p class="error">${fn:escapeXml(errmsg)}</p>
</c:if>

<form action="insert" method="post">
  <fieldset class="label-60">
    <div>
      <label class="required">名前</label><input type="text" name="name" value="${fn:escapeXml(registerUser.name)}">
    </div>
    <div>
      <label class="required">TEL</label><input type="text" name="tel" value="${fn:escapeXml(registerUser.telephone)}">
    </div>
    <div>
      <label class="required">PASS</label><input type="password" name="pass" value="${fn:escapeXml(registerUser.password)}">
    </div>
  </fieldset>
  <input type="submit" value="確認">
</form>
<div>
  <a href="menu.jsp">メニューに戻る</a>
</div>
</body>
</html>

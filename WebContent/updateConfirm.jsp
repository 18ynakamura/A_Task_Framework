<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新内容確認画面</title>
<link href="css/commons.css" rel="stylesheet">
<style>
.col {
  float: left;
}

.col-clear {
  clear: both;
}

#arrow {
  margin-top: 60px;
}
</style>
</head>
<body>
<p>これでよろしいですか？</p>

<c:if test="${not empty errmsg}">
  <p class="error">${fn:escapeXml(errmsg)}</p>
</c:if>

<form action="updateConfirm" method="post">
  <fieldset>
    <div>
      <label>ID</label><input type="text" name="id" value="${fn:escapeXml(beforeUser.id)}" readonly>
    </div>
  </fieldset>

  <fieldset class="col">
    <legend>変更前</legend>
    <div>
      <label>名前</label><input type="text" value="${fn:escapeXml(beforeUser.name)}" disabled>
    </div>
    <div>
      <label>TEL</label><input type="text" value="${fn:escapeXml(beforeUser.telephone)}" disabled>
    </div>
    <div>
      <label>PASS</label><input type="password" value="${fn:escapeXml(beforeUser.password)}" disabled>
    </div>
  </fieldset>

  <div id="arrow" class="col">⇒</div>

  <fieldset class="col label-110">
    <legend>変更後</legend>
    <div>
      <label>名前</label><input type="text" name="newName" value="${fn:escapeXml(afterUser.name)}" readonly>
    </div>
    <div>
      <label>TEL</label><input type="text" name="newTel" value="${fn:escapeXml(afterUser.telephone)}" readonly>
    </div>
    <div>
      <label>PASS(再入力)</label><input type="password" name="rePass" value="${fn:escapeXml(rePass)}">
    </div>
  </fieldset>

  <div class="col-clear">
    <input type="submit" name="button" value="更新">
    <input type="submit" name="button" value="戻る" onclick="location.href='updateInput.jsp'; return false;">
  </div>
</form>
<div>
  <a href="menu.jsp">メニューに戻る</a>
</div>
</body>
</html>

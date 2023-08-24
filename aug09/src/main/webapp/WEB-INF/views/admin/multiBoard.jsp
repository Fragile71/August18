<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin || main</title>
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link rel="stylesheet" href="../css/admin.css">
<link rel="stylesheet" href="../css/multiboard.css">
</head>
<body>
	<%@ include file="menu.jsp"%>

	<div class="main">
		<div class="article">
			<h1>메인영역</h1>

			<c:choose>
				<c:when test="${fn:length(list) gt 0}">

					<div class="div-table">


						<div class="div-row">
							<div class="div-cell">번호</div>
							<div class="div-cell">카테고리</div>
							<div class="div-cell">이름</div>
							<div class="div-cell">url</div>
							<div class="div-cell">참고</div>
						</div>



						<c:forEach items="${list }" var="row">
							<div class="div-row">
								<div class="div-cell">${row.b_no}</div>
								<div class="div-cell">${row.mb_cate}</div>
								<div class="div-cell">${row.b_catename}</div>
								<div class="div-cell">${row.b_url}</div>
								<div class="div-cell">${row.b_comment}</div>
							</div>

						</c:forEach>
					</div>

				</c:when>
				<c:otherwise>
					<h1>개시판에 글이 없습니다.</h1>
				</c:otherwise>
			</c:choose>

		</div>
	</div>

</body>
</html>
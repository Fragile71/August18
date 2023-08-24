<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>❤ Multiboard</title>
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<script src="./js/jquery-3.7.0.min.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	$(function() {

	});
</script>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<!-- Masthead-->
	<header class="masthead">
		<div class="container">
<c:forEach items="${boardList }" var="l">
<a href="${l.b_url }">${l.b_catename }</a>

</c:forEach>


			<h1>multiboard</h1>
	
			<c:choose>
				<c:when test="${fn:length(list) gt 0}">

					<table class="table table-dark table-hover table-striped">

						<thead>
							<tr class="row">
								<th class="col-1">번호</th>
								<th class="col-6">제목</th>
								<th class="col-2">글쓴이</th>
								<th class="col-2">날짜</th>
								<th class="col-1">읽음</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${list }" var="row">

								<tr class="row detail" onclick="location.href='./mbdetail?board=${param.board}&mbno=${row.mb_no}'" >
									<td class="col-1">${row.rowNum}</td>
									<td class="col-6 title">${row.mb_title}</td>
									<td class="col-2">${row.m_name}</td>
									<td class="col-2">${row.mb_date}</td>
									<td class="col-1">${row.mb_read}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<h1>개시판에 글이 없습니다.</h1>
				</c:otherwise>
			</c:choose>






			<button type="button" class="btn btn-secondary"
				onclick="location.href='./mbwrite?board=${param.board}'">글쓰기</button>
		</div>
	</header>


	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
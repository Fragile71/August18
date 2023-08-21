<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin || 공지사항</title>
<!-- Core theme CSS (includes Bootstrap)-->
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link rel="stylesheet" href="../css/admin.css">
<script src="./js/jquery-3.7.0.min.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	$(function() {

	});
</script>
<style type="text/css">
.notice-write-form{
	width: 95%;
	height: auto;
	margin : 10px;
	padding: 20px;
	box-sizing: border-box;
}
.notice-write-form input{
	height: 30px;
	width: 100%;	
}
.notice-write-form textarea {
	width: 100%;
	height: 300px;
	margin: 5px 0px;
}
.notice-write-form button {
	width: 100px;
	height: 50px;
}
</style>
</head>
<body>
	<div class="container">
<div class="menu">
			<div class="menu-item" onclick="url('multiBoard')"><i class="xi-layout xi-2x"></i>게시판 관리</div>
			<div class="menu-item" onclick="url('post')"><i class="xi-paper-o xi-2x"></i>게시글 관리</div>
			<div class="menu-item" onclick="url('member')"><i class="xi-user-o xi-2x"></i>회원 관리</div>
			<div class="menu-item" onclick="url('comment')"><i class="xi-comment-o xi-2x"></i>댓글 관리</div>
			<div class="menu-item" onclick="url('message')"><i class="xi-message-o xi-2x"></i>메시지 관리</div>
			<div class="menu-item" onclick="url('mail')"><i class="xi-document xi-2x"></i>메일 보내기</div>
			<div class="menu-item" onclick="url('notice')"><i class="xi-bell-o xi-2x"></i>공지사항</div>
			<div class="menu-item" onclick="url('logout')"><i class="xi-flag-o xi-2x"></i>로그아웃</div>
		</div>
	
		<script>
			function url(url) {
				location.href = "./" + url;
			}
		</script>
		<div class="main">
			<div class="article">
				<h1>공지사항</h1>
				<!-- 리스트가 없다면 해더도 안 나오게 해주세요  choose문 -->
				<table border="1">
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>게시일</td>
						<td>글쓴이</td>
						<td>삭제여부</td>
						<td>파일유무</td>
					</tr>
					<c:forEach items="${list}" var="row">
						<tr>
							<td>${row.nno }</td>
							<td>${row.ntitle}</td>
							<td>${row.ndate }</td>
							<td>${row.m_no }</td>

							<td><c:choose>
									<c:when test="${row.ndel eq 1}">
										<i class="xi-eye"></i>
									</c:when>
									<c:otherwise>
										<i class="xi-eye-off"></i>
									</c:otherwise>
								</c:choose></td>

							<td><c:if test="${row.norifile ne null}">
									<i class="xi-file-add"></i>
								</c:if></td>

						</tr>
					</c:forEach>

				</table>
				<div class="notice-write-form">
					<form action="./noticeWrite" method="post" enctype="multipart/form-data">
						<input type="text" name="title">
						<textarea name="content"></textarea>
						<input type="file" name="upFile">
						<button type="submit">글쓰기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
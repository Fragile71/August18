<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin || main</title>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link rel="stylesheet" href="../css/admin.css">
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
		<script>function url(url){location.href="./"+url;}</script>
		<div class="main">
			<div class="article">			
				<h1>메인영역</h1>
				<div class="box" style="background-color: red;" onclick="url('multiBoard')">
					게시판 관리
					<div id="comment">게시판을 관리합니다</div>
				</div>
				
				<div class="box" style="background-color: yellow;" onclick="url('post')">
					게시글 관리
					<div id="comment">게시글을 관리합니다</div>
				</div>
				
				<div class="box" style="background-color: green;" onclick="url('member')">
					회원 관리
					<div id="comment">회원을 관리합니다</div>
				</div>
				
				<div class="box" style="background-color: blue;" onclick="url('comment')">
					댓글 관리
					<div id="comment">댓글을 관리합니다</div>
				</div>
				
				<div class="box" style="background-color: purple;" onclick="url('mail')">
					메일 보내기
					<div id="comment">메일 보내기</div>
				</div>
				
				<div class="box" style="background-color: orange;" onclick="url('notice')">
					공지사항
					<div id="comment">공지를 쓰고 관리합니다</div>
				</div>
				
				
			</div>
		</div>
	</div>
</body>
</html>
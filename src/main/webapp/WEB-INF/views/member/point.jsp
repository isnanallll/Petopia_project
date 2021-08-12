<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!Doctype html>
<html>

<head>
<meta charset="UTF-8" />
<title>포인트 | Petopia</title>
<!-- CSS here -->
<link rel="stylesheet" href="/petopia/css/menu_test.css">
<link rel="stylesheet" href="/petopia/css/my_donation.css">
<!-- default_css -->
<%@include file="../include/default_css.jsp"%>
</head>

<body>
	<header> </header>
	<%@include file="../include/default_header.jsp"%>
	<%@include file="../include/mypage_sidebar.jsp"%>
	<main>
		<div class="donation-main">
			<div class="content">
				<h2>포인트</h2>
			</div>
			
			<div class="content">
				<div class="my-box">
					<div class="donation-info">
						<div>
							<h4>보유 포인트</h4>
						</div>
						<div>
							<h1>&nbsp 6</h1>
						</div>
						<div>
							<h5>&nbsp Point</h5>
						</div>
					</div>
				</div>
			</div>
			
			<div class="table">
				<div class="table-box">

					<table class="donation-table">
						<thead>
							<tr>
								<th>포인트 내역 &nbsp</th>
								<th class="info">*1년 이내의 내역만 조회 가능합니다.</th>
								<th>&nbsp &nbsp &nbsp</th>
								<th>사용 내역</th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<td>2021.06.02</td>
								<td>&nbsp &nbsp &nbsp</td>
								<td></td>
								<td>1000 point</td>
							</tr>
							<tr>
								<td>2021.06.02</td>
								<td>&nbsp &nbsp &nbsp</td>
								<td></td>
								<td>1000 point</td>
							</tr>
						</tbody>
					</table>

				</div>
			</div>

			


			<div class="content"></div>

		</div>
	</main>

	<footer>
		<%@include file="../include/default_footer.jsp"%>
	</footer>

	<script>
		
	<%@include file="../include/default_sidebar_js.jsp"%>
		
	</script>
</body>

</html>
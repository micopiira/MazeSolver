<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--@elvariable id="Vector2" type="me.micopiira.mazesolver.math.Vector2"--%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
	      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Document</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
	      integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
	<style>
		.grid {
			border: 1px solid black;
			width: 80vw;
			height: 80vw;
			max-height: 80vh;
			max-width: 80vh;
		}

		.grid__cell {
			height: 100%;
		}

		.grid__cell a {
			display: block;
			height: 100%;
		}

		.grid__cell--WALL {
			background-image: url(/bricks.jpg);
			background-size: contain;
		}

		.grid__cell--START {
			background-color: green;
		}

		.grid__cell--GOAL {
			background-color: red !important;
		}

		.grid__cell--onpath {
			background-color: cyan;
		}
	</style>
</head>
<body>
<div class="d-flex flex-column align-items-start" style="height: 100vh">
	<div class="col">
		<table class="grid mx-auto" style="table-layout: fixed;">
			<c:forEach var="y" begin="0" end="${size - 1}">
				<tr>
					<c:forEach var="x" begin="0" end="${size - 1}">
						<td>
							<spring:eval expression="T(me.micopiira.mazesolver.math.Vector2).of(x, y)" var="coord"/>
							<c:set var="mazepoint" value="${maze.get(coord).get()}"/>
							<div class="grid__cell grid__cell--${mazepoint.toString()}<c:if test="${solvedPath.contains(coord)}"> grid__cell--onpath</c:if>">
								<a href="${pageContext.request.contextPath}/toggle?x=${x}&y=${y}"></a>
							</div>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<form action="${pageContext.request.contextPath}/setsize" method="post">
			<input type="number" name="size" value="${size}" class="form-control">
			<input value="Set size" type="submit" class="btn btn-default">
		</form>
		<a href="${pageContext.request.contextPath}/solve" class="btn btn-primary">Solve</a>
	</div>
</div>
</body>
</html>


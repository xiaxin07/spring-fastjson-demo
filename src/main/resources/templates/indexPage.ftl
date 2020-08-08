<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/addOrderPage" method="post">
		<input type="hidden" name="token" value="${token}"> <span>用户名</span><input
			type="text" name="name"><br> <span>年龄</span><input
			type="text" name="age"><br> <input type="submit">
	</form>
</body>
</html>
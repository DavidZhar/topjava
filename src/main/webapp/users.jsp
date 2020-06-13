<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Users</h2>
<form method="post" action="users">
    <label for="users">Choose a user:</label>
    <select name="user" id="users">
        <option value="user1">User 1</option>
        <option value="user2">User 2</option>
    </select>
    <br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
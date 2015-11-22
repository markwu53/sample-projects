<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Tables</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>
<body>
    <h1>Tables</h1>
    <table class="table table-striped table-bordered table-hover">
        <tbody>
            <c:forEach items="${tables }" var="table2">
                <c:set var="schema" scope="request" value="${table2.get(0) }"/>
                <c:set var="table" scope="request" value="${table2.get(1) }"/>
                <c:set var="qualified_table" scope="request" value="${schema }${empty schema? '':'.' }${table }"/>
                <tr>
                    <td><a href="schema/${empty schema? 'null':schema }/table/${table }">${qualified_table }</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

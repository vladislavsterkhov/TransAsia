
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>JSP List Users Records</title>
</head>
<body>

<%--<c:out value="${sessionScope.pn}" />--%>

<%--<sql:setDataSource--%>
<%--var="myDS"--%>
<%--driver="org.postgresql.Driver"--%>
<%--url="jdbc:postgresql://localhost:5432/postgres"--%>
<%--user="postgres" password="toor"--%>
<%--/>--%>

<%--<sql:query var="listUsers"   dataSource="${myDS}">--%>
<%--SELECT * FROM users;--%>
<%--</sql:query>--%>


<script type="application/javascript">
    function filtering() {

        console.log(
            sessionStorage.getItem("sortBy")
        )


        document.filterForm.submit();

    }
</script>



<div align="center">
  <form action="/hello" method="post" id="filterForm" name="filterForm">
    <table >
      <th colspan="3">Filter</th>
      <tr>
        <td>PN</td>
        <td colspan="2">
          <input type="text" name="pn" id="pn" value="${sessionScope.pn}">
        </td>
      </tr>


      <tr>
        <td>Part Name</td>
        <td colspan="2">
          <input type="text" name="partName" id="partName" value="${sessionScope.partName}" >
        </td>
      </tr>



      <tr>
        <td>Vendor</td>
        <td colspan="2">
          <input type="text" name="vendor" id="vendor" value="${sessionScope.vendor}" >
        </td>
      </tr>



      <tr>
        <td>Qty</td>
        <td colspan="2">
          <input type="text" name="qty" id="qty" value="${sessionScope.qty}" >
        </td>
      </tr>



      <tr>
        <td>Shipped</td>
        <td>
          after <input type="text" name="afterShipped" id="afterShipped" value="${sessionScope.afterShipped}" >
        </td>
        <td>
          before <input type="text" name="beforeShipped" id="beforeShipped" value="${sessionScope.beforeShipped}" >
        </td>
      </tr>


      <tr>
        <td>Received</td>
        <td>
          after <input type="text" name="afterReceived" id="afterReceived" value="${sessionScope.afterReceived}" >
        </td>
        <td>
          before <input type="text" name="beforeReceived" id="beforeReceived" value="${sessionScope.beforeReceived}" >
        </td>
      </tr>

    </table>
  </form>
</div>




<div align="center">
  <button onclick="filtering()" id="filtering">Filter</button>
</div>



<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script type="application/javascript">

    function sort(name) {

        var userObj = {
            "name": name
        }


        $.ajax({
            type: "post",
            url: "hello", //this is my servlet
            data: userObj,
            success: function(msg){
                $('body').empty().append(msg);
            }
        });

    }



</script>


<div align="center" >
  <table border="1" cellpadding="5">


    <form name="submitForm" method="POST" action="/hello">
      <th><a href="javascript:sort('partNumber ASC')">PN</a></th>
      <th><a href="javascript:sort('partName ASC')">Part Name</a></th>
      <th><a href="javascript:sort('vendor ASC')">Vendor</a></th>
      <th><a href="javascript:sort('qty ASC')">Qty</a></th>
      <th><a href="javascript:sort('shipped ASC')">Shipped</a></th>
      <th><a href="javascript:sort('receive ASC')">Received</a></th>
    </form>




    <c:forEach var="entity" items="${requestScope.listEntity}">
      <tr >
        <td><c:out value="${entity.partNumber}" /></td>
        <td><c:out value="${entity.partName}" /></td>
        <td><c:out value="${entity.vendor}" /></td>
        <td><c:out value="${entity.qty}" /></td>
        <td><c:out value="${entity.shipped}" /></td>
        <td><c:out value="${entity.receive}" /></td>
      </tr>
    </c:forEach>


  </table>
</div>


</body>
</html>
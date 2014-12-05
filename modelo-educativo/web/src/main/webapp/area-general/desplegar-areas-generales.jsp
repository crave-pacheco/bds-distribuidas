<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <h2>Desplegar area generales</h2>
        <table>
            <thead>
                <tr>
                    <td>Id</td>
                    <td>Nombre</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${areasGenerales}" var="areaGeneral">
                    <tr>
                        <td>${areaGeneral.id}</td>
                        <td>${areaGeneral.nombre}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>

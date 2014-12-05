<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <h2>Desplegar programas</h2>
        <table>
            <thead>
                <tr>
                    <td>Id</td>
                    <td>Nombre</td>
                    <td>Area general id</td>
                    <td>Modalidad</td>
                    <td>Duracion horas</td>
                    <td>Descripcion</td>
                    <td>Beneficios</td>
                    <td>Nombre campus</td>
                    <td>Horario</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${programas}" var="programa">
                    <tr>
                        <td>${programa.id}</td>
                        <td>${programa.nombre}</td>
                        <td>${programa.areaGeneralId}</td>
                        <td>${programa.modalidad}</td>
                        <td>${programa.duracionHoras}</td>
                        <td>${programa.descripcion}</td>
                        <td>${programa.beneficios}</td>
                        <td>${programa.nombreCampus}</td>
                        <td>${programa.horario}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <h2>Crear programa</h2>
        <form action="controller" method="post">
            <label>Nombre</label><input name="nombrePrograma" type="text"/><br/>
            <label>Modalidad</label><input name="modalidad" type="text"/><br/>
            <label>Duracion horas</label><input name="duracionHoras" type="number"/><br/>
            <label>Descripcion</label><input name="descripcion" type="text"/><br/>
            <label>Beneficios</label><input name="beneficios" type="text"/><br/>
            <label>Horario</label><input name="horario" type="text"/><br/>
            <label>Nombre de campus</label><input name="nombreCampus" type="text"/><br/>
            <label>Area general</label>
            <select name="areaGeneralId">
                <c:forEach items="${areasGenerales}" var="areaGeneral">
                    <option value="${areaGeneral.id}">${areaGeneral.nombre}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="action" value="CrearPrograma"/>
            <input type="submit" value="Crear"/>
        </form>
    </body>
</html>

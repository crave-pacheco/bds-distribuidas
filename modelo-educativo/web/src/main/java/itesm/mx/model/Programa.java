package itesm.mx.model;

import java.util.UUID;

public class Programa {

    private String _nombre;
    private UUID _id;
    private UUID _areaGeneralId;
    private String _modalidad;
    private int _duracionHoras;
    private String _descripcion;
    private String _beneficios;
    private String _nombreCampus;
    private String _horario;

    public UUID getAreaGeneralId() {
        return _areaGeneralId;
    }

    public void setAreaGeneralId(UUID areaGeneralId) {
        _areaGeneralId = areaGeneralId;
    }

    public String getModalidad() {
        return _modalidad;
    }

    public void setModalidad(String modalidad) {
        _modalidad = modalidad;
    }

    public int getDuracionHoras() {
        return _duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        _duracionHoras = duracionHoras;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String descripcion) {
        _descripcion = descripcion;
    }

    public String getBeneficios() {
        return _beneficios;
    }

    public void setBeneficios(String beneficios) {
        _beneficios = beneficios;
    }

    public String getNombreCampus() {
        return _nombreCampus;
    }

    public void setNombreCampus(String nombreCampus) {
        _nombreCampus = nombreCampus;
    }

    public String getHorario() {
        return _horario;
    }

    public void setHorario(String horario) {
        _horario = horario;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String name) {
        this._nombre = name;
    }

    public UUID getId() {
        return _id;
    }

    public void setId(UUID id) {
        _id = id;
    }
}

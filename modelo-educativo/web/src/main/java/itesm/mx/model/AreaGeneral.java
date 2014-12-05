package itesm.mx.model;

import java.util.UUID;

public class AreaGeneral {

    private String _nombre;
    private UUID _id;

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

CREATE TABLE programas.programa (
  id uuid PRIMARY KEY,
  area_general_id uuid,
  nombre text,
  modalidad text,
  duracion_horas int,
  descripcion text,
  beneficios text,
  campus_a_informacion_campus map<text, frozen<informacion_campus>>
);
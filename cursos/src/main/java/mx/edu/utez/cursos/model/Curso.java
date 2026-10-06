package mx.edu.utez.cursos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String instructor;
    private Integer duracionHoras;
    private Double precio;

    public Curso() {}

    public Curso(String nombre, String instructor, Integer duracionHoras, Double precio) {
        this.nombre = nombre;
        this.instructor = instructor;
        this.duracionHoras = duracionHoras;
        this.precio = precio;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public Integer getDuracionHoras() { return duracionHoras; }
    public void setDuracionHoras(Integer duracionHoras) { this.duracionHoras = duracionHoras; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
}
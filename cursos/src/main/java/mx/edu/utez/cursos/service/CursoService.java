package mx.edu.utez.cursos.service;

import mx.edu.utez.cursos.model.Curso;
import mx.edu.utez.cursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> obtenerTodos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> obtenerPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso actualizar(Long id, Curso cursoDetalles) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));

        curso.setNombre(cursoDetalles.getNombre());
        curso.setInstructor(cursoDetalles.getInstructor());
        curso.setDuracionHoras(cursoDetalles.getDuracionHoras());
        curso.setPrecio(cursoDetalles.getPrecio());

        return cursoRepository.save(curso);
    }

    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }
}
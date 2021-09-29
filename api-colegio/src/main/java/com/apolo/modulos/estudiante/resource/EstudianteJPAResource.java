package com.apolo.modulos.estudiante.resource;

import com.apolo.dao.DeleteResponse;
import com.apolo.modulos.estudiante.dao.FiltroEstudianteRequest;
import com.apolo.modulos.estudiante.dao.MatriculaEstudianteRequest;
import com.apolo.modulos.estudiante.model.Estudiante;
import com.apolo.modulos.estudiante.model.Matricula;
import com.apolo.modulos.estudiante.service.IEstudianteService;
import com.apolo.modulos.estudiante.service.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class EstudianteJPAResource {

    private final IEstudianteService iEstudianteService;

    private final IMatriculaService iMatriculaService;

    @Autowired
    public EstudianteJPAResource(IEstudianteService iEstudianteService, IMatriculaService iMatriculaService) {
        this.iEstudianteService = iEstudianteService;
        this.iMatriculaService = iMatriculaService;
    }

    //estudiantes por filtro
    // GET  /estudiantes/
    @GetMapping("/estudiantes")
    public List<Estudiante> getByFilter(@RequestBody FiltroEstudianteRequest filtroEstudianteRequest) {
        return iEstudianteService.obtenerEstudiantesPorFiltro(filtroEstudianteRequest);
    }

    //Trae un estudiante por id
    // GET  /estudiantes/id
    @GetMapping("/estudiantes/{id}")
    public EntityModel<Estudiante> getById(@PathVariable Long id) {

        Optional<Estudiante> estudiante = iEstudianteService.findById(id);

        return EntityModel.of(estudiante.get());
    }

    //Eliminar estudiante por id
    @DeleteMapping("/estudiantes/{id}")
    public ResponseEntity<?> deleteEstudiante(@PathVariable Long id) {

        EntityModel<?> resource = EntityModel.of(new DeleteResponse(id));

        iEstudianteService.deleteById(id);


        return ResponseEntity.ok(resource);
    }

    //Matricular a un estudiante
    @PostMapping("/estudiantes/{id}/matriculas")
    public ResponseEntity<Matricula> createMatriculaEstudiante(@Valid  @RequestBody MatriculaEstudianteRequest matriculaEstudianteRequest, @PathVariable Long id){

        Matricula matricula = iMatriculaService.crearMatriculaEstudiante(matriculaEstudianteRequest);
        return ResponseEntity.ok(matricula);
    }

}

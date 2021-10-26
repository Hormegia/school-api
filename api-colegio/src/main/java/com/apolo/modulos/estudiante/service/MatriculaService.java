package com.apolo.modulos.estudiante.service;

import com.apolo.modulos.acudiente.model.Acudiente;
import com.apolo.modulos.acudiente.model.Acudiente_;
import com.apolo.modulos.acudiente.service.AcudienteService;
import com.apolo.modulos.estudiante.dao.MatriculaEstudianteRequest;
import com.apolo.modulos.estudiante.model.*;
import com.apolo.modulos.estudiante.repository.*;
import com.apolo.modulos.grados.model.Grado;
import com.apolo.modulos.grados.repository.GradoRepository;
import com.apolo.modulos.periodo.academico.model.PeriodoAcademico;
import com.apolo.modulos.periodo.academico.repository.PeriodoAcademicoRepository;
import com.apolo.modulos.usuarios.model.Usuario;
import com.apolo.modulos.usuarios.repository.UsuarioRepository;
import com.apolo.modulos.usuarios.service.UsuarioService;
import com.apolo.spring.database.GenericSpecification;
import com.apolo.spring.database.SearchCriteria;
import com.apolo.spring.database.SearchOperation;
import com.apolo.spring.exception.ObjetoNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MatriculaService implements IMatriculaService{


    private final DatosResponsableRepository datosResponsableRepository;

    private final InformacionAdicionalRepository informacionAdicionalRepository;

    private final InformacionEducativaRepository informacionEducativaRepository;

    private final MatriculaRepository matriculaRepository;

    private final GradoRepository gradoRepository;

    private final EstudianteRepository estudianteRepository;

    private final PeriodoAcademicoRepository periodoAcademicoRepository;

    private final UsuarioService usuarioService;

    private final AcudienteService acudienteService;


    @Autowired
    public MatriculaService(DatosResponsableRepository datosResponsableRepository, InformacionAdicionalRepository informacionAdicionalRepository, InformacionEducativaRepository informacionEducativaRepository, MatriculaRepository matriculaRepository, GradoRepository gradoRepository, EstudianteRepository estudianteRepository, PeriodoAcademicoRepository periodoAcademicoRepository, UsuarioService usuarioService, AcudienteService acudienteService) {
        this.datosResponsableRepository = datosResponsableRepository;
        this.informacionAdicionalRepository = informacionAdicionalRepository;
        this.informacionEducativaRepository = informacionEducativaRepository;
        this.matriculaRepository = matriculaRepository;
        this.gradoRepository = gradoRepository;
        this.estudianteRepository = estudianteRepository;
        this.periodoAcademicoRepository = periodoAcademicoRepository;
        this.usuarioService = usuarioService;
        this.acudienteService = acudienteService;
    }


    @Override
    public Matricula crearMatriculaEstudiante(MatriculaEstudianteRequest matriculaEstudianteRequest) {

        Matricula matricula = matriculaEstudianteRequest.getMatricula();

        Grado grado = gradoRepository.findById(matricula.getGrado().getId()).get();

        PeriodoAcademico periodoAcademico = periodoAcademicoRepository.findById(matricula.getPeriodoAcademico().getId()).get();

        Estudiante estudiante = estudianteRepository.findById(matricula.getEstudiante().getId()).get();

        DatosResponsable padre = matriculaEstudianteRequest.getDatosPadre();

        DatosResponsable madre = matriculaEstudianteRequest.getDatosMadre();

        DatosResponsable acudiente = matriculaEstudianteRequest.getDatosAcudiente();

        InformacionAdicional informacionAdicional = matriculaEstudianteRequest.getInformacionAdicional();

        InformacionEducativa [] informacionEducativa = matriculaEstudianteRequest.getInformacionEducativa();

        madre.setEsPadre(false);
        madre.setEsAcudiente(false);

        padre.setEsPadre(true);
        padre.setEsAcudiente(false);

        acudiente.setEsAcudiente(true);

        matricula.setEstudiante(estudiante);
        matricula.setGrado(grado);
        matricula.setPeriodoAcademico(periodoAcademico);

        Matricula matricula1 = matriculaRepository.save(matricula);

        informacionAdicional.setMatricula(matricula1);


        padre.setMatricula(matricula1);

        madre.setMatricula(matricula1);

        acudiente.setMatricula(matricula1);



        datosResponsableRepository.save(padre);
        datosResponsableRepository.save(madre);
        datosResponsableRepository.save(acudiente);

        informacionAdicionalRepository.save(informacionAdicional);

        for (InformacionEducativa ie: informacionEducativa) {
            Grado gradoHistorial = gradoRepository.findById(ie.getGrado().getId()).get();
            ie.setMatricula(matricula);
            ie.setGrado(gradoHistorial);
            informacionEducativaRepository.save(ie);
        }



        return matricula1;
    }

    @Override
    public List<Matricula> getAllMatriculasByAcudienteAndPeriodoAcademico(Long idAcudiente, Long idPeriodoAcademico) {

        Optional<Acudiente> acudienteOptional = acudienteService.findById(idAcudiente);
        Optional<PeriodoAcademico> periodoAcademicoOptional = periodoAcademicoRepository.findById(idPeriodoAcademico);

        Acudiente acudiente = acudienteOptional.get();

        if(!periodoAcademicoOptional.isPresent()){
            throw new ObjetoNoEncontradoException("No se encuentra un periodo académico con el id: " + idAcudiente);
        }

        PeriodoAcademico periodoAcademico = periodoAcademicoOptional.get();

        Usuario usuarioAutenticado = usuarioService.getUsuarioAutenticado();

        if(acudiente.getUsuario().getId() != usuarioAutenticado.getId())
            throw new ObjetoNoEncontradoException("Solo puedes ver las matriculas de los estudiantes que hayas registrado" );

        GenericSpecification<Matricula> genericSpecificationMatricula = new GenericSpecification<>();

        String [] joins  = new String[]{"estudiante", "acudiente"};

        genericSpecificationMatricula.add(new SearchCriteria(joins, SearchOperation.JOIN));
        genericSpecificationMatricula.add(new SearchCriteria("acudiente."+Acudiente_.ID, acudiente.getId(), SearchOperation.EQUAL));
        genericSpecificationMatricula.add(new SearchCriteria(Matricula_.PERIODO_ACADEMICO, periodoAcademico.getId(), SearchOperation.EQUAL));



        return matriculaRepository.findAll(genericSpecificationMatricula);
    }
}

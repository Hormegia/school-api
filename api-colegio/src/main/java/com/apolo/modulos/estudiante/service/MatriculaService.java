package com.apolo.modulos.estudiante.service;

import com.apolo.modulos.estudiante.dao.MatriculaEstudianteRequest;
import com.apolo.modulos.estudiante.model.*;
import com.apolo.modulos.estudiante.repository.*;
import com.apolo.modulos.grados.model.Grado;
import com.apolo.modulos.grados.repository.GradoRepository;
import com.apolo.modulos.periodo.academico.model.PeriodoAcademico;
import com.apolo.modulos.periodo.academico.repository.PeriodoAcademicoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    public MatriculaService(DatosResponsableRepository datosResponsableRepository, InformacionAdicionalRepository informacionAdicionalRepository, InformacionEducativaRepository informacionEducativaRepository, MatriculaRepository matriculaRepository, GradoRepository gradoRepository, EstudianteRepository estudianteRepository, PeriodoAcademicoRepository periodoAcademicoRepository) {
        this.datosResponsableRepository = datosResponsableRepository;
        this.informacionAdicionalRepository = informacionAdicionalRepository;
        this.informacionEducativaRepository = informacionEducativaRepository;
        this.matriculaRepository = matriculaRepository;
        this.gradoRepository = gradoRepository;
        this.estudianteRepository = estudianteRepository;
        this.periodoAcademicoRepository = periodoAcademicoRepository;
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

        padre.setEsPadre(true);

        acudiente.setEsAcudiente(true);

        matricula.setEstudiante(estudiante);
        matricula.setGrado(grado);

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
            ie.setMatricula(matricula);
            informacionEducativaRepository.save(ie);
        }



        return matricula1;
    }
}

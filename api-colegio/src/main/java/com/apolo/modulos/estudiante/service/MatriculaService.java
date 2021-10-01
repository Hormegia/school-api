package com.apolo.modulos.estudiante.service;

import com.apolo.modulos.estudiante.dao.MatriculaEstudianteRequest;
import com.apolo.modulos.estudiante.model.*;
import com.apolo.modulos.estudiante.repository.DatosResponsableRepository;
import com.apolo.modulos.estudiante.repository.InformacionAdicionalRepository;
import com.apolo.modulos.estudiante.repository.InformacionEducativaRepository;
import com.apolo.modulos.estudiante.repository.MatriculaRepository;
import com.apolo.modulos.grados.model.Grado;
import com.apolo.modulos.periodo.academico.model.PeriodoAcademico;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MatriculaService implements IMatriculaService{


    private final DatosResponsableRepository datosResponsableRepository;

    private final InformacionAdicionalRepository informacionAdicionalRepository;

    private final InformacionEducativaRepository informacionEducativaRepository;

    private final MatriculaRepository matriculaRepository;

    public MatriculaService(DatosResponsableRepository datosResponsableRepository, InformacionAdicionalRepository informacionAdicionalRepository, InformacionEducativaRepository informacionEducativaRepository, MatriculaRepository matriculaRepository) {
        this.datosResponsableRepository = datosResponsableRepository;
        this.informacionAdicionalRepository = informacionAdicionalRepository;
        this.informacionEducativaRepository = informacionEducativaRepository;
        this.matriculaRepository = matriculaRepository;
    }


    @Override
    public Matricula crearMatriculaEstudiante(MatriculaEstudianteRequest matriculaEstudianteRequest) {

        Matricula matricula = matriculaEstudianteRequest.getMatricula();

        Grado grado = matricula.getGrado();

        PeriodoAcademico periodoAcademico = matricula.getPeriodoAcademico();

        Estudiante estudiante = matricula.getEstudiante();

        DatosResponsable padre = matriculaEstudianteRequest.getDatosPadre();

        DatosResponsable madre = matriculaEstudianteRequest.getDatosMadre();

        DatosResponsable acudiente = matriculaEstudianteRequest.getDatosAcudiente();

        InformacionAdicional informacionAdicional = matriculaEstudianteRequest.getInformacionAdicional();

        InformacionEducativa [] informacionEducativa = matriculaEstudianteRequest.getInformacionEducativa();

        madre.setEsPadre(false);

        padre.setEsPadre(true);

        acudiente.setEsAcudiente(true);

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

package com.apolo.modulos.estudiante.service;

import com.apolo.modulos.estudiante.dao.MatriculaEstudianteRequest;
import com.apolo.modulos.estudiante.model.DatosResponsable;
import com.apolo.modulos.estudiante.model.InformacionAdicional;
import com.apolo.modulos.estudiante.model.InformacionEducativa;
import com.apolo.modulos.estudiante.model.Matricula;
import com.apolo.modulos.estudiante.repository.DatosResponsableRepository;
import com.apolo.modulos.estudiante.repository.InformacionAdicionalRepository;
import com.apolo.modulos.estudiante.repository.InformacionEducativaRepository;
import com.apolo.modulos.estudiante.repository.MatriculaRepository;
import com.apolo.modulos.grados.model.Grado;
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

        Grado grado = matriculaEstudianteRequest.getGrado();

        DatosResponsable padre = matriculaEstudianteRequest.getDatosPadre();

        DatosResponsable madre = matriculaEstudianteRequest.getDatosMadre();

        DatosResponsable acudiente = matriculaEstudianteRequest.getDatosAcudiente();

        InformacionAdicional informacionAdicional = matriculaEstudianteRequest.getInformacionAdicional();

        InformacionEducativa informacionEducativa = matriculaEstudianteRequest.getInformacionEducativa();

        madre.setEsPadre(false);

        padre.setEsPadre(true);

        acudiente.setEsAcudiente(true);

        Matricula matricula = matriculaEstudianteRequest.getMatricula();

        informacionAdicional.setMatricula(matricula);

        informacionEducativa.setMatricula(matricula);

        padre.setMatricula(matricula);

        madre.setMatricula(matricula);

        acudiente.setMatricula(matricula);

        System.err.println("WWWWWWWWW");
        datosResponsableRepository.save(padre);
        datosResponsableRepository.save(madre);
        datosResponsableRepository.save(acudiente);

        informacionAdicionalRepository.save(informacionAdicional);

        informacionEducativaRepository.save(informacionEducativa);


        return matriculaRepository.save(matricula);
    }
}

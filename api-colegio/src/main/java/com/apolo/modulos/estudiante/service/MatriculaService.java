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
import com.apolo.modulos.periodo.academico.service.PeriodoAcademicoService;
import com.apolo.modulos.usuarios.model.Usuario;
import com.apolo.modulos.usuarios.service.UsuarioService;
import com.apolo.spring.database.GenericSpecification;
import com.apolo.spring.database.SearchCriteria;
import com.apolo.spring.database.SearchOperation;
import com.apolo.spring.exception.ErrorGeneralExcepcion;
import com.apolo.spring.exception.ObjetoNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    private final PeriodoAcademicoService periodoAcademicoService;

    private final UsuarioService usuarioService;

    private final AcudienteService acudienteService;


    @Autowired
    public MatriculaService(DatosResponsableRepository datosResponsableRepository, InformacionAdicionalRepository informacionAdicionalRepository, InformacionEducativaRepository informacionEducativaRepository, MatriculaRepository matriculaRepository, GradoRepository gradoRepository, EstudianteRepository estudianteRepository, PeriodoAcademicoRepository periodoAcademicoRepository, PeriodoAcademicoService periodoAcademicoService, UsuarioService usuarioService, AcudienteService acudienteService) {
        this.datosResponsableRepository = datosResponsableRepository;
        this.informacionAdicionalRepository = informacionAdicionalRepository;
        this.informacionEducativaRepository = informacionEducativaRepository;
        this.matriculaRepository = matriculaRepository;
        this.gradoRepository = gradoRepository;
        this.estudianteRepository = estudianteRepository;
        this.periodoAcademicoRepository = periodoAcademicoRepository;
        this.periodoAcademicoService = periodoAcademicoService;
        this.usuarioService = usuarioService;
        this.acudienteService = acudienteService;
    }


    @Override
    public Matricula crearMatriculaEstudiante(MatriculaEstudianteRequest matriculaEstudianteRequest) {

        Matricula matricula = matriculaEstudianteRequest.getMatricula();

        Grado grado = gradoRepository.findById(matricula.getGrado().getId()).get();

        PeriodoAcademico periodoAcademico = periodoAcademicoRepository.findById(matricula.getPeriodoAcademico().getId()).get();

        Estudiante estudiante = estudianteRepository.findById(matricula.getEstudiante().getId()).get();


        // Se verifica que el estudiante todavía no se haya matriculado en el periodo académico recibido
        GenericSpecification<Matricula> genericSpecificationMatricula = new GenericSpecification<>();

        genericSpecificationMatricula.add(new SearchCriteria(Matricula_.ESTUDIANTE, estudiante.getId(), SearchOperation.EQUAL));
        genericSpecificationMatricula.add(new SearchCriteria(Matricula_.PERIODO_ACADEMICO, periodoAcademico.getId(), SearchOperation.EQUAL));

        List<Matricula> matriculasRegistradas = matriculaRepository.findAll(genericSpecificationMatricula);

        if(!matriculasRegistradas.isEmpty())
            throw new ErrorGeneralExcepcion(String.format("El estudiante ya se encuentra matriculado en el periodo académico %s", periodoAcademico.getNombre()));

        DatosResponsable padre = matriculaEstudianteRequest.getDatosPadre();

        DatosResponsable madre = matriculaEstudianteRequest.getDatosMadre();

        DatosResponsable acudiente = matriculaEstudianteRequest.getDatosAcudiente();

        InformacionAdicional informacionAdicional = matriculaEstudianteRequest.getInformacionAdicional();

        InformacionEducativa [] informacionEducativa = matriculaEstudianteRequest.getInformacionEducativa();

        matricula.setEstudiante(estudiante);
        matricula.setGrado(grado);
        matricula.setPeriodoAcademico(periodoAcademico);

        Matricula matricula1 = matriculaRepository.save(matricula);

        informacionAdicional.setMatricula(matricula1);



        //Se verifica que la información de los padres coincida con la información registrada para el estudiante

        madre.setEsPadre(false);
        madre.setEsAcudiente(false);

        padre.setEsPadre(true);
        padre.setEsAcudiente(false);

        acudiente.setEsAcudiente(true);

        if(madre.getId() == null){

            GenericSpecification<DatosResponsable> genericSpecificationMadre = new GenericSpecification<>();
            genericSpecificationMadre.add(new SearchCriteria(DatosResponsable_.ESTUDIANTE, estudiante.getId(), SearchOperation.EQUAL));
            genericSpecificationMadre.add(new SearchCriteria(DatosResponsable_.ES_PADRE, false, SearchOperation.EQUAL));

            List<DatosResponsable> madreExistente = datosResponsableRepository.findAll(genericSpecificationMadre);

            if(!madreExistente.isEmpty() && !madre.getNombreCompleto().equals(madreExistente.get(0).getNombreCompleto()))
                throw new ErrorGeneralExcepcion("Los datos asociados a  la madre no corresponden con la información registrada en anteriores matriculas");

        }else{
            madre.getEstudiante().add(estudiante);
            datosResponsableRepository.save(madre);

        }

        if(padre.getId() == null){

            GenericSpecification<DatosResponsable> genericSpecificationPadre = new GenericSpecification<>();
            genericSpecificationPadre.add(new SearchCriteria(DatosResponsable_.ESTUDIANTE, estudiante.getId(), SearchOperation.EQUAL));
            genericSpecificationPadre.add(new SearchCriteria(DatosResponsable_.ES_PADRE, true, SearchOperation.EQUAL));

            List<DatosResponsable> padreExistente = datosResponsableRepository.findAll(genericSpecificationPadre);

            if(!padreExistente.isEmpty() && !padre.getNombreCompleto().equals(padreExistente.get(0).getNombreCompleto()))
                throw new ErrorGeneralExcepcion("Los datos asociados al padre no corresponden con la información registrada en anteriores matriculas");

        }else{

            padre.getEstudiante().add(estudiante);
            datosResponsableRepository.save(padre);

        }

        acudiente.setMatricula(matricula1);

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

    public Boolean verificarAntiguedadMatricula (Matricula matricula){

        PeriodoAcademico periodoAcademico = matricula.getPeriodoAcademico();
        Optional<PeriodoAcademico> periodoAnteriorOptional = periodoAcademicoService.obtenerPeriodoAnterior(periodoAcademico);
        PeriodoAcademico periodoAnterior = null;

        if(periodoAnteriorOptional.isPresent())
             periodoAnterior = periodoAnteriorOptional.get();
        else
            return false;

        Matricula matriculaAntiguo = matriculaRepository.findByEstudianteIdAndPeriodoAcademicoId(matricula.getEstudiante().getId(),
                periodoAnterior.getId());

        return matriculaAntiguo != null;

    }
}

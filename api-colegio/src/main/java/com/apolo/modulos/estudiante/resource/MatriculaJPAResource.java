package com.apolo.modulos.estudiante.resource;


import com.apolo.modulos.estudiante.model.DatosResponsable;
import com.apolo.modulos.estudiante.model.Estudiante;
import com.apolo.modulos.estudiante.model.InformacionEducativa;
import com.apolo.modulos.estudiante.model.Matricula;
import com.apolo.modulos.estudiante.repository.DatosResponsableRepository;
import com.apolo.modulos.estudiante.repository.InformacionEducativaRepository;
import com.apolo.modulos.estudiante.repository.MatriculaRepository;
import com.apolo.modulos.estudiante.service.MatriculaService;
import com.apolo.spring.exception.ObjetoNoEncontradoException;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class MatriculaJPAResource {

    private final MatriculaRepository matriculaRepository;
    private final TemplateEngine templateEngine;
    private final MatriculaService matriculaService;
    private final InformacionEducativaRepository informacionEducativaRepository;
    private final DatosResponsableRepository datosResponsableRepository;

    @Autowired
    public MatriculaJPAResource(MatriculaRepository matriculaRepository, TemplateEngine templateEngine, MatriculaService matriculaService, InformacionEducativaRepository informacionEducativaRepository, DatosResponsableRepository datosResponsableRepository) {
        this.matriculaRepository = matriculaRepository;
        this.templateEngine = templateEngine;
        this.matriculaService = matriculaService;
        this.informacionEducativaRepository = informacionEducativaRepository;
        this.datosResponsableRepository = datosResponsableRepository;
    }

    //crear matricula
    // GET  /usuario/
    @GetMapping("/matriculas")
    public List<Matricula> getAll() {
        return matriculaRepository.findAll();
    }

    //buscar las matriculas de todos los estudiantes que tenga un acudiente en un periodo académico
    @GetMapping("/matriculas/acudientes/{idAcudiente}/periodosAcademicos/{idPeriodoAcademico}")
    public List<Matricula> getAllMatriculasByAcudienteAndPeriodoAcademico(@PathVariable Long idAcudiente, @PathVariable Long idPeriodoAcademico){


        return matriculaService.getAllMatriculasByAcudienteAndPeriodoAcademico(idAcudiente, idPeriodoAcademico);

    }

    @GetMapping("/matriculas/{idMatricula}/pdf")
    public String pdf (@PathVariable Long idMatricula, Model model, HttpServletResponse response) throws DocumentException, IOException {

        Optional<Matricula> matriculaOptional = matriculaRepository.findById(idMatricula);

        if(!matriculaOptional.isPresent())
            throw new ObjetoNoEncontradoException("No existe una matricula con el id: " + idMatricula);

        Matricula matricula = matriculaOptional.get();

        Date fecha = new Date();
        Context context = new Context();

        Estudiante estudiante = matricula.getEstudiante();
        List<InformacionEducativa> informacionEducativa = informacionEducativaRepository.getByMatriculaIdOrderByFechaAsc(matricula.getId());
        DatosResponsable acudiente = datosResponsableRepository.findByEstudianteIdAndEsAcudiente(estudiante.getId(), true);
        DatosResponsable madre = datosResponsableRepository.findByEstudianteIdAndEsPadreAndEsAcudienteFalse(estudiante.getId(), false);
        DatosResponsable padre = datosResponsableRepository.findByEstudianteIdAndEsPadreAndEsAcudienteFalse(estudiante.getId(), true);


        context.setVariable("matricula", matricula);
        context.setVariable("periodoAcademico", matricula.getPeriodoAcademico());
        context.setVariable("grado", matricula.getGrado());
        context.setVariable("estudiante", estudiante);
        context.setVariable("informacionEducativa", informacionEducativa);
        context.setVariable("acudiente", acudiente);
        context.setVariable("madre", madre);
        context.setVariable("padre", padre);
        context.setVariable("fecha", new SimpleDateFormat("dd-MM-yyyy").format(fecha));
        context.setVariable("hora", new SimpleDateFormat("hh:mm:ss").format(fecha));
        context.setVariable("year", new SimpleDateFormat("yyyy").format(fecha));
        context.setVariable("antiguo", matriculaService.verificarAntiguedadMatricula(matricula));



        String outputFolder = System.getProperty("user.home") + File.separator + "matricula.pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(templateEngine.process("matricula", context));
        renderer.layout();
        renderer.createPDF(outputStream);

        renderer.finishPDF();
        outputStream.close();

        return templateEngine.process("matricula", context);
    }
}


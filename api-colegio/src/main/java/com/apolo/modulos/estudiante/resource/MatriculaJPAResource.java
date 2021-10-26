package com.apolo.modulos.estudiante.resource;


import com.apolo.modulos.estudiante.model.Matricula;
import com.apolo.modulos.estudiante.repository.MatriculaRepository;
import com.apolo.modulos.estudiante.service.MatriculaService;
import com.lowagie.text.DocumentException;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class MatriculaJPAResource {

    private final MatriculaRepository matriculaRepository;

    private final MatriculaService matriculaService;

    public MatriculaJPAResource(MatriculaRepository matriculaRepository, MatriculaService matriculaService) {
        this.matriculaRepository = matriculaRepository;
        this.matriculaService = matriculaService;
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

    @GetMapping("/matriculas/{id}/pdf")
    public String pdf (HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setPrefix("templates/");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);


        IWebContext context = new WebContext( request,  response,  request.getServletContext());
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


package com.apolo.modulos.estudiante.resource;


import com.apolo.modulos.estudiante.model.Matricula;
import com.apolo.modulos.estudiante.repository.MatriculaRepository;
import com.apolo.modulos.usuarios.model.Usuario;
import com.lowagie.text.DocumentException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class MatriculaJPAResource {

    private final MatriculaRepository matriculaRepository;

    public MatriculaJPAResource(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    //crear matricula
    // GET  /usuario/
    @GetMapping("/matriculas")
    public List<Matricula> getAll() {
        return matriculaRepository.findAll();
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


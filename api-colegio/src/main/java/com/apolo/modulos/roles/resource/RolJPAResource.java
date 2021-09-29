package com.apolo.modulos.roles.resource;

import com.apolo.dao.DeleteResponse;
import com.apolo.modulos.roles.model.Rol;
import com.apolo.modulos.roles.repository.RolRepository;
import com.apolo.spring.exception.ErrorGeneralExcepcion;
import com.apolo.spring.exception.ObjetoNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class RolJPAResource {


    private final RolRepository rolRepository;

    @Autowired
    public RolJPAResource(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    //todos los roles
    @GetMapping("/roles")
    public List<Rol> retrieveAllRoles() {
        return rolRepository.findAll();
    }

    //obtener rol
    //roles/id
    @GetMapping("/roles/{id}")
    public EntityModel<Rol> getRol(@PathVariable Long id) {
        Optional<Rol> rol = rolRepository.findById(id);

        if (!rol.isPresent())
            throw new ObjetoNoEncontradoException("id-" + id);

        return EntityModel.of(rol.get());
    }

    //eliminar rol
    //roles/id
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> deleteRol(@PathVariable Long id) {

        EntityModel<?> resource = EntityModel.of(new DeleteResponse(id));

        rolRepository.deleteById(id);

        return ResponseEntity.ok(resource);
    }

    @PostMapping("/roles")
    public EntityModel<Rol> creaOEditarRol(@Valid @RequestBody Rol rol) {
        Long idRol = rol.getId();

        if (idRol != null) {
            Optional<Rol> rolExistente = rolRepository.findById(idRol);
            if (!rolExistente.isPresent())
                throw new ObjetoNoEncontradoException("No existe un rol con el id " + idRol);
        }else{

            String credencial = rol.getCredencial();

            Optional<Rol>  rolExistente  = rolRepository.findByCredencial(credencial);

            if (rolExistente.isPresent())
                throw new ErrorGeneralExcepcion("No puedes crear otro rol con la credencial " + credencial);
        }

        Rol nuevoRol = rolRepository.save(rol);

        return EntityModel.of(nuevoRol);

    }
}

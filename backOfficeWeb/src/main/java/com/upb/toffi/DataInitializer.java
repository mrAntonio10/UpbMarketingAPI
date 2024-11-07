package com.upb.toffi;

import com.upb.models.rol.Rol;
import com.upb.models.tipo_solicitud.TipoSolicitud;
import com.upb.models.user.User;
import com.upb.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;
    private final TipoSolicitudRepository tipoSolicitudRepository;

    @Override
    public void run(String... args) throws Exception {
      this.createRolesAndUsers();
      this.crearTiposEventos();

    }


    private void createRolesAndUsers() {

        //Roles
        Optional<Rol> rolRootOpt = rolRepository.findByNameAndStateTrue("ROOT");
        Rol rolRoot;
        if(!rolRootOpt.isPresent()) {
            rolRoot = Rol.builder()
                    .state(true)
                    .name("ROOT")
                    .build();
            rolRepository.save(rolRoot);
        } else {
            rolRoot = rolRootOpt.get();
        }

        Optional<Rol> rolStudentOpt = rolRepository.findByNameAndStateTrue("USER");
        Rol rolStudent;
        if(!rolStudentOpt.isPresent()) {
             rolStudent = Rol.builder()
                    .state(true)
                    .name("USER")
                    .build();
            rolRepository.save(rolStudent);
        } else {
            rolStudent = rolStudentOpt.get();
        }

        Optional<Rol> rolAdminOpt = rolRepository.findByNameAndStateTrue("ADMIN");
        Rol rolAdmin;
        if(!rolAdminOpt.isPresent()) {
             rolAdmin = Rol.builder()
                    .state(true)
                    .name("ADMIN")
                    .build();
            rolRepository.save(rolAdmin);
        } else {
            rolAdmin = rolAdminOpt.get();
        }

        if (!userRepository.findByEmailAndStateActive("admin@gmail.com").isPresent()) {
            User adminUser = new User();
            adminUser.setName("ADMIN");
            adminUser.setLastname("Administrator");
            adminUser.setEmail("admin@gmail.com");
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setState("ACTIVE");
            adminUser.setRol(rolAdmin);
            userRepository.save(adminUser);

            System.out.println("Usuario ADMIN creado.");
        } else {
            System.out.println("Usuario ADMIN ya existe.");
        }

        if (!userRepository.findByEmailAndStateActive("root@gmail.com").isPresent()) {
            User rootUser = new User();
            rootUser.setName("ROOT");
            rootUser.setLastname("Root");
            rootUser.setEmail("root@gmail.com");
            rootUser.setPassword(passwordEncoder.encode("root"));
            rootUser.setState("ACTIVE");
            rootUser.setRol(rolRoot);
            userRepository.save(rootUser);

            System.out.println("Usuario ROOT creado.");
        } else {
            System.out.println("Usuario ROOT ya existe.");
        }
    }

    private void crearTiposEventos() {
        if(!this.tipoSolicitudRepository.findTipoSolicitudByName("%ARTES%").isPresent()) {
            TipoSolicitud tsArtes = TipoSolicitud.builder()
                    .state(true)
                    .nombre("Artes")
                    .build();

            this.tipoSolicitudRepository.save(tsArtes);
        }

        if(!this.tipoSolicitudRepository.findTipoSolicitudByName("%VIDEOS%").isPresent()) {
            TipoSolicitud tsVideos = TipoSolicitud.builder()
                    .state(true)
                    .nombre("Videos")
                    .build();

            this.tipoSolicitudRepository.save(tsVideos);
        }

        if(!this.tipoSolicitudRepository.findTipoSolicitudByName("%EVENTOS%").isPresent()) {
            TipoSolicitud tsEventos = TipoSolicitud.builder()
                    .state(true)
                    .nombre("Eventos")
                    .build();

            this.tipoSolicitudRepository.save(tsEventos);
        }

        if(!this.tipoSolicitudRepository.findTipoSolicitudByName("%PODCAST%").isPresent()) {
            TipoSolicitud tsPodcast = TipoSolicitud.builder()
                    .state(true)
                    .nombre("Podcst")
                    .build();

            this.tipoSolicitudRepository.save(tsPodcast);
        }
    }
}

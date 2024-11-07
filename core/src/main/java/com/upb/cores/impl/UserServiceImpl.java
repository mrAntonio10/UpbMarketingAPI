package com.upb.cores.impl;


import ch.qos.logback.core.util.StringUtil;
import com.upb.cores.RolService;
import com.upb.cores.utils.StringUtilMod;
import com.upb.models.rol.Rol;
import com.upb.models.user.dto.UserDto;
import com.upb.repositories.UserRepository;
import com.upb.cores.UserService;
import com.upb.models.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final RolService rolService;


    public UserServiceImpl(UserRepository userRepository, RolService rolService) {
        this.userRepository = userRepository;
        this.rolService = rolService;
        this.encoder = new BCryptPasswordEncoder(10);
    }


    @Override
    @Transactional(readOnly = true)
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            log.info("Usuario deslogueado: {}", authentication.getName());
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            return ("/");
        } else {
            log.info("Usuario anónimo o no autenticado. Info {}", SecurityContextHolder.getContext().getAuthentication());
            return ("/");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> getUserPageableByBranchOffice(String name, String idBranchOffice, Authentication authentication,Pageable pageable) {
        name = (!StringUtil.isNullOrEmpty(name) ?  "%" +name.toUpperCase()+ "%" : null);
        idBranchOffice = (!StringUtil.isNullOrEmpty(idBranchOffice) ?  idBranchOffice : null);

        User user = (User) authentication.getPrincipal();

        if(!user.getRol().getName().equals("ROOT")) {
            log.info("PAGINACION DE NORMAL {}, {}", name, idBranchOffice);
            return null;
        } else {
            log.info("PAGINACION DE ROOT {}, {}", name, idBranchOffice);
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(String idUser) {
        return userRepository.findUserById(idUser).orElseThrow(
                () -> new NoSuchElementException("No fue posible recuperar los valores correspondientes al usuario"));
    }

    @Override
    @Transactional
    public UserDto createUser(String name, String lastname, String password, String phoneNumber, String email, String idRol, String idBranchOffice) {
        StringUtilMod.notNullStringMaxLength(name, 60, "Nombre");
        StringUtilMod.notNullStringMaxLength(lastname, 60, "apellido");
        StringUtilMod.notNullStringMaxLength(password, 60, "Contraseña");
        StringUtilMod.notNullEmailMatcher(email, "Email");

        StringUtilMod.canBeNull_NumberMatcherMaxLength(phoneNumber, 20, "Número telefónico");

        Rol rol = rolService.getRolById(idRol);

        User user = User.builder()
                .state("ACTIVE")
                .name(name)
                .lastname(lastname)
                .password(encoder.encode(password))
                .email(email)
                .rol(rol)
                .build();
        userRepository.save(user);

        return new UserDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUser(String id, String name, String lastname, String password, String phoneNumber, String email, String idRol, String state, String idBranchOffice) {
        StringUtilMod.notNullStringMaxLength(name, 60, "Nombre");
        StringUtilMod.notNullStringMaxLength(lastname, 60, "apellido");
        StringUtilMod.canBeNull_StringMaxLength(password, 60, "Contraseña");
        StringUtilMod.notNullEmailMatcher(email, "Email");
        StringUtilMod.throwStringIsNullOrEmpty(state, "Estado");

        StringUtilMod.canBeNull_NumberMatcherMaxLength(phoneNumber, 20, "Número telefónico");

        Rol rol = rolService.getRolById(idRol);

        User user = userRepository.findUserById(id).orElseThrow(
                () -> new NoSuchElementException("No fue posible recuperar los valores correspondientes al usuario")
        );

        if(!StringUtil.isNullOrEmpty(password)) {
            user.setPassword(encoder.encode(password));
        }
       user.setName(name);
       user.setLastname(lastname);
       user.setEmail(email);
       user.setRol(rol);
       user.setState(state);

       userRepository.save(user);

        return new UserDto(user);
    }

    @Override
    public UserDto deleteUserById(String id) {
        User user = userRepository.findUserById(id).orElseThrow(
                () -> new NoSuchElementException("No fue posible recuperar los valores correspondientes al usuario"));

        user.setState("DELETED");

        userRepository.save(user);

        return new UserDto(user);
    }
}

package com.crio.starter.controller;

import com.crio.starter.data.*;
import com.crio.starter.exchange.*;
import com.crio.starter.repository.*;
import com.crio.starter.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.stream.Stream;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.validation.*;

import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.dao.DataIntegrityViolationException;

import com.mongodb.lang.NonNull;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;

@Log4j2
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemesController {
@Autowired
UserRepository userRepository;
@Autowired
private final MemesService memesService;
@Autowired
private UserService userService;

private Map<String, LocalDateTime> usersLastAccess = new HashMap<>();

@GetMapping("/")
public RedirectView home() {
        return new RedirectView("/home.html");
}

@GetMapping("/login")
public String getCurrentUser(@AuthenticationPrincipal @Validated User user, Model model) {
        String username = user.getUsername();
        model.addAttribute("username", username);
        model.addAttribute("lastAccess", usersLastAccess.get(username));
        usersLastAccess.put(username, LocalDateTime.now());
        return "Success";
}

@GetMapping("/register")
public RedirectView register(final Model model){
        log.info("Get Register");
        model.addAttribute("userData", new UserData());
        return new RedirectView("register.html");
}
@RequestMapping("/index")
public String index() {
        return "index";
}

@GetMapping("/webpublic")
public String loginpub() {
        return "public";
}

@PostMapping("/InsertData")
public @ResponseBody String test(String user){
        log.info("Insert Data");
        return "HELLO TEST : " + user;
}

@GetMapping("/memes")
public List<MemesEntity> getMemes() {

        log.info("getMemes called");
        getResponseMeme getMemeResponse;
        getMemeResponse = memesService.getAllMemes();

        return getMemeResponse.getMemes();
}

@GetMapping("/memes/{memeId}")
public MemesEntity getMeme(@PathVariable String memeId) {

        log.info("getMeme called with {}", memeId);
        MemesEntity getMemeResponse;
        getMemeResponse = memesService.getMemes(memeId);

        return getMemeResponse;
}

@RequestMapping(value = "/register", method = RequestMethod.POST)
public @ResponseBody String registerUser(@Validated UserData user) {
        userService.register(user);
        if(Stream.of(user.getPassword(),user.getUsername()).allMatch(Objects::isNull))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                return "SUCCESS";
}

@PostMapping("/memes")
public getPostResponse postMeme(@Validated @RequestBody String memeJson)  {
        log.info("postMeme called with {}", memeJson);
        ObjectMapper objectMapper = new ObjectMapper();
        MemesEntity meme = new MemesEntity();
        try {
                meme = objectMapper.readValue(memeJson, MemesEntity.class);
        } catch (JsonProcessingException e) {
                e.printStackTrace();
        }
        if(Stream.of(meme.getName(),meme.getUrl(),meme.getCaption()).allMatch(Objects::isNull))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't post empty object");
        return memesService.postMeme(meme);
}

@GetMapping("/error")
public String error(HttpServletRequest request) {
        String message = (String) request.getSession().getAttribute("error.message");
        request.getSession().removeAttribute("error.message");
        return message;
}

}

package com.saml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SamlSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SamlSecurityApplication.class, args);
    }

    @RequestMapping("/")
    public String index() {
        return "home";
    }

    @RequestMapping("/secured/hello")
    public String hello(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        model.addAttribute("name", principal.getName());
        return "hello";
    }
}
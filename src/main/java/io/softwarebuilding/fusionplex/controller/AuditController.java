package io.softwarebuilding.fusionplex.controller;

import io.softwarebuilding.fusionplex.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuditController {

    private final AuditService auditService;

    @Autowired
    public AuditController( final AuditService auditService ) {
        this.auditService = auditService;
    }

    @GetMapping("/admin/audit/genres")
    public String showAuditGenresPage( final Model model ) {
        final String changes = this.auditService.getGenresChanges();

        model.addAttribute( "changes", changes );

        return "auditGenre";
    }

    @GetMapping("/admin/audit/titles")
    public String showAuditTitlesPage( final Model model ) {
        final String changes = this.auditService.getTitleChanges();

        model.addAttribute( "changes", changes );

        return "auditTitle";
    }
}

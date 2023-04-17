package site.polliakov.VscTemplateTestEnv.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.polliakov.VscTemplateTestEnv.Dtos.GetTemplateRequest;
import site.polliakov.VscTemplateTestEnv.Dtos.GetTemplateResponse;
import site.polliakov.VscTemplateTestEnv.Services.TemplateService;

import java.util.HashMap;

@RestController
public class TemplateController {
    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    private final TemplateService templateService;

    @PostMapping("/template-render")
    public ResponseEntity<GetTemplateResponse> renderTemplate(@RequestBody GetTemplateRequest request) {
        var template = request.getTemplate();
        var contextJson = request.getFormAnswersJson();

        var renderedTemplate = templateService.renderTemplate(template, contextJson);
        var response = new GetTemplateResponse(renderedTemplate);
        return ResponseEntity.ok(response);
    }
}

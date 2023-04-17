package site.polliakov.VscTemplateTestEnv.Services;

import site.polliakov.VscTemplateTestEnv.Exceptions.TemplateValidationException;

import java.util.Map;

public interface TemplateService {
    String renderTemplate(String template, String contextJson) throws TemplateValidationException;
}

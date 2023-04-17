package site.polliakov.VscTemplateTestEnv.Templates;

import site.polliakov.VscTemplateTestEnv.Exceptions.TemplateValidationException;

public interface TemplateValidator {
    void validOrThrow(String template) throws TemplateValidationException;
}

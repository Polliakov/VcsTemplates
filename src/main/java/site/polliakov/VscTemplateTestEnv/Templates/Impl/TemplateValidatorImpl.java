package site.polliakov.VscTemplateTestEnv.Templates.Impl;

import org.springframework.stereotype.Component;
import site.polliakov.VscTemplateTestEnv.Exceptions.TemplateValidationException;
import site.polliakov.VscTemplateTestEnv.Templates.TemplateValidator;

import java.util.regex.Pattern;

@Component
public class TemplateValidatorImpl implements TemplateValidator {
    private static final Pattern notAllowed = Pattern.compile(
            "#import|#parse",
            Pattern.CASE_INSENSITIVE
    );

    public void validOrThrow(String template) {
        var matcher = notAllowed.matcher(template);
        if (matcher.find())
            throw new TemplateValidationException("Template contains not allowed directive.");
    }
}

package site.polliakov.VscTemplateTestEnv.Services.Impl;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.springframework.stereotype.Service;
import site.polliakov.VscTemplateTestEnv.Exceptions.TemplateValidationException;
import site.polliakov.VscTemplateTestEnv.Services.TemplateService;
import site.polliakov.VscTemplateTestEnv.Templates.ContextParser;
import site.polliakov.VscTemplateTestEnv.Templates.GlobalContextVariables;
import site.polliakov.VscTemplateTestEnv.Templates.TemplateValidator;
import site.polliakov.VscTemplateTestEnv.Templates.Tools;

import java.io.StringReader;
import java.io.StringWriter;

@Service
public class TemplateServiceImpl implements TemplateService {
    public TemplateServiceImpl(
            TemplateValidator templateValidator,
            ContextParser contextParser,
            GlobalContextVariables globalVariables,
            Tools tools
    ) {
        this.templateValidator = templateValidator;
        this.contextParser = contextParser;
        this.globalVariables = globalVariables;
        this.tools = tools;
    }

    private final TemplateValidator templateValidator;
    private final ContextParser contextParser;
    private final GlobalContextVariables globalVariables;
    private final Tools tools;

    public String renderTemplate(String template, String contextJson) throws TemplateValidationException {
        templateValidator.validOrThrow(template);
        var context = buildContext(contextJson);
        return render(template, context);
    }

    private Context buildContext(String contextJson) {
        var context = contextJson.isBlank() ?
            new VelocityContext() :
            contextParser.parse(contextJson);

        globalVariables.addToContext(context);
        tools.addToContext(context);
        return context;
    }

    private String render(String template, Context context) {

        var writer = new StringWriter();
        var reader = new StringReader(template);
        Velocity.evaluate(
                context,
                writer,
                "Velocity String Template Evaluation",
                reader
        );
        return writer.toString();
    }
}

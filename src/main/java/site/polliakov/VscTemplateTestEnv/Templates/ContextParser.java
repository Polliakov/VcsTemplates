package site.polliakov.VscTemplateTestEnv.Templates;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;

public interface ContextParser {
    Context parse(String contextJson);
    void addToContext(Context context, String contextJson);
}

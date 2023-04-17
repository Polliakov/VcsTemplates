package site.polliakov.VscTemplateTestEnv.Templates.Impl;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.stereotype.Component;
import site.polliakov.VscTemplateTestEnv.Templates.Tools;

import java.util.HashMap;
import java.util.Map;

@Component
public class VcsTools implements Tools {
    private static final Map<String, Object> tools = new HashMap<>() {{
        put("dateTool", new DateTool());
    }};

    public void addToContext(Context context) {
        for (var tool : tools.entrySet())
            context.put(tool.getKey(), tool.getValue());
    }
}

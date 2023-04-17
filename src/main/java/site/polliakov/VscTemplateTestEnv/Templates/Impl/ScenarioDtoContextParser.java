package site.polliakov.VscTemplateTestEnv.Templates.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.stereotype.Component;
import site.polliakov.VscTemplateTestEnv.Exceptions.ContextParserException;
import site.polliakov.VscTemplateTestEnv.Templates.ContextParser;

import java.util.HashMap;
import java.util.Map;

@Component
public class ScenarioDtoContextParser implements ContextParser {

    private final ObjectMapper json = new ObjectMapper();
    private final TypeReference<HashMap<String, Object>> dynamicMapTypeRef = new TypeReference<>() {};

    private static final String[] requiredScenarioFields = {
        "serviceCode", "targetCode", "orderId", "currentScenarioId", "serviceDescriptorId",
        "serviceInfo"
    };

    public Context parse(String contextJson) {
        try {
            return new VelocityContext(
                parseOrThrow(contextJson)
            );
        }
        catch (Exception ex) {
            throw new ContextParserException(ex.getMessage());
        }
    }

    public void addToContext(Context context, String contextJson) {
        try {
            var contextEntries = parseOrThrow(contextJson);
            for (var entry : contextEntries.entrySet())
                context.put(entry.getKey(), entry.getValue());
        }
        catch (Exception ex) {
            throw new ContextParserException(ex.getMessage());
        }
    }

    public Map<String, Object> parseOrThrow(String contextJson) throws JsonProcessingException {
        var scenarioDto = json.readValue(contextJson, dynamicMapTypeRef);

        var context = new HashMap<String, Object>();
        addApplicantAnswers(context, scenarioDto);
        addRequiredFields(context, scenarioDto);

        return context;
    }

    @SuppressWarnings("unchecked")
    private void addApplicantAnswers(Map<String, Object> context, Map<String, Object> scenarioDto) {
        var applicantAnswers = (Map<String, Object>)scenarioDto.get("applicantAnswers");
        for (var answer : applicantAnswers.entrySet()){
            context.put(answer.getKey(), parseAnswerValue(answer));
        }
    }

    private void addRequiredFields(Map<String, Object> context, Map<String, Object> scenarioDto) {
        for (var field : requiredScenarioFields) {
            var value = scenarioDto.get(field);
            context.put(field, value);
        }
    }

    private Object parseAnswerValue(Map.Entry<String, Object> answer) {
        var valueRaw = getAnswerValue(answer);
        if (!(valueRaw instanceof String))
            return valueRaw;
        return parseIfJson((String)valueRaw);
    }

    @SuppressWarnings("unchecked")
    private Object getAnswerValue(Map.Entry<String, Object> answer) {
        var valueMap = (Map<String, Object>)answer.getValue();
        if (!valueMap.containsKey("value"))
            return null;
        return valueMap.get("value");
    }

    private Object parseIfJson(String value) {
        try {
            return json.readValue(value, dynamicMapTypeRef);
        }
        catch (Exception ex) {
            return value;
        }
    }
}

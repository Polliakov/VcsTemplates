package site.polliakov.VscTemplateTestEnv.Dtos;

import lombok.Data;

@Data
public class GetTemplateRequest {
    private String formAnswersJson;
    private String template;
}

package site.polliakov.VscTemplateTestEnv.Exceptions;

public class ContextParserException extends RuntimeException {
    public ContextParserException(String innerMessage) {
        super("Can't parse context.\nInner exception message: " + innerMessage);
    }
}

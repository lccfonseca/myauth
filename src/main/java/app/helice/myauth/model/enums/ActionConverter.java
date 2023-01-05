package app.helice.myauth.model.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ActionConverter implements AttributeConverter<Action, String> {

    @Override
    public String convertToDatabaseColumn(Action action) {
        if (action == null) {
            return null;
        }
        return action.getAction();
    }

    @Override
    public Action convertToEntityAttribute(String action) {
        if (action == null) {
            return null;
        }

        return Stream.of(Action.values())
                .filter(c -> c.getAction().equals(action))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}

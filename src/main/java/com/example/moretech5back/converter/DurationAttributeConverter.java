package online.mdfactory.backend.converter;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;

import java.time.Duration;

@Converter(autoApply = true)
@AllArgsConstructor
public class DurationAttributeConverter implements AttributeConverter<Duration, String> {
    private final FormatService formatService;

    @Override
    public String convertToDatabaseColumn(Duration duration) {
        return duration == null ? null: formatService.toString(duration);
    }

    @Override
    public Duration convertToEntityAttribute(String duration) {
        return duration == null ? null : formatService.toDuration(duration);
    }
}

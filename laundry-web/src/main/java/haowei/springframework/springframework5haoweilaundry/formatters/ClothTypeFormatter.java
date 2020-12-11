package haowei.springframework.springframework5haoweilaundry.formatters;

import haowei.springframework.springframework5haoweilaundry.model.ClothType;
import haowei.springframework.springframework5haoweilaundry.services.ClothTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class ClothTypeFormatter implements Formatter<ClothType> {
    private final ClothTypeService clothTypeService;

    public ClothTypeFormatter(ClothTypeService clothTypeService) {
        this.clothTypeService = clothTypeService;
    }

    @Override
    public String print(ClothType clothType, Locale locale) {
        return clothType.getName();
    }

    @Override
    public ClothType parse(String text, Locale locale) throws ParseException {
        Collection<ClothType> findPetTypes = clothTypeService.findAll();

        for (ClothType type : findPetTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }

        throw new ParseException("type not found: " + text, 0);
    }
}

package fortnox.challenge.boxinatorAPI.services;

import fortnox.challenge.boxinatorAPI.exceptions.InvalidBoxDTOException;
import fortnox.challenge.boxinatorAPI.model.Box;
import fortnox.challenge.boxinatorAPI.model.BoxDTO;
import fortnox.challenge.boxinatorAPI.model.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class BoxService implements IBoxService {

    @Autowired
    IBoxRepository boxRepo;

    @Override
    public Object getAllBoxes() {
        return boxRepo.getAllBoxes();
    }

    @Override
    public Box addBox(BoxDTO boxDTO) {
        return boxRepo.addBox(convertBoxDTOtoBox(boxDTO));
    }

    private Box convertBoxDTOtoBox(BoxDTO boxDTO) {
        // Validate Box Name and Weight
        validateRecipientName(boxDTO.getName());
        validateBoxWeight(boxDTO.getWeight());

        // Color validation
        Color color = null;

        try {
            color = Color.decode(boxDTO.getColor().trim());
        } catch (NumberFormatException e) {
            throw new InvalidBoxDTOException("Color format is invalid.");
        } catch (NullPointerException e) {
            throw new InvalidBoxDTOException("Given color was null.");
        }

        validateBoxColor(color);

        // Country validation
        CountryCode country = CountryCode.mapStringToCode(boxDTO.getCountryCode());
        validateCountryCode(country);

        // Create and return new box
        return new Box(
                boxDTO.getName().trim(),
                boxDTO.getWeight(),
                convertColorObjectToRGBString(color),
                country);
    }

    private void validateRecipientName(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new InvalidBoxDTOException("Name is required.");
        }
    }

    private void validateBoxWeight(double weight) {
        if (weight < 0) {
            throw new InvalidBoxDTOException("Weight must be non-negative.");
        }
    }

    private void validateBoxColor(Color color) {
        if (color.getBlue() != 0) {
            throw new InvalidBoxDTOException("Color must not contain any shades of blue.");
        }
    }

    private void validateCountryCode(CountryCode country) {
        if (country == null) {
            throw new InvalidBoxDTOException("Country code was not recognized.");
        }
    }

    private String convertColorObjectToRGBString(Color color) {
        return String.format("rgb(%d, %d, %d)", color.getRed(), color.getGreen(), color.getBlue());
    }
}

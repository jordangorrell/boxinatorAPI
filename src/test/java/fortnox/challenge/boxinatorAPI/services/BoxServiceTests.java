package fortnox.challenge.boxinatorAPI.services;

import fortnox.challenge.boxinatorAPI.exceptions.InvalidBoxDTOException;
import fortnox.challenge.boxinatorAPI.model.Box;
import fortnox.challenge.boxinatorAPI.model.BoxDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

@SpringBootTest
public class BoxServiceTests {

    @Autowired
    private IBoxService boxService;

    @MockBean
    private IBoxRepository boxRepo;

    @Test
    @DisplayName("Retrieve Boxes From Repository")
    void getAllBoxesFromRepo_ServiceReturnsSameObjectThatIsGivenByRepo() {
        // Set up list of boxes
        ArrayList<Box> repoBoxes = new ArrayList<Box>();
        repoBoxes.add(new Box("name", 0, "#000000", "SWE"));

        // Have boxRepo return list of boxes when getAllBoxes is called
        Mockito.doReturn(repoBoxes).when(boxRepo).getAllBoxes();

        // Assert that boxService returns the list that is given to it by boxRepo
        Object serviceBoxes = boxService.getAllBoxes();
        Assertions.assertSame(repoBoxes, serviceBoxes);
    }

    @Test
    @DisplayName("Add Valid Box")
    void addValidBox_DoesNotThrowException() {
        BoxDTO boxDTO = new BoxDTO("validName", 1, "#000000", "SWE");
        Mockito.doReturn(Mockito.mock(Box.class)).when(boxRepo).addBox(Mockito.any(Box.class));
        Assertions.assertDoesNotThrow(() -> boxService.addBox(boxDTO));
    }

    @Test
    @DisplayName("Add Box with Invalid Name Input")
    void addInvalidBox_InvalidNameInput_ThrowsInvalidBoxDTOException() {
        // Empty String
        Assertions.assertThrows(InvalidBoxDTOException.class,
                () -> boxService.addBox(new BoxDTO("", 1, "#000000", "SWE")));

        // Only White Space String
        Assertions.assertThrows(InvalidBoxDTOException.class,
                () -> boxService.addBox((new BoxDTO("     ", 1, "#000000", "SWE"))));

        // Null String
        Assertions.assertThrows(InvalidBoxDTOException.class,
                () -> boxService.addBox((new BoxDTO(null, 1, "#000000", "SWE"))));
    }

    @Test
    @DisplayName("Add Box with Invalid Weight Input")
    void addInvalidBox_InvalidWeightInput_ThrowsInvalidBoxDTOException() {
        // Negative Weight Input
        Assertions.assertThrows(InvalidBoxDTOException.class,
                () -> boxService.addBox((new BoxDTO("validName", -1, "#000000", "SWE"))));

        Assertions.assertThrows(InvalidBoxDTOException.class,
                () -> boxService.addBox((new BoxDTO("validName", -15, "#000000", "SWE"))));
    }

    @Test
    @DisplayName("Add Box with Invalid Color Input")
    void addInvalidBox_InvalidColorInput_ThrowsInvalidBoxDTOException() {
        // Invalid Format
        Assertions.assertThrows(InvalidBoxDTOException.class,
                () -> boxService.addBox((new BoxDTO("validName", 1, "not a color", "SWE"))));

        // Invalid Formatted Color (color cannot contain blue)
        Assertions.assertThrows(InvalidBoxDTOException.class,
                () -> boxService.addBox((new BoxDTO("validName", 1, "#555501", "SWE"))));

        Assertions.assertThrows(InvalidBoxDTOException.class,
                () -> boxService.addBox((new BoxDTO("validName", 1, "#5555FF", "SWE"))));

        // Null Color String
        Assertions.assertThrows(InvalidBoxDTOException.class,
                () -> boxService.addBox((new BoxDTO("validName", 1, null, "SWE"))));
    }

    @Test
    @DisplayName("Add Box with Invalid Country Input")
    void addInvalidBox_InvalidCountryInput_ThrowsInvalidBoxDTOException() {
        // Empty Country Code
        Assertions.assertThrows(InvalidBoxDTOException.class,
                () -> boxService.addBox((new BoxDTO("validName", 1, "#000000", ""))));

        // White Space Country Code
        Assertions.assertThrows(InvalidBoxDTOException.class,
                () -> boxService.addBox((new BoxDTO("validName", 1, "#000000", "        "))));

        // Unrecognized Country Code
        Assertions.assertThrows(InvalidBoxDTOException.class,
                () -> boxService.addBox((new BoxDTO("validName", 1, "#000000", "CCC"))));

        // Null Country Code String
        Assertions.assertThrows(InvalidBoxDTOException.class,
                () -> boxService.addBox((new BoxDTO("validName", 1, "#000000", null))));
    }

}

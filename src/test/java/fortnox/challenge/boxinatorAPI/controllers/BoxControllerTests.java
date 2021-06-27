package fortnox.challenge.boxinatorAPI.controllers;

import fortnox.challenge.boxinatorAPI.exceptions.InvalidBoxDTOException;
import fortnox.challenge.boxinatorAPI.model.Box;
import fortnox.challenge.boxinatorAPI.model.BoxDTO;
import fortnox.challenge.boxinatorAPI.services.IBoxService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@SpringBootTest
public class BoxControllerTests {

    @Autowired
    BoxController boxController;

    @MockBean
    IBoxService boxService;

    @Test
    @DisplayName("Retrieve Boxes from Service")
    void getAllBoxesEndpoint_normalOperation_returnsBoxServiceGetAllBoxes() {
        Object serviceBoxes = new ArrayList<Box>();
        Mockito.doReturn(serviceBoxes).when(boxService).getAllBoxes();

        Object controllerBoxes = boxController.getBoxes();

        Assertions.assertSame(serviceBoxes, controllerBoxes);
    }

    @Test
    @DisplayName("Failed Query for All Boxes Gives 500")
    void getAllBoxesEndpoint_exceptionThrown_sends500() {
        Mockito.doThrow(RuntimeException.class).when(boxService).getAllBoxes();

        try {
            boxController.getBoxes();
        } catch (ResponseStatusException e) {
            Assertions.assertEquals(500, e.getRawStatusCode());
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Invalid Box Gives 400")
    void addBoxEndpoint_failedToAddBoxDueToInvalidDTO_sends400() {
        Mockito.doThrow(InvalidBoxDTOException.class).when(boxService).addBox(Mockito.any(BoxDTO.class));

        try {
            boxController.addBox(Mockito.mock(BoxDTO.class));
        } catch (ResponseStatusException e) {
            Assertions.assertEquals(400, e.getRawStatusCode());
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Error Adding Box Gives 500")
    void addBoxEndpoint_failedToAddBoxDueThrownException_sends500() {
        Mockito.doThrow(RuntimeException.class).when(boxService).addBox(Mockito.any(BoxDTO.class));

        try {
            boxController.addBox(Mockito.mock(BoxDTO.class));
        } catch (ResponseStatusException e) {
            Assertions.assertEquals(500, e.getRawStatusCode());
        } catch (Exception e) {
            Assertions.fail();
        }
    }

}

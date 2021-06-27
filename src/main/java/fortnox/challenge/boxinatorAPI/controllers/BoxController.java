package fortnox.challenge.boxinatorAPI.controllers;

import fortnox.challenge.boxinatorAPI.exceptions.InvalidBoxDTOException;
import fortnox.challenge.boxinatorAPI.model.Box;
import fortnox.challenge.boxinatorAPI.model.BoxDTO;
import fortnox.challenge.boxinatorAPI.services.IBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/boxes")
public class BoxController {

    @Autowired
    IBoxService boxService;

    @GetMapping(produces = "application/json")
    public Object getBoxes()
    {
        try {
            return boxService.getAllBoxes();
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/addbox")
    public Box addBox(@RequestBody BoxDTO boxDTO) {
        try {
            return boxService.addBox(boxDTO);
        }
        catch (InvalidBoxDTOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

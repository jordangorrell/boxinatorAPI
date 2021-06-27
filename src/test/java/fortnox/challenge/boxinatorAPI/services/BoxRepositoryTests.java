package fortnox.challenge.boxinatorAPI.services;

import fortnox.challenge.boxinatorAPI.exceptions.DbAccessException;
import fortnox.challenge.boxinatorAPI.factories.IKeyFactory;
import fortnox.challenge.boxinatorAPI.model.Box;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class BoxRepositoryTests {

    @Autowired
    IBoxRepository boxRepo;

    @MockBean
    JdbcTemplate db;

    @MockBean
    IKeyFactory keyFactory;

    @Test
    @DisplayName("Retrieve Boxes From DB")
    void getBoxesFromDB_RepoReturnsSameObjectThatIsGivenByDB() {
        // Set up object for return
        List<Map<String, Object>> listFromDb = new ArrayList<Map<String, Object>>();

        // Have boxRepo return list of boxes when getAllBoxes is called
        Mockito.doReturn(listFromDb).when(db).queryForList(Mockito.anyString());

        // Assert that boxService returns the list that is given to it by boxRepo
        Object listFromRepo = boxRepo.getAllBoxes();
        Assertions.assertSame(listFromDb, listFromRepo);
    }

    @Test
    @DisplayName("Failed To Get All Boxes")
    void getBoxesFromDB_DbAccessExceptionThrownWhenFailingToQueryDB() {
        Mockito.doThrow(RuntimeException.class).when(db).queryForList(Mockito.anyString());

        Assertions.assertThrows(DbAccessException.class,
                () -> boxRepo.getAllBoxes());
    }

    @Test
    @DisplayName("Test Repo Add Box")
    void addBoxToDB_success() {
        int id = 5;

        KeyHolder keyHolder = Mockito.mock(GeneratedKeyHolder.class);
        Number keyHolderValue = id;

        Mockito.doReturn(keyHolder).when(keyFactory).generateKeyHolder();
        Mockito.doReturn(keyHolderValue).when(keyHolder).getKey();
        Mockito.doReturn(1).when(db).update(Mockito.any(PreparedStatementCreator.class),
                Mockito.any(GeneratedKeyHolder.class));


        Box box = new Box("validName", 0, "#000000", "SWE");
        Box newBox = boxRepo.addBox(box);

        Assertions.assertEquals(id, newBox.getId());
    }

    @Test
    @DisplayName("Repo Add Box Failed Update")
    void addBoxToDB_changeNotSaved_throwsDbAccessException() {
        Mockito.doReturn(new GeneratedKeyHolder()).when(keyFactory).generateKeyHolder();
        Mockito.doReturn(0).when(db).update(Mockito.any(PreparedStatementCreator.class),
                Mockito.any(GeneratedKeyHolder.class));

        Box box = new Box("validName", 0, "#000000", "SWE");
        Assertions.assertThrows(DbAccessException.class, () ->
                boxRepo.addBox(box));
    }

}

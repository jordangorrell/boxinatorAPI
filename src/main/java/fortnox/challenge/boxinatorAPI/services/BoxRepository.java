package fortnox.challenge.boxinatorAPI.services;

import fortnox.challenge.boxinatorAPI.exceptions.DbAccessException;
import fortnox.challenge.boxinatorAPI.factories.IKeyFactory;
import fortnox.challenge.boxinatorAPI.model.Box;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class BoxRepository implements IBoxRepository {

    @Autowired
    JdbcTemplate db;

    @Autowired
    IKeyFactory keyFactory;

    @Override
    public List<Map<String, Object>> getAllBoxes() {
        try {
            return db.queryForList("SELECT * FROM boxes");
        } catch (Exception e) {
            throw new DbAccessException("Failed to retrieve boxes from DB");
        }
    }

    @Override
    public Box addBox(Box box) {
        KeyHolder keyHolder = keyFactory.generateKeyHolder();

        String sql = "INSERT INTO boxes (name, weight, color, shippingCost, country) VALUES (?, ?, ?, ?, ?)";

        int result = db.update(connection -> {
            PreparedStatement ps = connection
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, box.getName());
            ps.setDouble(2, box.getWeight());
            ps.setString(3, box.getColor());
            ps.setDouble(4, box.getShippingCost());
            ps.setString(5, box.getCountry().getCode());
            return ps;
        }, keyHolder);

        if (result > 0) {
            box.setId(keyHolder.getKey().intValue());
            return box;
        } else {
            throw new DbAccessException("Failed to add new box to DB");
        }
    }

}

package hu.nive.ujratervezes.zarovizsga.dogtypes;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DogTypes {

    private DataSource dataSource;

    public DogTypes(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> getDogsByCountry(String country) {
        try (Connection conn = dataSource.getConnection();

             PreparedStatement ps = conn.prepareStatement("SELECT name FROM dog_types WHERE country = ? ORDER BY name")) {

            ps.setString(1, country.toUpperCase());
            if (getResult(ps).isEmpty()) {
                throw new IllegalArgumentException("Not found");
            }
            return getResult(ps);

        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot query", sqle);
        }
    }

    private List<String> getResult(PreparedStatement ps) {
        try (ResultSet rs = ps.executeQuery()) {
            return getNames(rs);
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot query", sqle);
        }
    }


    private List<String> getNames(ResultSet rs) throws SQLException {
        List<String > result = new ArrayList<>();

        while (rs.next()) {
            result.add(rs.getString("name").toLowerCase());
        }
        return result;
    }
}

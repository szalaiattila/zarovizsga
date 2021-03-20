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
        List<String> result = new ArrayList<>();
        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select `name` from dog_types where country = ? order by `name`;")) {
            stmt.setString(1,country.toUpperCase());
            addDogTypeToListByStatement(result, stmt);
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot connect", sqle);
        }
        return result;
    }

    private void addDogTypeToListByStatement(List<String> result, PreparedStatement stmt) throws SQLException {
        try(ResultSet rs = stmt.executeQuery()) {
            while(rs.next()) {
                result.add(rs.getString("name").toLowerCase());
            }
        }
    }
}

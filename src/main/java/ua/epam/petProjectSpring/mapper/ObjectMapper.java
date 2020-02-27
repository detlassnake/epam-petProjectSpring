package ua.epam.petProjectSpring.mapper;

import ua.epam.petProjectSpring.model.Developer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ObjectMapper {
    public static ArrayList<Developer> mapToDeveloper(ResultSet resultSet) throws SQLException {
        ArrayList<Developer> developerArrayList = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getInt("id");
            String developer_name = resultSet.getString("developer_name");
            String account_name = resultSet.getString("account_name");
            String skill_name = resultSet.getString("skill_name");
            Developer developer = new Developer();
            developer.setId(id);
            developer.setName(developer_name);
            developer.setAccount(account_name);
            developer.setSkill(skill_name);
            developerArrayList.add(developer);
        }
        return developerArrayList;
    }
}

package ua.epam.petProjectSpring.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ua.epam.petProjectSpring.mapper.ObjectMapper;
import ua.epam.petProjectSpring.model.Developer;
import ua.epam.petProjectSpring.repository.DeveloperRepository;
import ua.epam.petProjectSpring.util.ConnectionUtil;
import ua.epam.petProjectSpring.util.JdbcQueryStorageUtil;
import ua.epam.petProjectSpring.util.JdbcUtilLogic;

import java.sql.*;
import java.util.ArrayList;

@Repository
@Component
public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {
    private static final Logger logger = LoggerFactory.getLogger(JdbcDeveloperRepositoryImpl.class);

    @Override
    public Developer save(Developer developer) {
        logger.debug("JdbcDeveloperRepository->Save");
        return developerWriteToDB(JdbcQueryStorageUtil.sqlCreateDeveloper1, JdbcQueryStorageUtil.sqlCreateDeveloper2, developer);
    }

    @Override
    public ArrayList<Developer> getAll() {
        logger.debug("JdbcDeveloperRepository->Get All");
        return developerReadFromDB(JdbcQueryStorageUtil.sqlReadDeveloper);
    }

    @Override
    public Developer getById(Long id) {
        Developer developer = new Developer();
        try {
            logger.debug("JdbcDeveloperRepository->Get By Id");
            developer = developerReadFromDB(JdbcQueryStorageUtil.sqlReadByIdDeveloper + id + ";").get(0);
        } catch (IndexOutOfBoundsException e) {
            logger.warn("JdbcDeveloperRepository->Id not found");
        }
        return developer;
    }

    @Override
    public void update(Long id, Developer developer) {
        logger.debug("JdbcDeveloperRepository->Update");
        developerWriteToDB(JdbcQueryStorageUtil.sqlUpdateDeveloper, developer, id);
    }

    @Override
    public void deleteById(Long id) {
        logger.debug("JdbcDeveloperRepository->Delete By Id");
        JdbcUtilLogic.writeToDB(JdbcQueryStorageUtil.sqlDeleteDeveloper, id);
    }

    private Developer developerWriteToDB(String sql1, String sql2, Developer developer) {
        ArrayList<Developer> developerArrayList = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
                preparedStatement.setString(1, developer.getName());
                preparedStatement.setString(2, developer.getSkill());
                preparedStatement.setString(3, developer.getAccount());
                preparedStatement.executeUpdate();
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
                preparedStatement.setString(1, developer.getName());
                ResultSet resultSet = preparedStatement.executeQuery();
                developerArrayList = ObjectMapper.mapToDeveloper(resultSet);
            }
        } catch (SQLException e) {
            logger.error("JdbcDeveloperRepository developerWriteToDB->SQLException");
            e.printStackTrace();
        }
        assert developerArrayList != null;
        return developerArrayList.get(0);
    }

    private void developerWriteToDB(String sql, Developer developer, long id) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, developer.getName());
            preparedStatement.setString(2, developer.getSkill());
            preparedStatement.setString(3, developer.getAccount());
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("JdbcDeveloperRepository developerWriteToDB->SQLException");
            e.printStackTrace();
        }
    }

    private ArrayList<Developer> developerReadFromDB(String sql) {
        ArrayList<Developer> developerArrayList = null;
        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            developerArrayList = ObjectMapper.mapToDeveloper(resultSet);
        } catch (SQLException e) {
            logger.error("JdbcDeveloperRepository developerReadFromDB->SQLException");
            e.printStackTrace();
        }
        return developerArrayList;
    }
}
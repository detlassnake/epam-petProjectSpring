package ua.epam.petProjectSpring.util;

public class JdbcQueryStorageUtil {
    //JdbcDeveloperQuery
    public static String sqlCreateDeveloper1 = "INSERT INTO developer(developer_name, account_name, skill_name) VALUES (?, ?, ?);";
    public static String sqlCreateDeveloper2 = "SELECT * FROM developer WHERE account_name = ?;";
    public static String sqlReadDeveloper = "SELECT * FROM developer;";
    public static String sqlReadByIdDeveloper = "SELECT * FROM developer WHERE id = ";
    public static String sqlUpdateDeveloper = "UPDATE developer SET developer_name = ?, account_name = ?, skill_name = ? WHERE id = ?;";
    public static String sqlDeleteDeveloper = "DELETE FROM developer WHERE id = ?;";
}

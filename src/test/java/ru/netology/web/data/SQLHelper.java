package ru.netology.web.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    public static DataHelper.VerificationCode getVerificationCode() {
        var sql = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        try {
            var connection = getConnection();
            var code = runner.query(connection, sql, new ScalarHandler<String>());
            return new DataHelper.VerificationCode(code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


@SneakyThrows
private static void cleanDB (){
        var connection = getConnection();
        runner.execute("DELETE FROM card_transactions");
        runner.execute("DELETE FROM card_transactions");
        runner.execute("DELETE FROM card_transactions");
        runner.execute("DELETE FROM card_transactions");
    }



}

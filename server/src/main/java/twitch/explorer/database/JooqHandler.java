package twitch.explorer.database;


import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import twitch.explorer.database.jooq.db.Tables;
import twitch.explorer.database.jooq.db.tables.records.GameRecord;
import twitch.explorer.settings.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JooqHandler {

    //Jooq MySql Connection
    private Connection conn;
    private DSLContext create;

    public JooqHandler() throws SQLException {
        String userName = Config.getMySqlUserName();
        String password = Config.getMySqlPassword();
        String url = "jdbc:mysql://" + Config.getMySqlUrl();

        // Connection is the only JDBC resource that we need
        conn = DriverManager.getConnection(url, userName, password);
        create = DSL.using(conn, SQLDialect.MYSQL);
    }

    private void getGames() {
        Result<GameRecord> gameRecord = create.selectFrom(Tables.GAME).fetch();
        for (GameRecord r : gameRecord) {
            String awesome = r.getName();
        }
    }

    public void createGame(String name, String artUrl) {
        GameRecord record1 = create.newRecord(Tables.GAME);
        record1.setName(name);
        record1.setArtUrl(artUrl);
        record1.store();
    }

}

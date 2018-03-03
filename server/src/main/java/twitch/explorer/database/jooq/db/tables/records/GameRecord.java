/*
 * This file is generated by jOOQ.
*/
package twitch.explorer.database.jooq.db.tables.records;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import twitch.explorer.database.jooq.db.tables.Game;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class GameRecord extends UpdatableRecordImpl<GameRecord> implements Record3<Integer, String, String> {

    private static final long serialVersionUID = 735324861;

    /**
     * Setter for <code>twitch.game.game_id</code>.
     */
    public void setGameId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>twitch.game.game_id</code>.
     */
    public Integer getGameId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>twitch.game.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>twitch.game.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>twitch.game.art_url</code>.
     */
    public void setArtUrl(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>twitch.game.art_url</code>.
     */
    public String getArtUrl() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Game.GAME.GAME_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Game.GAME.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Game.GAME.ART_URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getGameId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getArtUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getGameId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getArtUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameRecord value1(Integer value) {
        setGameId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameRecord value3(String value) {
        setArtUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameRecord values(Integer value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached GameRecord
     */
    public GameRecord() {
        super(Game.GAME);
    }

    /**
     * Create a detached, initialised GameRecord
     */
    public GameRecord(Integer gameId, String name, String artUrl) {
        super(Game.GAME);

        set(0, gameId);
        set(1, name);
        set(2, artUrl);
    }
}

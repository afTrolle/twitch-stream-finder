/*
 * This file is generated by jOOQ.
*/
package twitch.explorer.database.jooq.db.tables;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import twitch.explorer.database.jooq.db.Indexes;
import twitch.explorer.database.jooq.db.Keys;
import twitch.explorer.database.jooq.db.Twitch;
import twitch.explorer.database.jooq.db.tables.records.FollowersRecord;


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
public class Followers extends TableImpl<FollowersRecord> {

    private static final long serialVersionUID = 1043106560;

    /**
     * The reference instance of <code>twitch.followers</code>
     */
    public static final Followers FOLLOWERS = new Followers();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FollowersRecord> getRecordType() {
        return FollowersRecord.class;
    }

    /**
     * The column <code>twitch.followers.followers</code>.
     */
    public final TableField<FollowersRecord, Integer> FOLLOWERS_ = createField("followers", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>twitch.followers.fetched</code>.
     */
    public final TableField<FollowersRecord, Timestamp> FETCHED = createField("fetched", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

    /**
     * The column <code>twitch.followers.user_id</code>.
     */
    public final TableField<FollowersRecord, Long> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * Create a <code>twitch.followers</code> table reference
     */
    public Followers() {
        this(DSL.name("followers"), null);
    }

    /**
     * Create an aliased <code>twitch.followers</code> table reference
     */
    public Followers(String alias) {
        this(DSL.name(alias), FOLLOWERS);
    }

    /**
     * Create an aliased <code>twitch.followers</code> table reference
     */
    public Followers(Name alias) {
        this(alias, FOLLOWERS);
    }

    private Followers(Name alias, Table<FollowersRecord> aliased) {
        this(alias, aliased, null);
    }

    private Followers(Name alias, Table<FollowersRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Twitch.TWITCH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.FOLLOWERS_FK_FOLLOWERS_USER_IDX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<FollowersRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<FollowersRecord, ?>>asList(Keys.FK_FOLLOWERS_USER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Followers as(String alias) {
        return new Followers(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Followers as(Name alias) {
        return new Followers(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Followers rename(String name) {
        return new Followers(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Followers rename(Name name) {
        return new Followers(name, null);
    }
}

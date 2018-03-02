/*
 * This file is generated by jOOQ.
*/
package twitch.explorer.database.jooq.db.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import twitch.explorer.database.jooq.db.Indexes;
import twitch.explorer.database.jooq.db.Keys;
import twitch.explorer.database.jooq.db.Twitch;
import twitch.explorer.database.jooq.db.tables.records.UserRecord;


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
public class User extends TableImpl<UserRecord> {

    private static final long serialVersionUID = -1371746633;

    /**
     * The reference instance of <code>twitch.user</code>
     */
    public static final User USER = new User();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserRecord> getRecordType() {
        return UserRecord.class;
    }

    /**
     * The column <code>twitch.user.user_id</code>.
     */
    public final TableField<UserRecord, Integer> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>twitch.user.login</code>.
     */
    public final TableField<UserRecord, String> LOGIN = createField("login", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>twitch.user.name</code>.
     */
    public final TableField<UserRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>twitch.user.description</code>.
     */
    public final TableField<UserRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>twitch.user.user_type_id</code>.
     */
    public final TableField<UserRecord, Integer> USER_TYPE_ID = createField("user_type_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>twitch.user.broadcaster_type_id</code>.
     */
    public final TableField<UserRecord, Integer> BROADCASTER_TYPE_ID = createField("broadcaster_type_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>twitch.user.offline_image</code>.
     */
    public final TableField<UserRecord, String> OFFLINE_IMAGE = createField("offline_image", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>twitch.user.profile_image</code>.
     */
    public final TableField<UserRecord, String> PROFILE_IMAGE = createField("profile_image", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>twitch.user.total_views</code>.
     */
    public final TableField<UserRecord, Integer> TOTAL_VIEWS = createField("total_views", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>twitch.user</code> table reference
     */
    public User() {
        this(DSL.name("user"), null);
    }

    /**
     * Create an aliased <code>twitch.user</code> table reference
     */
    public User(String alias) {
        this(DSL.name(alias), USER);
    }

    /**
     * Create an aliased <code>twitch.user</code> table reference
     */
    public User(Name alias) {
        this(alias, USER);
    }

    private User(Name alias, Table<UserRecord> aliased) {
        this(alias, aliased, null);
    }

    private User(Name alias, Table<UserRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.USER_BROADCASTERTYPE_ID_IDX, Indexes.USER_ID_IDX, Indexes.USER_ID_UNIQUE, Indexes.USER_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<UserRecord, Integer> getIdentity() {
        return Keys.IDENTITY_USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UserRecord> getPrimaryKey() {
        return Keys.KEY_USER_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UserRecord>> getKeys() {
        return Arrays.<UniqueKey<UserRecord>>asList(Keys.KEY_USER_PRIMARY, Keys.KEY_USER_ID_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<UserRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<UserRecord, ?>>asList(Keys.FK_USER_USER_TYPE, Keys.FK_USER_BROADCASTER_TYPE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User as(String alias) {
        return new User(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User as(Name alias) {
        return new User(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(String name) {
        return new User(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(Name name) {
        return new User(name, null);
    }
}
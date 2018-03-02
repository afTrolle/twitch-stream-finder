/*
 * This file is generated by jOOQ.
*/
package twitch.explorer.database.jooq.db.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
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
import twitch.explorer.database.jooq.db.tables.records.UserTypeRecord;


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
public class UserType extends TableImpl<UserTypeRecord> {

    private static final long serialVersionUID = 1617808887;

    /**
     * The reference instance of <code>twitch.user_type</code>
     */
    public static final UserType USER_TYPE = new UserType();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserTypeRecord> getRecordType() {
        return UserTypeRecord.class;
    }

    /**
     * The column <code>twitch.user_type.user_type_id</code>.
     */
    public final TableField<UserTypeRecord, Integer> USER_TYPE_ID = createField("user_type_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>twitch.user_type.type</code>. Current known users are User’s type: "staff", "admin", "global_mod", or "".
More info at https://dev.twitch.tv/docs/api/reference#get-users

     */
    public final TableField<UserTypeRecord, String> TYPE = createField("type", org.jooq.impl.SQLDataType.VARCHAR(45).nullable(false), this, "Current known users are User’s type: \"staff\", \"admin\", \"global_mod\", or \"\".\nMore info at https://dev.twitch.tv/docs/api/reference#get-users\n");

    /**
     * Create a <code>twitch.user_type</code> table reference
     */
    public UserType() {
        this(DSL.name("user_type"), null);
    }

    /**
     * Create an aliased <code>twitch.user_type</code> table reference
     */
    public UserType(String alias) {
        this(DSL.name(alias), USER_TYPE);
    }

    /**
     * Create an aliased <code>twitch.user_type</code> table reference
     */
    public UserType(Name alias) {
        this(alias, USER_TYPE);
    }

    private UserType(Name alias, Table<UserTypeRecord> aliased) {
        this(alias, aliased, null);
    }

    private UserType(Name alias, Table<UserTypeRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.USER_TYPE_ID_UNIQUE, Indexes.USER_TYPE_PRIMARY, Indexes.USER_TYPE_TYPE_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<UserTypeRecord, Integer> getIdentity() {
        return Keys.IDENTITY_USER_TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UserTypeRecord> getPrimaryKey() {
        return Keys.KEY_USER_TYPE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UserTypeRecord>> getKeys() {
        return Arrays.<UniqueKey<UserTypeRecord>>asList(Keys.KEY_USER_TYPE_PRIMARY, Keys.KEY_USER_TYPE_ID_UNIQUE, Keys.KEY_USER_TYPE_TYPE_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserType as(String alias) {
        return new UserType(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserType as(Name alias) {
        return new UserType(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UserType rename(String name) {
        return new UserType(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UserType rename(Name name) {
        return new UserType(name, null);
    }
}
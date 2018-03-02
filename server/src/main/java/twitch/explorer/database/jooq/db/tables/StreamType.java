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
import twitch.explorer.database.jooq.db.tables.records.StreamTypeRecord;


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
public class StreamType extends TableImpl<StreamTypeRecord> {

    private static final long serialVersionUID = -786286915;

    /**
     * The reference instance of <code>twitch.stream_type</code>
     */
    public static final StreamType STREAM_TYPE = new StreamType();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StreamTypeRecord> getRecordType() {
        return StreamTypeRecord.class;
    }

    /**
     * The column <code>twitch.stream_type.stream_type_id</code>.
     */
    public final TableField<StreamTypeRecord, Integer> STREAM_TYPE_ID = createField("stream_type_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>twitch.stream_type.name</code>.
     */
    public final TableField<StreamTypeRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(45).nullable(false), this, "");

    /**
     * Create a <code>twitch.stream_type</code> table reference
     */
    public StreamType() {
        this(DSL.name("stream_type"), null);
    }

    /**
     * Create an aliased <code>twitch.stream_type</code> table reference
     */
    public StreamType(String alias) {
        this(DSL.name(alias), STREAM_TYPE);
    }

    /**
     * Create an aliased <code>twitch.stream_type</code> table reference
     */
    public StreamType(Name alias) {
        this(alias, STREAM_TYPE);
    }

    private StreamType(Name alias, Table<StreamTypeRecord> aliased) {
        this(alias, aliased, null);
    }

    private StreamType(Name alias, Table<StreamTypeRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.STREAM_TYPE_NAME_UNIQUE, Indexes.STREAM_TYPE_PRIMARY, Indexes.STREAM_TYPE_STREAM_TYPE_ID_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<StreamTypeRecord, Integer> getIdentity() {
        return Keys.IDENTITY_STREAM_TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<StreamTypeRecord> getPrimaryKey() {
        return Keys.KEY_STREAM_TYPE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<StreamTypeRecord>> getKeys() {
        return Arrays.<UniqueKey<StreamTypeRecord>>asList(Keys.KEY_STREAM_TYPE_PRIMARY, Keys.KEY_STREAM_TYPE_STREAM_TYPE_ID_UNIQUE, Keys.KEY_STREAM_TYPE_NAME_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StreamType as(String alias) {
        return new StreamType(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StreamType as(Name alias) {
        return new StreamType(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public StreamType rename(String name) {
        return new StreamType(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public StreamType rename(Name name) {
        return new StreamType(name, null);
    }
}
/*
 * This file is generated by jOOQ.
*/
package twitch.explorer.database.jooq.db.tables.records;


import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row10;
import org.jooq.impl.UpdatableRecordImpl;

import twitch.explorer.database.jooq.db.tables.User;


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
public class UserRecord extends UpdatableRecordImpl<UserRecord> implements Record10<Long, String, String, String, Integer, Integer, String, String, Integer, Timestamp> {

    private static final long serialVersionUID = -1100272933;

    /**
     * Setter for <code>twitch.user.user_id</code>.
     */
    public void setUserId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>twitch.user.user_id</code>.
     */
    public Long getUserId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>twitch.user.login</code>.
     */
    public void setLogin(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>twitch.user.login</code>.
     */
    public String getLogin() {
        return (String) get(1);
    }

    /**
     * Setter for <code>twitch.user.name</code>.
     */
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>twitch.user.name</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>twitch.user.description</code>.
     */
    public void setDescription(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>twitch.user.description</code>.
     */
    public String getDescription() {
        return (String) get(3);
    }

    /**
     * Setter for <code>twitch.user.user_type_id</code>.
     */
    public void setUserTypeId(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>twitch.user.user_type_id</code>.
     */
    public Integer getUserTypeId() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>twitch.user.broadcaster_type_id</code>.
     */
    public void setBroadcasterTypeId(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>twitch.user.broadcaster_type_id</code>.
     */
    public Integer getBroadcasterTypeId() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>twitch.user.offline_image</code>.
     */
    public void setOfflineImage(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>twitch.user.offline_image</code>.
     */
    public String getOfflineImage() {
        return (String) get(6);
    }

    /**
     * Setter for <code>twitch.user.profile_image</code>.
     */
    public void setProfileImage(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>twitch.user.profile_image</code>.
     */
    public String getProfileImage() {
        return (String) get(7);
    }

    /**
     * Setter for <code>twitch.user.total_views</code>.
     */
    public void setTotalViews(Integer value) {
        set(8, value);
    }

    /**
     * Getter for <code>twitch.user.total_views</code>.
     */
    public Integer getTotalViews() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>twitch.user.updated</code>.
     */
    public void setUpdated(Timestamp value) {
        set(9, value);
    }

    /**
     * Getter for <code>twitch.user.updated</code>.
     */
    public Timestamp getUpdated() {
        return (Timestamp) get(9);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record10 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row10<Long, String, String, String, Integer, Integer, String, String, Integer, Timestamp> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row10<Long, String, String, String, Integer, Integer, String, String, Integer, Timestamp> valuesRow() {
        return (Row10) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return User.USER.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return User.USER.LOGIN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return User.USER.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return User.USER.DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return User.USER.USER_TYPE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return User.USER.BROADCASTER_TYPE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return User.USER.OFFLINE_IMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return User.USER.PROFILE_IMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field9() {
        return User.USER.TOTAL_VIEWS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field10() {
        return User.USER.UPDATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getLogin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component5() {
        return getUserTypeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component6() {
        return getBroadcasterTypeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getOfflineImage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getProfileImage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component9() {
        return getTotalViews();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component10() {
        return getUpdated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getLogin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getUserTypeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getBroadcasterTypeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getOfflineImage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getProfileImage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value9() {
        return getTotalViews();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value10() {
        return getUpdated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value1(Long value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value2(String value) {
        setLogin(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value3(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value4(String value) {
        setDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value5(Integer value) {
        setUserTypeId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value6(Integer value) {
        setBroadcasterTypeId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value7(String value) {
        setOfflineImage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value8(String value) {
        setProfileImage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value9(Integer value) {
        setTotalViews(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value10(Timestamp value) {
        setUpdated(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord values(Long value1, String value2, String value3, String value4, Integer value5, Integer value6, String value7, String value8, Integer value9, Timestamp value10) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserRecord
     */
    public UserRecord() {
        super(User.USER);
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(Long userId, String login, String name, String description, Integer userTypeId, Integer broadcasterTypeId, String offlineImage, String profileImage, Integer totalViews, Timestamp updated) {
        super(User.USER);

        set(0, userId);
        set(1, login);
        set(2, name);
        set(3, description);
        set(4, userTypeId);
        set(5, broadcasterTypeId);
        set(6, offlineImage);
        set(7, profileImage);
        set(8, totalViews);
        set(9, updated);
    }
}

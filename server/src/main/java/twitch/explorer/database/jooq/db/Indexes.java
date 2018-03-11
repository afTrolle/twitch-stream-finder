/*
 * This file is generated by jOOQ.
*/
package twitch.explorer.database.jooq.db;


import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;

import twitch.explorer.database.jooq.db.tables.BroadcasterType;
import twitch.explorer.database.jooq.db.tables.Client;
import twitch.explorer.database.jooq.db.tables.Community;
import twitch.explorer.database.jooq.db.tables.Followers;
import twitch.explorer.database.jooq.db.tables.Game;
import twitch.explorer.database.jooq.db.tables.Language;
import twitch.explorer.database.jooq.db.tables.Stream;
import twitch.explorer.database.jooq.db.tables.StreamCommunity;
import twitch.explorer.database.jooq.db.tables.StreamType;
import twitch.explorer.database.jooq.db.tables.User;
import twitch.explorer.database.jooq.db.tables.UserType;
import twitch.explorer.database.jooq.db.tables.Vote;


/**
 * A class modelling indexes of tables of the <code>twitch</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index BROADCASTER_TYPE_ID_UNIQUE = Indexes0.BROADCASTER_TYPE_ID_UNIQUE;
    public static final Index BROADCASTER_TYPE_PRIMARY = Indexes0.BROADCASTER_TYPE_PRIMARY;
    public static final Index BROADCASTER_TYPE_TYPE_UNIQUE = Indexes0.BROADCASTER_TYPE_TYPE_UNIQUE;
    public static final Index CLIENT_ID_UNIQUE = Indexes0.CLIENT_ID_UNIQUE;
    public static final Index CLIENT_PRIMARY = Indexes0.CLIENT_PRIMARY;
    public static final Index COMMUNITY_NAME_UNIQUE = Indexes0.COMMUNITY_NAME_UNIQUE;
    public static final Index COMMUNITY_PRIMARY = Indexes0.COMMUNITY_PRIMARY;
    public static final Index FOLLOWERS_FK_FOLLOWERS_USER_IDX = Indexes0.FOLLOWERS_FK_FOLLOWERS_USER_IDX;
    public static final Index FOLLOWERS_PRIMARY = Indexes0.FOLLOWERS_PRIMARY;
    public static final Index GAME_ART_URL_UNIQUE = Indexes0.GAME_ART_URL_UNIQUE;
    public static final Index GAME_ID_UNIQUE = Indexes0.GAME_ID_UNIQUE;
    public static final Index GAME_NAME_UNIQUE = Indexes0.GAME_NAME_UNIQUE;
    public static final Index GAME_PRIMARY = Indexes0.GAME_PRIMARY;
    public static final Index LANGUAGE_LANGUAGE_ID_UNIQUE = Indexes0.LANGUAGE_LANGUAGE_ID_UNIQUE;
    public static final Index LANGUAGE_NAME_UNIQUE = Indexes0.LANGUAGE_NAME_UNIQUE;
    public static final Index LANGUAGE_PRIMARY = Indexes0.LANGUAGE_PRIMARY;
    public static final Index STREAM_FK_STREAM_USER_IDX = Indexes0.STREAM_FK_STREAM_USER_IDX;
    public static final Index STREAM_GAME_ID_FK_IDX = Indexes0.STREAM_GAME_ID_FK_IDX;
    public static final Index STREAM_LANGUAGE_ID_FK_IDX = Indexes0.STREAM_LANGUAGE_ID_FK_IDX;
    public static final Index STREAM_PRIMARY = Indexes0.STREAM_PRIMARY;
    public static final Index STREAM_STREAM_ID_UNIQUE = Indexes0.STREAM_STREAM_ID_UNIQUE;
    public static final Index STREAM_STREAM_TYPE_ID_FK_IDX = Indexes0.STREAM_STREAM_TYPE_ID_FK_IDX;
    public static final Index STREAM_COMMUNITY_COMMUNITY_ID_FK_IDX = Indexes0.STREAM_COMMUNITY_COMMUNITY_ID_FK_IDX;
    public static final Index STREAM_TYPE_NAME_UNIQUE = Indexes0.STREAM_TYPE_NAME_UNIQUE;
    public static final Index STREAM_TYPE_PRIMARY = Indexes0.STREAM_TYPE_PRIMARY;
    public static final Index STREAM_TYPE_STREAM_TYPE_ID_UNIQUE = Indexes0.STREAM_TYPE_STREAM_TYPE_ID_UNIQUE;
    public static final Index USER_BROADCASTERTYPE_ID_IDX = Indexes0.USER_BROADCASTERTYPE_ID_IDX;
    public static final Index USER_ID_IDX = Indexes0.USER_ID_IDX;
    public static final Index USER_ID_UNIQUE = Indexes0.USER_ID_UNIQUE;
    public static final Index USER_PRIMARY = Indexes0.USER_PRIMARY;
    public static final Index USER_TYPE_ID_UNIQUE = Indexes0.USER_TYPE_ID_UNIQUE;
    public static final Index USER_TYPE_PRIMARY = Indexes0.USER_TYPE_PRIMARY;
    public static final Index USER_TYPE_TYPE_UNIQUE = Indexes0.USER_TYPE_TYPE_UNIQUE;
    public static final Index VOTE_COOKIE_UNIQUE = Indexes0.VOTE_COOKIE_UNIQUE;
    public static final Index VOTE_PRIMARY = Indexes0.VOTE_PRIMARY;
    public static final Index VOTE_USER_ID_FK_IDX = Indexes0.VOTE_USER_ID_FK_IDX;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index BROADCASTER_TYPE_ID_UNIQUE = Internal.createIndex("id_UNIQUE", BroadcasterType.BROADCASTER_TYPE, new OrderField[] { BroadcasterType.BROADCASTER_TYPE.BROADCASTER_TYPE_ID }, true);
        public static Index BROADCASTER_TYPE_PRIMARY = Internal.createIndex("PRIMARY", BroadcasterType.BROADCASTER_TYPE, new OrderField[] { BroadcasterType.BROADCASTER_TYPE.BROADCASTER_TYPE_ID }, true);
        public static Index BROADCASTER_TYPE_TYPE_UNIQUE = Internal.createIndex("type_UNIQUE", BroadcasterType.BROADCASTER_TYPE, new OrderField[] { BroadcasterType.BROADCASTER_TYPE.TYPE }, true);
        public static Index CLIENT_ID_UNIQUE = Internal.createIndex("id_UNIQUE", Client.CLIENT, new OrderField[] { Client.CLIENT.CLIENT_ID }, true);
        public static Index CLIENT_PRIMARY = Internal.createIndex("PRIMARY", Client.CLIENT, new OrderField[] { Client.CLIENT.CLIENT_ID }, true);
        public static Index COMMUNITY_NAME_UNIQUE = Internal.createIndex("name_UNIQUE", Community.COMMUNITY, new OrderField[] { Community.COMMUNITY.NAME }, true);
        public static Index COMMUNITY_PRIMARY = Internal.createIndex("PRIMARY", Community.COMMUNITY, new OrderField[] { Community.COMMUNITY.COMMUNITY_ID }, true);
        public static Index FOLLOWERS_FK_FOLLOWERS_USER_IDX = Internal.createIndex("fk_followers_user_idx", Followers.FOLLOWERS, new OrderField[] { Followers.FOLLOWERS.USER_ID }, false);
        public static Index FOLLOWERS_PRIMARY = Internal.createIndex("PRIMARY", Followers.FOLLOWERS, new OrderField[] { Followers.FOLLOWERS.FOLLOWERS_ID }, true);
        public static Index GAME_ART_URL_UNIQUE = Internal.createIndex("art_url_UNIQUE", Game.GAME, new OrderField[] { Game.GAME.ART_URL }, true);
        public static Index GAME_ID_UNIQUE = Internal.createIndex("id_UNIQUE", Game.GAME, new OrderField[] { Game.GAME.GAME_ID }, true);
        public static Index GAME_NAME_UNIQUE = Internal.createIndex("name_UNIQUE", Game.GAME, new OrderField[] { Game.GAME.NAME }, true);
        public static Index GAME_PRIMARY = Internal.createIndex("PRIMARY", Game.GAME, new OrderField[] { Game.GAME.GAME_ID }, true);
        public static Index LANGUAGE_LANGUAGE_ID_UNIQUE = Internal.createIndex("language_id_UNIQUE", Language.LANGUAGE, new OrderField[] { Language.LANGUAGE.LANGUAGE_ID }, true);
        public static Index LANGUAGE_NAME_UNIQUE = Internal.createIndex("name_UNIQUE", Language.LANGUAGE, new OrderField[] { Language.LANGUAGE.NAME }, true);
        public static Index LANGUAGE_PRIMARY = Internal.createIndex("PRIMARY", Language.LANGUAGE, new OrderField[] { Language.LANGUAGE.LANGUAGE_ID }, true);
        public static Index STREAM_FK_STREAM_USER_IDX = Internal.createIndex("fk_stream_user_idx", Stream.STREAM, new OrderField[] { Stream.STREAM.USER_ID }, false);
        public static Index STREAM_GAME_ID_FK_IDX = Internal.createIndex("game_id_fk_idx", Stream.STREAM, new OrderField[] { Stream.STREAM.GAME_ID }, false);
        public static Index STREAM_LANGUAGE_ID_FK_IDX = Internal.createIndex("language_id_fk_idx", Stream.STREAM, new OrderField[] { Stream.STREAM.LANGUAGE_ID }, false);
        public static Index STREAM_PRIMARY = Internal.createIndex("PRIMARY", Stream.STREAM, new OrderField[] { Stream.STREAM.STREAM_ID }, true);
        public static Index STREAM_STREAM_ID_UNIQUE = Internal.createIndex("stream_id_UNIQUE", Stream.STREAM, new OrderField[] { Stream.STREAM.STREAM_ID }, true);
        public static Index STREAM_STREAM_TYPE_ID_FK_IDX = Internal.createIndex("stream_type_id_fk_idx", Stream.STREAM, new OrderField[] { Stream.STREAM.STREAM_TYPE_ID }, false);
        public static Index STREAM_COMMUNITY_COMMUNITY_ID_FK_IDX = Internal.createIndex("community_id_fk_idx", StreamCommunity.STREAM_COMMUNITY, new OrderField[] { StreamCommunity.STREAM_COMMUNITY.COMMUNITY_ID }, false);
        public static Index STREAM_TYPE_NAME_UNIQUE = Internal.createIndex("name_UNIQUE", StreamType.STREAM_TYPE, new OrderField[] { StreamType.STREAM_TYPE.NAME }, true);
        public static Index STREAM_TYPE_PRIMARY = Internal.createIndex("PRIMARY", StreamType.STREAM_TYPE, new OrderField[] { StreamType.STREAM_TYPE.STREAM_TYPE_ID }, true);
        public static Index STREAM_TYPE_STREAM_TYPE_ID_UNIQUE = Internal.createIndex("stream_type_id_UNIQUE", StreamType.STREAM_TYPE, new OrderField[] { StreamType.STREAM_TYPE.STREAM_TYPE_ID }, true);
        public static Index USER_BROADCASTERTYPE_ID_IDX = Internal.createIndex("broadcastertype_id_idx", User.USER, new OrderField[] { User.USER.BROADCASTER_TYPE_ID }, false);
        public static Index USER_ID_IDX = Internal.createIndex("id_idx", User.USER, new OrderField[] { User.USER.USER_TYPE_ID }, false);
        public static Index USER_ID_UNIQUE = Internal.createIndex("id_UNIQUE", User.USER, new OrderField[] { User.USER.USER_ID }, true);
        public static Index USER_PRIMARY = Internal.createIndex("PRIMARY", User.USER, new OrderField[] { User.USER.USER_ID }, true);
        public static Index USER_TYPE_ID_UNIQUE = Internal.createIndex("id_UNIQUE", UserType.USER_TYPE, new OrderField[] { UserType.USER_TYPE.USER_TYPE_ID }, true);
        public static Index USER_TYPE_PRIMARY = Internal.createIndex("PRIMARY", UserType.USER_TYPE, new OrderField[] { UserType.USER_TYPE.USER_TYPE_ID }, true);
        public static Index USER_TYPE_TYPE_UNIQUE = Internal.createIndex("type_UNIQUE", UserType.USER_TYPE, new OrderField[] { UserType.USER_TYPE.TYPE }, true);
        public static Index VOTE_COOKIE_UNIQUE = Internal.createIndex("cookie_UNIQUE", Vote.VOTE, new OrderField[] { Vote.VOTE.COOKIE }, true);
        public static Index VOTE_PRIMARY = Internal.createIndex("PRIMARY", Vote.VOTE, new OrderField[] { Vote.VOTE.VOTE_ID }, true);
        public static Index VOTE_USER_ID_FK_IDX = Internal.createIndex("user_id_fk_idx", Vote.VOTE, new OrderField[] { Vote.VOTE.USER_ID }, false);
    }
}

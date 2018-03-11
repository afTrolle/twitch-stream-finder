/*
 * This file is generated by jOOQ.
*/
package twitch.explorer.database.jooq.db;


import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
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
import twitch.explorer.database.jooq.db.tables.records.BroadcasterTypeRecord;
import twitch.explorer.database.jooq.db.tables.records.ClientRecord;
import twitch.explorer.database.jooq.db.tables.records.CommunityRecord;
import twitch.explorer.database.jooq.db.tables.records.FollowersRecord;
import twitch.explorer.database.jooq.db.tables.records.GameRecord;
import twitch.explorer.database.jooq.db.tables.records.LanguageRecord;
import twitch.explorer.database.jooq.db.tables.records.StreamCommunityRecord;
import twitch.explorer.database.jooq.db.tables.records.StreamRecord;
import twitch.explorer.database.jooq.db.tables.records.StreamTypeRecord;
import twitch.explorer.database.jooq.db.tables.records.UserRecord;
import twitch.explorer.database.jooq.db.tables.records.UserTypeRecord;
import twitch.explorer.database.jooq.db.tables.records.VoteRecord;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>twitch</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<BroadcasterTypeRecord, Integer> IDENTITY_BROADCASTER_TYPE = Identities0.IDENTITY_BROADCASTER_TYPE;
    public static final Identity<ClientRecord, Integer> IDENTITY_CLIENT = Identities0.IDENTITY_CLIENT;
    public static final Identity<CommunityRecord, Integer> IDENTITY_COMMUNITY = Identities0.IDENTITY_COMMUNITY;
    public static final Identity<FollowersRecord, Integer> IDENTITY_FOLLOWERS = Identities0.IDENTITY_FOLLOWERS;
    public static final Identity<GameRecord, Integer> IDENTITY_GAME = Identities0.IDENTITY_GAME;
    public static final Identity<LanguageRecord, Integer> IDENTITY_LANGUAGE = Identities0.IDENTITY_LANGUAGE;
    public static final Identity<StreamTypeRecord, Integer> IDENTITY_STREAM_TYPE = Identities0.IDENTITY_STREAM_TYPE;
    public static final Identity<UserTypeRecord, Integer> IDENTITY_USER_TYPE = Identities0.IDENTITY_USER_TYPE;
    public static final Identity<VoteRecord, Long> IDENTITY_VOTE = Identities0.IDENTITY_VOTE;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<BroadcasterTypeRecord> KEY_BROADCASTER_TYPE_PRIMARY = UniqueKeys0.KEY_BROADCASTER_TYPE_PRIMARY;
    public static final UniqueKey<BroadcasterTypeRecord> KEY_BROADCASTER_TYPE_ID_UNIQUE = UniqueKeys0.KEY_BROADCASTER_TYPE_ID_UNIQUE;
    public static final UniqueKey<BroadcasterTypeRecord> KEY_BROADCASTER_TYPE_TYPE_UNIQUE = UniqueKeys0.KEY_BROADCASTER_TYPE_TYPE_UNIQUE;
    public static final UniqueKey<ClientRecord> KEY_CLIENT_PRIMARY = UniqueKeys0.KEY_CLIENT_PRIMARY;
    public static final UniqueKey<ClientRecord> KEY_CLIENT_ID_UNIQUE = UniqueKeys0.KEY_CLIENT_ID_UNIQUE;
    public static final UniqueKey<CommunityRecord> KEY_COMMUNITY_PRIMARY = UniqueKeys0.KEY_COMMUNITY_PRIMARY;
    public static final UniqueKey<CommunityRecord> KEY_COMMUNITY_NAME_UNIQUE = UniqueKeys0.KEY_COMMUNITY_NAME_UNIQUE;
    public static final UniqueKey<FollowersRecord> KEY_FOLLOWERS_PRIMARY = UniqueKeys0.KEY_FOLLOWERS_PRIMARY;
    public static final UniqueKey<GameRecord> KEY_GAME_PRIMARY = UniqueKeys0.KEY_GAME_PRIMARY;
    public static final UniqueKey<GameRecord> KEY_GAME_ID_UNIQUE = UniqueKeys0.KEY_GAME_ID_UNIQUE;
    public static final UniqueKey<GameRecord> KEY_GAME_NAME_UNIQUE = UniqueKeys0.KEY_GAME_NAME_UNIQUE;
    public static final UniqueKey<GameRecord> KEY_GAME_ART_URL_UNIQUE = UniqueKeys0.KEY_GAME_ART_URL_UNIQUE;
    public static final UniqueKey<LanguageRecord> KEY_LANGUAGE_PRIMARY = UniqueKeys0.KEY_LANGUAGE_PRIMARY;
    public static final UniqueKey<LanguageRecord> KEY_LANGUAGE_LANGUAGE_ID_UNIQUE = UniqueKeys0.KEY_LANGUAGE_LANGUAGE_ID_UNIQUE;
    public static final UniqueKey<LanguageRecord> KEY_LANGUAGE_NAME_UNIQUE = UniqueKeys0.KEY_LANGUAGE_NAME_UNIQUE;
    public static final UniqueKey<StreamRecord> KEY_STREAM_PRIMARY = UniqueKeys0.KEY_STREAM_PRIMARY;
    public static final UniqueKey<StreamRecord> KEY_STREAM_STREAM_ID_UNIQUE = UniqueKeys0.KEY_STREAM_STREAM_ID_UNIQUE;
    public static final UniqueKey<StreamTypeRecord> KEY_STREAM_TYPE_PRIMARY = UniqueKeys0.KEY_STREAM_TYPE_PRIMARY;
    public static final UniqueKey<StreamTypeRecord> KEY_STREAM_TYPE_STREAM_TYPE_ID_UNIQUE = UniqueKeys0.KEY_STREAM_TYPE_STREAM_TYPE_ID_UNIQUE;
    public static final UniqueKey<StreamTypeRecord> KEY_STREAM_TYPE_NAME_UNIQUE = UniqueKeys0.KEY_STREAM_TYPE_NAME_UNIQUE;
    public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = UniqueKeys0.KEY_USER_PRIMARY;
    public static final UniqueKey<UserRecord> KEY_USER_ID_UNIQUE = UniqueKeys0.KEY_USER_ID_UNIQUE;
    public static final UniqueKey<UserTypeRecord> KEY_USER_TYPE_PRIMARY = UniqueKeys0.KEY_USER_TYPE_PRIMARY;
    public static final UniqueKey<UserTypeRecord> KEY_USER_TYPE_ID_UNIQUE = UniqueKeys0.KEY_USER_TYPE_ID_UNIQUE;
    public static final UniqueKey<UserTypeRecord> KEY_USER_TYPE_TYPE_UNIQUE = UniqueKeys0.KEY_USER_TYPE_TYPE_UNIQUE;
    public static final UniqueKey<VoteRecord> KEY_VOTE_PRIMARY = UniqueKeys0.KEY_VOTE_PRIMARY;
    public static final UniqueKey<VoteRecord> KEY_VOTE_COOKIE_UNIQUE = UniqueKeys0.KEY_VOTE_COOKIE_UNIQUE;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<FollowersRecord, UserRecord> FK_FOLLOWERS_USER = ForeignKeys0.FK_FOLLOWERS_USER;
    public static final ForeignKey<StreamRecord, UserRecord> FK_STREAM_USER = ForeignKeys0.FK_STREAM_USER;
    public static final ForeignKey<StreamRecord, GameRecord> FK_STREAM_GAME = ForeignKeys0.FK_STREAM_GAME;
    public static final ForeignKey<StreamRecord, StreamTypeRecord> FK_STREAM_STREAM_TYPE = ForeignKeys0.FK_STREAM_STREAM_TYPE;
    public static final ForeignKey<StreamRecord, LanguageRecord> FK_STREAM_LANGUAGE = ForeignKeys0.FK_STREAM_LANGUAGE;
    public static final ForeignKey<StreamCommunityRecord, CommunityRecord> FK_STREAM_COMMUNITY_COMMUNITY = ForeignKeys0.FK_STREAM_COMMUNITY_COMMUNITY;
    public static final ForeignKey<UserRecord, UserTypeRecord> FK_USER_USER_TYPE = ForeignKeys0.FK_USER_USER_TYPE;
    public static final ForeignKey<UserRecord, BroadcasterTypeRecord> FK_USER_BROADCASTER_TYPE = ForeignKeys0.FK_USER_BROADCASTER_TYPE;
    public static final ForeignKey<VoteRecord, UserRecord> FK_VOTE_USER = ForeignKeys0.FK_VOTE_USER;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<BroadcasterTypeRecord, Integer> IDENTITY_BROADCASTER_TYPE = Internal.createIdentity(BroadcasterType.BROADCASTER_TYPE, BroadcasterType.BROADCASTER_TYPE.BROADCASTER_TYPE_ID);
        public static Identity<ClientRecord, Integer> IDENTITY_CLIENT = Internal.createIdentity(Client.CLIENT, Client.CLIENT.CLIENT_ID);
        public static Identity<CommunityRecord, Integer> IDENTITY_COMMUNITY = Internal.createIdentity(Community.COMMUNITY, Community.COMMUNITY.COMMUNITY_ID);
        public static Identity<FollowersRecord, Integer> IDENTITY_FOLLOWERS = Internal.createIdentity(Followers.FOLLOWERS, Followers.FOLLOWERS.FOLLOWERS_ID);
        public static Identity<GameRecord, Integer> IDENTITY_GAME = Internal.createIdentity(Game.GAME, Game.GAME.GAME_ID);
        public static Identity<LanguageRecord, Integer> IDENTITY_LANGUAGE = Internal.createIdentity(Language.LANGUAGE, Language.LANGUAGE.LANGUAGE_ID);
        public static Identity<StreamTypeRecord, Integer> IDENTITY_STREAM_TYPE = Internal.createIdentity(StreamType.STREAM_TYPE, StreamType.STREAM_TYPE.STREAM_TYPE_ID);
        public static Identity<UserTypeRecord, Integer> IDENTITY_USER_TYPE = Internal.createIdentity(UserType.USER_TYPE, UserType.USER_TYPE.USER_TYPE_ID);
        public static Identity<VoteRecord, Long> IDENTITY_VOTE = Internal.createIdentity(Vote.VOTE, Vote.VOTE.VOTE_ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<BroadcasterTypeRecord> KEY_BROADCASTER_TYPE_PRIMARY = Internal.createUniqueKey(BroadcasterType.BROADCASTER_TYPE, "KEY_broadcaster_type_PRIMARY", BroadcasterType.BROADCASTER_TYPE.BROADCASTER_TYPE_ID);
        public static final UniqueKey<BroadcasterTypeRecord> KEY_BROADCASTER_TYPE_ID_UNIQUE = Internal.createUniqueKey(BroadcasterType.BROADCASTER_TYPE, "KEY_broadcaster_type_id_UNIQUE", BroadcasterType.BROADCASTER_TYPE.BROADCASTER_TYPE_ID);
        public static final UniqueKey<BroadcasterTypeRecord> KEY_BROADCASTER_TYPE_TYPE_UNIQUE = Internal.createUniqueKey(BroadcasterType.BROADCASTER_TYPE, "KEY_broadcaster_type_type_UNIQUE", BroadcasterType.BROADCASTER_TYPE.TYPE);
        public static final UniqueKey<ClientRecord> KEY_CLIENT_PRIMARY = Internal.createUniqueKey(Client.CLIENT, "KEY_client_PRIMARY", Client.CLIENT.CLIENT_ID);
        public static final UniqueKey<ClientRecord> KEY_CLIENT_ID_UNIQUE = Internal.createUniqueKey(Client.CLIENT, "KEY_client_id_UNIQUE", Client.CLIENT.CLIENT_ID);
        public static final UniqueKey<CommunityRecord> KEY_COMMUNITY_PRIMARY = Internal.createUniqueKey(Community.COMMUNITY, "KEY_community_PRIMARY", Community.COMMUNITY.COMMUNITY_ID);
        public static final UniqueKey<CommunityRecord> KEY_COMMUNITY_NAME_UNIQUE = Internal.createUniqueKey(Community.COMMUNITY, "KEY_community_name_UNIQUE", Community.COMMUNITY.NAME);
        public static final UniqueKey<FollowersRecord> KEY_FOLLOWERS_PRIMARY = Internal.createUniqueKey(Followers.FOLLOWERS, "KEY_followers_PRIMARY", Followers.FOLLOWERS.FOLLOWERS_ID);
        public static final UniqueKey<GameRecord> KEY_GAME_PRIMARY = Internal.createUniqueKey(Game.GAME, "KEY_game_PRIMARY", Game.GAME.GAME_ID);
        public static final UniqueKey<GameRecord> KEY_GAME_ID_UNIQUE = Internal.createUniqueKey(Game.GAME, "KEY_game_id_UNIQUE", Game.GAME.GAME_ID);
        public static final UniqueKey<GameRecord> KEY_GAME_NAME_UNIQUE = Internal.createUniqueKey(Game.GAME, "KEY_game_name_UNIQUE", Game.GAME.NAME);
        public static final UniqueKey<GameRecord> KEY_GAME_ART_URL_UNIQUE = Internal.createUniqueKey(Game.GAME, "KEY_game_art_url_UNIQUE", Game.GAME.ART_URL);
        public static final UniqueKey<LanguageRecord> KEY_LANGUAGE_PRIMARY = Internal.createUniqueKey(Language.LANGUAGE, "KEY_language_PRIMARY", Language.LANGUAGE.LANGUAGE_ID);
        public static final UniqueKey<LanguageRecord> KEY_LANGUAGE_LANGUAGE_ID_UNIQUE = Internal.createUniqueKey(Language.LANGUAGE, "KEY_language_language_id_UNIQUE", Language.LANGUAGE.LANGUAGE_ID);
        public static final UniqueKey<LanguageRecord> KEY_LANGUAGE_NAME_UNIQUE = Internal.createUniqueKey(Language.LANGUAGE, "KEY_language_name_UNIQUE", Language.LANGUAGE.NAME);
        public static final UniqueKey<StreamRecord> KEY_STREAM_PRIMARY = Internal.createUniqueKey(Stream.STREAM, "KEY_stream_PRIMARY", Stream.STREAM.STREAM_ID);
        public static final UniqueKey<StreamRecord> KEY_STREAM_STREAM_ID_UNIQUE = Internal.createUniqueKey(Stream.STREAM, "KEY_stream_stream_id_UNIQUE", Stream.STREAM.STREAM_ID);
        public static final UniqueKey<StreamTypeRecord> KEY_STREAM_TYPE_PRIMARY = Internal.createUniqueKey(StreamType.STREAM_TYPE, "KEY_stream_type_PRIMARY", StreamType.STREAM_TYPE.STREAM_TYPE_ID);
        public static final UniqueKey<StreamTypeRecord> KEY_STREAM_TYPE_STREAM_TYPE_ID_UNIQUE = Internal.createUniqueKey(StreamType.STREAM_TYPE, "KEY_stream_type_stream_type_id_UNIQUE", StreamType.STREAM_TYPE.STREAM_TYPE_ID);
        public static final UniqueKey<StreamTypeRecord> KEY_STREAM_TYPE_NAME_UNIQUE = Internal.createUniqueKey(StreamType.STREAM_TYPE, "KEY_stream_type_name_UNIQUE", StreamType.STREAM_TYPE.NAME);
        public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = Internal.createUniqueKey(User.USER, "KEY_user_PRIMARY", User.USER.USER_ID);
        public static final UniqueKey<UserRecord> KEY_USER_ID_UNIQUE = Internal.createUniqueKey(User.USER, "KEY_user_id_UNIQUE", User.USER.USER_ID);
        public static final UniqueKey<UserTypeRecord> KEY_USER_TYPE_PRIMARY = Internal.createUniqueKey(UserType.USER_TYPE, "KEY_user_type_PRIMARY", UserType.USER_TYPE.USER_TYPE_ID);
        public static final UniqueKey<UserTypeRecord> KEY_USER_TYPE_ID_UNIQUE = Internal.createUniqueKey(UserType.USER_TYPE, "KEY_user_type_id_UNIQUE", UserType.USER_TYPE.USER_TYPE_ID);
        public static final UniqueKey<UserTypeRecord> KEY_USER_TYPE_TYPE_UNIQUE = Internal.createUniqueKey(UserType.USER_TYPE, "KEY_user_type_type_UNIQUE", UserType.USER_TYPE.TYPE);
        public static final UniqueKey<VoteRecord> KEY_VOTE_PRIMARY = Internal.createUniqueKey(Vote.VOTE, "KEY_vote_PRIMARY", Vote.VOTE.VOTE_ID);
        public static final UniqueKey<VoteRecord> KEY_VOTE_COOKIE_UNIQUE = Internal.createUniqueKey(Vote.VOTE, "KEY_vote_cookie_UNIQUE", Vote.VOTE.COOKIE);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<FollowersRecord, UserRecord> FK_FOLLOWERS_USER = Internal.createForeignKey(twitch.explorer.database.jooq.db.Keys.KEY_USER_PRIMARY, Followers.FOLLOWERS, "fk_followers_user", Followers.FOLLOWERS.USER_ID);
        public static final ForeignKey<StreamRecord, UserRecord> FK_STREAM_USER = Internal.createForeignKey(twitch.explorer.database.jooq.db.Keys.KEY_USER_PRIMARY, Stream.STREAM, "fk_stream_user", Stream.STREAM.USER_ID);
        public static final ForeignKey<StreamRecord, GameRecord> FK_STREAM_GAME = Internal.createForeignKey(twitch.explorer.database.jooq.db.Keys.KEY_GAME_PRIMARY, Stream.STREAM, "fk_stream_game", Stream.STREAM.GAME_ID);
        public static final ForeignKey<StreamRecord, StreamTypeRecord> FK_STREAM_STREAM_TYPE = Internal.createForeignKey(twitch.explorer.database.jooq.db.Keys.KEY_STREAM_TYPE_PRIMARY, Stream.STREAM, "fk_stream_stream_type", Stream.STREAM.STREAM_TYPE_ID);
        public static final ForeignKey<StreamRecord, LanguageRecord> FK_STREAM_LANGUAGE = Internal.createForeignKey(twitch.explorer.database.jooq.db.Keys.KEY_LANGUAGE_PRIMARY, Stream.STREAM, "fk_stream_language", Stream.STREAM.LANGUAGE_ID);
        public static final ForeignKey<StreamCommunityRecord, CommunityRecord> FK_STREAM_COMMUNITY_COMMUNITY = Internal.createForeignKey(twitch.explorer.database.jooq.db.Keys.KEY_COMMUNITY_PRIMARY, StreamCommunity.STREAM_COMMUNITY, "fk_stream_community_community", StreamCommunity.STREAM_COMMUNITY.COMMUNITY_ID);
        public static final ForeignKey<UserRecord, UserTypeRecord> FK_USER_USER_TYPE = Internal.createForeignKey(twitch.explorer.database.jooq.db.Keys.KEY_USER_TYPE_PRIMARY, User.USER, "fk_user_user_type", User.USER.USER_TYPE_ID);
        public static final ForeignKey<UserRecord, BroadcasterTypeRecord> FK_USER_BROADCASTER_TYPE = Internal.createForeignKey(twitch.explorer.database.jooq.db.Keys.KEY_BROADCASTER_TYPE_PRIMARY, User.USER, "fk_user_broadcaster_type", User.USER.BROADCASTER_TYPE_ID);
        public static final ForeignKey<VoteRecord, UserRecord> FK_VOTE_USER = Internal.createForeignKey(twitch.explorer.database.jooq.db.Keys.KEY_USER_PRIMARY, Vote.VOTE, "fk_vote_user", Vote.VOTE.USER_ID);
    }
}

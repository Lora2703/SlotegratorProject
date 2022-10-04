package cache;

import lombok.Getter;

@Getter
public enum TestCacheKey {
    ACCESS_TOKEN("accessToken"),
    USERNAME("username"),
    PASSWORD_CHANGE("passwordChange"),
    PASSWORD_REPEAT("passwordRepeat"),
    EMAIL("email"),
    NAME("name"),
    SURNAME("surname"),
    PLAYER_ID("playerId");

    private final String key;

    TestCacheKey(String key) {
        this.key = key;
    }
}

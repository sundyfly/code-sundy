package com.sundy.authorization.model;

/**
 *
 * @author sundy
 * @date 2017年10月19日 10:41
 */
/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年03月15日 10:23:08
 * 描述：Token的Model类，可以增加字段提高安全性，例如时间戳、url签名
 */
public class TokenModel {

    /**
     * 用户id
     */
    private long userId;

    /**
     * 随机生成的uuid
     */
    private String uuid;

    /**
     * 时间戳
     */
    private String timestamp;

    public TokenModel(long userId, String uuid, String timestamp) {
        this.userId = userId;
        this.uuid = uuid;
        this.timestamp = timestamp;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getToken() {
        return userId + "_" + timestamp + "_" + uuid;
    }

    @Override
    public String toString() {
        return "TokenModel{" +
                "userId=" + userId +
                ", uuid='" + uuid + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}

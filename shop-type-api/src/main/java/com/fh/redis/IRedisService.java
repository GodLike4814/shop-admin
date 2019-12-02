package com.fh.redis;

public interface IRedisService {

    void setStringKeyAndValue(String key, Object value);

    Boolean isExistKey(String categoryAll);

    Object getStringValueByKey(String categoryAll);
}

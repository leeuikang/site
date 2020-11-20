package com.example.site.common.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandMap {
    private Map<String, Object> map = new HashMap<>();

    public Object get(String key) {
        return map.get(key);
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public Object remove(String key) {
        return map.remove(key);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public boolean containsValue(String value) {
        return map.containsValue(value);
    }

    public void putAll(Map<? extends String, ?> m) {
        map.putAll(m);
    }

    public void clear() {
        map.clear();
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return map.entrySet();
    }

    public Map<String, Object> getMap(){
        return map;
    }

}

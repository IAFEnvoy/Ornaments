package iafenvoy.ornaments.config;

import com.google.gson.JsonSyntaxException;

public interface Config {
    void copy(String value) throws JsonSyntaxException;

    String toJson();
}

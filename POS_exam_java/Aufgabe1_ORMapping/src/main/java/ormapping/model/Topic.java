package ormapping.model;

import lombok.Getter;

import java.security.InvalidParameterException;
import java.util.Arrays;

@Getter
public enum Topic {
    SOFTWARE_ENGINEERING("SE");

    private final String shortName;

    Topic(String shortName) {
        this.shortName = shortName;
    }

    public static Topic findTopicByShortName(String shortName) {
        return Arrays.stream(Topic.values())
                .filter(t -> t.getShortName().equalsIgnoreCase(shortName))
                .findFirst()
                .orElseThrow(InvalidParameterException::new);
    }

}

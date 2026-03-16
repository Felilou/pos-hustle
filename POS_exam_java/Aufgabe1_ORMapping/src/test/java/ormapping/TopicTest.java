package ormapping;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TopicTest {

    @Nested
    class test_find_topic_by_shortname {

        @Test
        void good_case(){
            String shortName = "se";
            assertEquals(Topic.SOFTWARE_ENGINEERING, Topic.findTopicByShortName(shortName));
        }

        @Test
        void bad_case(){
            String shortName = "hansi";
            assertThrows(InvalidParameterException.class, () -> {
                Topic.findTopicByShortName(shortName);
            });
        }

    }

}
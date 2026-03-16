package ormapping;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseSubscriptionTest {

    public static CourseSubscription test;

    @BeforeAll
    static void SetUp(){
        test = CourseSubscription.builder()
                .course(new Course())
                .attendee(new Attendee())
                .subscriptionTimestamp(LocalDateTime.now())
                .attendance(new Attendance())
                .attendance(new Attendance())
                .attendances(List.of(new Attendance(), new Attendance()))
                .build();
    }

    @Test
    void test_set_up(){
        assertEquals(4, test.getAttendances().size());
    }


}
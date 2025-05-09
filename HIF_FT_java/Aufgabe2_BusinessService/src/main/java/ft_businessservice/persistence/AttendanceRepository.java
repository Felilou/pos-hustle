package ft_businessservice.persistence;

import ft_businessservice.domain.Attendance;
import ft_businessservice.domain.Exam;

import java.util.List;

public interface AttendanceRepository {
    List<Attendance> getAttendances(Exam exam);
}

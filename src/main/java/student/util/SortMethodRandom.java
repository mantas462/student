package student.util;

import student.model.Student;

import java.util.Collections;
import java.util.List;

public class SortMethodRandom extends SortMethod {
    @Override
    public List<Student> sort(List<Student> studentList) {
        Collections.shuffle(studentList);
        return studentList;
    }
}

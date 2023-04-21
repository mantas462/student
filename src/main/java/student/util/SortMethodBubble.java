package student.util;

import student.model.Student;

import java.util.List;

public class SortMethodBubble extends SortMethod {

    @Override
    public List<Student> sort(List<Student> studentList) {

        Student temp;
        if (studentList.size() > 1) {
            for (int x = 0; x < studentList.size(); x++) {
                for (int i = 0; i < studentList.size() - i; i++) {
                    if (studentList.get(i).getGrade().compareTo(studentList.get(i + 1).getGrade()) > 0) {
                        temp = studentList.get(i);
                        studentList.set(i, studentList.get(i + 1));
                        studentList.set(i + 1, temp);
                    }
                }
            }
        }
        return studentList;
    }
}

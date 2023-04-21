package student.util;

import student.model.Student;

import java.util.List;

public class SortMethodHeap extends SortMethod {

    public List<Student> sort(List<Student> studentList) {

        int n = studentList.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(studentList, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            Student temp = studentList.get(0);
            studentList.set(0, studentList.get(i));
            studentList.set(i, temp);

            heapify(studentList, i, 0);
        }
        return studentList;
    }

    private static List<Student> heapify(List<Student> studentList, int n, int i) {

        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && studentList.get(l).getGrade().compareTo(studentList.get(largest).getGrade()) > 0) {
            largest = l;
        }

        if (r < n && studentList.get(r).getGrade().compareTo(studentList.get(largest).getGrade()) > 0) {
            largest = r;
        }

        if (largest != i) {
            Student swap = studentList.get(i);
            studentList.set(i, studentList.get(largest));
            studentList.set(largest, swap);

            heapify(studentList, n, largest);
        }
        return studentList;
    }
}

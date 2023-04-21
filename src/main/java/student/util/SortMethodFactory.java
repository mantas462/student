package student.util;

import org.apache.commons.lang3.NotImplementedException;
import student.model.Student;
import student.model.StudentWithSortTime;

import java.util.List;

import static student.util.SortType.*;

public class SortMethodFactory {

    public StudentWithSortTime sort(List<Student> studentList, SortType sortType) {

        SortMethod sortMethod;
        if (sortType == BUBBLE) {
            sortMethod = new SortMethodBubble();
        } else if (sortType == MERGE) {
            sortMethod = new SortMethodMerge();
        } else if (sortType == HEAP) {
            sortMethod = new SortMethodHeap();
        } else if (sortType == RANDOM) {
            sortMethod = new SortMethodRandom();
        } else {
            throw new NotImplementedException("Sort type = [%s] is not implemented".formatted(sortType));
        }

        long startTime = System.nanoTime();
        List<Student> sortedStudentList = sortMethod.sort(studentList);
        long stopTime = System.nanoTime();
        long sortTime = stopTime - startTime;

        return StudentWithSortTime.builder()
                .studentList(sortedStudentList)
                .sortTimeInNs(sortTime)
                .build();
    }
}

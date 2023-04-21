package student.util;

import student.model.Student;

import java.util.ArrayList;
import java.util.List;

public class SortMethodMerge extends SortMethod {
    @Override
    public List<Student> sort(List<Student> studentList) {
        if (studentList.size() <= 1) {
            return studentList;
        }

        List<Student> aList = new ArrayList<>(studentList.subList(0, studentList.size() / 2));
        List<Student> bList = new ArrayList<>(studentList.subList(studentList.size() / 2, studentList.size()));

        sort(aList);
        sort(bList);

        merge(aList, bList, studentList);
        return studentList;
    }

    private static List<Student> merge(List<Student> alist, List<Student> blist, List<Student> list) {
        int alistIndex = 0, blistIndex = 0, listIndex = 0;
        while (alistIndex < alist.size() && blistIndex < blist.size()) {
            if (alist.get(alistIndex).getGrade().compareTo(blist.get(blistIndex).getGrade()) < 0) {
                list.set(listIndex, alist.get(alistIndex));
                alistIndex++;
            } else {
                list.set(listIndex, blist.get(blistIndex));
                blistIndex++;
            }
            listIndex++;
        }
        List<Student> rest;
        if (alistIndex == alist.size()) {
            rest = blist.subList(blistIndex, blist.size());
            for (int c = blistIndex; c < rest.size(); c++) {
                list.set(listIndex, blist.get(c));
                listIndex++;
            }
        } else {
            rest = alist.subList(alistIndex, alist.size());
            for (int c = alistIndex; c < rest.size(); c++) {
                list.set(listIndex, alist.get(c));
                listIndex++;
            }
        }
        return list;
    }
}

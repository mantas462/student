package student.model;

import java.util.List;

public class StudentWithSortTime {

    private List<Student> studentList;

    private long sortTimeInNs;

    public StudentWithSortTime(List<Student> studentList, long sortTimeInNs) {
        this.studentList = studentList;
        this.sortTimeInNs = sortTimeInNs;
    }

    public StudentWithSortTime() {
    }

    public static StudentWithSortTimeBuilder builder() {
        return new StudentWithSortTimeBuilder();
    }

    public List<Student> getStudentList() {
        return this.studentList;
    }

    public long getSortTimeInNs() {
        return this.sortTimeInNs;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void setSortTimeInNs(long sortTimeInNs) {
        this.sortTimeInNs = sortTimeInNs;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof StudentWithSortTime)) return false;
        final StudentWithSortTime other = (StudentWithSortTime) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$studentList = this.getStudentList();
        final Object other$studentList = other.getStudentList();
        if (this$studentList == null ? other$studentList != null : !this$studentList.equals(other$studentList))
            return false;
        if (this.getSortTimeInNs() != other.getSortTimeInNs()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof StudentWithSortTime;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $studentList = this.getStudentList();
        result = result * PRIME + ($studentList == null ? 43 : $studentList.hashCode());
        final long $sortTimeInNs = this.getSortTimeInNs();
        result = result * PRIME + (int) ($sortTimeInNs >>> 32 ^ $sortTimeInNs);
        return result;
    }

    public String toString() {
        return "StudentWithSortTime(studentList=" + this.getStudentList() + ", sortTimeInNs=" + this.getSortTimeInNs() + ")";
    }

    public static class StudentWithSortTimeBuilder {
        private List<Student> studentList;
        private long sortTimeInNs;

        StudentWithSortTimeBuilder() {
        }

        public StudentWithSortTimeBuilder studentList(List<Student> studentList) {
            this.studentList = studentList;
            return this;
        }

        public StudentWithSortTimeBuilder sortTimeInNs(long sortTimeInNs) {
            this.sortTimeInNs = sortTimeInNs;
            return this;
        }

        public StudentWithSortTime build() {
            return new StudentWithSortTime(this.studentList, this.sortTimeInNs);
        }

        public String toString() {
            return "StudentWithSortTime.StudentWithSortTimeBuilder(studentList=" + this.studentList + ", sortTimeInNs=" + this.sortTimeInNs + ")";
        }
    }
}

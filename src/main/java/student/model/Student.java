package student.model;

import java.math.BigDecimal;

public class Student {

    private String name;

    private BigDecimal grade;

    public Student(String name, BigDecimal grade) {
        this.name = name;
        this.grade = grade;
    }

    public Student() {
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getGrade() {
        return this.grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Student)) return false;
        final Student other = (Student) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$grade = this.getGrade();
        final Object other$grade = other.getGrade();
        if (this$grade == null ? other$grade != null : !this$grade.equals(other$grade)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Student;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $grade = this.getGrade();
        result = result * PRIME + ($grade == null ? 43 : $grade.hashCode());
        return result;
    }

    public String toString() {
        return "Student(name=" + this.getName() + ", grade=" + this.getGrade() + ")";
    }

    public static class StudentBuilder {
        private String name;
        private BigDecimal grade;

        StudentBuilder() {
        }

        public StudentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder grade(BigDecimal grade) {
            this.grade = grade;
            return this;
        }

        public Student build() {
            return new Student(this.name, this.grade);
        }

        public String toString() {
            return "Student.StudentBuilder(name=" + this.name + ", grade=" + this.grade + ")";
        }
    }
}

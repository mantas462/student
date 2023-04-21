package student.util;

public enum SortType {

    RANDOM("RANDOM"),

    BUBBLE("BUBBLE"),

    HEAP("HEAP"),

    MERGE("MERGE");

    private String method;

    SortType(String method) {
        this.method = method;
    }
}

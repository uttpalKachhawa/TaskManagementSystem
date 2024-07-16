package com.uk.management.enums;

public enum TaskPriority {

    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");

    private final String priority;

    TaskPriority(String priority){
        this.priority=priority;
    }

    @Override
    public String toString() {
        return this.priority;
        /*final StringBuilder sb = new StringBuilder("TaskStatus{");
        sb.append("status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();*/
    }
}

package com.TEKWILL_STUDY.course.support;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatus {
    NEW("new"),
    ASSIGNED("assigned"),
    IN_PROGRESS("in progress"),
    CANCELED("canceled"),
    COMPLETED("completed");

    private final String taskStatus;

    TaskStatus(String taskType){
        this.taskStatus =taskType;
    }

    @JsonValue
    public String getTaskStatus() {
        return taskStatus;
    }

    @JsonCreator
    public static TaskStatus fromValue(String value) {
        for (TaskStatus task : values()) {
            String currentStatus = task.getTaskStatus().toLowerCase();
            if (currentStatus.equals(value)) {
                return task;
            }
        }

        // Return a response entity with a 400 Bad Request status
        throw new UnsupportedOperationException(String.format("Unknown status: '%s'", value));    }

}
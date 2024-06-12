package com.TEKWILL_STUDY.course.support;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskStatusErrorResponse {


        private String message;

        public TaskStatusErrorResponse(String message) {
            this.message = message;
        }

}

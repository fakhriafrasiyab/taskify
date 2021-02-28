package com.taskify.taskifyApp.payload.response;

import com.taskify.taskifyApp.enums.BRStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse {

    private BRStatus status;
    private String error;
    private Object data;

    public BaseResponse() {
    }

    public BaseResponse(BRStatus status) {
        this.status = status;
    }

    public BaseResponse(BRStatus status, Object data) {
        this.status = status;
        this.data = data;
    }
}

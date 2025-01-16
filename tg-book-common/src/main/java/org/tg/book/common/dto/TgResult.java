package org.tg.book.common.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class TgResult<T> implements Serializable {
    private boolean success;
    private String code;
    private String message;
    private T data;

    public static <T> TgResult<T> ok(T data) {
        return ok("200", "成功", data);
    }

    public static <T> TgResult<T> ok(String code, String message, T data) {
        return build(true, code, message, data);
    }

    public static <T> TgResult<T> fail(String code, String message) {
        return fail(code, message, null);
    }

    public static <T> TgResult<T> fail(String code, String message, T data) {
        return build(false, code, message, data);
    }

    private static <T> TgResult<T> build(boolean success, String code, String message, T data) {
        TgResult<T> result = new TgResult<>();
        result.setSuccess(success);
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static TgResult<String> ok() {
        return ok("200", "成功", null);
    }

}

package com.czh.tool.czh.tool.enums;

/**
 *
 * @author czh
 * @since 1.0
 */

public enum BizCodeEnum {
    INVALIDSTRATEGY_EXCEPTION(10004,"无效的策略"),
    ;

    private Integer code;

    private String message;

    BizCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

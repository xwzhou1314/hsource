package com.hsource.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 异常状态码，状态信息
 * @author xwzhou
 * @date 2019-10-11
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    PRICE_CANNOT_BE_NULL(400,"价格部位空"),
    USER_NULL(201, "不存在"),
    DEL_USER_NULL(204, "已被删除，请勿重复操作"),
    UPLOAD_FILE_ERROR(500, "上传文件失败"),
    INVALID_TYPE_ERROR(400, "文件类型不匹配"),
    USER_PASS_FALSE(201, "用户账号或密码不正确"),
    DICT_NE_NULL(400, "数据字典为空"),
    INVITATION_NULL(400, "帖子为空"),
    REPLY_FALSE(500, "评论失败")
    ;
    private int code;
    private String msg;
}

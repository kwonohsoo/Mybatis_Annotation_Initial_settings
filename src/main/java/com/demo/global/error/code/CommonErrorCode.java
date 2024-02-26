package com.demo.global.error.code;

import lombok.Getter;

@Getter
public enum CommonErrorCode implements ErrorCode {

    PERMISSION_DENIED("ERROR_001", "해당 권한이 올바르지 않습니다. 다시 로그인 해주세요."),

    // Token Error
    WRONG_TOKEN("ERROR_001", "잘못된 토큰 정보입니다. 다시 로그인 해주세요."),
    UNSUPPORTED_TOKEN("ERROR_001", "지원하지 않은 토큰 형식입니다. 다시 로그인 해주세요."),
    UNKNOWN_TOKEN_ERROR("ERROR_001", "알 수 없는 토큰 오류입니다. 다시 로그인 해주세요."),
    EXPIRED_REFRESH_TOKEN("ERROR_001", "해당 토큰 정보가 올바르지 않습니다. 다시 로그인 해주세요."),
    EXPIRED_ACCESS_TOKEN("ERROR_002", "해당 토큰은 유효기간이 만료입니다. 새로운 토큰을 발급 받아주세요."),
    ACCESS_TOKEN_NOT_EXPIRED("ERROR_003", "해당 토큰의 유효기간이 만료되지 않았습니다."),

    // Common Error
    INVALID_INPUT_VALUE("COMMON_001", "입력값에 문제가 있습니다. 확인 후 다시 시도해 주세요."),
    METHOD_NOT_ALLOWED("COMMON_002", "입력값에 문제가 있습니다. 확인 후 다시 시도해 주세요."),
    INTERNAL_SERVER_ERROR("COMMON_003", "Server Error"),
    INVALID_TYPE_VALUE("COMMON_004", "올바른 입력 방법이 아닙니다."),
    ENTITY_NOT_FOUND("COMMON_005", "구성 요소를 찾지 못했습니다."),
    ENUM_MAPPING_NOT_FOUND("COMMON_006", "DB와 저장된 상태 정보를 찾을 수 없습니다."),
    ENUM_MAPPING_TO_DATABASE("COMMON_007", "DB 저장중 매핑되는 정보를 찾지 못했습니다."),
    ENUM_MAPPING_TO_ENTITY("COMMON_008", "DB와 매핑되는 정보를 찾지 못했습니다."),
    PAYLOAD_TOO_LARGE("COMMON_009", "요청된 정보가 너무 큽니다."),
    REQUEST_HEADER_NOT_FOUND("COMMON_010", "요청된 헤더 정보가 올바르지 않습니다."),
    COMBO_BOX_ENUM_INCORRECT("COMMON_011", "선택한 콤보박스의 정보가 올바르지 않습니다."),
    POST_NOT_FOUND("COMMON_012", "게시글을 찾을 수 없습니다."),
    ALREADY_DELETED("COMMON_013", "이미 삭제된 게시글 입니다."),
    UPDATE_FAIL("COMMON_014", "게시글 삭제 실패"),
    EMPTY_TITLE_ERROR("COMMON_015", "제목이 비어있습니다. 제목을 입력해 주세요."),
    EMPTY_CONTENT_ERROR("COMMON_016", "내용이 비어있습니다. 내용을 입력해 주세요."),
    CANNOT_UPDATE_DELETED_POST("COMMON_017", "삭제된 게시물은 수정할 수 없습니다."),

    // Auth Error
    USER_NOT_FOUND("AUTH_001", "사용자를 찾을 수 없습니다."),
    LOGIN_USER_PASSWORD_INVALID("AUTH_003", "비밀번호가 올바르지 않습니다."),
    LOGIN_SYSTEM_TARGET_INVALID("AUTH_004", "설정한 타겟의 정보가 올바르지 않습니다."),

    // 개인 정보 수정
    MYSELF_NOT_UPDATE_SUPER_ADMIN("COMMON_010", "슈퍼관리자의 개인정보는 수정할 수 없습니다."),
    MYSELF_UPDATE_FAIL("COMMON_011", "개인 정보 수정에 실패하였습니다."),
    MYSELF_SEARCH_FAIL("COMMON_012", "개인 정보 조회에 실패하였습니다."),
    MYSELF_PHOTO_UPLOAD_FAIL("COMMON_013", "사진 등록 중 오류가 발생하였습니다. 다시 시도해주세요."),
    MYSELF_NO_PHOTO("COMMON_014", "해당 사용자는 사진 이미지가 없습니다."),

    MYSELF_UPDATE_PW_FAIL("COMMON_021", "비밀번호는 영문, 숫자, 특수문자 포함 8~20자 입니다."),

    MYSELF_UPDATE_IMG_FAIL("COMMON_026", "이미지는 10MB 이내로 등록해주세요."),

    // 사용자 관리
    USER_MANAGE_USER_EXISTENCE("COMMON_015", "해당 사용자 ID는 이미 존재합니다."),
    USER_MANAGE_USER_DELETE_FAIL("COMMON_016", "사용자 삭제가 정상적으로 동작하지 않았습니다."),
    USER_MANAGE_USER_UPDATE_FAIL("COMMON_017", "사용자 수정이 정상적으로 동작하지 않았습니다."),
    USER_MANAGE_USER_ROLE_FAIL("COMMON_018", "사용자 권한 설정과 관련되어 오류가 발생했습니다."),
    USER_MANAGE_USER_NOT_FOUND("COMMON_019", "해당 사용자를 찾을 수 없습니다."),

    USER_MANAGE_USER_ID_LENGTH("COMMON_020", "해당 사용자 ID는 4자 ~ 20자 입니다."),
    USER_MANAGE_USER_NOT_PHONE_MEMBER("COMMON_021", "전화번호를 정확히 입력해주세요.(01012341234)"),
    WEATHER_NOT_FOUND("COMMON_022", "날씨 정보를 찾지 못했습니다."),
    ;

    private final String code;
    private final String message;

    CommonErrorCode(final String code, final String message) {
        this.message = message;
        this.code = code;
    }

}

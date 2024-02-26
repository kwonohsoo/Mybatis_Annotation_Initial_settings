package com.demo.global.values;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonValue {

    public static final String SUCCESS = "T";
    public static final String FAIL = "F";
    public static final String SUCCESS_KEY = "success";
    public static final String MESSAGE_KEY = "message";
    public static final String CODE = "code";

    public static final int SEARCH_USE_YN = 0;
    public static final int DELETE_USE_YN = 9;


    public static final String USER_PHOTO_PATH = "user_photo"; // 사용자 사진 경로
    public static final int PHOTO_CHANGED_STATUS = 1; // 사용자 사진 변경 상태 값
    public static final String AUTHORITY_ADMIN = "관리자";
    public static final String AUTHORITY_USER = "사용자";
    public static final String AUTHORITY_GUEST = "게스트";
    public static final String COMBO_BOX_ALL = "전체";

}

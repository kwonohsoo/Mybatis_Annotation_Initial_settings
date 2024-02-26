package com.demo.global.error;

import com.demo.global.error.code.CommonErrorCode;
import com.demo.global.values.CommonValue;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        try {
            setResponse(response);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void setResponse(HttpServletResponse response) throws IOException, JSONException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject json = new JSONObject();
        json.put(CommonValue.SUCCESS_KEY, CommonValue.FAIL);
        json.put(CommonValue.MESSAGE_KEY, CommonErrorCode.PERMISSION_DENIED.getMessage());
        json.put(CommonValue.CODE, CommonErrorCode.PERMISSION_DENIED.getCode());

        response.getWriter().print(json);
    }
}

package com.demo.global.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "임산물 데모",
                description = "임산물 프로젝트 초기 세팅",
                version = "1.0.0"))
@Configuration
public class SwaggerConfig {

    @Getter
    @RequiredArgsConstructor
    enum Menu {
        CREATE("등록", "/board/create"),
        VIEW("조회", "/board/view/**"),
        UPDATE("수정", "/board/update/**"),
        DELETE("삭제", "/board/delete/**");

        private final String groupName;
        private final String paths;
    }

    private GroupedOpenApi buildGroupedOpenApi(Menu menu) {
        return GroupedOpenApi.builder()
                .group(menu.getGroupName())
                .pathsToMatch(menu.getPaths())
                .build();
    }

    @Bean
    public GroupedOpenApi create() {
        return buildGroupedOpenApi(Menu.CREATE);
    }

    @Bean
    public GroupedOpenApi view() {
        return buildGroupedOpenApi(Menu.VIEW);
    }

    @Bean
    public GroupedOpenApi update() {
        return buildGroupedOpenApi(Menu.UPDATE);
    }

    @Bean
    public GroupedOpenApi delete() {
        return buildGroupedOpenApi(Menu.DELETE);
    }
}
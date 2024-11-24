package com.wrp.blog.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wrp
 * @since 2024-11-19 21:43
 **/
@Accessors(chain = true)
@Data
public class UserInfo {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String username;
    private Long photoId;
    private String token;
}

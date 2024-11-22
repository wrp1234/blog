package com.wrp.blog.user.param;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author wrp
 * @since 2024-11-22 22:55
 **/
@Data
public class UpdateUser {
    private LocalDate birthday;
    private String introduction;
    private Long photoId;
}

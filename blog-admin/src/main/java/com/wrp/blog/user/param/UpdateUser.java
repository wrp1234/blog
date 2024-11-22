package com.wrp.blog.user.param;

import jakarta.validation.constraints.NotNull;
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
    private Long photo;
}

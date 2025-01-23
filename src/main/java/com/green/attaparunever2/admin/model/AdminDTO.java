package com.green.attaparunever2.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminDTO {
    private long adminId;
    private String roleId;
    private String aid;
    private String name;
    private String email;
    private String phone;

    @JsonIgnore // swagger 표시 안되지만, 응답 때 빼는 역할도 한다.
    private String apw;
}

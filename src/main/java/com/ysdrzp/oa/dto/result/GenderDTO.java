package com.ysdrzp.oa.dto.result;

import lombok.Data;

@Data
public class GenderDTO {

    /**
     * 性别 0：保密、1：男、2：女
     */
    private Integer genderType;

    /**
     * 个数
     */
    private Integer count;
}

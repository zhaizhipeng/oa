package com.ysdrzp.oa.dto.result;

import lombok.Data;

@Data
public class GenderDistributionDTO {

    /**
     * 保密人数
     */
    private Integer secret = 0;

    /**
     * 男性
     */
    private Integer male = 0;

    /**
     * 女性
     */
    private Integer female = 0;
}

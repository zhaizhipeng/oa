package com.ysdrzp.oa.dto.result;

import lombok.Data;

@Data
public class OrgDistributionDTO {

    /**
     * 机构数组
     */
    private String[] orgNames;

    /**
     * 不同机构下的用户数
     */
    private Long[] orgCounts;
}

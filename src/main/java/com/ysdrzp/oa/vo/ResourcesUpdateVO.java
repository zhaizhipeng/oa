package com.ysdrzp.oa.vo;

import lombok.Data;

/**
 * 更新资源
 */
@Data
public class ResourcesUpdateVO {

    /**
     * 资源主键
     */
    private Long id;

    /**
     * 资源Key
     */
    private String resourcesKey;

    /**
     * 资源名称
     */
    private String resourcesName;

    /**
     * url
     */
    private Integer urlType;
}

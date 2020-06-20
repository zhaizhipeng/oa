package com.ysdrzp.oa.vo;

import lombok.Data;

/**
 * 新建资源
 */
@Data
public class ResourcesSaveVO {

    /**
     * 父资源Id
     */
    private Long fatherId;

    /**
     * 父资源名称
     */
    private String fatherMenuName;

    /**
     * 资源名称
     */
    private String resourcesName;

    /**
     * url类型 0：系统 、1：静态资源、2：按钮
     */
    private Integer urlType;

    /**
     * 资源描述信息
     */
    private String miscDesc;

}

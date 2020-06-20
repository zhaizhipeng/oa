package com.ysdrzp.oa.dto.result;

import lombok.Data;

import java.util.List;

/**
 * 资源树
 */
@Data
public class MenuTreeDTO {

    /**
     * 资源主键ID
     */
    private Long id;

    /**
     * 资源key
     */
    private String resourcesKey;

    /**
     * 资源名称
     */
    private String resourcesName;

    /**
     * url类型 1：静态资源、2：按钮
     */
    private Integer urlType;

    /**
     * 上级菜单资源
     */
    private MenuTreeDTO parent;

    /**
     * 子级菜单资源列表
     */
    List<MenuTreeDTO> children;

}

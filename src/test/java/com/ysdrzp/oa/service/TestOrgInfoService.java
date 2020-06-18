package com.ysdrzp.oa.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class TestOrgInfoService {

    @Autowired
    private ISysOrgInfoService sysOrgInfoService;

    @Test
    public void testSelect(){
        System.out.println(sysOrgInfoService.selectByPrimaryKey(1l));
    }
}

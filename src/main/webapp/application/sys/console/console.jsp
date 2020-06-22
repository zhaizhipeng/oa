<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/application/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <title>管控台</title>
        <link rel="stylesheet" href="${ysdrzp}/layui/css/layui.css" media="all" />
        <script>var ysdrzp = "${ysdrzp}";</script>
        <script src="https://cdn.bootcss.com/echarts/4.2.1-rc1/echarts.min.js"></script>
    </head>

    <body>

        <div id="genderChart" style="width: 600px; height:320px;"></div>

        <div style="width: 600px; height:60px;"></div>

        <div id="orgChart" style="width: 600px; height:320px;"></div>

        <script src="${ysdrzp}/layui/layui.js"></script>

        <script>

            layui.use(['jquery'], function(){
                var $ = layui.jquery;
                $.ajax({
                    type:'get',
                    url:'${ysdrzp}/console/genderDistribution',
                    success:function(data){
                        var genderData = data.data;
                        secret = genderData.secret;
                        male = genderData.male;
                        female = genderData.female;

                        var genderChart = echarts.init(document.getElementById('genderChart'));

                        var genderOption = {
                            title: {text: '用户男女比列分布'},
                            tooltip: {
                                trigger : 'item',
                                formatter : "{a} <br/>{b}: {c} ({d}%)"
                            },
                            series: [{
                                type: 'pie',
                                name: "占比数据",
                                data: [
                                    {name: '保密', value: secret},
                                    {name: '男', value: male},
                                    {name: '女', value: female}
                                ],
                                itemStyle: {
                                    // 阴影的大小
                                    shadowBlur: 100,
                                    // 阴影水平方向上的偏移
                                    shadowOffsetX: 0,
                                    // 阴影垂直方向上的偏移
                                    shadowOffsetY: 0,
                                    // 阴影颜色
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }]
                        };

                        genderChart.setOption(genderOption);
                    }
                });

                $.ajax({
                    type:'get',
                    url:'${ysdrzp}/console/orgDistribution',
                    success:function(data){
                        var orgData = data.data;
                        orgNames = orgData.orgNames;
                        orgCounts = orgData.orgCounts;

                        var orgChart = echarts.init(document.getElementById('orgChart'));

                        var orgOption = {
                            title: {text: '用户机构所属分布'},
                            xAxis: {
                                type: 'category',
                                data: orgNames
                            },
                            yAxis: {},
                            series: [
                                {
                                    type: 'bar',
                                    name: '总数',
                                    data: orgCounts
                                }
                            ]
                        };

                        orgChart.setOption(orgOption);
                    }
                });
            });

        </script>

    </body>
</html>
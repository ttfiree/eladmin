/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author lyc
* @date 2023-06-22
**/
@Data
public class GameAffixDto implements Serializable {

    /** 属性ID */
    private Integer id;

    /** 属性名称 */
    private String name;

    /** 版本 */
    private Integer version;

    /** 是否可生成 */
    private Integer spawnable;

    /** 是否稀有属性 */
    private Integer rare;

    /** 最小等级 */
    private Integer level;

    /** 最大等级 */
    private Integer maxlevel;

    /** 装备等级要求 */
    private Integer levelreq;

    /** 是否职业专属 */
    private String classspecific;

    /** 职业名称 */
    private String className;

    /** 职业等级要求 */
    private Integer classlevelreq;

    /** 生成频率 */
    private Integer frequency;

    /** 属性分组 */
    private Integer groups;

    /** 属性1代码 */
    private String mod1code;

    /** 属性1参数 */
    private String mod1param;

    /** 属性1最小值 */
    private Integer mod1min;

    /** 属性1最大值 */
    private Integer mod1max;

    /** 属性2代码 */
    private String mod2code;

    /** 属性2参数 */
    private String mod2param;

    /** 属性2最小值 */
    private Integer mod2min;

    /** 属性2最大值 */
    private Integer mod2max;

    /** 属性3代码 */
    private String mod3code;

    /** 属性3参数 */
    private String mod3param;

    /** 属性3最小值 */
    private Integer mod3min;

    /** 属性3最大值 */
    private Integer mod3max;

    /** 属性转化 */
    private String transform;

    /** 属性转化颜色 */
    private String transformcolor;

    /** 物品类型1 */
    private String itype1;

    /** 物品类型2 */
    private String itype2;

    /** 物品类型3 */
    private String itype3;

    /** 物品类型4 */
    private String itype4;

    /** 物品类型5 */
    private String itype5;

    /** 物品类型6 */
    private String itype6;

    /** 物品类型7 */
    private String itype7;

    /** 装备类型1 */
    private String etype1;

    /** 装备类型2 */
    private String etype2;

    /** 装备类型3 */
    private String etype3;

    /** 装备类型4 */
    private String etype4;

    /** 装备类型5 */
    private String etype5;

    /** 属性值除数 */
    private Integer divide;

    /** 属性值乘数 */
    private Integer multiply;

    /** 属性值增量 */
    private Integer addAffix;
}
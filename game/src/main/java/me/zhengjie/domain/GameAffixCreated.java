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
package me.zhengjie.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author lyc
* @date 2023-06-22
**/
@Entity
@Data
@Table(name="game_affix_created")
public class GameAffixCreated implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    @ApiModelProperty(value = "属性ID")
    private Integer id;

    @Column(name = "`char_id`")
    @ApiModelProperty(value = "角色id")
    private Integer charId;

    @Column(name = "`arm_id`")
    @ApiModelProperty(value = "装备id")
    private Integer armId;

    @Column(name = "`weapon_id`")
    @ApiModelProperty(value = "武器id")
    private Integer weaponId;

    @Column(name = "`is_equip`")
    @ApiModelProperty(value = "是否装备")
    private Integer isEquip;

    @Column(name = "`name`")
    @ApiModelProperty(value = "属性名称")
    private String name;

    @Column(name = "`version`")
    @ApiModelProperty(value = "版本")
    private Integer version;

    @Column(name = "`spawnable`")
    @ApiModelProperty(value = "是否可生成")
    private Integer spawnable;

    @Column(name = "`rare`")
    @ApiModelProperty(value = "是否稀有属性")
    private Integer rare;

    @Column(name = "`level`")
    @ApiModelProperty(value = "最小等级")
    private Integer level;

    @Column(name = "`maxlevel`")
    @ApiModelProperty(value = "最大等级")
    private Integer maxlevel;

    @Column(name = "`levelreq`")
    @ApiModelProperty(value = "装备等级要求")
    private Integer levelreq;

    @Column(name = "`classspecific`")
    @ApiModelProperty(value = "是否职业专属")
    private String classspecific;

    @Column(name = "`class`")
    @ApiModelProperty(value = "职业名称")
    private String className;

    @Column(name = "`classlevelreq`")
    @ApiModelProperty(value = "职业等级要求")
    private Integer classlevelreq;

    @Column(name = "`frequency`")
    @ApiModelProperty(value = "生成频率")
    private Integer frequency;

    @Column(name = "`groups`")
    @ApiModelProperty(value = "属性分组")
    private Integer groups;

    @Column(name = "`mod1code`")
    @ApiModelProperty(value = "属性1代码")
    private String mod1code;

    @Column(name = "`mod1param`")
    @ApiModelProperty(value = "属性1参数")
    private String mod1param;

    @Column(name = "`mod1min`")
    @ApiModelProperty(value = "属性1最小值")
    private Integer mod1min;

    @Column(name = "`mod1max`")
    @ApiModelProperty(value = "属性1最大值")
    private Integer mod1max;

    @Column(name = "`mod2code`")
    @ApiModelProperty(value = "属性2代码")
    private String mod2code;

    @Column(name = "`mod2param`")
    @ApiModelProperty(value = "属性2参数")
    private String mod2param;

    @Column(name = "`mod2min`")
    @ApiModelProperty(value = "属性2最小值")
    private Integer mod2min;

    @Column(name = "`mod2max`")
    @ApiModelProperty(value = "属性2最大值")
    private Integer mod2max;

    @Column(name = "`mod3code`")
    @ApiModelProperty(value = "属性3代码")
    private String mod3code;

    @Column(name = "`mod3param`")
    @ApiModelProperty(value = "属性3参数")
    private String mod3param;

    @Column(name = "`mod3min`")
    @ApiModelProperty(value = "属性3最小值")
    private Integer mod3min;

    @Column(name = "`mod3max`")
    @ApiModelProperty(value = "属性3最大值")
    private Integer mod3max;

    @Column(name = "`transform`")
    @ApiModelProperty(value = "属性转化")
    private String transform;

    @Column(name = "`transformcolor`")
    @ApiModelProperty(value = "属性转化颜色")
    private String transformcolor;

    @Column(name = "`itype1`")
    @ApiModelProperty(value = "物品类型1")
    private String itype1;

    @Column(name = "`itype2`")
    @ApiModelProperty(value = "物品类型2")
    private String itype2;

    @Column(name = "`itype3`")
    @ApiModelProperty(value = "物品类型3")
    private String itype3;

    @Column(name = "`itype4`")
    @ApiModelProperty(value = "物品类型4")
    private String itype4;

    @Column(name = "`itype5`")
    @ApiModelProperty(value = "物品类型5")
    private String itype5;

    @Column(name = "`itype6`")
    @ApiModelProperty(value = "物品类型6")
    private String itype6;

    @Column(name = "`itype7`")
    @ApiModelProperty(value = "物品类型7")
    private String itype7;

    @Column(name = "`etype1`")
    @ApiModelProperty(value = "装备类型1")
    private String etype1;

    @Column(name = "`etype2`")
    @ApiModelProperty(value = "装备类型2")
    private String etype2;

    @Column(name = "`etype3`")
    @ApiModelProperty(value = "装备类型3")
    private String etype3;

    @Column(name = "`etype4`")
    @ApiModelProperty(value = "装备类型4")
    private String etype4;

    @Column(name = "`etype5`")
    @ApiModelProperty(value = "装备类型5")
    private String etype5;

    @Column(name = "`divide`")
    @ApiModelProperty(value = "属性值除数")
    private Integer divide;

    @Column(name = "`multiply`")
    @ApiModelProperty(value = "属性值乘数")
    private Integer multiply;

    @Column(name = "`add_affix`")
    @ApiModelProperty(value = "属性值增量")
    private Integer addAffix;

    public void copy(GameAffixCreated source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}

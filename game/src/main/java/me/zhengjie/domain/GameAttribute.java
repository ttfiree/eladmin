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
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author lyc
* @date 2023-06-12
**/
@Entity
@Data
@Table(name="game_attribute")
public class GameAttribute implements Serializable {

    @Id
    @Column(name = "`id`")
    @ApiModelProperty(value = "角色ID")
    private Integer id;

    @Column(name = "`strength`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "力量")
    private Integer strength;

    @Column(name = "`dexterity`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "敏捷")
    private Integer dexterity;

    @Column(name = "`constitution`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "体质")
    private Integer constitution;

    @Column(name = "`intelligence`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "智力")
    private Integer intelligence;

    @Column(name = "`wisdom`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "感知")
    private Integer wisdom;

    @Column(name = "`charisma`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "魅力")
    private Integer charisma;

    @Column(name = "`damage`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "基础伤害")
    private Integer damage;

    @Column(name = "`attact_speed`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "攻速")
    private BigDecimal attactSpeed;

    @Column(name = "`critical_chance`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "暴击")
    private BigDecimal criticalChance;

    @Column(name = "`critical_damage`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "爆伤")
    private BigDecimal criticalDamage;

    @Column(name = "`ext_string_one`")
    @ApiModelProperty(value = "extStringOne")
    private String extStringOne;

    @Column(name = "`ext_string_two`")
    @ApiModelProperty(value = "extStringTwo")
    private String extStringTwo;

    @Column(name = "`ext_string_three`")
    @ApiModelProperty(value = "extStringThree")
    private String extStringThree;

    @Column(name = "`ext_string_four`")
    @ApiModelProperty(value = "extStringFour")
    private String extStringFour;

    @Column(name = "`ext_string_five`")
    @ApiModelProperty(value = "extStringFive")
    private String extStringFive;

    @Column(name = "`ext_decimal_one`")
    @ApiModelProperty(value = "extDecimalOne")
    private BigDecimal extDecimalOne;

    @Column(name = "`ext_decimal_two`")
    @ApiModelProperty(value = "extDecimalTwo")
    private BigDecimal extDecimalTwo;

    @Column(name = "`ext_decimal_three`")
    @ApiModelProperty(value = "extDecimalThree")
    private BigDecimal extDecimalThree;

    @Column(name = "`ext_decimal_four`")
    @ApiModelProperty(value = "extDecimalFour")
    private BigDecimal extDecimalFour;

    @Column(name = "`ext_decimal_five`")
    @ApiModelProperty(value = "extDecimalFive")
    private BigDecimal extDecimalFive;

    @Column(name = "`hit_points`")
    @ApiModelProperty(value = "生命值")
    private Integer hitPoints;

    @Column(name = "`armor_class`")
    @ApiModelProperty(value = "护甲值")
    private Integer armorClass;

    @Column(name = "`mazeId`")
    @ApiModelProperty(value = "迷宫id")
    private Integer mazeId;

    @Column(name = "`money_plus`")
    @ApiModelProperty(value = "金钱加成")
    private BigDecimal moneyPlus;

    @Column(name = "`exp_plus`")
    @ApiModelProperty(value = "经验加成")
    private Double expPlus;

    @Column(name = "`item_plus`")
    @ApiModelProperty(value = "掉率加成")
    private BigDecimal itemPlus;

    public void copy(GameAttribute source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}

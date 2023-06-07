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
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Random;

/**
* @website https://eladmin.vip
* @description /
* @author lyc
* @date 2023-06-06
**/
@Entity
@Data
@Table(name="game_monster")
public class GameMonster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    @ApiModelProperty(value = "怪物ID")
    private Integer id;

    @Column(name = "`maze_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "迷宫ID")
    private Long mazeId;

    @Column(name = "`create_time`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "createTime")
    private Timestamp createTime;

    @Column(name = "`is_delete`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "isDelete")
    private Integer isDelete;

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

    @Column(name = "`name`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "怪物名")
    private String name;

    @Column(name = "`experience`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "经验值")
    private Long experience;

    @Column(name = "`money`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "金钱")
    private Long money;

    @Column(name = "`item`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "爆率")
    private Long item;

    @Column(name = "`item_rate`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "爆率等级")
    private Long itemRate;

    @Column(name = "`hit_points`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "生命值")
    private Integer hitPoints;

    @Column(name = "`speed`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "移动速度")
    private Integer speed;

    @Column(name = "`critical_chance`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "暴击")
    private BigDecimal criticalChance;

    @Column(name = "`critical_damage`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "爆伤")
    private BigDecimal criticalDamage;

    @Column(name = "`armor_class`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "护甲值")
    private Integer armorClass;

    @Column(name = "`damage`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "基础伤害")
    private Integer damage;
    public void copy(GameMonster source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }


    public int attack() {
        Random random = new Random();
        if (random.nextDouble() < criticalChance.doubleValue()) { // 判断是否暴击
            return (int) (damage * (1 + criticalDamage.doubleValue()));
        } else {
            return damage;
        }
    }

    public int defense() {
        return armorClass;
    }

    public void takeDamage(int damage) {
        hitPoints -= damage;
        if (hitPoints < 0) {
            hitPoints = 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%s：生命值%d/%d，攻击力%d，防御力%d，速度%.2f，暴击率%.2f，暴击伤害%.2f",
                name, hitPoints,  damage, armorClass, speed, criticalChance, criticalDamage);
    }

    public boolean isCritical() {
        Random random = new Random();
        return random.nextDouble() < criticalChance.doubleValue();
    }
}

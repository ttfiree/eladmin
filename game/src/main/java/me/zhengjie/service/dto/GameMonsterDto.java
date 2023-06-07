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

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
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
@Data
public class GameMonsterDto implements Serializable {

    /** 怪物ID */
    private Integer id;

    /** 迷宫ID */
    private Long mazeId;

    private Timestamp createTime;

    private Integer isDelete;

    private String extStringOne;

    private String extStringTwo;

    private String extStringThree;

    private String extStringFour;

    private String extStringFive;

    private BigDecimal extDecimalOne;

    private BigDecimal extDecimalTwo;

    private BigDecimal extDecimalThree;

    private BigDecimal extDecimalFour;

    private BigDecimal extDecimalFive;

    /** 怪物名 */
    private String name;

    /** 经验值 */
    private Long experience;

    /** 金钱 */
    private Long money;

    /** 爆率 */
    private Long item;

    /** 爆率等级 */
    private Long itemRate;

    /** 生命值 */
    private Integer hitPoints;

    private Integer speed;


    private BigDecimal criticalChance;


    private BigDecimal criticalDamage;


    private Integer armorClass;


    private Integer damage;

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
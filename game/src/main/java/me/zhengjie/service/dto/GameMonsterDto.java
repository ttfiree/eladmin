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
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Random;

/**
* @website https://eladmin.vip
* @description /
* @author lyc
* @date 2023-06-12
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

    /** 速度 */
    private Integer speed;

    /** 暴击率 */
    private Integer criticalChance;

    /** 护甲 */
    private Integer armorClass;

    /** 伤害 */
    private Integer damage;

    /** 暴击伤害 */
    private Integer criticalDamage;

    /** 等级 */
    private Integer level;

    /** 是否是boss级别 */
    private Integer isBoss;

    /** 类型 */
    private Integer type;


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
        return String.format(name+"：生命值"+hitPoints+"，攻击力"+damage+"，防御力"+armorClass+"，速度"+speed+"，暴击率"+criticalChance+"，暴击伤害"+ criticalDamage);

    }

    public boolean isCritical() {
        Random random = new Random();
        return random.nextDouble() < criticalChance.doubleValue();
    }
}

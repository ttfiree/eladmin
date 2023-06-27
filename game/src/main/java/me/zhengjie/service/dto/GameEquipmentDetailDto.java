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
* @date 2023-06-27
**/
@Data
public class GameEquipmentDetailDto implements Serializable {

    /** ID */
    private Integer id;

    /** 装备ID 关联game_armors_created或者game_affix_created */
    private Integer itemId;

    /** 角色id */
    private Integer charId;

    /** 类型 */
    private Integer equipType;

    /** 装备代码 */
    private String code;

    /** 防御值 */
    private Integer ac;

    /** 防御值加成 */
    private Integer acPercent;

    /** 减少敌人魔法抵抗 */
    private Integer redMag;

    /** 力量 */
    private Integer str;

    /** 敏捷 */
    private Integer dex;

    /** 体力 */
    private Integer vit;

    /** 精力 */
    private Integer enr;

    /** 魔法值 */
    private Integer mana;

    /** 魔法值加成 */
    private Integer manaPercent;

    /** 生命值 */
    private Integer hp;

    /** 生命值加成 */
    private Integer hpPercent;

    /** 攻击值 */
    private Integer att;

    /** 格挡率 */
    private Integer block;

    /** 最小伤害 */
    private Integer dmgMin;

    /** 最大伤害 */
    private Integer dmgMax;

    /** 伤害加成 */
    private Integer dmgPercent;

    /** 伤害转移为魔法值 */
    private Integer dmgToMana;

    /** 反伤 */
    private Integer thorns;

    /** 攻击速度1 */
    private Integer swing1;

    /** 攻击速度2 */
    private Integer swing2;

    /** 攻击速度3 */
    private Integer swing3;

    /** 金币加成 */
    private Integer goldPercent;

    /** 魔法装备加成 */
    private Integer magPercent;

    /** 体力回复 */
    private Integer regenStam;

    /** 魔法值回复 */
    private Integer regenMana;

    /** 吸取魔法值 */
    private Integer manasteal;

    /** 吸取生命值 */
    private Integer lifesteal;

    /** 无视防御值 */
    private Integer ignoreAc;

    /** 降低敌人防御值 */
    private Integer reduceAc;

    /** 禁止回复 */
    private Integer noheal;

    /** 减慢敌人速度 */
    private Integer halfFreeze;

    /** 攻击值加成 */
    private Integer attPercent;

    /** 破甲 */
    private Integer crush;

    /** 敌人流血 */
    private Integer bloody;

    /** 减慢敌人速度 */
    private Integer slow;

    /** 吸取敌人体力 */
    private Integer stamdrain;

    /** 穿透敌人护甲 */
    private Integer pierce;

    /** 物品掉落率加成 */
    private Integer itemPercent;

    /** 所有属性加成 */
    private Integer allStats;

    /** 获得经验值加成 */
    private Integer addXp;

    /** 击杀敌人回复生命值 */
    private Integer healKill;

    /** 伤害降低 */
    private Integer dmgReduct;

    private Integer finalAc;

    private Integer finalDmg;
}
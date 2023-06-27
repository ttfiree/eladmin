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
* @date 2023-06-27
**/
@Entity
@Data
@Table(name="game_equipment_detail")
public class GameEquipmentDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    @ApiModelProperty(value = "ID")
    private Integer id;

    @Column(name = "`item_id`")
    @ApiModelProperty(value = "装备ID 关联game_armors_created或者game_affix_created")
    private Integer itemId;

    @Column(name = "`char_id`")
    @ApiModelProperty(value = "角色id")
    private Integer charId;

    @Column(name = "`equip_type`")
    @ApiModelProperty(value = "类型")
    private Integer equipType;

    @Column(name = "`code`")
    @ApiModelProperty(value = "装备代码")
    private String code;

    @Column(name = "`ac`")
    @ApiModelProperty(value = "防御值")
    private Integer ac;

    @Column(name = "`ac_percent`")
    @ApiModelProperty(value = "防御值加成")
    private Integer acPercent;

    @Column(name = "`red_mag`")
    @ApiModelProperty(value = "减少敌人魔法抵抗")
    private Integer redMag;

    @Column(name = "`str`")
    @ApiModelProperty(value = "力量")
    private Integer str;

    @Column(name = "`dex`")
    @ApiModelProperty(value = "敏捷")
    private Integer dex;

    @Column(name = "`vit`")
    @ApiModelProperty(value = "体力")
    private Integer vit;

    @Column(name = "`enr`")
    @ApiModelProperty(value = "精力")
    private Integer enr;

    @Column(name = "`mana`")
    @ApiModelProperty(value = "魔法值")
    private Integer mana;

    @Column(name = "`mana_percent`")
    @ApiModelProperty(value = "魔法值加成")
    private Integer manaPercent;

    @Column(name = "`hp`")
    @ApiModelProperty(value = "生命值")
    private Integer hp;

    @Column(name = "`hp_percent`")
    @ApiModelProperty(value = "生命值加成")
    private Integer hpPercent;

    @Column(name = "`att`")
    @ApiModelProperty(value = "攻击值")
    private Integer att;

    @Column(name = "`block`")
    @ApiModelProperty(value = "格挡率")
    private Integer block;

    @Column(name = "`dmg_min`")
    @ApiModelProperty(value = "最小伤害")
    private Integer dmgMin;

    @Column(name = "`dmg_max`")
    @ApiModelProperty(value = "最大伤害")
    private Integer dmgMax;

    @Column(name = "`dmg_percent`")
    @ApiModelProperty(value = "伤害加成")
    private Integer dmgPercent;

    @Column(name = "`dmg_to_mana`")
    @ApiModelProperty(value = "伤害转移为魔法值")
    private Integer dmgToMana;

    @Column(name = "`thorns`")
    @ApiModelProperty(value = "反伤")
    private Integer thorns;

    @Column(name = "`swing1`")
    @ApiModelProperty(value = "攻击速度1")
    private Integer swing1;

    @Column(name = "`swing2`")
    @ApiModelProperty(value = "攻击速度2")
    private Integer swing2;

    @Column(name = "`swing3`")
    @ApiModelProperty(value = "攻击速度3")
    private Integer swing3;

    @Column(name = "`gold_percent`")
    @ApiModelProperty(value = "金币加成")
    private Integer goldPercent;

    @Column(name = "`mag_percent`")
    @ApiModelProperty(value = "魔法装备加成")
    private Integer magPercent;

    @Column(name = "`regen_stam`")
    @ApiModelProperty(value = "体力回复")
    private Integer regenStam;

    @Column(name = "`regen_mana`")
    @ApiModelProperty(value = "魔法值回复")
    private Integer regenMana;

    @Column(name = "`manasteal`")
    @ApiModelProperty(value = "吸取魔法值")
    private Integer manasteal;

    @Column(name = "`lifesteal`")
    @ApiModelProperty(value = "吸取生命值")
    private Integer lifesteal;

    @Column(name = "`ignore_ac`")
    @ApiModelProperty(value = "无视防御值")
    private Integer ignoreAc;

    @Column(name = "`reduce_ac`")
    @ApiModelProperty(value = "降低敌人防御值")
    private Integer reduceAc;

    @Column(name = "`noheal`")
    @ApiModelProperty(value = "禁止回复")
    private Integer noheal;

    @Column(name = "`half_freeze`")
    @ApiModelProperty(value = "减慢敌人速度")
    private Integer halfFreeze;

    @Column(name = "`att_percent`")
    @ApiModelProperty(value = "攻击值加成")
    private Integer attPercent;

    @Column(name = "`crush`")
    @ApiModelProperty(value = "破甲")
    private Integer crush;

    @Column(name = "`bloody`")
    @ApiModelProperty(value = "敌人流血")
    private Integer bloody;

    @Column(name = "`slow`")
    @ApiModelProperty(value = "减慢敌人速度")
    private Integer slow;

    @Column(name = "`stamdrain`")
    @ApiModelProperty(value = "吸取敌人体力")
    private Integer stamdrain;

    @Column(name = "`pierce`")
    @ApiModelProperty(value = "穿透敌人护甲")
    private Integer pierce;

    @Column(name = "`item_percent`")
    @ApiModelProperty(value = "物品掉落率加成")
    private Integer itemPercent;

    @Column(name = "`all_stats`")
    @ApiModelProperty(value = "所有属性加成")
    private Integer allStats;

    @Column(name = "`add_xp`")
    @ApiModelProperty(value = "获得经验值加成")
    private Integer addXp;

    @Column(name = "`heal_kill`")
    @ApiModelProperty(value = "击杀敌人回复生命值")
    private Integer healKill;

    @Column(name = "`dmg_Reduct`")
    @ApiModelProperty(value = "伤害降低")
    private Integer dmgReduct;

    @Column(name = "`final_ac`")
    @ApiModelProperty(value = "finalAc")
    private Integer finalAc;

    @Column(name = "`final_dmg`")
    @ApiModelProperty(value = "finalDmg")
    private Integer finalDmg;

    public void copy(GameEquipmentDetail source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}

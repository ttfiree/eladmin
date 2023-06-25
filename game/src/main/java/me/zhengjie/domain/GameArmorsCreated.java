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
@Table(name="game_armors_created")
public class GameArmorsCreated implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    @ApiModelProperty(value = "ID")
    private Integer id;

    @Column(name = "`item_id`")
    @ApiModelProperty(value = "ID")
    private Integer itemId;

    @Column(name = "`char_id`")
    @ApiModelProperty(value = "所属角色")
    private Integer charId;

    @Column(name = "`is_equip`")
    @ApiModelProperty(value = "是否已装备")
    private Integer isEquip;

    @Column(name = "`name`")
    @ApiModelProperty(value = "护甲名称")
    private String name;

    @Column(name = "`version`")
    @ApiModelProperty(value = "版本")
    private Integer version;

    @Column(name = "`compactsave`")
    @ApiModelProperty(value = "紧凑保存")
    private Integer compactsave;

    @Column(name = "`rarity`")
    @ApiModelProperty(value = "稀有度")
    private Integer rarity;

    @Column(name = "`minac`")
    @ApiModelProperty(value = "防御")
    private Integer minac;

    @Column(name = "`spawnable`")
    @ApiModelProperty(value = "可生成")
    private Integer spawnable;

    @Column(name = "`absorbs`")
    @ApiModelProperty(value = "吸收")
    private Integer absorbs;

    @Column(name = "`speed`")
    @ApiModelProperty(value = "攻击速度")
    private Integer speed;

    @Column(name = "`reqstr`")
    @ApiModelProperty(value = "需求力量")
    private Integer reqstr;

    @Column(name = "`block`")
    @ApiModelProperty(value = "格挡率")
    private Integer block;

    @Column(name = "`durability`")
    @ApiModelProperty(value = "耐久度")
    private Integer durability;

    @Column(name = "`nodurability`")
    @ApiModelProperty(value = "无耐久度")
    private Integer nodurability;

    @Column(name = "`level`")
    @ApiModelProperty(value = "等级")
    private Integer level;

    @Column(name = "`levelreq`")
    @ApiModelProperty(value = "等级需求")
    private Integer levelreq;

    @Column(name = "`cost`")
    @ApiModelProperty(value = "价格")
    private Integer cost;

    @Column(name = "`gamble_cost`")
    @ApiModelProperty(value = "赌博价格")
    private Integer gambleCost;

    @Column(name = "`code`")
    @ApiModelProperty(value = "代码")
    private String code;

    @Column(name = "`namestr`")
    @ApiModelProperty(value = "名称字符串")
    private String namestr;

    @Column(name = "`magic_lvl`")
    @ApiModelProperty(value = "魔法等级")
    private Integer magicLvl;

    @Column(name = "`auto_prefix`")
    @ApiModelProperty(value = "自动前缀")
    private Integer autoPrefix;

    @Column(name = "`spelloffset`")
    @ApiModelProperty(value = "法术偏移量")
    private Integer spelloffset;

    @Column(name = "`component`")
    @ApiModelProperty(value = "组件")
    private Integer component;

    @Column(name = "`hasinv`")
    @ApiModelProperty(value = "是否有背包")
    private Integer hasinv;

    @Column(name = "`gemsockets`")
    @ApiModelProperty(value = "宝石插槽数量")
    private Integer gemsockets;

    @Column(name = "`gemapplytype`")
    @ApiModelProperty(value = "宝石类型")
    private Integer gemapplytype;

    @Column(name = "`rArm`")
    @ApiModelProperty(value = "右手武器")
    private Integer rarm;

    @Column(name = "`lArm`")
    @ApiModelProperty(value = "左手武器")
    private Integer larm;

    @Column(name = "`Torso`")
    @ApiModelProperty(value = "身体")
    private Integer torso;

    @Column(name = "`Legs`")
    @ApiModelProperty(value = "腿部")
    private Integer legs;

    @Column(name = "`rSPad`")
    @ApiModelProperty(value = "右肩垫")
    private Integer rspad;

    @Column(name = "`lSPad`")
    @ApiModelProperty(value = "左肩垫")
    private Integer lspad;

    @Column(name = "`useable`")
    @ApiModelProperty(value = "可用性")
    private Integer useable;

    @Column(name = "`type`")
    @ApiModelProperty(value = "类型")
    private String type;

    @Column(name = "`type2`")
    @ApiModelProperty(value = "类型2")
    private String type2;

    @Column(name = "`IS_unique`")
    @ApiModelProperty(value = "是否唯一")
    private Integer isUnique;

    @Column(name = "`transparent`")
    @ApiModelProperty(value = "是否透明")
    private Integer transparent;

    @Column(name = "`transtbl`")
    @ApiModelProperty(value = "透明度")
    private Integer transtbl;

    @Column(name = "`quivered`")
    @ApiModelProperty(value = "是否箭袋")
    private Integer quivered;

    @Column(name = "`lightradius`")
    @ApiModelProperty(value = "光照半径")
    private Integer lightradius;

    @Column(name = "`belt`")
    @ApiModelProperty(value = "是否腰带")
    private Integer belt;

    @Column(name = "`quest`")
    @ApiModelProperty(value = "是否任务物品")
    private Integer quest;

    @Column(name = "`missiletype`")
    @ApiModelProperty(value = "投射物类型")
    private Integer missiletype;

    @Column(name = "`durwarning`")
    @ApiModelProperty(value = "耐久度警告")
    private Integer durwarning;

    @Column(name = "`qntwarning`")
    @ApiModelProperty(value = "数量警告")
    private Integer qntwarning;

    @Column(name = "`mindam`")
    @ApiModelProperty(value = "最小伤害")
    private Integer mindam;

    @Column(name = "`maxdam`")
    @ApiModelProperty(value = "最大伤害")
    private Integer maxdam;

    @Column(name = "`strbonus`")
    @ApiModelProperty(value = "力量加成")
    private Integer strbonus;

    @Column(name = "`dexbonus`")
    @ApiModelProperty(value = "敏捷加成")
    private Integer dexbonus;

    @Column(name = "`gemoffset`")
    @ApiModelProperty(value = "宝石偏移量")
    private Integer gemoffset;

    public void copy(GameArmorsCreated source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}

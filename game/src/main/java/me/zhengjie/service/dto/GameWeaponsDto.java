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
public class GameWeaponsDto implements Serializable {

    /** ID */
    private Integer id;

    /** 物品ID */
    private Integer itemId;

    /** 名称 */
    private String name;

    /** 类型 */
    private String type;

    /** 副类型 */
    private String type2;

    /** 代码 */
    private String code;

    /** 替代外观 */
    private String alternategfx;

    /** 名称字符串 */
    private String namestr;

    /** 版本 */
    private Integer version;

    /** 紧凑保存 */
    private Integer compactsave;

    /** 稀有度 */
    private Integer rarity;

    /** 可生成 */
    private Integer spawnable;

    /** 最小伤害 */
    private Integer mindam;

    /** 最大伤害 */
    private Integer maxdam;

    /** 单手或双手 */
    private Integer or2handed;

    /** 双手 */
    private Integer handed;

    /** 双手最小伤害 */
    private Integer handmindam;

    /** 双手最大伤害 */
    private Integer handmaxdam;

    /** 最小远程伤害 */
    private Integer minmisdam;

    /** 最大远程伤害 */
    private Integer maxmisdam;

    /** 范围加成 */
    private Integer rangeadder;

    /** 攻击速度 */
    private Integer speed;

    /** 力量加成 */
    private Integer strbonus;

    /** 敏捷加成 */
    private Integer dexbonus;

    /** 需求力量 */
    private Integer reqstr;

    /** 需求敏捷 */
    private Integer reqdex;

    /** 耐久度 */
    private Integer durability;

    /** 无耐久度 */
    private Integer nodurability;

    /** 物品等级 */
    private Integer level;

    /** 需求等级 */
    private Integer levelreq;

    /** 价格 */
    private Integer cost;

    /** 赌博价格 */
    private Integer gambleCost;

    /** 魔法等级 */
    private Integer magicLvl;

    /** 自动前缀 */
    private String autoPrefix;

    /** OpenBetaGfx */
    private String openbetagfx;

    /** 普通代码 */
    private String normcode;

    /** 高级代码 */
    private String ubercode;

    /** 特殊代码 */
    private String ultracode;

    /** 武器类别 */
    private String wclass;

    /** 双手武器类别 */
    private String handedwclass;

    /** 组件 */
    private String component;

    /** 打击类别 */
    private String hitClass;

    /** 物品栏宽度 */
    private Integer invwidth;

    /** 物品栏高度 */
    private Integer invheight;

    /** 可堆叠 */
    private Integer stackable;

    /** 最小堆叠 */
    private Integer minstack;

    /** 最大堆叠 */
    private Integer maxstack;

    /** 生成堆叠 */
    private Integer spawnstack;

    /** 翻转文件 */
    private String flippyfile;

    /** 物品栏文件 */
    private String invfile;

    /** 唯一物品栏文件 */
    private String uniqueinvfile;

    /** 套装物品栏文件 */
    private String setinvfile;

    /** 是否有物品栏 */
    private Integer hasinv;

    /** 宝石插槽数量 */
    private Integer gemsockets;

    /** 宝石应用类型 */
    private Integer gemapplytype;

    /** 特殊属性 */
    private String special;

    /** 可使用 */
    private Integer useable;

    /** 掉落音效 */
    private String dropsound;

    /** 掉落音效帧数 */
    private Integer dropsfxframe;

    /** 使用音效 */
    private String usesound;

    /** 是否唯一 */
    private Integer isUnique;

    /** 是否透明 */
    private Integer transparent;

    /** 透明度 */
    private Integer transtbl;

    /** 是否箭袋 */
    private Integer quivered;

    /** 光照半径 */
    private Integer lightradius;

    /** 是否腰带 */
    private Integer belt;

    /** 是否任务物品 */
    private Integer quest;

    /** 任务难度检查 */
    private Integer questdiffcheck;

    /** 投射物类型 */
    private String missiletype;

    /** 耐久度警告 */
    private Integer durwarning;

    /** 堆叠数量警告 */
    private Integer qntwarning;

    /** 宝石偏移 */
    private Integer gemoffset;

    /** 位域1 */
    private Integer bitfield1;

    /** Charsi最小值 */
    private Integer charsimin;

    /** Charsi最大值 */
    private Integer charsimax;

    /** Charsi魔法最小值 */
    private Integer charsimagicmin;

    /** Charsi魔法最大值 */
    private Integer charsimagicmax;

    /** Charsi魔法等级 */
    private Integer charsimagiclvl;

    /** Gheed最小值 */
    private Integer gheedmin;

    /** Gheed最大值 */
    private Integer gheedmax;

    /** Gheed魔法最小值 */
    private Integer gheedmagicmin;

    /** Gheed魔法最大值 */
    private Integer gheedmagicmax;

    /** Gheed魔法等级 */
    private Integer gheedmagiclvl;

    /** Akara最小值 */
    private Integer akaramin;

    /** Akara最大值 */
    private Integer akaramax;

    /** Akara魔法最小值 */
    private Integer akaramagicmin;

    /** Akara魔法最大值 */
    private Integer akaramagicmax;

    /** Akara魔法等级 */
    private Integer akaramagiclvl;

    /** Fara最小值 */
    private Integer faramin;

    /** Fara最大值 */
    private Integer faramax;

    /** Fara魔法最小值 */
    private Integer faramagicmin;

    /** Fara魔法最大值 */
    private Integer faramagicmax;

    /** Fara魔法等级 */
    private Integer faramagiclvl;

    /** Lysander最小值 */
    private Integer lysandermin;

    /** Lysander最大值 */
    private Integer lysandermax;

    /** Lysander魔法最小值 */
    private Integer lysandermagicmin;

    /** Lysander魔法最大值 */
    private Integer lysandermagicmax;

    /** Lysander魔法等级 */
    private Integer lysandermagiclvl;

    /** Drognan最小值 */
    private Integer drognanmin;

    /** Drognan最大值 */
    private Integer drognanmax;

    /** Drognan魔法最小值 */
    private Integer drognanmagicmin;

    /** Drognan魔法最大值 */
    private Integer drognanmagicmax;

    /** Drognan魔法等级 */
    private Integer drognanmagiclvl;

    /** Hralti最小值 */
    private Integer hraltimin;

    /** Hralti最大值 */
    private Integer hraltimax;

    /** Hralti魔法最小值 */
    private Integer hraltimagicmin;

    /** Hralti魔法最大值 */
    private Integer hraltimagicmax;

    /** Hralti魔法等级 */
    private Integer hraltimagiclvl;

    /** Alkor最小值 */
    private Integer alkormin;

    /** Alkor最大值 */
    private Integer alkormax;

    /** Alkor魔法最小值 */
    private Integer alkormagicmin;

    /** Alkor魔法最大值 */
    private Integer alkormagicmax;

    /** Alkor魔法等级 */
    private Integer alkormagiclvl;

    /** Ormus最小值 */
    private Integer ormusmin;

    /** Ormus最大值 */
    private Integer ormusmax;

    /** Ormus魔法最小值 */
    private Integer ormusmagicmin;

    /** Ormus魔法最大值 */
    private Integer ormusmagicmax;

    /** Ormus魔法等级 */
    private Integer ormusmagiclvl;

    /** Elzix最小值 */
    private Integer elzixmin;

    /** Elzix最大值 */
    private Integer elzixmax;

    /** Elzix魔法最小值 */
    private Integer elzixmagicmin;

    /** Elzix魔法最大值 */
    private Integer elzixmagicmax;

    /** Elzix魔法等级 */
    private Integer elzixmagiclvl;

    /** Jamella最小值 */
    private Integer jamellamin;

    /** Jamella最大值 */
    private Integer jamellamax;

    /** Jamella魔法最小值 */
    private Integer jamellamagicmin;

    /** Jamella魔法最大值 */
    private Integer jamellamagicmax;

    /** Jamella魔法等级 */
    private Integer jamellamagiclvl;

    /** Malah最小值 */
    private Integer malahmin;

    /** Malah最大值 */
    private Integer malahmax;

    /** Malah魔法最小值 */
    private Integer malahmagicmin;

    /** Malah魔法最大值 */
    private Integer malahmagicmax;

    /** Malah魔法等级 */
    private Integer malahmagiclvl;

    /** Larzuk最小值 */
    private Integer larzukmin;

    /** Larzuk最大值 */
    private Integer larzukmax;

    /** Larzuk魔法最小值 */
    private Integer larzukmagicmin;

    /** Larzuk魔法最大值 */
    private Integer larzukmagicmax;

    /** Larzuk魔法等级 */
    private Integer larzukmagiclvl;

    /** Malah自动魔法 */
    private Integer malahautomagic;

    /** Malah自动魔法最小值 */
    private Integer malahautomagicmin;

    /** Malah自动魔法最大值 */
    private Integer malahautomagicmax;

    /** Malah自动魔法等级 */
    private Integer malahautomagiclvl;
}
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
@Table(name="game_weapons")
public class GameWeapons implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    @ApiModelProperty(value = "ID")
    private Integer id;

    @Column(name = "`item_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "物品ID")
    private Integer itemId;

    @Column(name = "`name`")
    @ApiModelProperty(value = "名称")
    private String name;

    @Column(name = "`type`")
    @ApiModelProperty(value = "类型")
    private String type;

    @Column(name = "`type2`")
    @ApiModelProperty(value = "副类型")
    private String type2;

    @Column(name = "`code`")
    @ApiModelProperty(value = "代码")
    private String code;

    @Column(name = "`alternateGfx`")
    @ApiModelProperty(value = "替代外观")
    private String alternategfx;

    @Column(name = "`namestr`")
    @ApiModelProperty(value = "名称字符串")
    private String namestr;

    @Column(name = "`version`")
    @ApiModelProperty(value = "版本")
    private Integer version;

    @Column(name = "`compactsave`")
    @ApiModelProperty(value = "紧凑保存")
    private Integer compactsave;

    @Column(name = "`rarity`")
    @ApiModelProperty(value = "稀有度")
    private Integer rarity;

    @Column(name = "`spawnable`")
    @ApiModelProperty(value = "可生成")
    private Integer spawnable;

    @Column(name = "`mindam`")
    @ApiModelProperty(value = "最小伤害")
    private Integer mindam;

    @Column(name = "`maxdam`")
    @ApiModelProperty(value = "最大伤害")
    private Integer maxdam;

    @Column(name = "`1or2handed`")
    @ApiModelProperty(value = "单手或双手")
    private Integer or2handed;

    @Column(name = "`2handed`")
    @ApiModelProperty(value = "双手")
    private Integer handed;

    @Column(name = "`2handmindam`")
    @ApiModelProperty(value = "双手最小伤害")
    private Integer handmindam;

    @Column(name = "`2handmaxdam`")
    @ApiModelProperty(value = "双手最大伤害")
    private Integer handmaxdam;

    @Column(name = "`minmisdam`")
    @ApiModelProperty(value = "最小远程伤害")
    private Integer minmisdam;

    @Column(name = "`maxmisdam`")
    @ApiModelProperty(value = "最大远程伤害")
    private Integer maxmisdam;

    @Column(name = "`rangeadder`")
    @ApiModelProperty(value = "范围加成")
    private Integer rangeadder;

    @Column(name = "`speed`")
    @ApiModelProperty(value = "攻击速度")
    private Integer speed;

    @Column(name = "`StrBonus`")
    @ApiModelProperty(value = "力量加成")
    private Integer strbonus;

    @Column(name = "`DexBonus`")
    @ApiModelProperty(value = "敏捷加成")
    private Integer dexbonus;

    @Column(name = "`reqstr`")
    @ApiModelProperty(value = "需求力量")
    private Integer reqstr;

    @Column(name = "`reqdex`")
    @ApiModelProperty(value = "需求敏捷")
    private Integer reqdex;

    @Column(name = "`durability`")
    @ApiModelProperty(value = "耐久度")
    private Integer durability;

    @Column(name = "`nodurability`")
    @ApiModelProperty(value = "无耐久度")
    private Integer nodurability;

    @Column(name = "`level`")
    @ApiModelProperty(value = "物品等级")
    private Integer level;

    @Column(name = "`levelreq`")
    @ApiModelProperty(value = "需求等级")
    private Integer levelreq;

    @Column(name = "`cost`")
    @ApiModelProperty(value = "价格")
    private Integer cost;

    @Column(name = "`gamble_cost`")
    @ApiModelProperty(value = "赌博价格")
    private Integer gambleCost;

    @Column(name = "`magic_lvl`")
    @ApiModelProperty(value = "魔法等级")
    private Integer magicLvl;

    @Column(name = "`auto_prefix`")
    @ApiModelProperty(value = "自动前缀")
    private String autoPrefix;

    @Column(name = "`OpenBetaGfx`")
    @ApiModelProperty(value = "OpenBetaGfx")
    private String openbetagfx;

    @Column(name = "`normcode`")
    @ApiModelProperty(value = "普通代码")
    private String normcode;

    @Column(name = "`ubercode`")
    @ApiModelProperty(value = "高级代码")
    private String ubercode;

    @Column(name = "`ultracode`")
    @ApiModelProperty(value = "特殊代码")
    private String ultracode;

    @Column(name = "`wclass`")
    @ApiModelProperty(value = "武器类别")
    private String wclass;

    @Column(name = "`2handedwclass`")
    @ApiModelProperty(value = "双手武器类别")
    private String handedwclass;

    @Column(name = "`component`")
    @ApiModelProperty(value = "组件")
    private String component;

    @Column(name = "`hit_class`")
    @ApiModelProperty(value = "打击类别")
    private String hitClass;

    @Column(name = "`invwidth`")
    @ApiModelProperty(value = "物品栏宽度")
    private Integer invwidth;

    @Column(name = "`invheight`")
    @ApiModelProperty(value = "物品栏高度")
    private Integer invheight;

    @Column(name = "`stackable`")
    @ApiModelProperty(value = "可堆叠")
    private Integer stackable;

    @Column(name = "`minstack`")
    @ApiModelProperty(value = "最小堆叠")
    private Integer minstack;

    @Column(name = "`maxstack`")
    @ApiModelProperty(value = "最大堆叠")
    private Integer maxstack;

    @Column(name = "`spawnstack`")
    @ApiModelProperty(value = "生成堆叠")
    private Integer spawnstack;

    @Column(name = "`flippyfile`")
    @ApiModelProperty(value = "翻转文件")
    private String flippyfile;

    @Column(name = "`invfile`")
    @ApiModelProperty(value = "物品栏文件")
    private String invfile;

    @Column(name = "`uniqueinvfile`")
    @ApiModelProperty(value = "唯一物品栏文件")
    private String uniqueinvfile;

    @Column(name = "`setinvfile`")
    @ApiModelProperty(value = "套装物品栏文件")
    private String setinvfile;

    @Column(name = "`hasinv`")
    @ApiModelProperty(value = "是否有物品栏")
    private Integer hasinv;

    @Column(name = "`gemsockets`")
    @ApiModelProperty(value = "宝石插槽数量")
    private Integer gemsockets;

    @Column(name = "`gemapplytype`")
    @ApiModelProperty(value = "宝石应用类型")
    private Integer gemapplytype;

    @Column(name = "`special`")
    @ApiModelProperty(value = "特殊属性")
    private String special;

    @Column(name = "`useable`")
    @ApiModelProperty(value = "可使用")
    private Integer useable;

    @Column(name = "`dropsound`")
    @ApiModelProperty(value = "掉落音效")
    private String dropsound;

    @Column(name = "`dropsfxframe`")
    @ApiModelProperty(value = "掉落音效帧数")
    private Integer dropsfxframe;

    @Column(name = "`usesound`")
    @ApiModelProperty(value = "使用音效")
    private String usesound;

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

    @Column(name = "`questdiffcheck`")
    @ApiModelProperty(value = "任务难度检查")
    private Integer questdiffcheck;

    @Column(name = "`missiletype`")
    @ApiModelProperty(value = "投射物类型")
    private String missiletype;

    @Column(name = "`durwarning`")
    @ApiModelProperty(value = "耐久度警告")
    private Integer durwarning;

    @Column(name = "`qntwarning`")
    @ApiModelProperty(value = "堆叠数量警告")
    private Integer qntwarning;

    @Column(name = "`gemoffset`")
    @ApiModelProperty(value = "宝石偏移")
    private Integer gemoffset;

    @Column(name = "`bitfield1`")
    @ApiModelProperty(value = "位域1")
    private Integer bitfield1;

    @Column(name = "`CharsiMin`")
    @ApiModelProperty(value = "Charsi最小值")
    private Integer charsimin;

    @Column(name = "`CharsiMax`")
    @ApiModelProperty(value = "Charsi最大值")
    private Integer charsimax;

    @Column(name = "`CharsiMagicMin`")
    @ApiModelProperty(value = "Charsi魔法最小值")
    private Integer charsimagicmin;

    @Column(name = "`CharsiMagicMax`")
    @ApiModelProperty(value = "Charsi魔法最大值")
    private Integer charsimagicmax;

    @Column(name = "`CharsiMagicLvl`")
    @ApiModelProperty(value = "Charsi魔法等级")
    private Integer charsimagiclvl;

    @Column(name = "`GheedMin`")
    @ApiModelProperty(value = "Gheed最小值")
    private Integer gheedmin;

    @Column(name = "`GheedMax`")
    @ApiModelProperty(value = "Gheed最大值")
    private Integer gheedmax;

    @Column(name = "`GheedMagicMin`")
    @ApiModelProperty(value = "Gheed魔法最小值")
    private Integer gheedmagicmin;

    @Column(name = "`GheedMagicMax`")
    @ApiModelProperty(value = "Gheed魔法最大值")
    private Integer gheedmagicmax;

    @Column(name = "`GheedMagicLvl`")
    @ApiModelProperty(value = "Gheed魔法等级")
    private Integer gheedmagiclvl;

    @Column(name = "`AkaraMin`")
    @ApiModelProperty(value = "Akara最小值")
    private Integer akaramin;

    @Column(name = "`AkaraMax`")
    @ApiModelProperty(value = "Akara最大值")
    private Integer akaramax;

    @Column(name = "`AkaraMagicMin`")
    @ApiModelProperty(value = "Akara魔法最小值")
    private Integer akaramagicmin;

    @Column(name = "`AkaraMagicMax`")
    @ApiModelProperty(value = "Akara魔法最大值")
    private Integer akaramagicmax;

    @Column(name = "`AkaraMagicLvl`")
    @ApiModelProperty(value = "Akara魔法等级")
    private Integer akaramagiclvl;

    @Column(name = "`FaraMin`")
    @ApiModelProperty(value = "Fara最小值")
    private Integer faramin;

    @Column(name = "`FaraMax`")
    @ApiModelProperty(value = "Fara最大值")
    private Integer faramax;

    @Column(name = "`FaraMagicMin`")
    @ApiModelProperty(value = "Fara魔法最小值")
    private Integer faramagicmin;

    @Column(name = "`FaraMagicMax`")
    @ApiModelProperty(value = "Fara魔法最大值")
    private Integer faramagicmax;

    @Column(name = "`FaraMagicLvl`")
    @ApiModelProperty(value = "Fara魔法等级")
    private Integer faramagiclvl;

    @Column(name = "`LysanderMin`")
    @ApiModelProperty(value = "Lysander最小值")
    private Integer lysandermin;

    @Column(name = "`LysanderMax`")
    @ApiModelProperty(value = "Lysander最大值")
    private Integer lysandermax;

    @Column(name = "`LysanderMagicMin`")
    @ApiModelProperty(value = "Lysander魔法最小值")
    private Integer lysandermagicmin;

    @Column(name = "`LysanderMagicMax`")
    @ApiModelProperty(value = "Lysander魔法最大值")
    private Integer lysandermagicmax;

    @Column(name = "`LysanderMagicLvl`")
    @ApiModelProperty(value = "Lysander魔法等级")
    private Integer lysandermagiclvl;

    @Column(name = "`DrognanMin`")
    @ApiModelProperty(value = "Drognan最小值")
    private Integer drognanmin;

    @Column(name = "`DrognanMax`")
    @ApiModelProperty(value = "Drognan最大值")
    private Integer drognanmax;

    @Column(name = "`DrognanMagicMin`")
    @ApiModelProperty(value = "Drognan魔法最小值")
    private Integer drognanmagicmin;

    @Column(name = "`DrognanMagicMax`")
    @ApiModelProperty(value = "Drognan魔法最大值")
    private Integer drognanmagicmax;

    @Column(name = "`DrognanMagicLvl`")
    @ApiModelProperty(value = "Drognan魔法等级")
    private Integer drognanmagiclvl;

    @Column(name = "`HraltiMin`")
    @ApiModelProperty(value = "Hralti最小值")
    private Integer hraltimin;

    @Column(name = "`HraltiMax`")
    @ApiModelProperty(value = "Hralti最大值")
    private Integer hraltimax;

    @Column(name = "`HraltiMagicMin`")
    @ApiModelProperty(value = "Hralti魔法最小值")
    private Integer hraltimagicmin;

    @Column(name = "`HraltiMagicMax`")
    @ApiModelProperty(value = "Hralti魔法最大值")
    private Integer hraltimagicmax;

    @Column(name = "`HraltiMagicLvl`")
    @ApiModelProperty(value = "Hralti魔法等级")
    private Integer hraltimagiclvl;

    @Column(name = "`AlkorMin`")
    @ApiModelProperty(value = "Alkor最小值")
    private Integer alkormin;

    @Column(name = "`AlkorMax`")
    @ApiModelProperty(value = "Alkor最大值")
    private Integer alkormax;

    @Column(name = "`AlkorMagicMin`")
    @ApiModelProperty(value = "Alkor魔法最小值")
    private Integer alkormagicmin;

    @Column(name = "`AlkorMagicMax`")
    @ApiModelProperty(value = "Alkor魔法最大值")
    private Integer alkormagicmax;

    @Column(name = "`AlkorMagicLvl`")
    @ApiModelProperty(value = "Alkor魔法等级")
    private Integer alkormagiclvl;

    @Column(name = "`OrmusMin`")
    @ApiModelProperty(value = "Ormus最小值")
    private Integer ormusmin;

    @Column(name = "`OrmusMax`")
    @ApiModelProperty(value = "Ormus最大值")
    private Integer ormusmax;

    @Column(name = "`OrmusMagicMin`")
    @ApiModelProperty(value = "Ormus魔法最小值")
    private Integer ormusmagicmin;

    @Column(name = "`OrmusMagicMax`")
    @ApiModelProperty(value = "Ormus魔法最大值")
    private Integer ormusmagicmax;

    @Column(name = "`OrmusMagicLvl`")
    @ApiModelProperty(value = "Ormus魔法等级")
    private Integer ormusmagiclvl;

    @Column(name = "`ElzixMin`")
    @ApiModelProperty(value = "Elzix最小值")
    private Integer elzixmin;

    @Column(name = "`ElzixMax`")
    @ApiModelProperty(value = "Elzix最大值")
    private Integer elzixmax;

    @Column(name = "`ElzixMagicMin`")
    @ApiModelProperty(value = "Elzix魔法最小值")
    private Integer elzixmagicmin;

    @Column(name = "`ElzixMagicMax`")
    @ApiModelProperty(value = "Elzix魔法最大值")
    private Integer elzixmagicmax;

    @Column(name = "`ElzixMagicLvl`")
    @ApiModelProperty(value = "Elzix魔法等级")
    private Integer elzixmagiclvl;

    @Column(name = "`JamellaMin`")
    @ApiModelProperty(value = "Jamella最小值")
    private Integer jamellamin;

    @Column(name = "`JamellaMax`")
    @ApiModelProperty(value = "Jamella最大值")
    private Integer jamellamax;

    @Column(name = "`JamellaMagicMin`")
    @ApiModelProperty(value = "Jamella魔法最小值")
    private Integer jamellamagicmin;

    @Column(name = "`JamellaMagicMax`")
    @ApiModelProperty(value = "Jamella魔法最大值")
    private Integer jamellamagicmax;

    @Column(name = "`JamellaMagicLvl`")
    @ApiModelProperty(value = "Jamella魔法等级")
    private Integer jamellamagiclvl;

    @Column(name = "`MalahMin`")
    @ApiModelProperty(value = "Malah最小值")
    private Integer malahmin;

    @Column(name = "`MalahMax`")
    @ApiModelProperty(value = "Malah最大值")
    private Integer malahmax;

    @Column(name = "`MalahMagicMin`")
    @ApiModelProperty(value = "Malah魔法最小值")
    private Integer malahmagicmin;

    @Column(name = "`MalahMagicMax`")
    @ApiModelProperty(value = "Malah魔法最大值")
    private Integer malahmagicmax;

    @Column(name = "`MalahMagicLvl`")
    @ApiModelProperty(value = "Malah魔法等级")
    private Integer malahmagiclvl;

    @Column(name = "`LarzukMin`")
    @ApiModelProperty(value = "Larzuk最小值")
    private Integer larzukmin;

    @Column(name = "`LarzukMax`")
    @ApiModelProperty(value = "Larzuk最大值")
    private Integer larzukmax;

    @Column(name = "`LarzukMagicMin`")
    @ApiModelProperty(value = "Larzuk魔法最小值")
    private Integer larzukmagicmin;

    @Column(name = "`LarzukMagicMax`")
    @ApiModelProperty(value = "Larzuk魔法最大值")
    private Integer larzukmagicmax;

    @Column(name = "`LarzukMagicLvl`")
    @ApiModelProperty(value = "Larzuk魔法等级")
    private Integer larzukmagiclvl;

    @Column(name = "`MalahAutoMagic`")
    @ApiModelProperty(value = "Malah自动魔法")
    private Integer malahautomagic;

    @Column(name = "`MalahAutoMagicMin`")
    @ApiModelProperty(value = "Malah自动魔法最小值")
    private Integer malahautomagicmin;

    @Column(name = "`MalahAutoMagicMax`")
    @ApiModelProperty(value = "Malah自动魔法最大值")
    private Integer malahautomagicmax;

    @Column(name = "`MalahAutoMagicLvl`")
    @ApiModelProperty(value = "Malah自动魔法等级")
    private Integer malahautomagiclvl;

    public void copy(GameWeapons source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}

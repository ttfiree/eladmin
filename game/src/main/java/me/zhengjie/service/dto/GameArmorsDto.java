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

import java.awt.*;
import java.io.Serializable;
import java.util.List;

/**
* @website https://eladmin.vip
* @description /
* @author lyc
* @date 2023-06-22
**/
@Data
public class GameArmorsDto implements Serializable {

    /** ID */
    private Integer id;

    /** 护甲名称 */
    private String name;

    /** 版本 */
    private Integer version;

    /** 紧凑保存 */
    private Integer compactsave;

    /** 稀有度 */
    private Integer rarity;

    /** 最小防御 */
    private Integer minac;

    /** 可生成 */
    private Integer spawnable;

    /** 最大防御 */
    private Integer maxac;

    /** 吸收 */
    private Integer absorbs;

    /** 攻击速度 */
    private Integer speed;

    /** 需求力量 */
    private Integer reqstr;

    /** 格挡率 */
    private Integer block;

    /** 耐久度 */
    private Integer durability;

    /** 无耐久度 */
    private Integer nodurability;

    /** 等级 */
    private Integer level;

    /** 等级需求 */
    private Integer levelreq;

    /** 价格 */
    private Integer cost;

    /** 赌博价格 */
    private Integer gambleCost;

    /** 代码 */
    private String code;

    /** 名称字符串 */
    private String namestr;

    /** 魔法等级 */
    private Integer magicLvl;

    /** 自动前缀 */
    private Integer autoPrefix;

    /** 备用图形代码 */
    private String alternategfx;

    /** 公测图形代码 */
    private String openBetaGfx;

    /** 普通模式代码 */
    private String normcode;

    /** 超级模式代码 */
    private String ubercode;

    /** 终极模式代码 */
    private String ultracode;

    /** 法术偏移量 */
    private Integer spelloffset;

    /** 组件 */
    private Integer component;

    /** 背包宽度 */
    private Integer invwidth;

    /** 背包高度 */
    private Integer invheight;

    /** 是否有背包 */
    private Integer hasinv;

    /** 宝石插槽数量 */
    private Integer gemsockets;

    /** 宝石类型 */
    private Integer gemapplytype;

    /** 动画文件 */
    private String flippyfile;

    /** 背包文件 */
    private String invfile;

    /** 唯一背包文件 */
    private String uniqueinvfile;

    /** 套装背包文件 */
    private String setinvfile;

    /** 右手武器 */
    private Integer rarm;

    /** 左手武器 */
    private Integer larm;

    /** 身体 */
    private Integer torso;

    /** 腿部 */
    private Integer legs;

    /** 右肩垫 */
    private Integer rspad;

    /** 左肩垫 */
    private Integer lspad;

    /** 可用性 */
    private Integer useable;

    /** 可投掷 */
    private Integer throwable;

    /** 可叠加 */
    private Integer stackable;

    /** 最小叠加数 */
    private Integer minstack;

    /** 最大叠加数 */
    private Integer maxstack;

    /** 类型 */
    private String type;

    /** 类型2 */
    private String type2;

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

    /** 投射物类型 */
    private Integer missiletype;

    /** 耐久度警告 */
    private Integer durwarning;

    /** 数量警告 */
    private Integer qntwarning;

    /** 最小伤害 */
    private Integer mindam;

    /** 最大伤害 */
    private Integer maxdam;

    /** 力量加成 */
    private Integer strbonus;

    /** 敏捷加成 */
    private Integer dexbonus;

    /** 宝石偏移量 */
    private Integer gemoffset;

    private String nameHtml;

    private List<String> statsHtml;

    private Integer charId;

    public boolean isShield() {
        if (type != null) {
            if (type.equals("ashd") || type.equals("shie")
                    || type.equals("head")) {
                return true;
            }
        }
        return false;
    }

    public Color getItemColor() {
        if (isUnique==1) {
            // return Color.yellow.darker().darker();
            return new Color(16, 133, 213);
        }
        /*if (isSet()) {
            return Color.green.darker();
        }*/
        if (version>2&&version<5) {
            return Color.gray;
        }
        if (version>4&&version<7) {
            return Color.yellow.brighter();
        }
        if (version>6&&version<9) {
            return Color.red;
        }
        if (version>8&&version<11) {
            return Color.orange;
        }
        if (version>10&&version<13) {
            return new Color(255, 222, 173);
        }else {
            //返回紫色
            return new Color(255, 0, 255);
        }
    }
}

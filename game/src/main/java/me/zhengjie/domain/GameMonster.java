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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @website https://eladmin.vip
* @description /
* @author lyc
* @date 2023-06-20
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

    @Column(name = "`maze_id`", nullable = false)
    @NotNull
    @ApiModelProperty(value = "迷宫ID")
    private Long mazeId;

    @Column(name = "`create_time`", nullable = false)
    @NotNull
    @ApiModelProperty(value = "createTime")
    private Timestamp createTime;

    @Column(name = "`is_delete`", nullable = false)
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

    @Column(name = "`name`", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "怪物名")
    private String name;

    @Column(name = "`experience`", nullable = false)
    @NotNull
    @ApiModelProperty(value = "经验值")
    private Long experience;

    @Column(name = "`money`", nullable = false)
    @NotNull
    @ApiModelProperty(value = "金钱")
    private Long money;

    @Column(name = "`item`", nullable = false)
    @NotNull
    @ApiModelProperty(value = "爆率")
    private Long item;

    @Column(name = "`item_rate`", nullable = false)
    @NotNull
    @ApiModelProperty(value = "爆率等级")
    private Long itemRate;

    @Column(name = "`hit_points`", nullable = false)
    @NotNull
    @ApiModelProperty(value = "生命值")
    private Integer hitPoints;

    @Column(name = "`speed`")
    @ApiModelProperty(value = "速度")
    private Integer speed;

    @Column(name = "`critical_Chance`")
    @ApiModelProperty(value = "暴击率")
    private Integer criticalChance;

    @Column(name = "`armor_Class`")
    @ApiModelProperty(value = "护甲")
    private Integer armorClass;

    @Column(name = "`damage`")
    @ApiModelProperty(value = "伤害")
    private Integer damage;

    @Column(name = "`critical_Damage`")
    @ApiModelProperty(value = "暴击伤害")
    private Integer criticalDamage;

    @Column(name = "`level`")
    @ApiModelProperty(value = "等级")
    private Integer level;

    @Column(name = "`is_boss`")
    @ApiModelProperty(value = "是否是boss级别")
    private Integer isBoss;

    @Column(name = "`type`")
    @ApiModelProperty(value = "类型")
    private Integer type;

    @Column(name = "`min_hp`")
    @ApiModelProperty(value = "最小生命值")
    private Integer minHp;

    @Column(name = "`AC`")
    @ApiModelProperty(value = "准确率")
    private Integer ac;

    @Column(name = "`max_damage`")
    @ApiModelProperty(value = "最大伤害")
    private Integer maxDamage;

    @Column(name = "`min_damage`")
    @ApiModelProperty(value = "最小伤害")
    private Integer minDamage;

    @Column(name = "`max_hp`")
    @ApiModelProperty(value = "最大生命值")
    private Integer maxHp;

    public void copy(GameMonster source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }

}

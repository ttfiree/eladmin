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

/**
* @website https://eladmin.vip
* @description /
* @author lyc
* @date 2023-06-05
**/
@Entity
@Data
@Table(name="game_character")
public class GameCharacter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    @ApiModelProperty(value = "角色ID")
    private Integer id;

    @Column(name = "`user_id`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "用户ID")
    private Long userId;

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
    @ApiModelProperty(value = "角色名")
    private String name;


    @Column(name = "`level`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "等级")
    private Integer level;

    @Column(name = "`experience`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "经验值")
    private Long experience;

    @Column(name = "`money`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "金钱")
    private Long money;

    @Column(name = "`hit_points`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "生命值")
    private Integer hitPoints;

    @Column(name = "`armor_class`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "护甲值")
    private Integer armorClass;

    @Column(name = "`speed`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "移动速度")
    private Integer speed;

    public void copy(GameCharacter source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}

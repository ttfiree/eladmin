package me.zhengjie.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class GameResult {

    @Column(name = "`name`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "角色名")
    private String battleLog;

    private long battleTime;

    private long battleAgainTime;

    private long revocery;

    private int levelUp;

    private long exp;

}

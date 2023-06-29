package me.zhengjie.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.service.dto.GameArmorsDto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class GameResult {


    private String battleLog;

    private long battleTime;

    private long battleAgainTime;

    private long revocery;

    private int levelUp;

    private long exp;

    private int win;

    private GameArmorsDto gameArmorsDto;

}

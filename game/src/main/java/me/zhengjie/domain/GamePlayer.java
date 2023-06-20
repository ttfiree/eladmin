package me.zhengjie.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Random;
@Data
public class GamePlayer  implements Serializable {

    @Column(name = "`name`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "角色名")
    private String name;

    @Column(name = "`hit_points`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "生命值")
    private Integer hitPoints;

    private Integer rPoints;

    @Column(name = "`armor_class`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "护甲值")
    private Integer armorClass;

    @Column(name = "`speed`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "移动速度")
    private Integer speed;

    @Column(name = "`strength`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "力量")
    private Integer strength;

    @Column(name = "`dexterity`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "敏捷")
    private Integer dexterity;

    @Column(name = "`constitution`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "体质")
    private Integer constitution;

    @Column(name = "`intelligence`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "智力")
    private Integer intelligence;

    @Column(name = "`wisdom`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "感知")
    private Integer wisdom;

    @Column(name = "`charisma`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "魅力")
    private Integer charisma;

    @Column(name = "`damage`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "基础伤害")
    private Integer damage;


    @Column(name = "`critical_chance`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "暴击")
    private BigDecimal criticalChance;

    @Column(name = "`critical_damage`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "爆伤")
    private BigDecimal criticalDamage;


    /** 迷宫id */
    private Integer mazeId;

    /** 金钱加成 */
    private BigDecimal moneyPlus;

    /** 经验加成 */
    private Double expPlus;

    /** 掉率加成 */
    private BigDecimal itemPlus;

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


    public boolean isAlive() {
        return rPoints > 0;
    }

    public int attack() {
        Random random = new Random();
        if (random.nextDouble() < criticalChance.doubleValue()) { // 判断是否暴击
            return (int) (damage * (1 + criticalDamage.doubleValue()));
        } else {
            return damage;
        }
    }

    public int defense() {
        return armorClass;
    }

    public void takeDamage(int damage) {
        rPoints -= damage;
        if (rPoints < 0) {
            rPoints = 0;
        }
    }

    @Override
    public String toString() {
        return String.format(name+"：生命值"+rPoints+"/"+hitPoints+"，攻击力"+damage+"，防御力"+armorClass+"，速度"+speed+"，暴击率"+criticalChance+"，暴击伤害"+ criticalDamage);
    }

    public boolean isCritical() {
        Random random = new Random();
        return random.nextDouble() < criticalChance.doubleValue();
    }
}

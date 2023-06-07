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


    public boolean isAlive() {
        return hitPoints > 0;
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
        hitPoints -= damage;
        if (hitPoints < 0) {
            hitPoints = 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%s：生命值%d/%d，攻击力%d，防御力%d，速度%.2f，暴击率%.2f，暴击伤害%.2f",
                name, hitPoints,  damage, armorClass, speed, criticalChance, criticalDamage);
    }

    public boolean isCritical() {
        Random random = new Random();
        return random.nextDouble() < criticalChance.doubleValue();
    }
}

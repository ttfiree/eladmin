package me.zhengjie.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.domain.*;
import me.zhengjie.service.FightService;
import me.zhengjie.service.GameAttributeService;
import me.zhengjie.service.GameMonsterService;
import me.zhengjie.service.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Service
@RequiredArgsConstructor
public class FightServiceImpl implements FightService {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private GameAttributeService gameAttributeService;
    @Autowired
    private GameMonsterService gameMonsterService;

    public GamePlayer buildPlayer(GameCharacterDto character){

        GameAttributeDto attribute = gameAttributeService.findById(character.getId());
        GamePlayer player = new GamePlayer();
        BeanUtils.copyProperties(attribute,player);
        BeanUtils.copyProperties(character,player);
        return player;

    }

    public GameMonsterDto buildMonster(GameCharacterDto character){
        GameAttributeDto attribute = gameAttributeService.findById(character.getId());
        GameMonsterQueryCriteria gameMonsterQueryCriteria = new GameMonsterQueryCriteria();
        gameMonsterQueryCriteria.setMazeId(attribute.getMazeId());
        return gameMonsterService.queryAll(gameMonsterQueryCriteria).get(0);
    }

    public void fight(GamePlayer player, GameMonsterDto monster,GameResult gameResult) {
        StringBuilder sb = new StringBuilder();
        sb.append("人物属性：").append(player).append("\n");
        sb.append("怪物属性：").append(monster).append("\n");
        // 进行战斗
        int round = 1;
        double playerProgress = 0; // 玩家攻击进度
        double monsterProgress = 0; // 怪物攻击进度
        double realDuration = 1; // 战斗速度
        LocalDateTime localDateTime = LocalDateTime.now();
        // 计算现实时间和攻击速度
        double playerSpeed = player.getSpeed();
        double monsterSpeed = monster.getSpeed();
        double totalDuration = 0;
        sb.append(localDateTime.format(dateTimeFormatter)).append("战斗开始");
        while (player.isAlive() && monster.isAlive()) {
            sb.append("第" + round + "回合开始：");
            double usedTime = 0;
            // 同时进行角色和怪物的攻击
            boolean playerAttacked = false;
            boolean monsterAttacked = false;
            while (!playerAttacked && !monsterAttacked) {
                playerProgress += playerSpeed;
                monsterProgress += monsterSpeed;
                usedTime += 1;
                if (playerProgress >= 1) {
                    // 玩家攻击
                    int playerAttack = player.attack();
                    int monsterDefense = monster.defense();
                    int playerDamage = playerAttack - monsterDefense;
                    if (playerDamage > 0) {
                        boolean isCritical = player.isCritical(); // 判断是否暴击
                        if (isCritical) {
                            playerDamage *= 2; // 暴击伤害翻倍
                            sb.append(String.format("["+localDateTime.plusSeconds((long) usedTime).format(dateTimeFormatter)+"]第 " + round + " 回合，玩家对怪物造成了"+playerDamage+"点暴击伤害！", playerDamage));
                        } else {
                            sb.append(String.format("["+localDateTime.plusSeconds((long) usedTime).format(dateTimeFormatter)+"]第 " + round + " 回合，玩家对怪物造成了"+playerDamage+"点伤害！", playerDamage));
                        }
                        monster.takeDamage(playerDamage);
                    } else {
                        sb.append(String.format("["+localDateTime.plusSeconds((long) usedTime).format(dateTimeFormatter)+"]第 " + round + " 回合，玩家的攻击被怪物格挡了！"));
                    }
                    playerProgress -= 1;
                    playerAttacked = true;
                }
                if (monsterProgress >= 1) {
                    // 怪物攻击
                    int monsterAttack = monster.attack();
                    int playerDefense = player.defense();
                    int monsterDamage = monsterAttack - playerDefense;
                    if (monsterDamage > 0) {
                        boolean isCritical = monster.isCritical(); // 判断是否暴击
                        if (isCritical) {
                            monsterDamage *= 2; // 暴击伤害翻倍
                            sb.append(String.format("["+localDateTime.plusSeconds((long) usedTime).format(dateTimeFormatter)+"]第 " + round + " 回合，怪物对玩家造成了"+monsterDamage+"点暴击伤害！" ));
                        } else {
                            sb.append(String.format("["+localDateTime.plusSeconds((long) usedTime).format(dateTimeFormatter)+"]第 " + round + " 回合，怪物对玩家造成了"+monsterDamage+"点伤害！"));
                        }
                        player.takeDamage(monsterDamage);
                    } else {
                        sb.append(String.format("["+localDateTime.plusSeconds((long) usedTime).format(dateTimeFormatter)+"]第 " + round + " 回合，怪物的攻击被玩家格挡了！"));
                    }
                    monsterProgress -= 1;
                    monsterAttacked = true;
                }
            }
            // 打印人物和怪物的属性
            sb.append("人物属性：").append(player);
            sb.append("怪物属性：").append(monster);
            totalDuration += usedTime;
            round++;
        }

// 计算总的战斗时间并打印

        sb.append("战斗结束，！").append(localDateTime.plusSeconds((long) totalDuration).format(dateTimeFormatter)).append("%n");
// 打印战斗结果
        if (player.isAlive()) {
            sb.append("战斗结束，玩家胜利！");
        } else {
            sb.append("战斗结束，怪物胜利！");
        }
        gameResult.setBattleLog(sb.toString());
    }

}




package me.zhengjie.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import me.zhengjie.domain.*;
import me.zhengjie.service.ExpService;
import me.zhengjie.service.FightService;
import me.zhengjie.service.GameAttributeService;
import me.zhengjie.service.GameMonsterService;
import me.zhengjie.service.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FightServiceImpl implements FightService {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private GameAttributeService gameAttributeService;
    @Autowired
    private GameMonsterService gameMonsterService;
    @Autowired
    private ExpService expService;

    public GamePlayer buildPlayer(GameCharacterDto character){

        GameAttributeDto attribute = gameAttributeService.findById(character.getId());
        GamePlayer player = new GamePlayer();
        BeanUtils.copyProperties(attribute,player);
        BeanUtils.copyProperties(character,player);
        player.setRPoints(player.getHitPoints());
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
        sb.append("人物属性：").append(player).append("END");
        sb.append("怪物属性：").append(monster).append("END");
        int mhp = monster.getHitPoints();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 进行战斗
        int round = 1;
        double playerProgress = 0; // 玩家攻击进度
        double monsterProgress = 0; // 怪物攻击进度
        double realDuration = 1; // 战斗速度
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instant = Instant.now();
        // 计算现实时间和攻击速度
        double playerSpeed = player.getSpeed();
        double monsterSpeed = monster.getSpeed();
        double totalDuration = 0;
        sb.append(localDateTime.format(dateTimeFormatter)).append("战斗开始").append("END");
        long usedTime = 0;
        while (player.isAlive() && monster.isAlive()&&round<30) {
           /* sb.append(localDateTime.format(dateTimeFormatter)).append("第" + round + "回合开始：");*/
            // 同时进行角色和怪物的攻击
            boolean playerAttacked = false;
            boolean monsterAttacked = false;
            while (!playerAttacked && !monsterAttacked) {
                playerProgress += playerSpeed;
                monsterProgress += monsterSpeed;
                usedTime += 1;
                if (playerProgress >= 10) {
                    // 玩家攻击
                    int playerAttack = player.attack();
                    int monsterDefense = monster.defense();
                    int playerDamage = playerAttack - monsterDefense;
                    if (playerDamage > 0) {
                        boolean isCritical = player.isCritical(); // 判断是否暴击
                        if (isCritical) {
                            playerDamage *= 2; // 暴击伤害翻倍
                            sb.append(String.format("第 " + round + " 回合，玩家对怪物造成了<font color=\"#c24f4a\">"+playerDamage+"</font>点<b>暴击</b>伤害！", playerDamage)).append(" ").append(monster.getHitPoints()).append("/").append(mhp).append("END");
                        } else {
                            sb.append(String.format("第 " + round + " 回合，玩家对怪物造成了<font color=\"#f9963b\">"+playerDamage+"</font>点伤害！", playerDamage)).append(" ").append(monster.getHitPoints()).append("/").append(mhp).append("END");
                        }
                        monster.takeDamage(playerDamage);
                    } else {
                        sb.append(String.format("第 " + round + " 回合，玩家的攻击被怪物<font color=\"#c24f4a\">格挡</font>了！")).append(" ").append(monster.getHitPoints()).append("/").append(mhp).append("END");
                    }
                    playerProgress -= 10;
                    playerAttacked = true;
                    round++;
                }
                if (monsterProgress >= 10) {
                    // 怪物攻击
                    int monsterAttack = monster.attack();
                    int playerDefense = player.defense();
                    int monsterDamage = monsterAttack - playerDefense;
                    if (monsterDamage > 0) {
                        boolean isCritical = monster.isCritical(); // 判断是否暴击
                        if (isCritical) {
                            monsterDamage *= 2; // 暴击伤害翻倍
                            sb.append(String.format("第 " + round + " 回合，怪物对玩家造成了" + monsterDamage + "点暴击伤害！")).append(" ").append(player.getRPoints()).append("/").append(player.getHitPoints()).append("END");
                        } else {
                            sb.append(String.format("第 " + round + " 回合，怪物对玩家造成了" + monsterDamage + "点伤害！")).append(" ").append(" ").append(player.getRPoints()).append("/").append(player.getHitPoints()).append("END");
                        }
                        player.takeDamage(monsterDamage);
                    } else {
                        sb.append(String.format("第 " + round + " 回合，怪物的攻击被玩家格挡了！")).append(" ").append(" ").append(player.getRPoints()).append("/").append(player.getHitPoints()).append("END");
                    }
                    monsterProgress -= 10;
                    monsterAttacked = true;
                    round++;
                }
            }
            // 打印人物和怪物的属性
            totalDuration += usedTime;

        }

// 打印战斗结果
        if (player.isAlive()) {
            sb.append("战斗结束，玩家胜利！").append("END");
        } else {
            sb.append("战斗结束，怪物胜利！").append("END");
        }
        //计算经验值
        expService.dealExpAfterBattle(player,monster,gameResult);
        gameResult.setBattleLog(sb.toString());
        gameResult.setBattleTime(usedTime);
        long r;
        //计算恢复时间
        if(gameResult.getLevelUp()==1){
            r = 10;
        }else {
            r = recovery(player);
        }
        sb.append("恢复时间").append(r).append("秒。").append("END");
        instant = instant.plusSeconds(usedTime).plusSeconds(r);
        gameResult.setBattleAgainTime(instant.toEpochMilli());
        gameResult.setRevocery(r);

    }

    private long recovery(GamePlayer player){
        int lhp = player.getHitPoints() - player.getRPoints();
        return lhp/2+10;
    }

    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File jsonFile = new File("C:\\Users\\Administrator\\IdeaProjects\\eladmin\\game\\src\\main\\java\\me\\zhengjie\\content\\monsters.json");
            JsonNode rootNode = mapper.readTree(jsonFile);
            List<JsonNode> newNodes = new ArrayList<>();
            List<String> sqlStatements = new ArrayList<>();

            for (JsonNode node : rootNode) {
                String key = node.get("Key").asText();
                String zhCN = node.get("zhCN").asText();

                // Generate the SQL statement for this record
                String sql = "UPDATE game_monster SET name = '" + zhCN + "' WHERE ext_String_one = '" + key + "';";
                sqlStatements.add(sql);
            }
            for (String sql : sqlStatements) {
                System.out.println(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}




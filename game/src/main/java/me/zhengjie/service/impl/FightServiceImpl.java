package me.zhengjie.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import me.zhengjie.content.DataMap;
import me.zhengjie.domain.*;
import me.zhengjie.service.*;
import me.zhengjie.service.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.math.BigDecimal.*;
import static nl.basjes.shaded.org.antlr.v4.runtime.misc.Utils.readFile;

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
    @Autowired
    private GameItemService gameItemService;

    public GamePlayer buildPlayer(GameCharacterDto character){

        GameAttributeDto attribute = gameAttributeService.findById(character.getId());
        GamePlayer player = new GamePlayer();
        BeanUtils.copyProperties(attribute,player);
        BeanUtils.copyProperties(character,player);
        player.setRPoints(player.getHitPoints());
        player.setCharId(character.getId());
        return player;

    }

    public GameMonsterDto buildMonster(GameCharacterDto character){
        GameMonsterQueryCriteria gameMonsterQueryCriteria = new GameMonsterQueryCriteria();
        gameMonsterQueryCriteria.setLevelLess(5);
        List<GameMonsterDto> gameMonsterDtos = gameMonsterService.queryAll(gameMonsterQueryCriteria);
        //随机取其中一个
        GameMonsterDto g = gameMonsterDtos.get((int)(Math.random()*gameMonsterDtos.size()));
        int maxhp = g.getMaxHp();
        int minhp = g.getMinHp();
        //根据最大值最小值随机生成一个值
        int hp = (int)(Math.random()*(maxhp-minhp+1))+minhp;
        g.setHitPoints(hp);
        //随机生成攻击力
        int maxatk = g.getMaxDamage();
        int minatk = g.getMinDamage();
        int atk = (int)(Math.random()*(maxatk-minatk+1))+minatk;
        g.setDamage(atk);
        return g;
    }

    public void fight(GamePlayer player, GameMonsterDto monster,GameResult gameResult) {
        StringBuilder sb = new StringBuilder();
        sb.append("人物属性：").append(player).append("END");
        sb.append("怪物属性：").append(monster).append("END");
        int mhp = monster.getHitPoints();
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
            gameResult.setWin(1);
            player.setItem(monster.getItemRate());
            //计算经验值
            expService.dealExpAfterBattle(player,monster,gameResult);
            GameArmorsDto gameArmorsDto = gameItemService.createItem(player);
            if(gameArmorsDto!=null){
                sb.append("获得装备：");
                gameResult.setGameArmorsDto(gameArmorsDto);
            }
        } else {
            sb.append("战斗结束，怪物胜利！").append("END");
            gameResult.setWin(0);
        }
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
        int lhp;
        BigDecimal i = valueOf(player.getRPoints()).multiply(valueOf(100)).divide(valueOf(player.getHitPoints()));
        lhp =  100-i.intValue();
        return lhp/2+10;
    }

    public static void main(String[] args) throws IOException {
        //读取本地文件为字符串
        File file = new File("C:\\Users\\Administrator\\IdeaProjects\\eladmin\\eladmin-system\\src\\main\\resources\\itemStat.json");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = null;
        StringBuilder sb = new StringBuilder();
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        //将json数组字符串转换为JSONArray对象
        JSONArray jsonArray = JSONArray.parseArray(sb.toString());
        //遍历json数组
        for (int i = 0; i < jsonArray.size(); i++) {
            //获取每一个JsonObject对象
            JSONObject myjObject = jsonArray.getJSONObject(i);
            //获取每一个对象中的值
            myjObject.put("maxvalue",100);
        }
        System.out.println(jsonArray);
    }

/*    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Administrator\\IdeaProjects\\eladmin\\eladmin-system\\src\\main\\resources\\itemStat.json");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = null;
        StringBuilder sb = new StringBuilder();
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        //将json数组字符串转换为JSONArray对象
        List<JSONObject> jsonArray = JSONArray.parseArray(sb.toString(), JSONObject.class);
        ObjectMapper mapper = new ObjectMapper();
        File file2 = new File("C:\\Users\\Administrator\\IdeaProjects\\eladmin\\eladmin-system\\src\\main\\resources\\patch.json");
        InputStream inputStream = file2.toURI().toURL().openStream();
        mapper = new ObjectMapper();
            Map<String, String> PATCH_MAP = Collections.unmodifiableMap(mapper.readValue(inputStream, new TypeReference<Map<String, String>>(){}));
        File file3 = new File("C:\\Users\\Administrator\\IdeaProjects\\eladmin\\eladmin-system\\src\\main\\resources\\exp.json");
            inputStream = file3.toURI().toURL().openStream();
            mapper = new ObjectMapper();
            Map<String, String> EXP2_MAP = Collections.unmodifiableMap(mapper.readValue(inputStream, new TypeReference<Map<String, String>>(){}));
        File file4 = new File("C:\\Users\\Administrator\\IdeaProjects\\eladmin\\eladmin-system\\src\\main\\resources\\string.json");

        inputStream = file4.toURI().toURL().openStream();
            mapper = new ObjectMapper();
            Map<String, String> STRING_MAP = Collections.unmodifiableMap(mapper.readValue(inputStream, new TypeReference<Map<String, String>>(){}));
        //遍历json数组
        for (int i = 0; i < jsonArray.size(); i++) {
            String lValue = PATCH_MAP.get(jsonArray.get(i).get("descstrpos"));

            if ( lValue == null )
            {
                lValue = EXP2_MAP.get(jsonArray.get(i).get("descstrpos"));
                if ( lValue == null )
                {
                    lValue = STRING_MAP.get(jsonArray.get(i).get("descstrpos"));
                }
            }
            jsonArray.get(i).putOnce("desnew",lValue);
        }
        System.out.println(jsonArray.toString());
    }*/


}




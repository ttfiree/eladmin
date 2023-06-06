package me.zhengjie.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class FightTest {


    private static  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
        // 初始化人物和怪物
        Character player = new Character("玩家", 100, 20, 15, 0.2, 0.2, 0.1);
        Monster monster = new Monster("怪物", 80, 18, 10, 0.1, 0.1, 0.05);

        // 打印人物和怪物的属性
        System.out.println("人物属性：" + player);
        System.out.println("怪物属性：" + monster);
// 进行战斗
        int round = 1;
        double playerProgress = 0; // 玩家攻击进度
        double monsterProgress = 0; // 怪物攻击进度
         double realDuration = 1; // 记录战斗的总时长
        LocalDateTime localDateTime = LocalDateTime.now();
        // 计算现实时间和攻击速度
        double playerSpeed = player.getSpeed() * realDuration;
        double monsterSpeed = monster.getSpeed() * realDuration;
        double totalDuration = 0;
        System.out.println("开始时间"+localDateTime.format(dateTimeFormatter));
        while (player.isAlive() && monster.isAlive()) {
            System.out.println("第" + round + "回合开始：");
            double usedTime = 0;
            // 同时进行角色和怪物的攻击
            boolean playerAttacked = false;
            boolean monsterAttacked = false;
            while (!playerAttacked && !monsterAttacked) {
                playerProgress += playerSpeed;
                monsterProgress += monsterSpeed;
                usedTime+=1;
                if (playerProgress >= 1) {
                    // 玩家攻击
                    int playerAttack = player.attack();
                    int monsterDefense = monster.defense();
                    int playerDamage = playerAttack - monsterDefense;
                    if (playerDamage > 0) {
                        boolean isCritical = player.isCritical(); // 判断是否暴击
                        if (isCritical) {
                            playerDamage *= 2; // 暴击伤害翻倍
                            System.out.println(String.format("%.2f秒后，第 "+ round +" 回合，玩家对怪物造成了%d点暴击伤害！", usedTime, playerDamage));
                        } else {
                            System.out.println(String.format("%.2f秒后，第 "+ round +" 回合，玩家对怪物造成了%d点伤害！", usedTime, playerDamage));
                        }
                        monster.takeDamage(playerDamage);
                    } else {
                        System.out.println(String.format("%.2f秒后，第 "+ round +" 回合，玩家的攻击被怪物格挡了！", usedTime));
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
                            System.out.println(String.format("%.2f秒后，第 "+ round +" 回合，怪物对玩家造成了%d点暴击伤害！", usedTime, monsterDamage));
                        } else {
                            System.out.println(String.format("%.2f秒后，第 "+ round +" 回合，怪物对玩家造成了%d点伤害！", usedTime, monsterDamage));
                        }
                        player.takeDamage(monsterDamage);
                    } else {
                        System.out.println(String.format("%.2f秒后，第 "+ round +" 回合，怪物的攻击被玩家格挡了！", usedTime));
                    }
                    monsterProgress -= 1;
                    monsterAttacked = true;
                }
            }

            // 打印人物和怪物的属性
            System.out.println("人物属性：" + player);
            System.out.println("怪物属性：" + monster);
            totalDuration+=usedTime;
            round++;
        }

// 计算总的战斗时间并打印

        System.out.printf("战斗结束，！" + localDateTime.plusSeconds((long)totalDuration).format(dateTimeFormatter) + "%n");
// 打印战斗结果
        if (player.isAlive()) {
            System.out.println("战斗结束，玩家胜利！");
        } else {
            System.out.println("战斗结束，怪物胜利！");
        }
    }
}

class Character {
    private String name;
    private int maxHp;
    private int hp;
    private int attack;
    private int defense;
    private double speed;
    private double criticalRate; // 暴击率
    private double criticalDamage; // 暴击伤害倍数

    public Character(String name, int maxHp, int attack, int defense, double speed, double criticalRate, double criticalDamage) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.criticalRate = criticalRate;
        this.criticalDamage = criticalDamage;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int attack() {
        Random random = new Random();
        if (random.nextDouble() < criticalRate) { // 判断是否暴击
            return (int) (attack * (1 + criticalDamage));
        } else {
            return attack;
        }
    }

    public int defense() {
        return defense;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isCritical() {
        Random random = new Random();
        return random.nextDouble() < criticalRate;
    }

    @Override
    public String toString() {
        return String.format("%s：生命值%d/%d，攻击力%d，防御力%d，速度%.2f，暴击率%.2f，暴击伤害%.2f",
                name, hp, maxHp, attack, defense, speed, criticalRate, criticalDamage);
    }
}

class Monster extends Character {
    public Monster(String name, int maxHp, int attack, int defense, double speed, double criticalRate, double criticalDamage) {
        super(name, maxHp, attack, defense, speed, criticalRate, criticalDamage);
    }
}

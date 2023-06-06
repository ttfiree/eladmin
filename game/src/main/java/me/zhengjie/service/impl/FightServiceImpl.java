package me.zhengjie.service.impl;

import java.util.Random;

public class FightServiceImpl {

    /*public void fight(){
        // 初始化人物和怪物
        Character player = new Character("玩家", 100, 20, 15, 0.2, 0.2);
        Monster monster = new Monster("怪物", 80, 18, 10, 0.1, 0.1);

        // 打印人物和怪物的属性
        System.out.println("人物属性：" + player);
        System.out.println("怪物属性：" + monster);

        // 进行战斗
        int round = 1;
        long startTime = System.currentTimeMillis(); // 记录开始时间
        while (player.isAlive() && monster.isAlive()) {
            System.out.println("第" + round + "回合开始：");

            // 计算现实时间和攻击速度
            long currentTime = System.currentTimeMillis();
            double timeElapsed = (double) (currentTime - startTime) / 1000;
            double playerSpeed = player.getSpeed() * timeElapsed;
            double monsterSpeed = monster.getSpeed() * timeElapsed;
            double progress = 0; // 进度
            while (progress < 1) {
                if (playerSpeed >= monsterSpeed) {
                    progress += monsterSpeed;
                    if (progress >= 1) {
                        // 玩家攻击
                        int playerAttack = player.attack();
                        int monsterDefense = monster.defense();
                        int playerDamage = playerAttack - monsterDefense;
                        if (playerDamage > 0) {
                            long attackTime = System.currentTimeMillis();
                            System.out.printf("%.2f秒后，玩家对怪物造成了%d点伤害！\n", timeElapsed, playerDamage);
                            monster.takeDamage(playerDamage);
                            timeElapsed += (double) (System.currentTimeMillis() - attackTime) / 1000;
                        } else {
                            System.out.printf("%.2f秒后，玩家的攻击被怪物格挡了！\n", timeElapsed);
                        }
                    }
                } else {
                    progress += playerSpeed;
                    if (progress >= 1) {
                        // 怪物攻击
                        int monsterAttack = monster.attack();
                        int playerDefense = player.defense();
                        int monsterDamage = monsterAttack - playerDefense;
                        if (monsterDamage > 0) {
                            long attackTime = System.currentTimeMillis();
                            System.out.printf("%.2f秒后，怪物对玩家造成了%d点伤害！\n", timeElapsed, monsterDamage);
                            player.takeDamage(monsterDamage);
                            timeElapsed += (double) (System.currentTimeMillis() - attackTime) / 1000;
                        } else {
                            System.out.printf("%.2f秒后，怪物的攻击被玩家格挡了！\n", timeElapsed);
                        }
                    }
                }
            }

            // 打印战斗过程中的日志
            System.out.println("人物属性：" + player);
            System.out.println("怪物属性：" + monster);

            round++;
        }
        long endTime = System.currentTimeMillis(); // 记录结束时间
        double timeElapsed = (double) (endTime - startTime) / 1000; // 计算战斗时间
        System.out.printf("战斗结束，用时%.2f秒\n", timeElapsed);

        // 打印战斗结果
        if (player.isAlive()) {
            System.out.println("战斗结束，玩家胜利！");
        } else {
            System.out.println("战斗结束，怪物胜利！");
        }
    }*/
    class Character {
        private String name; // 名称
        private int hp; // 生命值
        private int attack; // 攻击力
        private int defense; // 防御力
        private double speed; // 攻击速度
        private double crit; // 暴击率
        private double progress; // 进度

        public Character(String name, int hp, int attack, int defense, double speed, double crit) {
            this.name = name;
            this.hp = hp;
            this.attack = attack;
            this.defense = defense;
            this.speed = speed;
            this.crit = crit;
            this.progress = 0;
        }

        // 攻击
        public int attack() {
            Random random = new Random();
            int damage = (int) (this.attack * (1 + random.nextDouble()) * (1 + this.crit));
            return damage;
        }

        // 受到伤害
        public void takeDamage(int damage) {
            int actualDamage = Math.max(damage - this.defense, 0);
            this.hp -= actualDamage;
            if (this.hp < 0) {
                this.hp = 0;
            }
            System.out.printf("%s受到了%d点伤害，剩余生命值%d\n", this.name, actualDamage, this.hp);
        }

        // 是否存活
        public boolean isAlive() {
            return this.hp > 0;
        }

        // toString方法
        @Override
        public String toString() {
            return String.format("%s（生命值：%d，攻击力：%d，防御力：%d，攻击速度：%.2f，暴击率：%.2f）",
                    this.name, this.hp, this.attack, this.defense, this.speed, this.crit);
        }

        // getter和setter方法
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHp() {
            return hp;
        }

        public void setHp(int hp) {
            this.hp = hp;
        }

        public int getAttack() {
            return attack;
        }

        public void setAttack(int attack) {
            this.attack = attack;
        }

        public int getDefense() {
            return defense;
        }

        public void setDefense(int defense) {
            this.defense = defense;
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public double getCrit() {
            return crit;
        }

        public void setCrit(double crit) {
            this.crit = crit;
        }

        public double getProgress() {
            return progress;
        }

        public void setProgress(double progress) {
            this.progress = progress;
        }
    }

    class Monster extends Character {

        public Monster(String name, int hp, int attack, int defense, double speed, double crit) {
            super(name, hp, attack, defense, speed, crit);
        }

        // 攻击
        @Override
        public int attack() {
            Random random = new Random();
            int damage = (int) (this.getAttack() * (1 + random.nextDouble()));
            return damage;
        }
    }
}

package me.zhengjie.service;

import me.zhengjie.domain.GamePlayer;
import me.zhengjie.domain.GameResult;
import me.zhengjie.service.dto.GameAttributeDto;
import me.zhengjie.service.dto.GameCharacterDto;
import me.zhengjie.service.dto.GameMonsterDto;

public interface FightService {


    public void fight(GamePlayer player, GameMonsterDto monster, GameResult gameResult);

    public GamePlayer buildPlayer(GameCharacterDto character);

    public GameMonsterDto buildMonster(GameCharacterDto character);
}

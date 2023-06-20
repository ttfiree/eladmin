package me.zhengjie.service;

import me.zhengjie.domain.GamePlayer;
import me.zhengjie.domain.GameResult;
import me.zhengjie.service.dto.GameMonsterDto;

public interface ExpService {

    void dealExpAfterBattle (GamePlayer player, GameMonsterDto monster, GameResult gameResult);
}

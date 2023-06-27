package me.zhengjie.service;

import me.zhengjie.domain.GamePlayer;
import me.zhengjie.domain.GameResult;
import me.zhengjie.service.dto.GameArmorsDto;

public interface GameItemService {

    GameArmorsDto createItem(GamePlayer player);
}

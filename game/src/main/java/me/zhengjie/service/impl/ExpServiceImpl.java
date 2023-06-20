package me.zhengjie.service.impl;

import cn.hutool.core.bean.BeanUtil;
import me.zhengjie.content.DataMap;
import me.zhengjie.domain.GameCharacter;
import me.zhengjie.domain.GamePlayer;
import me.zhengjie.domain.GameResult;
import me.zhengjie.service.ExpService;
import me.zhengjie.service.GameCharacterService;
import me.zhengjie.service.dto.GameMonsterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpServiceImpl implements ExpService {
    @Autowired
    private GameCharacterService gameCharacterService;
    @Override
    public void dealExpAfterBattle(GamePlayer player, GameMonsterDto monster, GameResult gameResult) {

        long exp = monster.getExperience();
        gameResult.setExp(exp);
        long expNow = player.getExperience();
        long needExp = DataMap.EXP_MAP.get(player.getLevel());
        if(exp+expNow>=needExp){
           int l =  player.getLevel();
           l++;
            player.setExperience(exp+expNow-needExp);
            player.setLevel(l);
            gameResult.setLevelUp(1);
        }else {
            expNow+=exp;
            player.setExperience(expNow);
            gameResult.setLevelUp(0);
        }
        GameCharacter character = new GameCharacter();
        BeanUtil.copyProperties(player,character);
        gameCharacterService.update(character);

    }
}

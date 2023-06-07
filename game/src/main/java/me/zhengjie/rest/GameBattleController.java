package me.zhengjie.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.AnonymousAccess;
import me.zhengjie.annotation.Log;
import me.zhengjie.domain.GameCharacter;
import me.zhengjie.domain.GamePlayer;
import me.zhengjie.domain.GameResult;
import me.zhengjie.service.FightService;
import me.zhengjie.service.GameAttributeService;
import me.zhengjie.service.dto.GameAttributeQueryCriteria;
import me.zhengjie.service.dto.GameMonsterDto;
import me.zhengjie.service.impl.FightServiceImpl;
import me.zhengjie.service.mapstruct.GameCharacterMapper;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Api(tags = "战斗管理")
@RequestMapping("/api/gameBattle")
public class GameBattleController {
    @Autowired
    private final FightService fightService;
    @Autowired
    private final GameCharacterMapper gameCharacterMapper;

    @Log("战斗")
    @ApiOperation("战斗")
    @GetMapping(value = "/battle")
    @AnonymousAccess
    public GameResult exportGameAttribute(@Validated @RequestBody GameCharacter resources) throws IOException {
        Long userId = SecurityUtils.getCurrentUserId();
        GamePlayer player = fightService.buildPlayer(gameCharacterMapper.toDto(resources));
        GameMonsterDto monsterDto = fightService.buildMonster(gameCharacterMapper.toDto(resources));
        GameResult gameResult = new GameResult();
        fightService.fight(player,monsterDto,gameResult);
        return gameResult;
    }
}

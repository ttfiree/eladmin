package me.zhengjie.rest;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.AnonymousAccess;
import me.zhengjie.annotation.Log;
import me.zhengjie.content.DataMap;
import me.zhengjie.domain.GameCharacter;
import me.zhengjie.domain.GamePlayer;
import me.zhengjie.domain.GameResult;
import me.zhengjie.domain.Result;
import me.zhengjie.service.*;
import me.zhengjie.service.dto.*;
import me.zhengjie.service.impl.FightServiceImpl;
import me.zhengjie.service.mapstruct.GameCharacterMapper;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@RequiredArgsConstructor
@Api(tags = "战斗管理")
@RequestMapping("/api/gameBattle")
public class GameBattleController {
    @Autowired
    private final FightService fightService;
    @Autowired
    private final GameCharacterMapper gameCharacterMapper;
    @Autowired
    private GameCharacterService gameCharacterService;
    @Autowired
    private GameItemService gameItemService;


    @Log("战斗")
    @ApiOperation("战斗")
    @PostMapping(value = "/battle")
    @AnonymousAccess
    public GameResult exportGameAttribute(@Validated @RequestBody GameCharacter resources) throws IOException {
        Long userId = SecurityUtils.getCurrentUserId();
        GameCharacterQueryCriteria gameCharacterQueryCriteria = new GameCharacterQueryCriteria();
        gameCharacterQueryCriteria.setUserId(userId);
        GameCharacterDto character = gameCharacterService.findOne(gameCharacterQueryCriteria);
        GamePlayer player = fightService.buildPlayer(character);
        GameMonsterDto monsterDto = fightService.buildMonster(character);
        GameResult gameResult = new GameResult();
        fightService.fight(player,monsterDto,gameResult);
        return gameResult;
    }
    @Log("掉落")
    @ApiOperation("掉落")
    @PostMapping(value = "/drop")
    @AnonymousAccess
    public GameArmorsDto drop() throws IOException {
        Long userId = SecurityUtils.getCurrentUserId();
        GameCharacterQueryCriteria gameCharacterQueryCriteria = new GameCharacterQueryCriteria();
        gameCharacterQueryCriteria.setUserId(userId);
        GameCharacterDto character = gameCharacterService.findOne(gameCharacterQueryCriteria);
        GamePlayer player = fightService.buildPlayer(character);
        return gameItemService.createItem(player);
    }
    @Log("地图")
    @ApiOperation("地图")
    @PostMapping(value = "/map")
    @AnonymousAccess
    public Result getMap()  {
        Result result = new Result();
        Map<String,Integer>  map = DataMap.LEVEL_MAP;
        //将map key value 互换，同时将双引号转义
        Map<Integer,String>  map2 = new HashMap<>();

        map.forEach((key, value) ->
                map2.put(value, key));
        result.setCode(200);
        //将map2转为一个list
        List<Map<String,Object>> list = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : map2.entrySet()) {
            Map<String,Object> map3 = new HashMap<>();
            map3.put("name",entry.getValue());
            map3.put("level",entry.getKey());
            list.add(map3);
        }
        result.setData(list);
        return result;
    }

}

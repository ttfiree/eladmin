package me.zhengjie.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.zhengjie.content.DataMap;
import me.zhengjie.domain.GameCharacter;
import me.zhengjie.domain.GamePlayer;
import me.zhengjie.domain.GameResult;
import me.zhengjie.service.ExpService;
import me.zhengjie.service.GameCharacterService;
import me.zhengjie.service.dto.GameMonsterDto;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        Long userId = SecurityUtils.getCurrentUserId();
        character.setId(Math.toIntExact(userId));
        gameCharacterService.update(character);

    }

    public static void main(String[] args) {
        File file = new File("E:\\game\\java\\eladmin-system\\src\\main\\resources\\string.json");
        //file是一个json文件，将其转为一个map
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> nMap = new HashMap<>();
            Map<String, String> map = mapper.readValue(file, new TypeReference<Map<String, String>>() {
            });
            File file2 = new File("F:\\下载\\D2R 1.0本地化文本\\strings\\item-modifiers.json");
            //file2是一个json文件，将其转为一个JsonObject对象的List
            mapper = new ObjectMapper();
            List<JSONObject> list = mapper.readValue(file2, new TypeReference<List<JSONObject>>() {
            });
            Map<String, String> map1 = list.stream().collect(Collectors.toMap(item -> item.get("Key").toString(), item -> item.get("zhCN").toString()));
            //遍历list
            map.keySet().forEach(o -> {
                if (map1.containsKey(o)) {
                    //如果map1.get("zhCN")是null，替换为" "
                    if (map1.get(o) == null) {
                        map1.put(o, " ");
                    }
                    nMap.put(o, map1.get(o));
                } else {
                    nMap.put(o, map.get(o));
                }
            });
            //将map转为json字符串
            String json = mapper.writeValueAsString(nMap);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

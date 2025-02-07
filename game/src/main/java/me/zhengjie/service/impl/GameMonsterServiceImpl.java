/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.service.impl;

import me.zhengjie.domain.GameMonster;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.repository.GameMonsterRepository;
import me.zhengjie.service.GameMonsterService;
import me.zhengjie.service.dto.GameMonsterDto;
import me.zhengjie.service.dto.GameMonsterQueryCriteria;
import me.zhengjie.service.mapstruct.GameMonsterMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://eladmin.vip
* @description 服务实现
* @author lyc
* @date 2023-06-20
**/
@Service
@RequiredArgsConstructor
public class GameMonsterServiceImpl implements GameMonsterService {

    private final GameMonsterRepository gameMonsterRepository;
    private final GameMonsterMapper gameMonsterMapper;

    @Override
    public Map<String,Object> queryAll(GameMonsterQueryCriteria criteria, Pageable pageable){
        Page<GameMonster> page = gameMonsterRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameMonsterMapper::toDto));
    }

    @Override
    public List<GameMonsterDto> queryAll(GameMonsterQueryCriteria criteria){
        return gameMonsterMapper.toDto(gameMonsterRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public GameMonsterDto findById(Integer id) {
        GameMonster gameMonster = gameMonsterRepository.findById(id).orElseGet(GameMonster::new);
        ValidationUtil.isNull(gameMonster.getId(),"GameMonster","id",id);
        return gameMonsterMapper.toDto(gameMonster);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameMonsterDto create(GameMonster resources) {
        return gameMonsterMapper.toDto(gameMonsterRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GameMonster resources) {
        GameMonster gameMonster = gameMonsterRepository.findById(resources.getId()).orElseGet(GameMonster::new);
        ValidationUtil.isNull( gameMonster.getId(),"GameMonster","id",resources.getId());
        gameMonster.copy(resources);
        gameMonsterRepository.save(gameMonster);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            gameMonsterRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<GameMonsterDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameMonsterDto gameMonster : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("迷宫ID", gameMonster.getMazeId());
            map.put(" createTime",  gameMonster.getCreateTime());
            map.put(" isDelete",  gameMonster.getIsDelete());
            map.put(" extStringOne",  gameMonster.getExtStringOne());
            map.put(" extStringTwo",  gameMonster.getExtStringTwo());
            map.put(" extStringThree",  gameMonster.getExtStringThree());
            map.put(" extStringFour",  gameMonster.getExtStringFour());
            map.put(" extStringFive",  gameMonster.getExtStringFive());
            map.put(" extDecimalOne",  gameMonster.getExtDecimalOne());
            map.put(" extDecimalTwo",  gameMonster.getExtDecimalTwo());
            map.put(" extDecimalThree",  gameMonster.getExtDecimalThree());
            map.put(" extDecimalFour",  gameMonster.getExtDecimalFour());
            map.put(" extDecimalFive",  gameMonster.getExtDecimalFive());
            map.put("怪物名", gameMonster.getName());
            map.put("经验值", gameMonster.getExperience());
            map.put("金钱", gameMonster.getMoney());
            map.put("爆率", gameMonster.getItem());
            map.put("爆率等级", gameMonster.getItemRate());
            map.put("生命值", gameMonster.getHitPoints());
            map.put("速度", gameMonster.getSpeed());
            map.put("暴击率", gameMonster.getCriticalChance());
            map.put("护甲", gameMonster.getArmorClass());
            map.put("伤害", gameMonster.getDamage());
            map.put("暴击伤害", gameMonster.getCriticalDamage());
            map.put("等级", gameMonster.getLevel());
            map.put("是否是boss级别", gameMonster.getIsBoss());
            map.put("类型", gameMonster.getType());
            map.put("最小生命值", gameMonster.getMinHp());
            map.put("准确率", gameMonster.getAc());
            map.put("最大伤害", gameMonster.getMaxDamage());
            map.put("最小伤害", gameMonster.getMinDamage());
            map.put("最大生命值", gameMonster.getMaxHp());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
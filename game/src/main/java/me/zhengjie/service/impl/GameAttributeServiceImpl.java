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

import me.zhengjie.domain.GameAttribute;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.repository.GameAttributeRepository;
import me.zhengjie.service.GameAttributeService;
import me.zhengjie.service.dto.GameAttributeDto;
import me.zhengjie.service.dto.GameAttributeQueryCriteria;
import me.zhengjie.service.mapstruct.GameAttributeMapper;
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
* @date 2023-06-12
**/
@Service
@RequiredArgsConstructor
public class GameAttributeServiceImpl implements GameAttributeService {

    private final GameAttributeRepository gameAttributeRepository;
    private final GameAttributeMapper gameAttributeMapper;

    @Override
    public Map<String,Object> queryAll(GameAttributeQueryCriteria criteria, Pageable pageable){
        Page<GameAttribute> page = gameAttributeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameAttributeMapper::toDto));
    }

    @Override
    public List<GameAttributeDto> queryAll(GameAttributeQueryCriteria criteria){
        return gameAttributeMapper.toDto(gameAttributeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public GameAttributeDto findById(Integer id) {
        GameAttribute gameAttribute = gameAttributeRepository.findById(id).orElseGet(GameAttribute::new);
        ValidationUtil.isNull(gameAttribute.getId(),"GameAttribute","id",id);
        return gameAttributeMapper.toDto(gameAttribute);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameAttributeDto create(GameAttribute resources) {
        return gameAttributeMapper.toDto(gameAttributeRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GameAttribute resources) {
        GameAttribute gameAttribute = gameAttributeRepository.findById(resources.getId()).orElseGet(GameAttribute::new);
        ValidationUtil.isNull( gameAttribute.getId(),"GameAttribute","id",resources.getId());
        gameAttribute.copy(resources);
        gameAttributeRepository.save(gameAttribute);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            gameAttributeRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<GameAttributeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameAttributeDto gameAttribute : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("力量", gameAttribute.getStrength());
            map.put("敏捷", gameAttribute.getDexterity());
            map.put("体质", gameAttribute.getConstitution());
            map.put("智力", gameAttribute.getIntelligence());
            map.put("感知", gameAttribute.getWisdom());
            map.put("魅力", gameAttribute.getCharisma());
            map.put("基础伤害", gameAttribute.getDamage());
            map.put("攻速", gameAttribute.getAttactSpeed());
            map.put("暴击", gameAttribute.getCriticalChance());
            map.put("爆伤", gameAttribute.getCriticalDamage());
            map.put(" extStringOne",  gameAttribute.getExtStringOne());
            map.put(" extStringTwo",  gameAttribute.getExtStringTwo());
            map.put(" extStringThree",  gameAttribute.getExtStringThree());
            map.put(" extStringFour",  gameAttribute.getExtStringFour());
            map.put(" extStringFive",  gameAttribute.getExtStringFive());
            map.put(" extDecimalOne",  gameAttribute.getExtDecimalOne());
            map.put(" extDecimalTwo",  gameAttribute.getExtDecimalTwo());
            map.put(" extDecimalThree",  gameAttribute.getExtDecimalThree());
            map.put(" extDecimalFour",  gameAttribute.getExtDecimalFour());
            map.put(" extDecimalFive",  gameAttribute.getExtDecimalFive());
            map.put("生命值", gameAttribute.getHitPoints());
            map.put("护甲值", gameAttribute.getArmorClass());
            map.put("迷宫id", gameAttribute.getMazeId());
            map.put("金钱加成", gameAttribute.getMoneyPlus());
            map.put("经验加成", gameAttribute.getExpPlus());
            map.put("掉率加成", gameAttribute.getItemPlus());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}

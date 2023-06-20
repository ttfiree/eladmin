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

import me.zhengjie.domain.GameCharacter;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.repository.GameCharacterRepository;
import me.zhengjie.service.GameCharacterService;
import me.zhengjie.service.dto.GameCharacterDto;
import me.zhengjie.service.dto.GameCharacterQueryCriteria;
import me.zhengjie.service.mapstruct.GameCharacterMapper;
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
public class GameCharacterServiceImpl implements GameCharacterService {

    private final GameCharacterRepository gameCharacterRepository;
    private final GameCharacterMapper gameCharacterMapper;

    @Override
    public Map<String,Object> queryAll(GameCharacterQueryCriteria criteria, Pageable pageable){
        Page<GameCharacter> page = gameCharacterRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameCharacterMapper::toDto));
    }

    @Override
    public List<GameCharacterDto> queryAll(GameCharacterQueryCriteria criteria){
        return gameCharacterMapper.toDto(gameCharacterRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public GameCharacterDto findById(Integer id) {
        GameCharacter gameCharacter = gameCharacterRepository.findById(id).orElseGet(GameCharacter::new);
        ValidationUtil.isNull(gameCharacter.getId(),"GameCharacter","id",id);
        return gameCharacterMapper.toDto(gameCharacter);
    }
    @Override
    @Transactional
    public GameCharacterDto findOne(GameCharacterQueryCriteria criteria) {
        return gameCharacterMapper.toDto(gameCharacterRepository.findOne((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)).get());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameCharacterDto create(GameCharacter resources) {
        return gameCharacterMapper.toDto(gameCharacterRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GameCharacter resources) {
        GameCharacter gameCharacter = gameCharacterRepository.findById(resources.getId()).orElseGet(GameCharacter::new);
        ValidationUtil.isNull( gameCharacter.getId(),"GameCharacter","id",resources.getId());
        gameCharacter.copy(resources);
        gameCharacterRepository.save(gameCharacter);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            gameCharacterRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<GameCharacterDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameCharacterDto gameCharacter : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户ID", gameCharacter.getUserId());
            map.put(" createTime",  gameCharacter.getCreateTime());
            map.put(" isDelete",  gameCharacter.getIsDelete());
            map.put(" extStringOne",  gameCharacter.getExtStringOne());
            map.put(" extStringTwo",  gameCharacter.getExtStringTwo());
            map.put(" extStringThree",  gameCharacter.getExtStringThree());
            map.put(" extStringFour",  gameCharacter.getExtStringFour());
            map.put(" extStringFive",  gameCharacter.getExtStringFive());
            map.put(" extDecimalOne",  gameCharacter.getExtDecimalOne());
            map.put(" extDecimalTwo",  gameCharacter.getExtDecimalTwo());
            map.put(" extDecimalThree",  gameCharacter.getExtDecimalThree());
            map.put(" extDecimalFour",  gameCharacter.getExtDecimalFour());
            map.put(" extDecimalFive",  gameCharacter.getExtDecimalFive());
            map.put("角色名", gameCharacter.getName());
            map.put("等级", gameCharacter.getLevel());
            map.put("经验值", gameCharacter.getExperience());
            map.put("金钱", gameCharacter.getMoney());
            map.put("生命值", gameCharacter.getHitPoints());
            map.put("护甲值", gameCharacter.getArmorClass());
            map.put("移动速度", gameCharacter.getSpeed());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}

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

import me.zhengjie.domain.GameAffixCreated;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.repository.GameAffixCreatedRepository;
import me.zhengjie.service.GameAffixCreatedService;
import me.zhengjie.service.dto.GameAffixCreatedDto;
import me.zhengjie.service.dto.GameAffixCreatedQueryCriteria;
import me.zhengjie.service.mapstruct.GameAffixCreatedMapper;
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
* @date 2023-06-22
**/
@Service
@RequiredArgsConstructor
public class GameAffixCreatedServiceImpl implements GameAffixCreatedService {

    private final GameAffixCreatedRepository gameAffixCreatedRepository;
    private final GameAffixCreatedMapper gameAffixCreatedMapper;

    @Override
    public Map<String,Object> queryAll(GameAffixCreatedQueryCriteria criteria, Pageable pageable){
        Page<GameAffixCreated> page = gameAffixCreatedRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameAffixCreatedMapper::toDto));
    }

    @Override
    public List<GameAffixCreatedDto> queryAll(GameAffixCreatedQueryCriteria criteria){
        return gameAffixCreatedMapper.toDto(gameAffixCreatedRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public GameAffixCreatedDto findById(Integer id) {
        GameAffixCreated gameAffixCreated = gameAffixCreatedRepository.findById(id).orElseGet(GameAffixCreated::new);
        ValidationUtil.isNull(gameAffixCreated.getId(),"GameAffixCreated","id",id);
        return gameAffixCreatedMapper.toDto(gameAffixCreated);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameAffixCreatedDto create(GameAffixCreated resources) {
        return gameAffixCreatedMapper.toDto(gameAffixCreatedRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GameAffixCreated resources) {
        GameAffixCreated gameAffixCreated = gameAffixCreatedRepository.findById(resources.getId()).orElseGet(GameAffixCreated::new);
        ValidationUtil.isNull( gameAffixCreated.getId(),"GameAffixCreated","id",resources.getId());
        gameAffixCreated.copy(resources);
        gameAffixCreatedRepository.save(gameAffixCreated);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            gameAffixCreatedRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<GameAffixCreatedDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameAffixCreatedDto gameAffixCreated : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("角色id", gameAffixCreated.getCharId());
            map.put("装备id", gameAffixCreated.getArmId());
            map.put("武器id", gameAffixCreated.getWeaponId());
            map.put("是否装备", gameAffixCreated.getIsEquip());
            map.put("属性名称", gameAffixCreated.getName());
            map.put("版本", gameAffixCreated.getVersion());
            map.put("是否可生成", gameAffixCreated.getSpawnable());
            map.put("是否稀有属性", gameAffixCreated.getRare());
            map.put("最小等级", gameAffixCreated.getLevel());
            map.put("最大等级", gameAffixCreated.getMaxlevel());
            map.put("装备等级要求", gameAffixCreated.getLevelreq());
            map.put("是否职业专属", gameAffixCreated.getClassspecific());
            map.put("职业名称", gameAffixCreated.getClass());
            map.put("职业等级要求", gameAffixCreated.getClasslevelreq());
            map.put("生成频率", gameAffixCreated.getFrequency());
            map.put("属性分组", gameAffixCreated.getGroups());
            map.put("属性1代码", gameAffixCreated.getMod1code());
            map.put("属性1参数", gameAffixCreated.getMod1param());
            map.put("属性1最小值", gameAffixCreated.getMod1min());
            map.put("属性1最大值", gameAffixCreated.getMod1max());
            map.put("属性2代码", gameAffixCreated.getMod2code());
            map.put("属性2参数", gameAffixCreated.getMod2param());
            map.put("属性2最小值", gameAffixCreated.getMod2min());
            map.put("属性2最大值", gameAffixCreated.getMod2max());
            map.put("属性3代码", gameAffixCreated.getMod3code());
            map.put("属性3参数", gameAffixCreated.getMod3param());
            map.put("属性3最小值", gameAffixCreated.getMod3min());
            map.put("属性3最大值", gameAffixCreated.getMod3max());
            map.put("属性转化", gameAffixCreated.getTransform());
            map.put("属性转化颜色", gameAffixCreated.getTransformcolor());
            map.put("物品类型1", gameAffixCreated.getItype1());
            map.put("物品类型2", gameAffixCreated.getItype2());
            map.put("物品类型3", gameAffixCreated.getItype3());
            map.put("物品类型4", gameAffixCreated.getItype4());
            map.put("物品类型5", gameAffixCreated.getItype5());
            map.put("物品类型6", gameAffixCreated.getItype6());
            map.put("物品类型7", gameAffixCreated.getItype7());
            map.put("装备类型1", gameAffixCreated.getEtype1());
            map.put("装备类型2", gameAffixCreated.getEtype2());
            map.put("装备类型3", gameAffixCreated.getEtype3());
            map.put("装备类型4", gameAffixCreated.getEtype4());
            map.put("装备类型5", gameAffixCreated.getEtype5());
            map.put("属性值除数", gameAffixCreated.getDivide());
            map.put("属性值乘数", gameAffixCreated.getMultiply());
            map.put("属性值增量", gameAffixCreated.getAddAffix());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
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

import me.zhengjie.domain.GameAffix;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.repository.GameAffixRepository;
import me.zhengjie.service.GameAffixService;
import me.zhengjie.service.dto.GameAffixDto;
import me.zhengjie.service.dto.GameAffixQueryCriteria;
import me.zhengjie.service.mapstruct.GameAffixMapper;
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
public class GameAffixServiceImpl implements GameAffixService {

    private final GameAffixRepository gameAffixRepository;
    private final GameAffixMapper gameAffixMapper;

    @Override
    public Map<String,Object> queryAll(GameAffixQueryCriteria criteria, Pageable pageable){
        Page<GameAffix> page = gameAffixRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameAffixMapper::toDto));
    }

    @Override
    public List<GameAffixDto> queryAll(GameAffixQueryCriteria criteria){
        return gameAffixMapper.toDto(gameAffixRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public GameAffixDto findById(Integer id) {
        GameAffix gameAffix = gameAffixRepository.findById(id).orElseGet(GameAffix::new);
        ValidationUtil.isNull(gameAffix.getId(),"GameAffix","id",id);
        return gameAffixMapper.toDto(gameAffix);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameAffixDto create(GameAffix resources) {
        return gameAffixMapper.toDto(gameAffixRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GameAffix resources) {
        GameAffix gameAffix = gameAffixRepository.findById(resources.getId()).orElseGet(GameAffix::new);
        ValidationUtil.isNull( gameAffix.getId(),"GameAffix","id",resources.getId());
        gameAffix.copy(resources);
        gameAffixRepository.save(gameAffix);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            gameAffixRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<GameAffixDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameAffixDto gameAffix : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("属性名称", gameAffix.getName());
            map.put("版本", gameAffix.getVersion());
            map.put("是否可生成", gameAffix.getSpawnable());
            map.put("是否稀有属性", gameAffix.getRare());
            map.put("最小等级", gameAffix.getLevel());
            map.put("最大等级", gameAffix.getMaxlevel());
            map.put("装备等级要求", gameAffix.getLevelreq());
            map.put("是否职业专属", gameAffix.getClassspecific());
            map.put("职业名称", gameAffix.getClass());
            map.put("职业等级要求", gameAffix.getClasslevelreq());
            map.put("生成频率", gameAffix.getFrequency());
            map.put("属性分组", gameAffix.getGroups());
            map.put("属性1代码", gameAffix.getMod1code());
            map.put("属性1参数", gameAffix.getMod1param());
            map.put("属性1最小值", gameAffix.getMod1min());
            map.put("属性1最大值", gameAffix.getMod1max());
            map.put("属性2代码", gameAffix.getMod2code());
            map.put("属性2参数", gameAffix.getMod2param());
            map.put("属性2最小值", gameAffix.getMod2min());
            map.put("属性2最大值", gameAffix.getMod2max());
            map.put("属性3代码", gameAffix.getMod3code());
            map.put("属性3参数", gameAffix.getMod3param());
            map.put("属性3最小值", gameAffix.getMod3min());
            map.put("属性3最大值", gameAffix.getMod3max());
            map.put("属性转化", gameAffix.getTransform());
            map.put("属性转化颜色", gameAffix.getTransformcolor());
            map.put("物品类型1", gameAffix.getItype1());
            map.put("物品类型2", gameAffix.getItype2());
            map.put("物品类型3", gameAffix.getItype3());
            map.put("物品类型4", gameAffix.getItype4());
            map.put("物品类型5", gameAffix.getItype5());
            map.put("物品类型6", gameAffix.getItype6());
            map.put("物品类型7", gameAffix.getItype7());
            map.put("装备类型1", gameAffix.getEtype1());
            map.put("装备类型2", gameAffix.getEtype2());
            map.put("装备类型3", gameAffix.getEtype3());
            map.put("装备类型4", gameAffix.getEtype4());
            map.put("装备类型5", gameAffix.getEtype5());
            map.put("属性值除数", gameAffix.getDivide());
            map.put("属性值乘数", gameAffix.getMultiply());
            map.put("属性值增量", gameAffix.getAddAffix());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
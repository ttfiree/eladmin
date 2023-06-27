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

import me.zhengjie.domain.GameEquipmentDetail;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.repository.GameEquipmentDetailRepository;
import me.zhengjie.service.GameEquipmentDetailService;
import me.zhengjie.service.dto.GameEquipmentDetailDto;
import me.zhengjie.service.dto.GameEquipmentDetailQueryCriteria;
import me.zhengjie.service.mapstruct.GameEquipmentDetailMapper;
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
* @date 2023-06-27
**/
@Service
@RequiredArgsConstructor
public class GameEquipmentDetailServiceImpl implements GameEquipmentDetailService {

    private final GameEquipmentDetailRepository gameEquipmentDetailRepository;
    private final GameEquipmentDetailMapper gameEquipmentDetailMapper;

    @Override
    public Map<String,Object> queryAll(GameEquipmentDetailQueryCriteria criteria, Pageable pageable){
        Page<GameEquipmentDetail> page = gameEquipmentDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameEquipmentDetailMapper::toDto));
    }

    @Override
    public List<GameEquipmentDetailDto> queryAll(GameEquipmentDetailQueryCriteria criteria){
        return gameEquipmentDetailMapper.toDto(gameEquipmentDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public GameEquipmentDetailDto findById(Integer id) {
        GameEquipmentDetail gameEquipmentDetail = gameEquipmentDetailRepository.findById(id).orElseGet(GameEquipmentDetail::new);
        ValidationUtil.isNull(gameEquipmentDetail.getId(),"GameEquipmentDetail","id",id);
        return gameEquipmentDetailMapper.toDto(gameEquipmentDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameEquipmentDetailDto create(GameEquipmentDetail resources) {
        return gameEquipmentDetailMapper.toDto(gameEquipmentDetailRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GameEquipmentDetail resources) {
        GameEquipmentDetail gameEquipmentDetail = gameEquipmentDetailRepository.findById(resources.getId()).orElseGet(GameEquipmentDetail::new);
        ValidationUtil.isNull( gameEquipmentDetail.getId(),"GameEquipmentDetail","id",resources.getId());
        gameEquipmentDetail.copy(resources);
        gameEquipmentDetailRepository.save(gameEquipmentDetail);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            gameEquipmentDetailRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<GameEquipmentDetailDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameEquipmentDetailDto gameEquipmentDetail : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("装备ID 关联game_armors_created或者game_affix_created", gameEquipmentDetail.getItemId());
            map.put("角色id", gameEquipmentDetail.getCharId());
            map.put("类型", gameEquipmentDetail.getEquipType());
            map.put("装备代码", gameEquipmentDetail.getCode());
            map.put("防御值", gameEquipmentDetail.getAc());
            map.put("防御值加成", gameEquipmentDetail.getAcPercent());
            map.put("减少敌人魔法抵抗", gameEquipmentDetail.getRedMag());
            map.put("力量", gameEquipmentDetail.getStr());
            map.put("敏捷", gameEquipmentDetail.getDex());
            map.put("体力", gameEquipmentDetail.getVit());
            map.put("精力", gameEquipmentDetail.getEnr());
            map.put("魔法值", gameEquipmentDetail.getMana());
            map.put("魔法值加成", gameEquipmentDetail.getManaPercent());
            map.put("生命值", gameEquipmentDetail.getHp());
            map.put("生命值加成", gameEquipmentDetail.getHpPercent());
            map.put("攻击值", gameEquipmentDetail.getAtt());
            map.put("格挡率", gameEquipmentDetail.getBlock());
            map.put("最小伤害", gameEquipmentDetail.getDmgMin());
            map.put("最大伤害", gameEquipmentDetail.getDmgMax());
            map.put("伤害加成", gameEquipmentDetail.getDmgPercent());
            map.put("伤害转移为魔法值", gameEquipmentDetail.getDmgToMana());
            map.put("反伤", gameEquipmentDetail.getThorns());
            map.put("攻击速度1", gameEquipmentDetail.getSwing1());
            map.put("攻击速度2", gameEquipmentDetail.getSwing2());
            map.put("攻击速度3", gameEquipmentDetail.getSwing3());
            map.put("金币加成", gameEquipmentDetail.getGoldPercent());
            map.put("魔法装备加成", gameEquipmentDetail.getMagPercent());
            map.put("体力回复", gameEquipmentDetail.getRegenStam());
            map.put("魔法值回复", gameEquipmentDetail.getRegenMana());
            map.put("吸取魔法值", gameEquipmentDetail.getManasteal());
            map.put("吸取生命值", gameEquipmentDetail.getLifesteal());
            map.put("无视防御值", gameEquipmentDetail.getIgnoreAc());
            map.put("降低敌人防御值", gameEquipmentDetail.getReduceAc());
            map.put("禁止回复", gameEquipmentDetail.getNoheal());
            map.put("减慢敌人速度", gameEquipmentDetail.getHalfFreeze());
            map.put("攻击值加成", gameEquipmentDetail.getAttPercent());
            map.put("破甲", gameEquipmentDetail.getCrush());
            map.put("敌人流血", gameEquipmentDetail.getBloody());
            map.put("减慢敌人速度", gameEquipmentDetail.getSlow());
            map.put("吸取敌人体力", gameEquipmentDetail.getStamdrain());
            map.put("穿透敌人护甲", gameEquipmentDetail.getPierce());
            map.put("物品掉落率加成", gameEquipmentDetail.getItemPercent());
            map.put("所有属性加成", gameEquipmentDetail.getAllStats());
            map.put("获得经验值加成", gameEquipmentDetail.getAddXp());
            map.put("击杀敌人回复生命值", gameEquipmentDetail.getHealKill());
            map.put("伤害降低", gameEquipmentDetail.getDmgReduct());
            map.put(" finalAc",  gameEquipmentDetail.getFinalAc());
            map.put(" finalDmg",  gameEquipmentDetail.getFinalDmg());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
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

import me.zhengjie.domain.GameArmorsCreated;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.repository.GameArmorsCreatedRepository;
import me.zhengjie.service.GameArmorsCreatedService;
import me.zhengjie.service.dto.GameArmorsCreatedDto;
import me.zhengjie.service.dto.GameArmorsCreatedQueryCriteria;
import me.zhengjie.service.mapstruct.GameArmorsCreatedMapper;
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
public class GameArmorsCreatedServiceImpl implements GameArmorsCreatedService {

    private final GameArmorsCreatedRepository gameArmorsCreatedRepository;
    private final GameArmorsCreatedMapper gameArmorsCreatedMapper;

    @Override
    public Map<String,Object> queryAll(GameArmorsCreatedQueryCriteria criteria, Pageable pageable){
        Page<GameArmorsCreated> page = gameArmorsCreatedRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameArmorsCreatedMapper::toDto));
    }

    @Override
    public List<GameArmorsCreatedDto> queryAll(GameArmorsCreatedQueryCriteria criteria){
        return gameArmorsCreatedMapper.toDto(gameArmorsCreatedRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public GameArmorsCreatedDto findById(Integer id) {
        GameArmorsCreated gameArmorsCreated = gameArmorsCreatedRepository.findById(id).orElseGet(GameArmorsCreated::new);
        ValidationUtil.isNull(gameArmorsCreated.getId(),"GameArmorsCreated","id",id);
        return gameArmorsCreatedMapper.toDto(gameArmorsCreated);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameArmorsCreatedDto create(GameArmorsCreated resources) {
        return gameArmorsCreatedMapper.toDto(gameArmorsCreatedRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GameArmorsCreated resources) {
        GameArmorsCreated gameArmorsCreated = gameArmorsCreatedRepository.findById(resources.getId()).orElseGet(GameArmorsCreated::new);
        ValidationUtil.isNull( gameArmorsCreated.getId(),"GameArmorsCreated","id",resources.getId());
        gameArmorsCreated.copy(resources);
        gameArmorsCreatedRepository.save(gameArmorsCreated);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            gameArmorsCreatedRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<GameArmorsCreatedDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameArmorsCreatedDto gameArmorsCreated : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("ID", gameArmorsCreated.getItemId());
            map.put("所属角色", gameArmorsCreated.getCharId());
            map.put("是否已装备", gameArmorsCreated.getIsEquip());
            map.put("护甲名称", gameArmorsCreated.getName());
            map.put("版本", gameArmorsCreated.getVersion());
            map.put("紧凑保存", gameArmorsCreated.getCompactsave());
            map.put("稀有度", gameArmorsCreated.getRarity());
            map.put("防御", gameArmorsCreated.getMinac());
            map.put("可生成", gameArmorsCreated.getSpawnable());
            map.put("吸收", gameArmorsCreated.getAbsorbs());
            map.put("攻击速度", gameArmorsCreated.getSpeed());
            map.put("需求力量", gameArmorsCreated.getReqstr());
            map.put("格挡率", gameArmorsCreated.getBlock());
            map.put("耐久度", gameArmorsCreated.getDurability());
            map.put("无耐久度", gameArmorsCreated.getNodurability());
            map.put("等级", gameArmorsCreated.getLevel());
            map.put("等级需求", gameArmorsCreated.getLevelreq());
            map.put("价格", gameArmorsCreated.getCost());
            map.put("赌博价格", gameArmorsCreated.getGambleCost());
            map.put("代码", gameArmorsCreated.getCode());
            map.put("名称字符串", gameArmorsCreated.getNamestr());
            map.put("魔法等级", gameArmorsCreated.getMagicLvl());
            map.put("自动前缀", gameArmorsCreated.getAutoPrefix());
            map.put("法术偏移量", gameArmorsCreated.getSpelloffset());
            map.put("组件", gameArmorsCreated.getComponent());
            map.put("是否有背包", gameArmorsCreated.getHasinv());
            map.put("宝石插槽数量", gameArmorsCreated.getGemsockets());
            map.put("宝石类型", gameArmorsCreated.getGemapplytype());
            map.put("右手武器", gameArmorsCreated.getRarm());
            map.put("左手武器", gameArmorsCreated.getLarm());
            map.put("身体", gameArmorsCreated.getTorso());
            map.put("腿部", gameArmorsCreated.getLegs());
            map.put("右肩垫", gameArmorsCreated.getRspad());
            map.put("左肩垫", gameArmorsCreated.getLspad());
            map.put("可用性", gameArmorsCreated.getUseable());
            map.put("类型", gameArmorsCreated.getType());
            map.put("类型2", gameArmorsCreated.getType2());
            map.put("是否唯一", gameArmorsCreated.getIsUnique());
            map.put("是否透明", gameArmorsCreated.getTransparent());
            map.put("透明度", gameArmorsCreated.getTranstbl());
            map.put("是否箭袋", gameArmorsCreated.getQuivered());
            map.put("光照半径", gameArmorsCreated.getLightradius());
            map.put("是否腰带", gameArmorsCreated.getBelt());
            map.put("是否任务物品", gameArmorsCreated.getQuest());
            map.put("投射物类型", gameArmorsCreated.getMissiletype());
            map.put("耐久度警告", gameArmorsCreated.getDurwarning());
            map.put("数量警告", gameArmorsCreated.getQntwarning());
            map.put("最小伤害", gameArmorsCreated.getMindam());
            map.put("最大伤害", gameArmorsCreated.getMaxdam());
            map.put("力量加成", gameArmorsCreated.getStrbonus());
            map.put("敏捷加成", gameArmorsCreated.getDexbonus());
            map.put("宝石偏移量", gameArmorsCreated.getGemoffset());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
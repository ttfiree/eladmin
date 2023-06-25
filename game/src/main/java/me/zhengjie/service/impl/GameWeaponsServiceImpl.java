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

import me.zhengjie.domain.GameWeapons;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.repository.GameWeaponsRepository;
import me.zhengjie.service.GameWeaponsService;
import me.zhengjie.service.dto.GameWeaponsDto;
import me.zhengjie.service.dto.GameWeaponsQueryCriteria;
import me.zhengjie.service.mapstruct.GameWeaponsMapper;
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
public class GameWeaponsServiceImpl implements GameWeaponsService {

    private final GameWeaponsRepository gameWeaponsRepository;
    private final GameWeaponsMapper gameWeaponsMapper;

    @Override
    public Map<String,Object> queryAll(GameWeaponsQueryCriteria criteria, Pageable pageable){
        Page<GameWeapons> page = gameWeaponsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameWeaponsMapper::toDto));
    }

    @Override
    public List<GameWeaponsDto> queryAll(GameWeaponsQueryCriteria criteria){
        return gameWeaponsMapper.toDto(gameWeaponsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public GameWeaponsDto findById(Integer id) {
        GameWeapons gameWeapons = gameWeaponsRepository.findById(id).orElseGet(GameWeapons::new);
        ValidationUtil.isNull(gameWeapons.getId(),"GameWeapons","id",id);
        return gameWeaponsMapper.toDto(gameWeapons);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameWeaponsDto create(GameWeapons resources) {
        return gameWeaponsMapper.toDto(gameWeaponsRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GameWeapons resources) {
        GameWeapons gameWeapons = gameWeaponsRepository.findById(resources.getId()).orElseGet(GameWeapons::new);
        ValidationUtil.isNull( gameWeapons.getId(),"GameWeapons","id",resources.getId());
        gameWeapons.copy(resources);
        gameWeaponsRepository.save(gameWeapons);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            gameWeaponsRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<GameWeaponsDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameWeaponsDto gameWeapons : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("物品ID", gameWeapons.getItemId());
            map.put("名称", gameWeapons.getName());
            map.put("类型", gameWeapons.getType());
            map.put("副类型", gameWeapons.getType2());
            map.put("代码", gameWeapons.getCode());
            map.put("替代外观", gameWeapons.getAlternategfx());
            map.put("名称字符串", gameWeapons.getNamestr());
            map.put("版本", gameWeapons.getVersion());
            map.put("紧凑保存", gameWeapons.getCompactsave());
            map.put("稀有度", gameWeapons.getRarity());
            map.put("可生成", gameWeapons.getSpawnable());
            map.put("最小伤害", gameWeapons.getMindam());
            map.put("最大伤害", gameWeapons.getMaxdam());
            map.put("单手或双手", gameWeapons.getOr2handed());
            map.put("双手", gameWeapons.getHanded());
            map.put("双手最小伤害", gameWeapons.getHandmindam());
            map.put("双手最大伤害", gameWeapons.getHandmaxdam());
            map.put("最小远程伤害", gameWeapons.getMinmisdam());
            map.put("最大远程伤害", gameWeapons.getMaxmisdam());
            map.put("范围加成", gameWeapons.getRangeadder());
            map.put("攻击速度", gameWeapons.getSpeed());
            map.put("力量加成", gameWeapons.getStrbonus());
            map.put("敏捷加成", gameWeapons.getDexbonus());
            map.put("需求力量", gameWeapons.getReqstr());
            map.put("需求敏捷", gameWeapons.getReqdex());
            map.put("耐久度", gameWeapons.getDurability());
            map.put("无耐久度", gameWeapons.getNodurability());
            map.put("物品等级", gameWeapons.getLevel());
            map.put("需求等级", gameWeapons.getLevelreq());
            map.put("价格", gameWeapons.getCost());
            map.put("赌博价格", gameWeapons.getGambleCost());
            map.put("魔法等级", gameWeapons.getMagicLvl());
            map.put("自动前缀", gameWeapons.getAutoPrefix());
            map.put("OpenBetaGfx", gameWeapons.getOpenbetagfx());
            map.put("普通代码", gameWeapons.getNormcode());
            map.put("高级代码", gameWeapons.getUbercode());
            map.put("特殊代码", gameWeapons.getUltracode());
            map.put("武器类别", gameWeapons.getWclass());
            map.put("双手武器类别", gameWeapons.getHandedwclass());
            map.put("组件", gameWeapons.getComponent());
            map.put("打击类别", gameWeapons.getHitClass());
            map.put("物品栏宽度", gameWeapons.getInvwidth());
            map.put("物品栏高度", gameWeapons.getInvheight());
            map.put("可堆叠", gameWeapons.getStackable());
            map.put("最小堆叠", gameWeapons.getMinstack());
            map.put("最大堆叠", gameWeapons.getMaxstack());
            map.put("生成堆叠", gameWeapons.getSpawnstack());
            map.put("翻转文件", gameWeapons.getFlippyfile());
            map.put("物品栏文件", gameWeapons.getInvfile());
            map.put("唯一物品栏文件", gameWeapons.getUniqueinvfile());
            map.put("套装物品栏文件", gameWeapons.getSetinvfile());
            map.put("是否有物品栏", gameWeapons.getHasinv());
            map.put("宝石插槽数量", gameWeapons.getGemsockets());
            map.put("宝石应用类型", gameWeapons.getGemapplytype());
            map.put("特殊属性", gameWeapons.getSpecial());
            map.put("可使用", gameWeapons.getUseable());
            map.put("掉落音效", gameWeapons.getDropsound());
            map.put("掉落音效帧数", gameWeapons.getDropsfxframe());
            map.put("使用音效", gameWeapons.getUsesound());
            map.put("是否唯一", gameWeapons.getIsUnique());
            map.put("是否透明", gameWeapons.getTransparent());
            map.put("透明度", gameWeapons.getTranstbl());
            map.put("是否箭袋", gameWeapons.getQuivered());
            map.put("光照半径", gameWeapons.getLightradius());
            map.put("是否腰带", gameWeapons.getBelt());
            map.put("是否任务物品", gameWeapons.getQuest());
            map.put("任务难度检查", gameWeapons.getQuestdiffcheck());
            map.put("投射物类型", gameWeapons.getMissiletype());
            map.put("耐久度警告", gameWeapons.getDurwarning());
            map.put("堆叠数量警告", gameWeapons.getQntwarning());
            map.put("宝石偏移", gameWeapons.getGemoffset());
            map.put("位域1", gameWeapons.getBitfield1());
            map.put("Charsi最小值", gameWeapons.getCharsimin());
            map.put("Charsi最大值", gameWeapons.getCharsimax());
            map.put("Charsi魔法最小值", gameWeapons.getCharsimagicmin());
            map.put("Charsi魔法最大值", gameWeapons.getCharsimagicmax());
            map.put("Charsi魔法等级", gameWeapons.getCharsimagiclvl());
            map.put("Gheed最小值", gameWeapons.getGheedmin());
            map.put("Gheed最大值", gameWeapons.getGheedmax());
            map.put("Gheed魔法最小值", gameWeapons.getGheedmagicmin());
            map.put("Gheed魔法最大值", gameWeapons.getGheedmagicmax());
            map.put("Gheed魔法等级", gameWeapons.getGheedmagiclvl());
            map.put("Akara最小值", gameWeapons.getAkaramin());
            map.put("Akara最大值", gameWeapons.getAkaramax());
            map.put("Akara魔法最小值", gameWeapons.getAkaramagicmin());
            map.put("Akara魔法最大值", gameWeapons.getAkaramagicmax());
            map.put("Akara魔法等级", gameWeapons.getAkaramagiclvl());
            map.put("Fara最小值", gameWeapons.getFaramin());
            map.put("Fara最大值", gameWeapons.getFaramax());
            map.put("Fara魔法最小值", gameWeapons.getFaramagicmin());
            map.put("Fara魔法最大值", gameWeapons.getFaramagicmax());
            map.put("Fara魔法等级", gameWeapons.getFaramagiclvl());
            map.put("Lysander最小值", gameWeapons.getLysandermin());
            map.put("Lysander最大值", gameWeapons.getLysandermax());
            map.put("Lysander魔法最小值", gameWeapons.getLysandermagicmin());
            map.put("Lysander魔法最大值", gameWeapons.getLysandermagicmax());
            map.put("Lysander魔法等级", gameWeapons.getLysandermagiclvl());
            map.put("Drognan最小值", gameWeapons.getDrognanmin());
            map.put("Drognan最大值", gameWeapons.getDrognanmax());
            map.put("Drognan魔法最小值", gameWeapons.getDrognanmagicmin());
            map.put("Drognan魔法最大值", gameWeapons.getDrognanmagicmax());
            map.put("Drognan魔法等级", gameWeapons.getDrognanmagiclvl());
            map.put("Hralti最小值", gameWeapons.getHraltimin());
            map.put("Hralti最大值", gameWeapons.getHraltimax());
            map.put("Hralti魔法最小值", gameWeapons.getHraltimagicmin());
            map.put("Hralti魔法最大值", gameWeapons.getHraltimagicmax());
            map.put("Hralti魔法等级", gameWeapons.getHraltimagiclvl());
            map.put("Alkor最小值", gameWeapons.getAlkormin());
            map.put("Alkor最大值", gameWeapons.getAlkormax());
            map.put("Alkor魔法最小值", gameWeapons.getAlkormagicmin());
            map.put("Alkor魔法最大值", gameWeapons.getAlkormagicmax());
            map.put("Alkor魔法等级", gameWeapons.getAlkormagiclvl());
            map.put("Ormus最小值", gameWeapons.getOrmusmin());
            map.put("Ormus最大值", gameWeapons.getOrmusmax());
            map.put("Ormus魔法最小值", gameWeapons.getOrmusmagicmin());
            map.put("Ormus魔法最大值", gameWeapons.getOrmusmagicmax());
            map.put("Ormus魔法等级", gameWeapons.getOrmusmagiclvl());
            map.put("Elzix最小值", gameWeapons.getElzixmin());
            map.put("Elzix最大值", gameWeapons.getElzixmax());
            map.put("Elzix魔法最小值", gameWeapons.getElzixmagicmin());
            map.put("Elzix魔法最大值", gameWeapons.getElzixmagicmax());
            map.put("Elzix魔法等级", gameWeapons.getElzixmagiclvl());
            map.put("Jamella最小值", gameWeapons.getJamellamin());
            map.put("Jamella最大值", gameWeapons.getJamellamax());
            map.put("Jamella魔法最小值", gameWeapons.getJamellamagicmin());
            map.put("Jamella魔法最大值", gameWeapons.getJamellamagicmax());
            map.put("Jamella魔法等级", gameWeapons.getJamellamagiclvl());
            map.put("Malah最小值", gameWeapons.getMalahmin());
            map.put("Malah最大值", gameWeapons.getMalahmax());
            map.put("Malah魔法最小值", gameWeapons.getMalahmagicmin());
            map.put("Malah魔法最大值", gameWeapons.getMalahmagicmax());
            map.put("Malah魔法等级", gameWeapons.getMalahmagiclvl());
            map.put("Larzuk最小值", gameWeapons.getLarzukmin());
            map.put("Larzuk最大值", gameWeapons.getLarzukmax());
            map.put("Larzuk魔法最小值", gameWeapons.getLarzukmagicmin());
            map.put("Larzuk魔法最大值", gameWeapons.getLarzukmagicmax());
            map.put("Larzuk魔法等级", gameWeapons.getLarzukmagiclvl());
            map.put("Malah自动魔法", gameWeapons.getMalahautomagic());
            map.put("Malah自动魔法最小值", gameWeapons.getMalahautomagicmin());
            map.put("Malah自动魔法最大值", gameWeapons.getMalahautomagicmax());
            map.put("Malah自动魔法等级", gameWeapons.getMalahautomagiclvl());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
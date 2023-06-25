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

import me.zhengjie.domain.GameArmors;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.repository.GameArmorsRepository;
import me.zhengjie.service.GameArmorsService;
import me.zhengjie.service.dto.GameArmorsDto;
import me.zhengjie.service.dto.GameArmorsQueryCriteria;
import me.zhengjie.service.mapstruct.GameArmorsMapper;
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
public class GameArmorsServiceImpl implements GameArmorsService {

    private final GameArmorsRepository gameArmorsRepository;
    private final GameArmorsMapper gameArmorsMapper;

    @Override
    public Map<String,Object> queryAll(GameArmorsQueryCriteria criteria, Pageable pageable){
        Page<GameArmors> page = gameArmorsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(gameArmorsMapper::toDto));
    }

    @Override
    public List<GameArmorsDto> queryAll(GameArmorsQueryCriteria criteria){
        return gameArmorsMapper.toDto(gameArmorsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public GameArmorsDto findById(Integer id) {
        GameArmors gameArmors = gameArmorsRepository.findById(id).orElseGet(GameArmors::new);
        ValidationUtil.isNull(gameArmors.getId(),"GameArmors","id",id);
        return gameArmorsMapper.toDto(gameArmors);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameArmorsDto create(GameArmors resources) {
        return gameArmorsMapper.toDto(gameArmorsRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GameArmors resources) {
        GameArmors gameArmors = gameArmorsRepository.findById(resources.getId()).orElseGet(GameArmors::new);
        ValidationUtil.isNull( gameArmors.getId(),"GameArmors","id",resources.getId());
        gameArmors.copy(resources);
        gameArmorsRepository.save(gameArmors);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            gameArmorsRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<GameArmorsDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GameArmorsDto gameArmors : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("护甲名称", gameArmors.getName());
            map.put("版本", gameArmors.getVersion());
            map.put("紧凑保存", gameArmors.getCompactsave());
            map.put("稀有度", gameArmors.getRarity());
            map.put("最小防御", gameArmors.getMinac());
            map.put("可生成", gameArmors.getSpawnable());
            map.put("最大防御", gameArmors.getMaxac());
            map.put("吸收", gameArmors.getAbsorbs());
            map.put("攻击速度", gameArmors.getSpeed());
            map.put("需求力量", gameArmors.getReqstr());
            map.put("格挡率", gameArmors.getBlock());
            map.put("耐久度", gameArmors.getDurability());
            map.put("无耐久度", gameArmors.getNodurability());
            map.put("等级", gameArmors.getLevel());
            map.put("等级需求", gameArmors.getLevelreq());
            map.put("价格", gameArmors.getCost());
            map.put("赌博价格", gameArmors.getGambleCost());
            map.put("代码", gameArmors.getCode());
            map.put("名称字符串", gameArmors.getNamestr());
            map.put("魔法等级", gameArmors.getMagicLvl());
            map.put("自动前缀", gameArmors.getAutoPrefix());
            map.put("备用图形代码", gameArmors.getAlternategfx());
            map.put("公测图形代码", gameArmors.getOpenBetaGfx());
            map.put("普通模式代码", gameArmors.getNormcode());
            map.put("超级模式代码", gameArmors.getUbercode());
            map.put("终极模式代码", gameArmors.getUltracode());
            map.put("法术偏移量", gameArmors.getSpelloffset());
            map.put("组件", gameArmors.getComponent());
            map.put("背包宽度", gameArmors.getInvwidth());
            map.put("背包高度", gameArmors.getInvheight());
            map.put("是否有背包", gameArmors.getHasinv());
            map.put("宝石插槽数量", gameArmors.getGemsockets());
            map.put("宝石类型", gameArmors.getGemapplytype());
            map.put("动画文件", gameArmors.getFlippyfile());
            map.put("背包文件", gameArmors.getInvfile());
            map.put("唯一背包文件", gameArmors.getUniqueinvfile());
            map.put("套装背包文件", gameArmors.getSetinvfile());
            map.put("右手武器", gameArmors.getRarm());
            map.put("左手武器", gameArmors.getLarm());
            map.put("身体", gameArmors.getTorso());
            map.put("腿部", gameArmors.getLegs());
            map.put("右肩垫", gameArmors.getRspad());
            map.put("左肩垫", gameArmors.getLspad());
            map.put("可用性", gameArmors.getUseable());
            map.put("可投掷", gameArmors.getThrowable());
            map.put("可叠加", gameArmors.getStackable());
            map.put("最小叠加数", gameArmors.getMinstack());
            map.put("最大叠加数", gameArmors.getMaxstack());
            map.put("类型", gameArmors.getType());
            map.put("类型2", gameArmors.getType2());
            map.put("是否唯一", gameArmors.getIsUnique());
            map.put("是否透明", gameArmors.getTransparent());
            map.put("透明度", gameArmors.getTranstbl());
            map.put("是否箭袋", gameArmors.getQuivered());
            map.put("光照半径", gameArmors.getLightradius());
            map.put("是否腰带", gameArmors.getBelt());
            map.put("是否任务物品", gameArmors.getQuest());
            map.put("投射物类型", gameArmors.getMissiletype());
            map.put("耐久度警告", gameArmors.getDurwarning());
            map.put("数量警告", gameArmors.getQntwarning());
            map.put("最小伤害", gameArmors.getMindam());
            map.put("最大伤害", gameArmors.getMaxdam());
            map.put("力量加成", gameArmors.getStrbonus());
            map.put("敏捷加成", gameArmors.getDexbonus());
            map.put("宝石偏移量", gameArmors.getGemoffset());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
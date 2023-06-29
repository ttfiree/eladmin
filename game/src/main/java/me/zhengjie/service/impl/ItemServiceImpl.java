package me.zhengjie.service.impl;

import cn.hutool.json.JSONObject;
import me.zhengjie.content.D2Prop;
import me.zhengjie.content.D2PropCollection;
import me.zhengjie.content.DataMap;
import me.zhengjie.domain.GameArmorsCreated;
import me.zhengjie.domain.GameEquipmentDetail;
import me.zhengjie.domain.GamePlayer;
import me.zhengjie.domain.GameResult;
import me.zhengjie.service.GameArmorsCreatedService;
import me.zhengjie.service.GameArmorsService;
import me.zhengjie.service.GameEquipmentDetailService;
import me.zhengjie.service.dto.GameArmorsCreatedDto;
import me.zhengjie.service.dto.GameArmorsDto;
import me.zhengjie.service.GameItemService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ItemServiceImpl implements GameItemService {


    @Autowired
    private GameArmorsService gameArmorsService;
    @Autowired
    private GameArmorsCreatedService gameArmorsCreatedService;
    @Autowired
    private GameEquipmentDetailService gameEquipmentDetailService;
    @Override
    public GameArmorsDto createItem(GamePlayer player) {
        long item = player.getItem();
        //item是0-100的数字，随机数计算是否比item小，小则掉落
        int random = RandomUtils.nextInt(0, 100);
        if(random>item){
            return null;
        }
        int id = RandomUtils.nextInt(2, player.getLevel());
        GameArmorsDto gameArmorsDto = gameArmorsService.findById(id);
        GameEquipmentDetail gameEquipmentDetail = new GameEquipmentDetail();
        try {
            gameArmorsDto = generatePropStringNoHtmlTags(gameArmorsDto,gameEquipmentDetail);
        } catch (Exception e) {
            System.out.println("NoSuchFieldException");
        }
        gameArmorsDto.setCharId(player.getCharId());
        GameArmorsCreated gameArmorsCreatedDto = new GameArmorsCreated();
        BeanUtils.copyProperties(gameArmorsDto,gameArmorsCreatedDto);
        gameArmorsCreatedDto.setItemId(gameArmorsDto.getId());
        gameArmorsCreatedDto.setId(null);
        if(gameEquipmentDetail.getAcPercent()!=null){
            //gameEquipmentDetail.getAcPercent()为增幅百分比 如50就增加50%
            gameArmorsCreatedDto.setMinac(gameArmorsCreatedDto.getMinac()*(1+gameEquipmentDetail.getAcPercent()/100));
        }
        if(gameEquipmentDetail.getAc()!=null){
            gameArmorsCreatedDto.setMinac(gameArmorsCreatedDto.getMinac()+gameEquipmentDetail.getAc());
        }
        gameEquipmentDetail.setFinalAc(gameArmorsCreatedDto.getMinac());
        GameArmorsCreatedDto gameArmorsCreated = gameArmorsCreatedService.create(gameArmorsCreatedDto);
        gameEquipmentDetail.setItemId(gameArmorsCreated.getId());
        gameEquipmentDetail.setCharId(player.getCharId());
        gameEquipmentDetailService.create(gameEquipmentDetail);
        return gameArmorsDto;
    }



    private GameArmorsDto generatePropStringNoHtmlTags(GameArmorsDto gameArmors,GameEquipmentDetail gameEquipmentDetail ) throws NoSuchFieldException, IllegalAccessException {
        List<String> list = new ArrayList<>();
        String rgb = (Integer.toHexString(gameArmors.getItemColor().getRGB())).substring(2);
        gameArmors.setNameHtml("<font color=\"#" + rgb + "\">"+ gameArmors.getName() + "</font>" );
        int minac  = gameArmors.getMinac();
        int maxac = gameArmors.getMaxac();
        //随机生成防御力在minac和maxac之间
        int ac = (int) (Math.random() * (maxac - minac + 1) + minac);
        gameArmors.setMinac(ac);
        list.add("防御: " +  ac );
        if (gameArmors.getLevelreq() > 0)list.add("等级需求: " + gameArmors.getLevelreq() );
        if (gameArmors.getReqstr() > 0)list.add("力量需求: " + gameArmors.getReqstr() );
        if (gameArmors.getLevel() != 0)list.add("物品等级: " + gameArmors.getLevel());

        int n = RandomUtils.nextInt(5, 20);
        gameArmors.setVersion(n);
        list.add("词缀数量: " + n );

        List<String> keyList = new ArrayList<>(DataMap.ITEM_STAT_MAP.keySet());
        int keyListSize = keyList.size();
        List<Integer> randomIndices = IntStream.range(0, keyListSize).boxed().collect(Collectors.toList());
        Collections.shuffle(randomIndices);

        for (int i = 0; i < n; i++) {
            int index = randomIndices.get(i);
            String key = keyList.get(index);
            double value = Math.random();
            BigDecimal  bigDecimal = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
            JSONObject jsonObject = DataMap.ITEM_STAT_MAP.get(key);
            BigDecimal bigDecimal1 = bigDecimal.multiply(new BigDecimal(jsonObject.get("maxvalue").toString())).setScale(0, RoundingMode.HALF_UP);
            list.add(jsonObject.get("desnew").toString().replace("value",String.valueOf(bigDecimal1)));
            createDetail(jsonObject,bigDecimal1,gameEquipmentDetail);
        }
        //根据随机取出的key，从DataMap.PROPERTY_MAP中取出对应的value
        gameArmors.setStatsHtml(list);
        return gameArmors;
    }

   private void createDetail(JSONObject jsonObject, BigDecimal value, GameEquipmentDetail gameArmorsCreated) throws NoSuchFieldException, IllegalAccessException {
        //根据jsonObject.get("value")的值为gameArmorsCreated的字段名，设置gameArmorsCreated对应字段的值为value
       String fieldName = jsonObject.get("column").toString();
       Field field = gameArmorsCreated.getClass().getDeclaredField(fieldName);
       field.setAccessible(true);
       Integer oldValue = (Integer) field.get(gameArmorsCreated);
       Integer newValue = (oldValue == null) ? value.intValue() : (oldValue + value.intValue());
       field.set(gameArmorsCreated, newValue);
   }








}

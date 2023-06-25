package me.zhengjie.service.impl;

import cn.hutool.json.JSONObject;
import me.zhengjie.content.D2Prop;
import me.zhengjie.content.D2PropCollection;
import me.zhengjie.content.DataMap;
import me.zhengjie.service.GameArmorsService;
import me.zhengjie.service.dto.GameArmorsDto;
import me.zhengjie.service.GameItemService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class ItemServiceImpl implements GameItemService {


    @Autowired
    private GameArmorsService gameArmorsService;
    @Override
    public GameArmorsDto createItem() {
        //2到204随机去除一个数
        int id = RandomUtils.nextInt(2, 204);
        GameArmorsDto gameArmorsDto = gameArmorsService.findById(id);
        StringBuffer sb = generatePropStringNoHtmlTags(gameArmorsDto);
        return gameArmorsDto;
    }



    private StringBuffer generatePropStringNoHtmlTags(GameArmorsDto gameArmors){
        D2PropCollection iProps = new D2PropCollection();
        List<String> list = new ArrayList<>();
        //iProps.tidy();
        StringBuffer dispStr = new StringBuffer();
        String base = (Integer.toHexString(Color.white.getRGB())).substring(2);
        String rgb = (Integer.toHexString(gameArmors.getItemColor().getRGB())).substring(2);
        gameArmors.setNameHtml("<font color=\"#" + rgb + "\">"+ gameArmors.getName() + "</font>" );
        int minac  = gameArmors.getMinac();
        int maxac = gameArmors.getMaxac();
        int iBlock = gameArmors.getBlock();
        //随机生成防御力在minac和maxac之间
        int ac = (int) (Math.random() * (maxac - minac + 1) + minac);
                dispStr.append("Defense: " +  ac );
                if(gameArmors.isShield()){
                    dispStr.append("Chance to Block: " + iBlock );
                }
                    dispStr.append("Indestructible" + "<br>&#10;");
        if (gameArmors.getLevelreq() > 0)list.add("等级需求: " + gameArmors.getLevelreq() );
        if (gameArmors.getReqstr() > 0)list.add("力量需求: " + gameArmors.getReqstr() );
        if (gameArmors.getLevel() != 0)list.add("物品等级: " + gameArmors.getLevel());

        //将DataMap.PROPERTY_MAP的key转为list，并随机取出n个元素
        int n = RandomUtils.nextInt(5, 20);
        list.add("词缀数量: " + n );
        //从keyList中随机取出n个元素
        List<String> randomKeyList = new ArrayList<>(DataMap.ITEM_STAT_MAP.keySet());
        for (int i = 0; i < n; i++) {
            int index = RandomUtils.nextInt(0, DataMap.ITEM_STAT_MAP.keySet().size());
            iProps.add(new D2Prop(Integer.valueOf(randomKeyList.get(index)),new int[]{1,20},0));
        }
        //根据随机取出的key，从DataMap.PROPERTY_MAP中取出对应的value
        getItemPropertyString(iProps,list);
        gameArmors.setStatsHtml(list);
        return dispStr.append("</font>");
    }

    private StringBuffer getItemPropertyString(D2PropCollection iProps,List<String> list){
        int iCharLvl = 10;

        StringBuffer dispStr = new StringBuffer();

            StringBuffer setBuf = iProps.generateDisplay(36, iCharLvl,list);
            if (setBuf.length() > 33) {
                dispStr.append(setBuf);
            }
        return dispStr;
    }







}

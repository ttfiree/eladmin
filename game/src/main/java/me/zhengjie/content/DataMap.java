package me.zhengjie.content;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class DataMap {

    public static final Map<Integer, Long> EXP_MAP;
    public static final Map<String, Integer> LEVEL_MAP;
    public static final Map<String, Object> PROPERTY_MAP;

    public static final Map<String, JSONObject> ITEM_STAT_MAP;
    public static final Map<String, String> PATCH_MAP;
    public static final Map<String, String> EXP2_MAP;
    public static final Map<String, String> STRING_MAP;
    static {
        Map<Integer, Long> expMap = new HashMap<>();
        expMap.put(1, 0L);
        expMap.put(2, 680L);
        expMap.put(3, 2140L);
        expMap.put(4, 5160L);
        expMap.put(5, 10640L);
        expMap.put(6, 19860L);
        expMap.put(7, 33480L);
        expMap.put(8, 53020L);
        expMap.put(9, 79930L);
        expMap.put(10, 116860L);
        expMap.put(11, 166090L);
        expMap.put(12, 235420L);
        expMap.put(13, 330940L);
        expMap.put(14, 465650L);
        expMap.put(15, 654550L);
        expMap.put(16, 917700L);
        expMap.put(17, 1276270L);
        expMap.put(18, 1771560L);
        expMap.put(19, 2446210L);
        expMap.put(20, 3351270L);
        expMap.put(21, 4548890L);
        expMap.put(22, 6121930L);
        expMap.put(23, 8167100L);
        expMap.put(24, 10794070L);
        expMap.put(25, 14126420L);
        expMap.put(26, 18305070L);
        expMap.put(27, 23473820L);
        expMap.put(28, 29786150L);
        expMap.put(29, 37417040L);
        expMap.put(30, 46553020L);
        expMap.put(31, 57394370L);
        expMap.put(32, 70159940L);
        expMap.put(33, 85090080L);
        expMap.put(34, 102463810L);
        expMap.put(35, 122595960L);
        expMap.put(36, 145834920L);
        expMap.put(37, 172567380L);
        expMap.put(38, 203219440L);
        expMap.put(39, 238269330L);
        expMap.put(40, 278236820L);
        expMap.put(41, 323705230L);
        expMap.put(42, 375319610L);
        expMap.put(43, 433791840L);
        expMap.put(44, 499901550L);
        expMap.put(45, 574498630L);
        expMap.put(46, 658513910L);
        expMap.put(47, 753051120L);
        expMap.put(48, 859278170L);
        expMap.put(49, 978393290L);
        expMap.put(50, 1111606800L);
        expMap.put(51, 1262472780L);
        expMap.put(52, 1429763520L);
        expMap.put(53, 1614914280L);
        expMap.put(54, 1819388800L);
        expMap.put(55, 2044745500L);
        expMap.put(56, 2292684400L);
        expMap.put(57, 2565026820L);
        expMap.put(58, 2863734560L);
        expMap.put(59, 3190911420L);
        expMap.put(60, 3548823320L);
        expMap.put(61, 3940000000L);
        expMap.put(62, 4367172000L);
        expMap.put(63, 4833376000L);
        expMap.put(64, 5341888000L);
        expMap.put(65, 5891264000L);
        expMap.put(66, 6488064000L);
        expMap.put(67, 7130032000L);
        expMap.put(68, 7815488000L);
        expMap.put(69, 8533152000L);
        expMap.put(70, 9282816000L);
        expMap.put(71, 10064928000L);
        expMap.put(72, 10973760000L);
        expMap.put(73, 11927360000L);
        expMap.put(74, 12927648000L);
        expMap.put(75, 13976576000L);
        expMap.put(76, 15076192000L);
        expMap.put(77, 16228544000L);
        expMap.put(78, 17435784000L);
        expMap.put(79, 18700160000L);
        expMap.put(80, 20023920000L);
        expMap.put(81, 21409312000L);
        expMap.put(82, 22858680000L);
        expMap.put(83, 24374368000L);
        expMap.put(84, 25958720000L);
        expMap.put(85, 27614080000L);
        expMap.put(86, 29342800000L);
        expMap.put(87, 31147136000L);
        expMap.put(88, 33029344000L);
        expMap.put(89, 34991780000L);
        expMap.put(90, 37036800000L);
        expMap.put(91, 39166752000L);
        expMap.put(92, 41384080000L);
        expMap.put(93, 43691232000L);
        expMap.put(94, 46090656000L);
        expMap.put(95, 48584800000L);
        expMap.put(96, 51176112000L);
        expMap.put(97, 53867040000L);
        expMap.put(98, 56660032000L);
        expMap.put(99, 59557536000L);

        EXP_MAP = Collections.unmodifiableMap(expMap);
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = JsonParser.class.getClassLoader().getResourceAsStream("map.json");
        try {
            LEVEL_MAP = Collections.unmodifiableMap(mapper.readValue(inputStream, new TypeReference<Map<String, Integer>>(){}));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        inputStream = JsonParser.class.getClassLoader().getResourceAsStream("properties.json");

        // 将JSON文件解析为JSON对象
        mapper = new ObjectMapper();
        try {
            PROPERTY_MAP = Collections.unmodifiableMap(mapper.readValue(inputStream, new TypeReference<Map<String, Map<String, Object>>>(){}));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        inputStream = JsonParser.class.getClassLoader().getResourceAsStream("itemStat.json");

        // 将inputStream解析为JSON对象的list
        mapper = new ObjectMapper();
        try {
           List<JSONObject> ITEM_STAT_LIST = Collections.unmodifiableList(mapper.readValue(inputStream, new TypeReference<List<JSONObject>>(){}));
           // 将list转换为map
              ITEM_STAT_MAP = ITEM_STAT_LIST.stream().collect(Collectors.toMap(item -> item.get("item_id").toString(), item -> item));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        inputStream = JsonParser.class.getClassLoader().getResourceAsStream("patch.json");
        mapper = new ObjectMapper();
        try {
            PATCH_MAP = Collections.unmodifiableMap(mapper.readValue(inputStream, new TypeReference<Map<String, String>>(){}));
            inputStream = JsonParser.class.getClassLoader().getResourceAsStream("exp.json");
            mapper = new ObjectMapper();
            EXP2_MAP = Collections.unmodifiableMap(mapper.readValue(inputStream, new TypeReference<Map<String, String>>(){}));
            inputStream = JsonParser.class.getClassLoader().getResourceAsStream("string.json");
            mapper = new ObjectMapper();
            STRING_MAP = Collections.unmodifiableMap(mapper.readValue(inputStream, new TypeReference<Map<String, String>>(){}));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

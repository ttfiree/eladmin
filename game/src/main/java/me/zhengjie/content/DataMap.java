package me.zhengjie.content;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DataMap {

    public static final Map<Integer, Long> EXP_MAP;
    public static final Map<String, Integer> LEVEL_MAP;
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
       String json = "{\n" +
               "  \"库拉斯特上层区\": 81,\n" +
               "  \"世界之石要塞三层\": 130,\n" +
               "  \"鲜血荒地\": 2,\n" +
               "  \"古代水道\": 65,\n" +
               "  \"外侧回廊\": 27,\n" +
               "  \"监牢一层\": 29,\n" +
               "  \"库拉斯特堤道\": 82,\n" +
               "  \"修道院大门\": 26,\n" +
               "  \"外域荒原\": 104,\n" +
               "  \"崔斯特姆\": 38,\n" +
               "  \"沼泽陷坑三层\": 90,\n" +
               "  \"亚巴顿\": 125,\n" +
               "  \"邪恶洞穴\": 8,\n" +
               "  \"沃特大厅\": 124,\n" +
               "  \"蜘蛛森林\": 76,\n" +
               "  \"监牢二层\": 30,\n" +
               "  \"漂流洞窟\": 116,\n" +
               "  \"利爪蝮蛇神殿\": 61,\n" +
               "  \"沼泽陷坑\": 86,\n" +
               "  \"剥皮魔监牢\": 88,\n" +
               "  \"地下通道一层\": 10,\n" +
               "  \"憎恨囚牢\": 101,\n" +
               "  \"寝陵\": 19,\n" +
               "  \"亚瑞特高原\": 112,\n" +
               "  \"地下通道二层\": 14,\n" +
               "  \"高塔地窖四层\": 24,\n" +
               "  \"碎石荒野\": 41,\n" +
               "  \"库拉斯特海港\": 75,\n" +
               "  \"地下墓穴四层\": 37,\n" +
               "  \"被遗忘的藏骨室\": 96,\n" +
               "  \"女眷住处二层\": 51,\n" +
               "  \"墓穴\": 18,\n" +
               "  \"冰冻之河\": 114,\n" +
               "  \"混沌避难所\": 108,\n" +
               "  \"废弃的礼拜堂\": 95,\n" +
               "  \"群蛇峡谷\": 45,\n" +
               "  \"炼狱深渊\": 127,\n" +
               "  \"利爪腹蛇神庙二层\": 61,\n" +
               "  \"亡者大殿\": 56,\n" +
               "  \"先祖之路\": 118,\n" +
               "  \"神秘避难所\": 74,\n" +
               "  \"女眷住处一层\": 50,\n" +
               "  \"火焰之河\": 107,\n" +
               "  \"蛆虫巢穴三层\": 64,\n" +
               "  \"蜘蛛洞穴\": 85,\n" +
               "  \"毁灭的神庙\": 94,\n" +
               "  \"黑色沼泽\": 6,\n" +
               "  \"大教堂\": 33,\n" +
               "  \"法师峡谷\": 46,\n" +
               "  \"沼泽陷坑二层\": 87,\n" +
               "  \"黑暗森林\": 5,\n" +
               "  \"被遗忘的高塔\": 20,\n" +
               "  \"监牢三层\": 31,\n" +
               "  \"埋骨之地\": 17,\n" +
               "  \"痛楚大厅\": 122,\n" +
               "  \"毁灭的礼拜堂\": 98,\n" +
               "  \"水晶通道\": 113,\n" +
               "  \"沼泽陷坑一层\": 86,\n" +
               "  \"泰摩高地\": 7,\n" +
               "  \"高塔地窖五层\": 25,\n" +
               "  \"洞坑二层\": 15,\n" +
               "  \"憎恨囚牢一层\": 100,\n" +
               "  \"利爪蝮蛇神殿一层\": 58,\n" +
               "  \"深坑一层\": 12,\n" +
               "  \"失落之城\": 44,\n" +
               "  \"高塔地窖二层\": 22,\n" +
               "  \"神罚之城\": 106,\n" +
               "  \"哈洛加斯\": 109,\n" +
               "  \"碎石古墓\": 59,\n" +
               "  \"蛆虫巢穴一层\": 62,\n" +
               "  \"混沌要塞\": 103,\n" +
               "  \"剥皮魔监牢一层\": 88,\n" +
               "  \"被遗忘的神殿\": 97,\n" +
               "  \"憎恨囚牢三层\": 102,\n" +
               "  \"剥皮魔监牢二层\": 89,\n" +
               "  \"蛆虫巢穴二层\": 63,\n" +
               "  \"血腥丘陵\": 110,\n" +
               "  \"洞坑一层\": 11,\n" +
               "  \"石块旷野\": 4,\n" +
               "  \"洞穴二层\": 13,\n" +
               "  \"冰冷之原\": 3,\n" +
               "  \"阿克隆深渊\": 126,\n" +
               "  \"高塔地窖三层\": 23,\n" +
               "  \"内侧回廊\": 32,\n" +
               "  \"营房\": 28,\n" +
               "  \"世界之石大殿\": 132,\n" +
               "  \"神秘的奶牛关\": 39,\n" +
               "  \"巨蛛巢穴\": 84,\n" +
               "  \"流亡者营地\": 1,\n" +
               "  \"王宫监牢三层\": 54,\n" +
               "  \"高塔地窖一层\": 21,\n" +
               "  \"下水道一层\": 92,\n" +
               "  \"洞穴一层\": 9,\n" +
               "  \"憎恨囚牢二层\": 101,\n" +
               "  \"下水道二层\": 93,\n" +
               "  \"亡者大殿三层\": 60,\n" +
               "  \"蛆虫巢穴\": 64,\n" +
               "  \"库拉斯特下层区\": 79,\n" +
               "  \"冰冻高地\": 111,\n" +
               "  \"庞大湿地\": 77,\n" +
               "  \"尼拉塞克的神殿\": 121,\n" +
               "  \"女眷住处\": 51,\n" +
               "  \"王宫监牢一层\": 52,\n" +
               "  \"冰川小径\": 115,\n" +
               "  \"亚瑞特之巅\": 120,\n" +
               "  \"绝望平原\": 105,\n" +
               "  \"亡者大殿一层\": 56,\n" +
               "  \"地下墓穴三层\": 36,\n" +
               "  \"库拉斯特集市\": 80,\n" +
               "  \"王宫监牢二层\": 53,\n" +
               "  \"下水道三层\": 49,\n" +
               "  \"下水道\": 93,\n" +
               "  \"亡者大殿二层\": 57,\n" +
               "  \"王宫监牢\": 52,\n" +
               "  \"废弃的藏骨室\": 99,\n" +
               "  \"鲁·高因\": 40,\n" +
               "  \"世界之石要塞二层\": 129,\n" +
               "  \"干燥高地\": 42,\n" +
               "  \"毁灭王座\": 131,\n" +
               "  \"世界之石要塞一层\": 128,\n" +
               "  \"碎石古墓二层\": 59,\n" +
               "  \"偏远绿洲\": 43,\n" +
               "  \"地下墓穴二层\": 35,\n" +
               "  \"冰冻苔原\": 117,\n" +
               "  \"碎石古墓一层\": 55,\n" +
               "  \"崔凡克\": 83,\n" +
               "  \"剥皮魔监牢三层\": 91,\n" +
               "  \"剥皮魔丛林\": 78,\n" +
               "  \"地下墓穴一层\": 34,\n" +
               "  \"深坑二层\": 16,\n" +
               "  \"寒冰地窖\": 119\n" +
               "}\n";
        try {
            Map<String, Integer> map = mapper.readValue(json, new TypeReference<Map<String, Integer>>(){});
            LEVEL_MAP = Collections.unmodifiableMap(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

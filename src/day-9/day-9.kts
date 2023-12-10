class History(dataset: MutableList<Int>) {
    val datasets = mutableListOf(dataset)

    private fun areAllZeros(dataset: List<Int>): Boolean {
        for (element in dataset) {
            if (element != 0) return false
        }
        return true
    }

    fun computeDifference() {
        val lastDataSet = datasets.last()
        if (areAllZeros(lastDataSet)) return;

        val childDataSet = mutableListOf<Int>()
        for (i in 0..lastDataSet.size - 2) {
            childDataSet.add(lastDataSet[i + 1] - lastDataSet[i])
        }
        datasets.add(childDataSet)
        computeDifference()
    }

    fun predictNext() {
        for (i in 0..datasets.size - 2) {
            var sum = 0
            for (j in i..datasets.size - 2) {
                sum += datasets[j].last()
            }
            datasets[i].add(sum)
        }
        datasets.last().add(0)
    }

    fun predictPrevious() {
        datasets.last().add(0)

        for (i in datasets.size - 2 downTo 0) {
            val previousValue = datasets[i].first() - datasets[i + 1].first()
            datasets[i].add(0, previousValue)
        }
    }

    override fun toString(): String {
        var text = ""
        var tab = 0

        for (dataset in datasets) {
            text += "  ".repeat(tab)
            for (value in dataset) {
                text += value
                text += if (value > 9) "  "
                else "   "
            }
            text += "\n"
            tab += 1
        }

        return text
    }
}

fun main() {
//    val histories = listOf(
//            History(mutableListOf(0, 3, 6, 9, 12, 15)),
//            History(mutableListOf(1, 3, 6, 10, 15, 21)),
//            History(mutableListOf(10, 13, 16, 21, 30, 45))
//    )

    val histories = listOf(History(mutableListOf(18,34,62,104,162,241,354,541,935,1948,4716,12044,30251,72571,165186,357653,740584,1475142,2841482,5317016,9700712)),
            History(mutableListOf(-1,9,31,63,102,139,150,87,-129,-609,-1472,-2740,-4028,-3797,2219,24016,82639,219061,508309,1081061,2155578)),
            History(mutableListOf(-5,-12,-21,-31,-28,39,297,1010,2680,6225,13298,26846,52051,97846,179258,320897,561985,963402,1617317,2660071,4289086)),
            History(mutableListOf(16,31,55,93,166,327,690,1491,3227,6970,15040,32365,69094,145421,300225,606225,1196290,2309185,4370163,8136063,14963082)),
            History(mutableListOf(18,23,28,33,38,43,48,53,58,63,68,73,78,83,88,93,98,103,108,113,118)),
            History(mutableListOf(-5,6,29,60,96,153,294,676,1649,3981,9347,21326,47356,102536,217094,451214,924476,1873567,3762890,7490707,14761961)),
            History(mutableListOf(10,17,28,38,42,35,12,-32,-102,-203,-340,-518,-742,-1017,-1348,-1740,-2198,-2727,-3332,-4018,-4790)),
            History(mutableListOf(0,-2,13,57,151,332,670,1317,2626,5413,11503,24829,53593,114457,240641,497654,1014156,2042992,4082021,8111461,16052851)),
            History(mutableListOf(2,-5,-16,-25,-5,111,464,1335,3268,7283,15220,30263,57701,105991,188196,323879,541542,881707,1400744,2175559,3309263)),
            History(mutableListOf(20,39,64,88,105,115,131,188,354,743,1530,2968,5407,9315,15301,24140,36800,54471,78596,110904,153445)),
            History(mutableListOf(5,20,48,85,136,226,422,889,2012,4620,10355,22266,45839,91022,176573,339567,655669,1280810,2534320,5062061,10155672)),
            History(mutableListOf(-4,7,30,65,112,171,242,325,420,527,646,777,920,1075,1242,1421,1612,1815,2030,2257,2496)),
            History(mutableListOf(14,23,32,41,50,59,68,77,86,95,104,113,122,131,140,149,158,167,176,185,194)),
            History(mutableListOf(11,30,63,120,220,388,654,1068,1763,3140,6337,14316,34222,82251,193340,440052,969124,2072447,4326020,8863625,17915757)),
            History(mutableListOf(11,34,66,115,205,387,772,1599,3362,7044,14547,29479,58594,114438,220229,418836,789125,1475186,2737408,5039495,9192899)),
            History(mutableListOf(11,30,67,130,228,390,704,1378,2821,5740,11247,20958,37026,61958,97894,144742,196127,231466,201531,3450,-561040)),
            History(mutableListOf(-3,5,35,96,204,394,749,1464,2974,6198,13001,27079,55667,112816,225592,445682,871240,1689106,3259812,6295685,12243111)),
            History(mutableListOf(23,42,67,108,201,434,980,2132,4335,8210,14565,24388,38817,59082,86414,121916,166391,220122,282599,352188,425737)),
            History(mutableListOf(16,15,5,-10,-17,11,137,517,1525,4014,9853,23020,51804,113206,241667,506174,1043166,2118233,4238285,8348654,16167381)),
            History(mutableListOf(-1,-3,-5,-6,-1,19,67,160,319,569,939,1462,2175,3119,4339,5884,7807,10165,13019,16434,20479)),
            History(mutableListOf(11,31,63,113,194,334,587,1042,1817,3010,4562,5996,6107,3054,-3733,-6260,32771,231121,902709,2804259,7639228)),
            History(mutableListOf(18,29,37,44,64,143,403,1122,2877,6819,15226,32602,67777,137751,274481,536587,1029449,1940411,3603300,6623874,12137191)),
            History(mutableListOf(22,44,80,150,295,580,1102,2010,3541,6073,10193,16775,27060,42727,65941,99361,146088,209530,293158,400124,532709)),
            History(mutableListOf(10,32,77,154,266,410,582,796,1140,1915,3935,9107,21460,48851,105644,216735,423382,791394,1422337,2468528,4152710)),
            History(mutableListOf(-6,5,34,81,144,221,327,547,1157,2873,7357,18257,43344,98837,217992,467944,982647,2027667,4128793,8329233,16705299)),
            History(mutableListOf(17,19,28,55,110,202,339,528,775,1085,1462,1909,2428,3020,3685,4422,5229,6103,7040,8035,9082)),
            History(mutableListOf(8,4,10,33,90,232,581,1385,3099,6512,12971,24826,46382,85977,160437,304275,587854,1151656,2268212,4449675,8628094)),
            History(mutableListOf(17,17,18,20,30,76,220,577,1368,3072,6800,15098,33501,73311,156264,321989,639451,1223915,2261374,4042856,7011568)),
            History(mutableListOf(2,17,42,92,199,418,838,1608,2995,5505,10133,18884,35856,69465,136972,273659,551392,1115933,2260864,4570832,9191533)),
            History(mutableListOf(21,32,35,30,18,11,70,383,1400,4069,10293,23891,52647,112539,236050,487707,991855,1980402,3872207,7401376,13819568)),
            History(mutableListOf(8,23,51,98,177,333,692,1548,3520,7853,17013,35846,73745,148507,292874,565148,1065760,1962267,3525959,6184090,10592713)),
            History(mutableListOf(23,37,64,118,232,485,1056,2320,5000,10400,20780,40008,74749,136640,246166,439308,778493,1369951,2390288,4125930,7030094)),
            History(mutableListOf(20,45,92,181,337,589,973,1548,2442,3953,6738,12131,22639,42673,79579,145042,256944,441765,737624,1198065,1896701)),
            History(mutableListOf(4,7,16,31,52,79,112,151,196,247,304,367,436,511,592,679,772,871,976,1087,1204)),
            History(mutableListOf(-3,10,36,70,97,86,-21,-325,-990,-2262,-4472,-7980,-12923,-18336,-19424,87,87451,366536,1136900,3075432,7624577)),
            History(mutableListOf(5,17,52,130,280,548,1018,1851,3363,6202,11754,23030,46514,95896,199474,414641,855922,1748676,3530985,7045243,13895097)),
            History(mutableListOf(10,28,54,93,159,292,589,1249,2632,5332,10264,18765,32709,54636,87895,136801,206806,304684,438730,618973,857403)),
            History(mutableListOf(15,27,56,123,262,535,1073,2167,4444,9181,18845,38013,74944,144273,271598,501124,907939,1616841,2830175,4869658,8247946)),
            History(mutableListOf(5,9,27,68,136,240,428,873,2063,5195,12963,31081,71110,155471,325918,657189,1277993,2401833,4370267,7710870,13211108)),
            History(mutableListOf(17,30,49,87,184,433,1020,2281,4779,9404,17499,31015,52698,86311,136894,211065,317365,466650,672533,951879,1325356)),
            History(mutableListOf(11,10,11,26,87,251,617,1365,2826,5597,10728,20031,36596,65648,115944,201992,347477,590404,990617,1640528,2680093)),
            History(mutableListOf(14,17,20,23,26,29,32,35,38,41,44,47,50,53,56,59,62,65,68,71,74)),
            History(mutableListOf(8,13,25,51,101,192,352,619,1024,1536,1936,1587,-905,-8065,-23725,-52495,-97592,-155457,-204701,-185717,34289)),
            History(mutableListOf(-6,-2,19,79,210,454,863,1499,2434,3750,5539,7903,10954,14814,19615,25499,32618,41134,51219,63055,76834)),
            History(mutableListOf(12,25,45,65,83,106,155,290,690,1848,4980,12804,30923,70147,150214,305519,593632,1107579,1993071,3472091,5874483)),
            History(mutableListOf(18,27,39,66,144,349,818,1775,3562,6675,11805,19884,32136,50133,75856,111761,160850,226747,313779,427062,572592)),
            History(mutableListOf(10,23,39,55,72,112,255,710,1936,4831,11009,23187,45706,85212,151525,258726,426494,681727,1060483,1610279,2392788)),
            History(mutableListOf(8,17,30,46,75,146,320,715,1550,3215,6374,12108,22105,38904,66200,109217,175156,273725,417758,623930,913575)),
            History(mutableListOf(10,23,51,98,172,285,453,696,1038,1507,2135,2958,4016,5353,7017,9060,11538,14511,18043,22202,27060)),
            History(mutableListOf(11,17,45,114,254,512,974,1828,3514,7037,14570,30579,63924,131848,267693,536042,1062713,2094525,4118891,8103872,15980670)),
            History(mutableListOf(18,43,82,147,272,524,1009,1871,3282,5421,8440,12415,17280,22742,28175,32491,33986,30159,17502,-8741,-54848)),
            History(mutableListOf(11,10,17,55,162,392,814,1509,2565,4070,6103,8723,11956,15780,20108,24769,29487,33858,37325,39151,38390)),
            History(mutableListOf(-8,-10,-6,16,92,293,732,1577,3102,5847,11022,21395,43074,88855,184182,377249,755324,1469880,2773370,5070142,8982544)),
            History(mutableListOf(22,43,82,145,238,367,538,757,1030,1363,1762,2233,2782,3415,4138,4957,5878,6907,8050,9313,10702)),
            History(mutableListOf(19,29,48,86,162,332,747,1757,4076,9034,18993,38146,74236,142358,273135,529462,1039061,2054775,4061482,7958530,15360674)),
            History(mutableListOf(27,40,53,66,79,92,105,118,131,144,157,170,183,196,209,222,235,248,261,274,287)),
            History(mutableListOf(3,-1,-4,9,62,192,454,920,1678,2860,4751,8038,14231,26211,48714,88304,151929,242330,347229,417585,330010)),
            History(mutableListOf(10,10,22,51,94,139,176,235,479,1415,4358,12408,32395,78530,178890,386382,796496,1574992,2999694,5521809,9853676)),
            History(mutableListOf(-2,-10,-24,-52,-105,-197,-333,-472,-442,252,2869,10434,29942,77010,185279,424621,934986,1986958,4086314,8148756,15783309)),
            History(mutableListOf(8,18,41,84,163,301,521,834,1222,1616,1869,1724,777,-1565,-6131,-14038,-26748,-46130,-74527,-114828,-170545)),
            History(mutableListOf(-4,7,35,82,147,226,312,395,462,497,481,392,205,-108,-578,-1239,-2128,-3285,-4753,-6578,-8809)),
            History(mutableListOf(-1,13,47,110,214,382,664,1158,2044,3667,6750,12878,25472,51592,105142,212555,423094,827950,1595922,3039239,5731439)),
            History(mutableListOf(18,23,29,36,44,53,63,74,86,99,113,128,144,161,179,198,218,239,261,284,308)),
            History(mutableListOf(-1,4,19,59,163,406,916,1906,3728,6957,12524,21955,37883,65279,114495,208634,400787,812970,1721455,3739047,8195977)),
            History(mutableListOf(29,52,96,173,309,561,1048,2009,3906,7594,14573,27318,49689,87576,150522,256684,447278,824709,1644692,3523541,7879238)),
            History(mutableListOf(23,28,30,29,25,18,8,-5,-21,-40,-62,-87,-115,-146,-180,-217,-257,-300,-346,-395,-447)),
            History(mutableListOf(2,2,-8,-34,-87,-188,-361,-601,-798,-584,962,5898,18639,48596,115871,263177,579714,1246782,2621028,5380118,10768157)),
            History(mutableListOf(8,22,36,61,135,341,842,1955,4293,9010,18193,35464,66902,122516,218795,383513,665258,1152438,2010102,3547783,6336845)),
            History(mutableListOf(3,9,29,72,150,273,447,681,1009,1533,2493,4370,8028,14901,27231,48363,83103,138145,222573,348444,531458)),
            History(mutableListOf(17,28,61,144,321,652,1214,2106,3465,5517,8740,14344,25549,50720,110635,254746,596771,1391424,3197620,7220587,16017085)),
            History(mutableListOf(5,11,29,64,121,205,321,474,669,911,1205,1556,1969,2449,3001,3630,4341,5139,6029,7016,8105)),
            History(mutableListOf(1,10,32,79,176,372,755,1471,2747,4918,8458,14015,22450,34880,52725,77759,112165,158594,220228,300847,404900)),
            History(mutableListOf(1,14,38,77,139,250,486,1034,2304,5133,11149,23398,47380,92691,175527,322373,575275,999176,1691888,2797371,4523097)),
            History(mutableListOf(20,28,36,52,105,258,630,1438,3081,6308,12534,24388,46616,87602,162204,297698,547002,1017980,1935920,3770228,7478675)),
            History(mutableListOf(10,22,40,79,173,390,849,1739,3340,6046,10390,17071,26983,41246,61239,88635,125438,174022,237172,318127,420625)),
            History(mutableListOf(9,7,5,3,1,-1,-3,-5,-7,-9,-11,-13,-15,-17,-19,-21,-23,-25,-27,-29,-31)),
            History(mutableListOf(20,36,65,114,192,310,481,720,1044,1472,2025,2726,3600,4674,5977,7540,9396,11580,14129,17082,20480)),
            History(mutableListOf(2,7,32,83,168,300,509,871,1570,3037,6271,13559,30004,66606,146286,315606,667951,1390588,2862357,5858842,11983421)),
            History(mutableListOf(13,11,9,7,5,3,1,-1,-3,-5,-7,-9,-11,-13,-15,-17,-19,-21,-23,-25,-27)),
            History(mutableListOf(-5,8,42,104,201,340,528,772,1079,1456,1910,2448,3077,3804,4636,5580,6643,7832,9154,10616,12225)),
            History(mutableListOf(-1,12,48,114,223,403,713,1287,2446,4951,10532,22937,49926,106934,223651,455788,906491,1766792,3395490,6482590,12389323)),
            History(mutableListOf(2,7,12,17,22,27,32,37,42,47,52,57,62,67,72,77,82,87,92,97,102)),
            History(mutableListOf(7,32,78,164,320,596,1093,2039,3945,7903,16152,33166,67740,136897,272941,535665,1032611,1951386,3609370,6527704,11538200)),
            History(mutableListOf(11,29,62,110,173,251,344,452,575,713,866,1034,1217,1415,1628,1856,2099,2357,2630,2918,3221)),
            History(mutableListOf(7,22,59,129,251,466,868,1666,3291,6562,12925,24779,45903,81998,141358,235684,381055,599070,918175,1375189,2017043)),
            History(mutableListOf(-6,-13,-16,-4,43,157,383,785,1483,2792,5615,12367,28897,68207,157461,351360,758580,1596014,3302824,6782177,13910799)),
            History(mutableListOf(10,11,10,18,56,155,356,710,1278,2131,3350,5026,7260,10163,13856,18470,24146,31035,39298,49106,60640)),
            History(mutableListOf(21,30,33,26,5,-34,-86,-99,133,1221,4707,14079,36605,86471,189908,393244,775125,1464520,2666565,4698816,8041077)),
            History(mutableListOf(9,16,33,66,125,219,347,484,564,480,162,-139,853,6860,26535,77379,191600,425213,874428,1710845,3264406)),
            History(mutableListOf(9,14,21,40,91,204,431,881,1804,3777,8084,17433,37216,77593,156768,305924,576395,1049776,1851807,3171014,5283249)),
            History(mutableListOf(18,26,32,41,68,141,307,653,1378,3006,6932,16671,40484,96575,222942,495464,1060266,2190298,4381980,8516369,16121260)),
            History(mutableListOf(0,2,5,10,23,60,156,394,991,2511,6335,15635,37318,85787,189984,406125,839914,1683952,3278673,6208592,11450105)),
            History(mutableListOf(5,10,16,21,22,23,43,126,362,935,2221,4966,10581,21598,42338,79849,145179,255056,434054,717331,1154032)),
            History(mutableListOf(13,24,46,93,201,449,1003,2192,4625,9358,18120,33607,59853,102687,170285,273826,428261,653204,973954,1422657,2039617)),
            History(mutableListOf(10,18,33,71,154,309,566,967,1608,2759,5158,10669,23646,53570,119839,260007,543302,1091920,2113407,3948419,7139306)),
            History(mutableListOf(9,24,41,69,137,300,645,1297,2425,4248,7041,11141,16953,24956,35709,49857,68137,91384,120537,156645,200873)),
            History(mutableListOf(17,45,96,194,374,693,1271,2375,4564,8923,17441,33664,63942,120012,224569,423395,811583,1589381,3178722,6460606,13245914)),
            History(mutableListOf(10,7,7,10,19,57,195,604,1654,4105,9479,20784,43912,90308,181994,360861,705494,1360911,2589787,4858386,8977013)),
            History(mutableListOf(15,35,66,107,170,302,623,1387,3073,6513,13064,24831,44948,77924,130061,209951,329059,502399,749310,1094339,1568238)),
            History(mutableListOf(12,35,76,140,232,357,520,726,980,1287,1652,2080,2576,3145,3792,4522,5340,6251,7260,8372,9592)),
            History(mutableListOf(-5,-6,-7,-8,-9,-10,-11,-12,-13,-14,-15,-16,-17,-18,-19,-20,-21,-22,-23,-24,-25)),
            History(mutableListOf(19,30,46,82,159,298,522,878,1491,2662,5022,9754,18895,35730,65290,114966,195251,320622,510574,790818,1194655)),
            History(mutableListOf(3,17,51,111,203,333,507,731,1011,1353,1763,2247,2811,3461,4203,5043,5987,7041,8211,9503,10923)),
            History(mutableListOf(2,9,16,23,30,37,44,51,58,65,72,79,86,93,100,107,114,121,128,135,142)),
            History(mutableListOf(17,20,36,81,185,411,890,1885,3918,8026,16259,32620,64843,127860,250820,491663,967587,1918212,3834234,7713599,15559025)),
            History(mutableListOf(15,28,42,52,53,40,8,-48,-133,-252,-410,-612,-863,-1168,-1532,-1960,-2457,-3028,-3678,-4412,-5235)),
            History(mutableListOf(22,37,62,95,130,165,221,375,810,1885,4228,8855,17318,31885,55755,93311,150414,234741,356170,527215,763514)),
            History(mutableListOf(9,7,11,30,75,165,340,694,1466,3264,7546,17543,39882,87252,182553,365077,699391,1287725,2286813,3930292,6557933)),
            History(mutableListOf(7,1,4,32,107,257,524,988,1817,3370,6423,12677,25882,54243,115379,246168,521623,1089979,2236198,4494362,8842905)),
            History(mutableListOf(0,16,49,115,254,555,1187,2426,4658,8328,13798,21077,29409,36812,40026,36365,31564,63648,265539,1014847,3269232)),
            History(mutableListOf(22,30,38,46,54,62,70,78,86,94,102,110,118,126,134,142,150,158,166,174,182)),
            History(mutableListOf(-4,-11,-21,-37,-54,-47,41,311,920,2093,4135,7443,12518,19977,30565,45167,64820,90725,124259,166987,220674)),
            History(mutableListOf(14,27,62,132,252,431,673,1001,1518,2519,4668,9254,18540,36219,67991,122275,211070,350979,564410,880968,1339052)),
            History(mutableListOf(2,16,41,81,140,222,331,471,646,860,1117,1421,1776,2186,2655,3187,3786,4456,5201,6025,6932)),
            History(mutableListOf(18,21,32,71,175,412,910,1922,3975,8188,16897,34824,71248,144130,288182,570924,1124664,2209491,4335242,8493113,16586869)),
            History(mutableListOf(26,37,48,59,70,81,92,103,114,125,136,147,158,169,180,191,202,213,224,235,246)),
            History(mutableListOf(23,32,51,95,183,337,585,976,1617,2740,4801,8615,15580,28221,51745,98343,198274,426872,968084,2260757,5329836)),
            History(mutableListOf(28,49,74,101,128,153,174,189,196,193,178,149,104,41,-42,-147,-276,-431,-614,-827,-1072)),
            History(mutableListOf(16,38,85,182,377,747,1403,2502,4282,7158,11969,20578,37251,71667,145215,303799,643546,1362400,2864219,5969588,12341659)),
            History(mutableListOf(8,-2,-17,-24,6,119,374,845,1661,3145,6159,12848,28148,62758,138886,301109,636306,1309008,2621823,5114960,9724350)),
            History(mutableListOf(28,53,90,149,249,418,693,1120,1754,2659,3908,5583,7775,10584,14119,18498,23848,30305,38014,47129,57813)),
            History(mutableListOf(20,37,65,105,160,237,348,517,805,1373,2630,5588,12717,29951,71230,168459,392762,898725,2014163,4417311,9476534)),
            History(mutableListOf(11,22,43,86,182,401,876,1828,3596,6692,11926,20680,35453,60851,105257,183486,320809,558818,963701,1637602,2733856)),
            History(mutableListOf(11,19,23,23,19,11,-1,-17,-37,-61,-89,-121,-157,-197,-241,-289,-341,-397,-457,-521,-589)),
            History(mutableListOf(6,14,42,104,223,435,795,1398,2445,4408,8380,16751,34472,71443,147138,299695,603696,1204218,2380082,4657362,9006109)),
            History(mutableListOf(17,20,21,27,50,118,307,805,2027,4828,10926,23771,50306,104394,213168,428241,844637,1631524,3080403,5678395,10216738)),
            History(mutableListOf(15,43,91,174,322,591,1079,1947,3445,5943,9967,16240,25728,39691,59739,87893,126651,179059,248787,340210,458494)),
            History(mutableListOf(9,16,41,88,170,332,698,1555,3487,7572,15655,30710,57304,102176,174944,288953,462277,718888,1090005,1615636,2346326)),
            History(mutableListOf(15,22,29,36,43,50,57,64,71,78,85,92,99,106,113,120,127,134,141,148,155)),
            History(mutableListOf(20,30,45,69,116,226,495,1127,2510,5322,10703,20600,38524,71192,131951,247666,472200,912206,1775425,3459103,6707980)),
            History(mutableListOf(6,0,-5,7,62,196,455,895,1582,2592,4011,5935,8470,11732,15847,20951,27190,34720,43707,54327,66766)),
            History(mutableListOf(8,21,39,74,143,266,464,757,1162,1691,2349,3132,4025,5000,6014,7007,7900,8593,8963,8862,8115)),
            History(mutableListOf(21,33,57,113,235,475,923,1757,3340,6382,12183,22980,42472,76760,136325,240440,426799,771447,1427693,2698059,5161041)),
            History(mutableListOf(14,19,32,70,159,343,707,1414,2756,5219,9562,16910,28861,47607,76069,118046,178378,263123,379748,537334,746795)),
            History(mutableListOf(18,33,56,106,216,433,818,1446,2406,3801,5748,8378,11836,16281,21886,28838,37338,47601,59856,74346,91328)),
            History(mutableListOf(-6,-8,0,21,48,54,-23,-300,-974,-2352,-4886,-9213,-16200,-26994,-43077,-66326,-99078,-144200,-205164,-286127,-392016)),
            History(mutableListOf(-6,-15,-13,19,109,305,689,1391,2602,4594,7773,12812,20920,34282,56646,93955,154886,251271,398771,618957,945083)),
            History(mutableListOf(3,0,-7,-19,-37,-62,-95,-137,-189,-252,-327,-415,-517,-634,-767,-917,-1085,-1272,-1479,-1707,-1957)),
            History(mutableListOf(16,36,74,133,216,324,450,569,624,508,42,-1051,-3176,-6900,-12990,-22455,-36592,-57036,-85814,-125403,-178792)),
            History(mutableListOf(-6,-15,-33,-63,-94,-91,19,369,1180,2788,5658,10370,17587,28154,43941,71296,131984,293191,742271,1960167,5101618)),
            History(mutableListOf(21,33,45,57,69,81,93,105,117,129,141,153,165,177,189,201,213,225,237,249,261)),
            History(mutableListOf(14,13,26,69,161,329,630,1211,2435,5108,10849,22652,45696,88466,164255,293124,504404,839831,1357412,2136127,3281579)),
            History(mutableListOf(11,29,76,169,328,576,939,1446,2129,3023,4166,5599,7366,9514,12093,15156,18759,22961,27824,33413,39796)),
            History(mutableListOf(13,33,66,118,195,303,450,656,988,1658,3266,7372,17823,43806,106737,255320,597227,1363175,3031830,6565330,13840583)),
            History(mutableListOf(27,47,75,111,155,207,267,335,411,495,587,687,795,911,1035,1167,1307,1455,1611,1775,1947)),
            History(mutableListOf(7,7,10,25,73,201,501,1143,2443,5003,9994,19729,38825,76525,151197,298711,587391,1143631,2194146,4133305,7627177)),
            History(mutableListOf(26,47,89,180,362,691,1237,2084,3330,5087,7481,10652,14754,19955,26437,34396,44042,55599,69305,85412,104186)),
            History(mutableListOf(15,29,43,57,71,85,99,113,127,141,155,169,183,197,211,225,239,253,267,281,295)),
            History(mutableListOf(4,7,27,73,150,273,489,903,1712,3267,6207,11735,22123,41534,77256,141559,254885,452544,800588,1433858,2643097)),
            History(mutableListOf(0,-1,-4,-12,-29,-43,6,276,1102,3136,7619,16928,35682,72975,146885,293622,586261,1173510,2360483,4776809,9723002)),
            History(mutableListOf(2,7,21,48,97,182,322,541,868,1337,1987,2862,4011,5488,7352,9667,12502,15931,20033,24892,30597)),
            History(mutableListOf(9,8,18,64,186,437,874,1539,2439,3553,4912,6810,10229,17674,34989,75696,171554,397363,926147,2145261,4888461)),
            History(mutableListOf(11,22,37,62,118,249,538,1142,2365,4807,9658,19256,38113,74765,145090,278317,528158,994035,1862610,3490273,6567315)),
            History(mutableListOf(-4,2,18,53,128,282,592,1222,2516,5150,10358,20247,38216,69494,121812,206224,338092,538250,834362,1262489,1868880)),
            History(mutableListOf(8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28)),
            History(mutableListOf(7,6,7,15,43,122,319,766,1701,3521,6851,12646,22369,38331,64343,106919,177387,295416,494655,831409,1397551)),
            History(mutableListOf(23,41,75,138,264,523,1036,1990,3653,6389,10673,17106,26430,39543,57514,81598,113251,154145,206183,271514,352548)),
            History(mutableListOf(12,32,58,80,78,12,-197,-697,-1732,-3627,-6663,-10746,-14752,-15419,-5662,27787,107460,268918,565044,1070548,1886162)),
            History(mutableListOf(24,41,73,139,274,534,1001,1788,3044,4959,7769,11761,17278,24724,34569,47354,63696,84293,109929,141479,179914)),
            History(mutableListOf(6,17,24,20,-1,-46,-134,-314,-667,-1231,-1736,-978,4390,21898,66356,164136,358560,715961,1331440,2332560,3878145)),
            History(mutableListOf(12,30,62,127,258,502,920,1587,2592,4038,6042,8735,12262,16782,22468,29507,38100,48462,60822,75423,92522)),
            History(mutableListOf(-3,1,21,67,157,334,689,1390,2717,5103,9181,15837,26269,42052,65209,98288,144445,207533,292197,403975,549405)),
            History(mutableListOf(1,-3,2,19,53,124,287,660,1461,3055,6012,11177,19753,33398,54337,85490,130617,194481,283030,403599,565133)),
            History(mutableListOf(6,3,7,39,146,415,991,2113,4187,7915,14494,25889,45169,76875,127364,205043,320372,485475,713153,1015043,1398612)),
            History(mutableListOf(2,9,25,55,108,197,339,555,870,1313,1917,2719,3760,5085,6743,8787,11274,14265,17825,22023,26932)),
            History(mutableListOf(-3,7,38,108,248,509,972,1761,3059,5135,8430,13858,23698,43826,88589,190389,417182,903224,1903327,3893021,7765725)),
            History(mutableListOf(8,18,28,38,48,58,68,78,88,98,108,118,128,138,148,158,168,178,188,198,208)),
            History(mutableListOf(7,19,50,103,183,302,478,725,1037,1385,1792,2657,5700,16233,47968,132307,335111,785475,1723326,3576187,7080977)),
            History(mutableListOf(10,22,34,46,58,70,82,94,106,118,130,142,154,166,178,190,202,214,226,238,250)),
            History(mutableListOf(-6,1,26,79,184,392,800,1591,3116,6054,11731,22779,44506,87686,174065,346888,690470,1365723,2673336,5164099,9828340)),
            History(mutableListOf(17,15,4,-21,-66,-140,-258,-445,-741,-1207,-1932,-3041,-4704,-7146,-10658,-15609,-22459,-31773,-44236,-60669,-82046)),
            History(mutableListOf(25,53,105,196,355,642,1172,2155,3975,7363,13799,26460,52411,107482,226673,485470,1041993,2217981,4651117,9577412,19349919)),
            History(mutableListOf(15,42,84,141,213,300,402,519,651,798,960,1137,1329,1536,1758,1995,2247,2514,2796,3093,3405)),
            History(mutableListOf(-1,8,27,75,181,378,701,1193,1923,3021,4737,7534,12229,20201,33691,56226,93207,152710,246559,391741,612245)),
            History(mutableListOf(24,32,53,99,184,327,555,921,1568,2903,6009,13532,31461,72543,162711,353198,743593,1525994,3069160,6076243,11872847)),
            History(mutableListOf(1,3,15,52,152,391,898,1874,3629,6664,11853,20846,36952,67016,125232,240502,469937,922489,1798603,3454296,6501328)),
            History(mutableListOf(20,40,77,151,299,578,1066,1871,3176,5372,9361,17147,32875,64526,126530,243619,456308,828464,1457501,2487823,4128227)),
            History(mutableListOf(10,33,67,107,155,230,391,793,1797,4166,9422,20547,43446,90070,185027,379211,776916,1587691,3221601,6457483,12729197)),
            History(mutableListOf(9,23,50,101,199,391,772,1536,3083,6247,12777,26310,54231,111029,224039,442817,853837,1602735,2926964,5202475,9008911)),
            History(mutableListOf(15,24,33,51,116,318,829,1936,4078,7901,14366,24974,42209,70345,116816,194409,324609,542502,903727,1494061,2442322)),
            History(mutableListOf(10,3,-9,-26,-48,-75,-107,-144,-186,-233,-285,-342,-404,-471,-543,-620,-702,-789,-881,-978,-1080)),
            History(mutableListOf(9,9,7,9,36,145,471,1302,3199,7173,14931,29203,54162,95949,163315,268392,427605,662737,1002159,1482237,2148928)),
            History(mutableListOf(20,41,66,89,107,128,189,404,1068,2840,7026,16018,34097,69229,137443,273338,553975,1150078,2430029,5161750,10890518)),
            History(mutableListOf(25,43,61,76,97,164,374,917,2138,4663,9672,19493,38865,77541,155494,313059,630235,1262615,2506795,4916750,9506085)),
            History(mutableListOf(12,21,35,49,58,57,41,5,-56,-147,-273,-439,-650,-911,-1227,-1603,-2044,-2555,-3141,-3807,-4558)),
            History(mutableListOf(10,34,82,173,341,656,1271,2510,5012,9946,19312,36343,66023,115736,196061,321728,512750,795746,1205470,1786561,2595529)),
            History(mutableListOf(12,29,54,96,184,376,782,1615,3290,6598,13002,25166,47990,90772,171812,328143,635824,1251807,2499590,5042764,10231852)),
            History(mutableListOf(10,26,70,159,326,643,1257,2455,4785,9281,17883,34221,65059,122886,230412,428095,786308,1424372,2539452,4449259,7653644)),
            History(mutableListOf(9,36,86,175,332,611,1116,2050,3808,7144,13445,25139,46276,83446,147663,259113,459607,843736,1631696,3328771,7055192)),
            History(mutableListOf(7,21,38,58,81,107,136,168,203,241,282,326,373,423,476,532,591,653,718,786,857)),
            History(mutableListOf(1,-1,-3,-5,-7,-9,-11,-13,-15,-17,-19,-21,-23,-25,-27,-29,-31,-33,-35,-37,-39)),
            History(mutableListOf(8,25,61,139,298,602,1152,2102,3679,6205,10123,16053,24976,38823,62151,106441,200238,411493,894021,1979371,4350626)),
            History(mutableListOf(7,3,-1,-5,-9,-13,-17,-21,-25,-29,-33,-37,-41,-45,-49,-53,-57,-61,-65,-69,-73)),
            History(mutableListOf(13,21,34,57,103,204,424,869,1685,3022,4916,7006,7990,4837,-7716,-35323,-73975,-77405,129646,1056907,3997165)),
            History(mutableListOf(8,12,11,16,62,217,591,1345,2700,4946,8451,13670,21154,31559,45655,64335,88624,119688,158843,207564,267494)),
            History(mutableListOf(16,20,38,89,197,404,810,1648,3396,6929,13732,26242,48477,87259,154564,271855,477696,840530,1479258,2595207,4520253)),
            History(mutableListOf(12,32,56,83,109,124,117,110,253,1016,3506,9912,24034,51778,101393,183083,307442,481928,704308,951665,1163155)),
            History(mutableListOf(-4,0,9,31,96,266,644,1382,2688,4832,8151,13053,20020,29610,42458,59276,80852,108048,141797,183099,233016)),
            History(mutableListOf(15,25,45,83,148,267,520,1113,2523,5765,12863,27683,57444,115502,226428,434972,821199,1524957,2784357,4996787,8821668)),
            History(mutableListOf(2,-6,-3,39,165,441,958,1836,3228,5324,8355,12597,18375,26067,36108,48994,65286,85614,110681,141267,178233)))

    val sumNext = histories
            .map { it.computeDifference(); it.predictNext(); it.datasets.first().last() }
            .toList()
            .sum()
    println(sumNext)

    val sumPrevious = histories
            .map { it.computeDifference(); it.predictPrevious(); it.datasets.first().first() }
            .toList()
            .sum()
    println(sumPrevious)
}

main()


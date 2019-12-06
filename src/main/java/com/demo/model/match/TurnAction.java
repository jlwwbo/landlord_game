package com.demo.model.match;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: beibe
 * @date: 2019/12/4 20:52
 * 注意：该TurnAction不涉及癞子模式。
 */


public class TurnAction {
    // 总牌数量常量
    public static final int TOTAL_CARDS = 54;


    //出牌的时间，以timestamp形式，方便数据库记录
    public Timestamp actionTime;

    //出牌的种类
    public TurnType turnType;

    //出牌的参数，以level形式呈现
    public int bigParameter;
    public int smallParameter;


    /*
     * 传入出牌的数据
     * @param 以List<int>格式。int的值根据Poker.java的id参数决定。
     * @return 4个成员变量
     * https://www.cnblogs.com/zcscnn/p/7743507.html List类型的初始化
     */
    TurnAction(List<Integer> pokers) {

        this.actionTime = new Timestamp(System.currentTimeMillis());
        List<Poker> pokersEnum = new ArrayList<Poker>();
        for(int pokerInt : pokers) {
            Poker pokerEnum = pokerToEnum(pokerInt);
            if(pokerEnum == Poker.ILLGEAL) {
                this.turnType = turnType.ILLEGAL;
                return;
            }
            pokersEnum.add(pokerEnum);
        }
        this.turnType = isValidAction(pokersEnum, this.bigParameter,this.smallParameter);

    }

    /*
     * 判断出的手牌是否合法
     * @params 出的手牌，以List形式
     * @return TurnType，不合法返回TurnType的INVALID
     * @return big/small parameter，以改变函数params的形式体现
     */
    public static TurnType isValidAction(List<Poker> pokers, int bigParameter, int smallParameter) {

        // @do
        return TurnType.ILLEGAL;
    }

    /*
     * 将int形式的poker数据转为Enum形式
     * @params poker,int
     * @return poker,Poker
     * https://blog.csdn.net/Luck_ZZ/article/details/79996810
     */
    public static Poker pokerToEnum(int poker) {
        if(poker < 0 || poker >= TOTAL_CARDS){
            return Poker.ILLGEAL;
        }
        return Poker.values()[poker];
    }
}

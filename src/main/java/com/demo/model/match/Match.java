package com.demo.model.match;

import com.demo.model.Garment;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author: beibe
 * @date: 2019/12/4 14:02
 */

public class Match {
    // 以下是常量部分

    // 发牌常量
    public static final int PLAYER_NUMBER = 3;
    public static final int TOTAL_CARDS = 54;
    public static final int INITIAL_DIPOSIT = 17;
    public static final int ADDITIONAL_DIPOSIT = 3;
    public static final int POKER_MAXIMUM_NUMBER = INITIAL_DIPOSIT + ADDITIONAL_DIPOSIT;

    // 道具常量
    public static final int GARMENT_MAXIMUM_NUMBER = 10;

    // 常量部分结束



    // 以下是成员变量部分
    // PART ONE 会记录到数据库中的部分

    // 房间ID号
    // 查询做ID的方法 @do 需要非常的长，以便于存储
    // 我们认为好友组队，每一局都有一个不同的matchID
    public int matchID;

    // 每个玩家的用户ID
    public int[] playersID;

    // 每个玩家的道具信息
    public Garment[][] playersProps;

    // 每个玩家最开始的牌（包括抢地主得到的牌）
    public Poker[][] cardsTotal;

    // 底牌信息
    public Poker[] hiddenPokers;

    // 记录出牌动作的数组
    public List<TurnAction> actions;

    // 地主在players中的index，默认为-1，即没有选定
    public int lordIndex = -1;

    // 牌局开始的时间
    public Timestamp startTime;

    // PART ONE END



    // PART TWO 不会记录到数据库的部分

    // 当前每个玩家剩余的牌
    public Poker[][] cardsNow;

    // 现在轮到谁发牌了
    public int whoseTurnNow = -1;

    // 是否是不洗牌模式
    public boolean isNoShuffleMode;

    // 是否是明牌模式
    public boolean isClearCardMode;
    // PART TWO END
    // 成员变量部分结束



    // 以下是函数部分

    /*
     * 构造函数
     * 自动生成房间ID
     * 初始化数组变量
     * @paras 用户ID，以List形式
     */
    Match(int[] playersID, boolean isNoShuffleMode, boolean isClearCardMode) {
        if(playersID.length != PLAYER_NUMBER) {
            this.matchID = -1;
            return;
        }

        this.playersID = playersID;
        this.isNoShuffleMode = isNoShuffleMode;
        this.isClearCardMode = isClearCardMode;
        this.playersID = new int[PLAYER_NUMBER];
        this.playersProps = new Garment[PLAYER_NUMBER][GARMENT_MAXIMUM_NUMBER];
        this.cardsTotal = new Poker[PLAYER_NUMBER][POKER_MAXIMUM_NUMBER];
        this.hiddenPokers = new Poker[ADDITIONAL_DIPOSIT];
        this.cardsNow = new Poker[PLAYER_NUMBER][POKER_MAXIMUM_NUMBER];
        this.startTime = new Timestamp(System.currentTimeMillis());

        // 以下是房间ID的生成函数 @do

    }

    /*
     * 发牌函数，作为抢地主过程的一部分
     * 需要判断洗牌还是非洗牌两种
     * @return 返回在players的pokers和hiddenPokers中
     */
    public void DealCards() {

        if(isNoShuffleMode) {
            DealCardsNoShuffle();
        } else {
            DealCardsNormal();
        }

    }

    /*
     * 不洗牌发牌
     */
    public void DealCardsNoShuffle() {

    }

    /*
     * 正常模式(洗牌)发牌
     */
    public void DealCardsNormal() {

    }

    /*
     * 抢地主函数
     * @return 成功返回lordIndex，失败返回-1
     * 这块重点写，因为涉及到交互
     */
    public int ChooseLord() {

        // @do
        return 0;
    }

    /*
     * 一个测试函数，用来检查发牌常量是否合法
     * 标准1：总牌量是一套牌的倍数
     * 标准2：每个人拿到的牌*人数+未发的牌=总牌量
     * @return 不符合标准throw exception
     */
    public void isConstantLegal() {
        final int oneSet = 54;

        // @do

    }

    // 函数部分结束
}

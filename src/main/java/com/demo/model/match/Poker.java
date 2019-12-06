package com.demo.model.match;

/**
 * @author: beibe
 * @date: 2019/12/4 14:19
 */

public enum Poker {

    DIAMOND_3(0, 3),
    DIAMOND_4(1, 4),
    DIAMOND_5(2, 5),
    DIAMOND_6(3, 6),
    DIAMOND_7(4, 7),
    DIAMOND_8(5, 8),
    DIAMOND_9(6, 9),
    DIAMOND_10(7, 10),
    DIAMOND_J(8, 11),
    DIAMOND_Q(9, 12),
    DIAMOND_K(10, 13),
    DIAMOND_A(11, 14),
    DIAMOND_2(12, 15),

    CLUB_3(13, 3),
    CLUB_4(14, 4),
    CLUB_5(15, 5),
    CLUB_6(16, 6),
    CLUB_7(17, 7),
    CLUB_8(18, 8),
    CLUB_9(19, 9),
    CLUB_10(20, 10),
    CLUB_J(21, 11),
    CLUB_Q(22, 12),
    CLUB_K(23, 13),
    CLUB_A(24, 14),
    CLUB_2(25, 15),

    SPADE_3(26, 3),
    SPADE_4(27, 4),
    SPADE_5(28, 5),
    SPADE_6(29, 6),
    SPADE_7(30, 7),
    SPADE_8(31, 8),
    SPADE_9(32, 9),
    SPADE_10(33, 10),
    SPADE_J(34, 11),
    SPADE_Q(35, 12),
    SPADE_K(36, 13),
    SPADE_A(37, 14),
    SPADE_2(38, 15),

    HEART_3(39, 3),
    HEART_4(40, 4),
    HEART_5(41, 5),
    HEART_6(42, 6),
    HEART_7(43, 7),
    HEART_8(44, 8),
    HEART_9(45, 9),
    HEART_10(46, 10),
    HEART_J(47, 11),
    HEART_Q(48, 12),
    HEART_K(49, 13),
    HEART_A(50, 14),
    HEART_2(51, 15),

    SMALL_KING(52, 16),
    BIG_KING(53, 17),

    ILLGEAL(54, -1)
    ;


    private int id;

    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    Poker(int id, int level) {
        this.id = id;
        this.level = level;
    }
}

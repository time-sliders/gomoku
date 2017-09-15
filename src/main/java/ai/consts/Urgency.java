package ai.consts;

/**
 * 紧急程度
 *
 * @author luyun
 * @version AI polishing
 * @since 2017.09.14
 */
public interface Urgency {

    /**
     * 无风险
     * 无论怎么走都不需要拦截
     * 1 o o o _ 1
     */
    int LEVEL_1 = 1;

    /**
     * 低风险
     * 再走2步必须拦截
     * 1 o o
     * _ o _
     */
    int LEVEL_2 = 2;

    /**
     * 中风险
     * 再走一步则必须拦截
     * 1 o o o
     * 1 o o _ o
     * _ o o _
     * 1 _ o o o _ 1
     */
    int LEVEL_3 = 3;

    /**
     * 高风险
     * 再不拦截就输了
     * o o o _ o
     * 1 o o o o
     * _ o o _ o _
     * _ o o o _
     */
    int LEVEL_4 = 4;

    /**
     * 致命,已经输了:
     * _ o o o o _
     */
    int LEVEL_5 = 5;
}

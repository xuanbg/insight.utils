package com.insight.utils;

/**
 * @author 宣炳刚
 * @date 2017年9月14日
 * @remark 雪花算法ID生成器
 */
public class SnowflakeCreator {

    /**
     * 起始的时间戳:2020-01-01 00:00:00
     */
    private final static long START_STMP = 1577808000000L;

    /**
     * 每一部分占用的位数
     */
    private final static long MACHINE_BIT = 6;
    private final static long SEQUENCE_BIT = 12;
    private final static long SUB_BIT = 4;

    /**
     * MAX_SEQUENCE = 4095
     */
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long SEQUENCE_LEFT = SUB_BIT;
    private final static long MACHINE_LEFT = SEQUENCE_LEFT + SEQUENCE_BIT;
    private final static long TIMESTMP_LEFT = MACHINE_LEFT + MACHINE_BIT;

    private final long machineId;
    private long sequence = 0;
    private long lastStmp = 0;

    /**
     * 初始化机器标识
     * 0 < machineId < MAX_MACHINE_NUM 63
     *
     * @param machineId 设备ID
     */
    public SnowflakeCreator(long machineId) {
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     *
     * @param subId 子ID, 0-15
     * @return ID
     */
    public synchronized Long nextId(long subId) {
        long currStmp = System.currentTimeMillis();
        if (currStmp > lastStmp) {
            // 时间更新,序列归零
            sequence = 0L;
        } else {
            // 时间回拨时,继续使用前一个时间戳
            currStmp = lastStmp;
        }

        sequence++;
        if (sequence > MAX_SEQUENCE) {
            // 序列溢出时,借用下一个时间戳
            sequence = 0L;
            currStmp++;
        }

        // 上次时间戳更新为当前时间戳的值
        lastStmp = currStmp;

        // 返回ID:|0|timestmp(41)|machine(6)|sequence(12)|sub_id(4)|
        return (currStmp - START_STMP) << TIMESTMP_LEFT | machineId << MACHINE_LEFT | sequence << SEQUENCE_LEFT | subId;
    }
}

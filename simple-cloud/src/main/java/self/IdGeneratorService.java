package self;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 分布式uuid
 */
@Service
public class IdGeneratorService {

    private static final Logger LOG = LoggerFactory.getLogger(IdGeneratorService.class);

    // 机器标识位数
    private final static long workerIdBits = 10L;
    // 毫秒内自增位数
    private final static long sequenceBits = 12L;
    // 机器ID偏左移12位
    private final static long workerIdShift = sequenceBits;
    // 时间毫秒左移22位
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    //sequence掩码，确保sequnce不会超出上限
    private final static long sequenceMask = ~(-1L << sequenceBits);
    //上次时间戳
    private AtomicLong lastTimestamp = new AtomicLong(-1);
    //自增原子序列
    private AtomicLong sequence = new AtomicLong(0L);
    //服务器ID
    private long workerId;
    //服务名
    private String serviceName;
    //redis机器标识key
    private static String machineCacheKey;

    RedisTemplate<String, Object> redisTemplate;

    private void init() {
        machineCacheKey = serviceName + "_idGenerator_machineId";
        //获取机器编号
        this.workerId = this.getMachineNum();
    }

    public Long generateId() {
        long result = -1L;
        while (result == -1) {
            result = this.getNextId();
        }
        return result;
    }

    /**
     * 获取分布式自增id
     *
     * @return id
     */
    private long getNextId() {
        //获取时间戳
        long timestamp = this.getTimeMillis();
        long sequenceValue = sequence.get();
        long lastTime = lastTimestamp.get();
        boolean needResetSequence = false;

        //如果时间戳与上次时间戳相同
        if (timestamp == lastTime) {
            if (sequence.get() >= 4095) {
                return -1;
            }
            //当前毫秒内，则+1
            sequenceValue = sequence.incrementAndGet();
        } else {
            //需要重置sequence
            needResetSequence = true;
            //时间戳与上一次不同，更新时间戳
            if (!lastTimestamp.compareAndSet(lastTime, timestamp)) {
                //更新失败，重新进行查询
                return -1;
            }
        }

        //重置sequence
        if (needResetSequence) {
            //时间戳与上次不同，将自增序列重置为0
            if (sequence.compareAndSet(sequenceValue, 0)) {
                sequenceValue = 0;
            } else {
                //重置失败，重新进行查询
                return -1;
            }
        }
        // ID偏移组合生成最终的ID，并返回ID
        return (timestamp << timestampLeftShift) | (workerId << workerIdShift) | (sequenceValue & sequenceMask);
    }

    private long getTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * redis获取唯一编号
     *
     * @return redis分配机器id
     */
    private long getMachineNum() {
        if (!this.isKeyExist(machineCacheKey)) {
            Random ra = new Random();
            int value = ra.nextInt(1024);
            this.set(machineCacheKey, value);
        }
        return this.incr(machineCacheKey) % 1024;
    }

    /**
     * redis increment
     *
     * @param key redisKey
     * @return 自增后的结果
     */
    private Long incr(String key) {
        return redisTemplate.opsForValue().increment(key, 1);
    }

    /**
     * 判断key是否存在
     *
     * @param key redisKey
     * @return 是否存在
     */
    private Boolean isKeyExist(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * redis赋值
     *
     * @param key   redisKey
     * @param value redisValue
     * @return 是否赋值成功
     */
    private Boolean set(String key, Integer value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

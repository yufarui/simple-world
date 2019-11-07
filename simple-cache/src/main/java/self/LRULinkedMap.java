package self;

import java.util.*;

/**
 * 依赖 linkedHashMap 实现 lrucache
 * 目标:
 * 1.实现一个 LRU 缓存，当缓存数据达到 X 之后需要淘汰掉最近最少使用的数据。
 * 2.Y 小时之内没有被访问的数据也需要淘汰掉。
 */
public class LRULinkedMap<K, V> {

    private static final int X = 16;
    // 设定为 2 秒钟
    private static final int Y = 2;

    private int capacity;
    private LinkedHashMap<K, Value<V>> lrucache;

    public LRULinkedMap(int initialCapacity) {

        this.capacity = initialCapacity;

        lrucache = new LinkedHashMap<K, Value<V>>(initialCapacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, Value<V>> eldest) {
                return super.size() >= capacity;
            }
        };

        Thread daemonThread = createDaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();
    }

    public void put(K key, V v) {
        Value<V> vValue = new Value<>(v);
        lrucache.put(key, vValue);
    }

    public V get(K key) {
        Value<V> vValue = lrucache.get(key);
        return vValue == null ? null : vValue.getV();
    }

    public Thread createDaemonThread() {
        Thread daemon = new Thread(() -> {
            while (true) {

                Date date = new Date();
                if (lrucache == null || lrucache.size() == 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                    continue;
                }

                System.out.println(new Date() + "嗨,我开始了哦");
                // 思考点:为什么 不使用 keySet ,因为 getValue也会修改 modCount
                Set<Map.Entry<K, Value<V>>> entries = lrucache.entrySet();

                Iterator<Map.Entry<K, Value<V>>> it = entries.iterator();
                boolean needRemove = false;
                while (it.hasNext()) {
                    Map.Entry<K, Value<V>> next = it.next();
                    needRemove = needRemove ? true : needRemove(next.getValue(), date.getTime());
                    if (needRemove) {
                        System.out.println("[remove]" + new Date() + next);
                        it.remove();
                    }
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }

        });
        return daemon;
    }

    private boolean needRemove(Value<V> vValue, long currentTime) {
        if (vValue == null || vValue.getDate() == null) {
            return true;
        }
        long cacheTime = vValue.getDate().getTime();

        return (currentTime - cacheTime) / 1000 >= Y;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(new Date());

        LRULinkedMap<String, Integer> map = new LRULinkedMap<>(5);
        map.put("1", 1);
        map.put("2", 2);

        map.put("3", 3);
        Thread.sleep(1000);
        System.out.println(map.lrucache.size());

        map.put("4", 4);
        Thread.sleep(1000);
        System.out.println(map.lrucache.size());


        Thread.sleep(1000);
        System.out.println(map.lrucache.size());
        Thread.sleep(1000);
        System.out.println(map.lrucache.size());
        map.put("1", 1);
        Thread.sleep(3000);

        System.out.println(map.lrucache.size());
    }
}

package guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class GuavaCacheDemo1 {
    static Cache<String, Object> testCache = CacheBuilder.newBuilder()
            .weakKeys()
            .recordStats()
            .build();

    public static void main(String[] args){
        Object obj1 = new Object();

        testCache.put("1234",obj1);

        obj1 = "123";
        testCache.invalidate("1234");
        System.gc();

        System.out.println(testCache.getIfPresent("1234"));

        System.out.println(testCache.stats());

    }
}

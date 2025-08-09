package ragwithpdf;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class VectorStoreRepository {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void cleanUpStore(){
        Set<String> keys = redisTemplate.keys("redis*");
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
            System.out.println("Deleted " + keys.size() + " vector keys from Redis.");
        } else {
            System.out.println("No vector keys found to delete.");
        }
    }


}

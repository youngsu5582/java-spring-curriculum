package util;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.testcontainers.shaded.com.google.common.base.CaseFormat;

import java.util.List;

/**
 * DB 초기화 코드
 * 참고 링크 : https://www.youtube.com/watch?v=ITVpmjM4mUE
 */
@Service
@ActiveProfiles("test")
public class DatabaseCleanUp implements InitializingBean {
    @PersistenceContext
    private EntityManager entityManager;

    private List<String> tableNames;

    @Override
    public void afterPropertiesSet() {
        try {
            System.out.println(entityManager.getMetamodel());
            System.out.println(entityManager.getMetamodel().getEntities());
            tableNames = entityManager.getMetamodel().getEntities().stream()
                    .filter(e -> e.getJavaType().getAnnotation(Entity.class) != null)
                    .map(e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()))
                    .toList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        /**
         * DB 를 일관적으로 Clear 하는 코드
         * /h2 -> mysql -> postgresql 순으로 주석처리 되어있다.
         * 
         */
//            entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        //entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0;").executeUpdate();
        entityManager.createNativeQuery("SET CONSTRAINTS ALL DEFERRED;").executeUpdate();

        for (String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();

            //entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
            //entityManager.createNativeQuery("ALTER TABLE " + tableName+" AUTO_INCREMENT = 1").executeUpdate();
            entityManager.createNativeQuery("ALTER SEQUENCE " + tableName + "_seq RESTART WITH 1;").executeUpdate();
        }
//            entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
//            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1;").executeUpdate();
        entityManager.createNativeQuery("SET CONSTRAINTS ALL IMMEDIATE;").executeUpdate();
    }
}

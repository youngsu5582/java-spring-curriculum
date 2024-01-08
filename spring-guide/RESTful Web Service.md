# RESTful Web Service

## Guide Link

https://spring.io/guides/gs/rest-service/

### Test

- 여기서 테스트는 , E2E 테스트
```java
@SpringBootTest  
@AutoConfigureMockMvc  
class RestServiceApplicationTests {  
    @Autowired  
    MockMvc mockMvc;  
  
    @Test  
    void getGreeting() throws Exception {  
        mockMvc.perform(get("/greeting").param("name", "Spring"))  
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$.name").value("Hello , Spring!"));  
    }  
}
```

#### AutoConfigureMvc

- MockMvc Instance 를 자동으로 구성해주는 어노테이션
=> MockMvc 를 자동으로 Annotation 해줌
#### MockMVC

- Web Application 을 실제 구동하지 않고 , 테스트 할 수 있는 Utiliy Class
- 각 Test Class 당 하나의 MockMVC Instance 를 만들어 주입되는 방법
##### Why?

싱글톤 패턴으로 만들어 , 하나의 Instacne 가 모든걸 수행하면 안되냐고 생각할 수 있다.
-> 이는 매우 비효율 적 + 위험
1. 테스트 격리 : 각 테스트는 독립적 실행 ( 다른 테스트와 상태 공유 X )
2. Context Caching : 같은 설정 가진 테스트 클래스들은 재사용 이 가능하게 해줌
	-> 강제로 , Singleton 패턴을 고수할 필요 X ( 해당 클래스 생성 비용이 최소화 되므로 )
3. 병렬 테스트 실행 : 병렬로 실행 시 , 자신만의 Instance 를 가지는게 필수!
4. 테스트 스프링 설정 : 특정 테스트에는 추가적인 요소가 필요할 수 있음
	-> 각기 다른 설정으로 Instance 생성하는 것이 매우 유용


### Spring 구동 두가지

./gradlew bootRun ( 80%가 정상 작동! )
./gradlew build -> java -jar build/libs/rest-service-0.0.1-SNAPSHOT.jar
( Versioning 지정 하지 않는 이상 , 계속 0.0.1 인듯 )


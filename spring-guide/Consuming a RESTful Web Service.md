
# Consuming a RESTful Web Service

## Guide Link

https://spring.io/guides/gs/consuming-rest/

### ApplicationRunner

- Spring Boot Application 시작 지점에서 실행되는 코드를 정의하는 Interface
	( 비슷한 기능으로 CommandLineRunner  존재 )

- 초기화 및 준비 작업 수행하는데 유용

```java
@Component  
public class QuoteRunner implements ApplicationRunner {  
  
    @Autowired  
    RestTemplate restTemplate;  
  
    private static final Logger logger = LoggerFactory.getLogger(QuoteRunner.class);  
  
  
    @Override  
    public void run(ApplicationArguments args) throws Exception {  
        Quote quote = restTemplate.getForObject("http://localhost:8080/api/random", Quote.class);  
        logger.info("quote {}", quote);  
    }  
  
}
```

이렇게 ApplicationRunner 를 implement 하고 run 구현시 자동으로 실행해준다 ( 당연히 , @Component 는 설정해야함! )

### RestTemplate

- Spring 에서 제공하는 HTTP 통신 위한 Template Class
- RESTful Service 와 통신 쉽게 하게 도와줌
- getForObject , postForEntity , exchange 등등 다양한 메소드 사용 가능
- Header , Default Authentication , Custom Interceptor 등등 추가 가능

### Live Template

Intellij - Setting - Live Templates 에서 추가 가능

```java
private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger($CLASSNAME$.class);  
$END$
```

우측 Edit Variables 에서 선택해야함
![450](https://i.imgur.com/9toTxK4.png)

CLASSNAME - className() 으로 Mapping

- 이렇게 , 자신이 자주 사용하는 Command 추가 가능

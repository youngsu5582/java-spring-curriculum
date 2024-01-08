# Scheduling Tasks

## Guide Link

https://spring.io/guides/gs/scheduling-tasks/

### SpyBean

- SpringBoot 의 테스트 기능 중 하나
- Spring Context 에 있는 Bean 을 Mockito 의 Spy 로 감싸 테스트 가능하게 해줌

=> 동작을 그대로 유지하며 , 특정 메소드 호출 추적하거나 , 결과 조작할때 사용

( 향로의 의견에 따르면 이렇게 특정 메소드 호출을 추적하여 테스팅 하는 것은 바람직하지 않음! )

### Component

- Class 가 Spring Container 에 의해 관리되는 Component 임을 알려주는 어노테이션
	= Nest 의 Injectable
- Spring 의 Component Scanning 에 의해 자동으로 검색되어 빈으로 자동 등록

### Scheudled

- 특정 메소드 일정 주기 실행하도록 스케줄링
- cron 표현식 통한 스케줄링 , 고정 지연 시간 ( fixedDelay ) , 고정 주기 ( fixedRate ) 등으로 지원
- 기본적으로 비동기적 실행 X ( 여러 작업 실행하려면 , 직접 비동기적 실행을 구현해줘야 함 )

- SpringBootApplication 에 @EnableScheduling 어노테이션을 붙혀줘야 한다



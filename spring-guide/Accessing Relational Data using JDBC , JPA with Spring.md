# Accessing Relational Data using JDBC , JPA with Spring

## Guide Link

https://spring.io/guides/gs/relational-data-access/
https://spring.io/guides/gs/accessing-data-jpa/

### JDBC

- Java Database Connectivity
- Java Application 에서 DB에 접근할 수 있게 해주는 API
- 매우 원시적이며 , connection - close 작업을 반복해야하므로 비효율적
### JDBCTemplate

- JDBC 를 더욱 쉽게 접근하게 도와줌
- DataSource 통해 연결 관리하므로 connection - close 필요 X
- 자동으로 리소스 정리

=> 이 역시도 , JDBC 자체가 이제 안쓰이는 추세


### JPA

- Java Persistance API
- 위의 JDBC 들을 대체하여 사용하는 주력 ORM ( Object Relation Mapping ) 프레임워크

- 인터페이스 이므로 , Hibernate or OpenJPA 등이 JPA 를 구현

메소드 명을 기반으로 , SQL Query 문 변환

findOneById -> find , One , Where Id 와 같은 형식으로 변환

매우 무궁무진한 사용성을 가짐
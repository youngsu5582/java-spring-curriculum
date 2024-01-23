### find()

EntityManager 에서 조회 연산 수행
첫 번째 파라미터 : 엔티티 타입 지정
두 번째 파라미터 : 식별자 전달 ( Primary Key )
-> @id 어노테이션 정의된 필드 사용

```java
UserEntity userEntity = entityManager.find(UserEntity.class,email);
```

엔티티를 조회했을 때 식별자에 
매핑되는 엔티티 존재하면 엔티티 객체 리턴
매핑되는 엔티티가 없으면 null 리턴

### getReference()

find 메소드와 마찬가지로 , EntityManager 에서 조회 연산 수행
DB 조회를 즉시 수행하지 않고 , Proxy 인스턴스 반환
( 실제 Entity Class 를 상속받은 객체 )

인스턴스의 속성에 접근하는 순간 ( 실제 데이터가 필요한 순간 ) JPA가 DB에 쿼리 보내 해당 데이터 로드
-> Lazy Loading

EntityManager 세션 유지되는 상황에서 사용해야 객체를 세션 밖에서도 사용 가능 ( 당연 - 실행 당시에 연결을 해야하므로 )
-> close 이후에는 사용 불가능
매핑되는 엔티티가 없으면 EntityNotFoundException 발생

그리고 , 두개 크게 성능 부하 차이 X
=> 사실상 find 를 사용하자

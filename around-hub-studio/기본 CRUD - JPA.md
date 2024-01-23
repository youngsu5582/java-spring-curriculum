모든 맥락은 앞서 말한것처럼
1. EntityManager 생성 ( EntityManagerFactory 통해 생성 )
2. EntityManager 가 가지고 있는 트랜잭션 시작
3. EntityManager 통해 영속 컨텍스트에 접근하고 객체 작업
4. 트랜잭션 커밋해 DB 반영
5. EntityManager 종료
해당 맥락에서 작동한다.
### 데이터 추가
1. EntityManager 생성
2. EntityTransaction 시작
3. 저장하고자 하는 Entity 생성
4. EntityManager.persist() 메소드 통해 , 영속성 컨텍스트에 Entity 객체 저장
5. EntityTransaction.commit() 메소드 통해 , DB에 실제 반영
( 3~5 단계에서 예외 발생 시 transaction.rollback() )
6. EntityManager.close() 종료
```java
private void ancientSaveUser(Customer customer // 3){
	EntityManagerFactory factory = Persistence.createEntityManagerFactory(  
	        "testdb"  
	);  
	EntityManager manager = factory.createEntityManager();  // 1
    entityManager.getTransaction().begin();  // 2
    try{  
        Customer findCustomer = entityManager.find(Customer.class,customer.getLastName());  // 중복 검증
        if(findCustomer != null){  
            throw new Error();  
        }  
        entityManager.persist(customer);  // 4
        entityManager.getTransaction().commit();  // 5
    }catch (Exception e){  
        entityManager.getTransaction().rollback();  
    }finally {  
        entityManager.close();  // 6
    }  
}
```
### 데이터 조회 - 단일 조회
단일 조회 경우 , 트랜잭션 필요 X
1. Entity Manager 생성
2. EntityManager.find() 메소드 활용해 Entity 조회
3. EntityManager.close() 종료
4. 조회 성공 ( 조회된 값이 없다면 Null )
( find 의 두번째 인자는 무조건 Primary key 값을 넣어줘야 한다. )
```java
Customer customer = entityManager.find(Customer.class,id);  
return Optional.ofNullable(customer);
```
### 데이터 변경
1. EntityManager 생성
2. EntityTransaction 시작
3. 변경하고자 하는 Entity 조회
4. 조회된 Entity 객체에서 값 변경
5. EntityTransaction.commit() 메소드 통해 변경 감지 & DB에 실제 반영 ( Dirty Check 단 )
( 3~5 단계에서 예외 발생 시 transaction.rollback() )
6. EntityManager.close() 종료
### 데이터 삭제
1. EntityManager 생성
2. EntityTransaction 시작
3. 삭제하고자 하는 Entity 조회
4. EntityManager.remove() 메소드 통해 영속성 컨텍스트에서 영속 객체 삭제
5. EntityTransaction.commit() 메소드 통해 변경 감지 & DB에 실제 반영 ( Dirty Check 단 )
( 3~5 단계에서 예외 발생 시 transaction.rollback() )
6. EntityManager.close() 종료

### TypedQuery

작성한 JPQL 쿼리를 실행시키기 위한 객체

#### JPQL

Java Persistence Query Language
SQL 과 유사하나 , DB 테이블 대신 엔티티 객체 & 필드 대상 쿼리 작성
-> 객체 지향적 방식으로 DB 작업하게 도와줌

1. 객체 중심 : 테이블이 아닌 엔티티 클래스 & 속성 기반 쿼리 작성
2. 데이터베이스 독립성 : JPA 구현체에 의해 특정 DB SQL 로 변환
3. 표준화 구문 : JPQL 은 JPA 일부로 표준화 , 다양한 JPA 구현체에서 지원

```java
TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.firstName = :name",Customer.class);
```

타입이 명확하면 TypedQuery<`T`> = createQuery(쿼리문,T.class);
불명확하면 Query = createQuery(쿼리문);

-> 왠만하면 무조건 TypedQuery 를 써야할듯
```java
List<Customer> c = query.getResultList();
```
- 결과값 리턴


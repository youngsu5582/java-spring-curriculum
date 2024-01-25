### H2 DB

1.5M 정도로 매우 가벼움
테스트 용


### Dialect

-> DB 에 맞는 방언으로 선택

각각 DB 마다 제공 SQL 문법 & 함수가 달라서 선택을 해줘야 함

### 주의

엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유

엔티티 매니저는 쓰레드간 공유 X

=> 이들은 어차피 Spring Application이 관리해줌

JPA 데이터 모든 변경은 트랜잭션 안에서 실행

### DB 스키마 자동 생성

DDL 을 애플리케이션 실행 시점 자동 생성

테이블 중심 -> 객체 중심
( DB 방언 활용해 적절한 DDL 생성 )

- 무조건 DDL 은 개발 장비에서만 사용
( 운영서버에서 날리면 난리남 , 적절히 다듬은 후 사용 )

#### 자동 생성 Option

- create : 기존 테이블 삭제 후 다시 생성 ( DROP + CREATE )
- create-drop : create 와 같으나 , 종료 시점 테이블 DROP
- update : 변경분만 반영 ( 운영 DB 에서는 절대 사용 불가능 - alter 문이 가볍지가 않음 lock , io 남용 )
- validate : 엔티티 & 테이블 정상 매핑됐는지만 확인
- none : 사용 X 

=> 상황과 환경에 맞게 선택을 해야 한다

운영 장비에서는 절대 create , create-drop , update 사용 하면 안된다

- 개발 초기 단계 : create , update
- 테스트 서버 : update , validate
- 스테이징 & 운영 서버 : validate , none

### 매핑 어노테이션

철저히 , DB에 어떻게 매핑될지에 대해서 다룸
Java 단에서 영향 X
#### Column

- DB 에 Column 명을 바꾸고 싶을때 사용
```java
@Column("userName")
String name;
```
- insertable , updateable : 읽기 전용
	( false 일 시 , 이 값이 insert / update 할 떄 안들어감 )
- nullable : null 허용 여부 , DDL 단위에서 사용
- unique : 유니크 제약 조건 , DDL 단위에서 사용
- columnDefinition : DB에 직접 컬럼 정의 - SQL 문
- , length , precision , scale 

name -> userName 으로 매핑

#### Temporal

날짜 타입 매핑
```java
// 다음 필드는 날짜만을 저장합니다.
@Temporal(TemporalType.DATE) 
private Date dateField; 
// 다음 필드는 시간과 날짜를 모두 저장합니다. 
@Temporal(TemporalType.TIMESTAMP) 
private Date timestampField;
```
=> Java 8 이후에는 사용하지 않는다

```java
// 다음 필드는 날짜만을 저장합니다. 
@Column(columnDefinition = "DATE") 
private LocalDate dateField; 
// 다음 필드는 시간과 날짜를 모두 저장합니다. 
@Column(columnDefinition = "TIMESTAMP") 
private LocalDateTime timestampField;
```

java.time 패키지에 새로운 API 도입으로 타입 지정후 사용 가능
#### Enumerated

Enum 을 사용 가능하게 해줌
```java
@Enumerated(EnumType.STRING)
MemberType memberType;

public enum MemberType{
	USER,ADMIN
}
// ❌ 절대 하면 안되는 습관
```

- 현업에서는 무조건 String 으로 사용해야함

Enum 을 단순 정수인 값만 넣으면 절대 안된다 ( 추가되면서 , 값이 다 꼬일수 있다 )
=> EnumType 의 기본값은 Ordinal ( 순서 저장 ) 이나 절대 쓰지말자

#### Lob

CLOB , BLOB 매핑

- CLOB : String , char[] , java.sql.CLOB
- BLOB : byte[] , java.sql.BLOB

```java
@Lob
private String lobString;
@Lob
private byte[] lobByte;
```

#### Transient

해당 필드는 매핑 X
애플리케이션에서 DB 저장하지 않는 필드

### 식별자 매핑 방법

#### IDENTITY

- DB에 위임
- MySQL 추천
#### SEQUENCE

- DB 시퀸스 오브젝트 사용
- ORACLE 추천
( @SequenceGenerator 필요 )
#### TABLE

- 키 생성용 테이블 사용
- 모든 DB에서 사용 가능
( @TableGenerator 필요 )
#### AUTO

- 방언 따라 자동 지정 ( 세 가지 중 자동 지정 )
- 기본값

### 권장 식별자 전략

- 기본 키 제약 조건 : null X , 유일 , 변하지 않는다
- 미래까지 이 조건 만족 자연키는 찾기 어려움
	-> 대체키를 사용 ( 현실적인 정보는 변할 가능성이 존재하므로 ) 
- 주민등록번호 같은 개인 키도 기본 키로 적절하지 않다
- 키 생성 전략을 사용하자 ( DB 마다 성능 최적화를 찾아봐야 한다 )

=> Long + 대체 키 + 키 생성전략 사용
### VO

Value Object

> I often find it's useful to represent things as a compound.
> 
> ( 2d coordinate of x and y value , amount of money consist of a number )

한 개 or 그 이상 속성들을 묶어서 특정 값을 나타내는 객체

#### 조건

##### 1. equals & hashCode 메소드 재정의

타입 , 내부 속성값이 같다면 당연히 같은 객체 취급

( x,y 좌표가 2,2 인 Point 클래스가 두개 있으면 당연히 같은 클래스 취급! )

Intellij 제공 자동 생성 기능 사용하자

( hashCode - 재정의 X 시 메모리 주소값 사용 hash값 , 객체 비교 용도로 사용 )
hashCode 로 먼저 비교후 -> equals 로 비교

##### 2. 수정자 ( setter ) 없는 불변 객체

속성 값 자체가 식별자
-> 값이 바뀌면 다른 값이 되어서 추적 불가능

```java
Order 첫번째_주문 = new Order()
첫번째_주문.setFood("떢볶이");
첫번째_주문.setQuantity(2);

Order 두번째_주문 = 첫번째_주문;
두번째_주문.setFood("마라떢볶이");
```

수정자가 있을 시 복사될 때 의도치 않은 객체들이 함께 변경되는 문제 유발
( 같이 변경될 가능성 존재 )

=> 반드시 값 변경할 수 없는 불변 객체로 만들어야 함 ( 즉 , 생성자 통해 한 번만 할당 )

#### VO 의 이점

- 객체 생성될 때 해당 객체 안 제약사항 ( validate ... ) 추가 가능

- 생성될 인스턴스가 정해져 있거나 적은 경우 미리 생성 후 캐싱 가능

- Entity 가 지나지체 거대해지는 것 방지 가능

### DTO

Data Transfer Object

계층 간 데이터 전달하기 위한 객체

- getter / setter 메소드만 가짐 ( 다른 로직을 가지지 않는다 )
-> DTO 역시도 불변 객체가 BEST! ( 불변을 보장하므로 )

- View 에서 사용 ( Entity 는 절대 View 에서 사용 X - View 는 자주 변경되므로 )

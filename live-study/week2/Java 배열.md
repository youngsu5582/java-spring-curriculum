### 자바 배열

배열은 원시타입이나 , 참조 타입으로 구성된 특별 객체
배열의 타입에 의해 담기는 값 결정

- 사이즈는 0부터 시작
- 들어가는 데이터로 사이즈 결정 ( 생성 시 , 명시적 사이즈 결정도 가능 )
- 배열 안의 배열도 가능

Class 와 마찬가지로 , 참조 타입 + 객체
```java
int [] ary;
String[] stringAry;
```

이렇게 선언 가능!

### Array[] VS List<>

#### Array[]

- 고정 크기 : 생성 시 그 크기 결정 , 생성된 배열 크기 변경 불가능
- 타입 제한 : 한 타입의 요소만 저장 가능
- 효율적 메모리 사용 : 연속적 메모리 공간 사용 , 인덱스 통해 요소 빠르게 접근 가능
- 단순한 구조 : 배열은 간단하게 요소 저장 및 접근에 사용 , length 속성 통해 배열 크기 알 수 있음
- 제한된 기능 : 동적인 작업 위한 내장 메소드가 없음 ( add , remove ... )

#### List<>

- 동적 크기 : List 를 구현하는 클래스 ( ArrayList , LinkedList ) 들은 요소 추가 & 제거 따라 동적 크기 조정
- 객체 저장 : 객체 참조를 저장 ( 객체인 래퍼 클래스들만 사용 - int(X) , Integer(O) )
- 유연성 : add , remove , get 등 다양한 메소드 제공
- 제네릭 지원 : 제네릭을 사용해 , 다양한타입 객체를 안전하게 저장 하도록 함
- 반복자 제공 : Iterator 제공해 리스트 순회 방법 제공 
- 컬렉션 프레임워크 일부 : 컬렉션 기반 알고리즘 & 도구 사용 가능
List 의 구현체 ( add() , remove() , clear() , contain() 등등 사용 가능 )
### Analysis

```java
public class ArrayList<E> extends AbstractList<E>  
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
```

#### RandomAccess

리스트가 빠른 임의 접근 지원하는 것을 표시하는 마커 인터페이스
내부 배열 사용해 , 인덱스 통한 빠른 접근 가능

##### Marker Interface?

메소드를 가지지 않고 , 단지 특정 클래스가 특정 속성 & 행동 가진 것을 나타내기 위해 사용하는 인터페이스

#### Cloneable

객체가 복제 가능한 것을 나타내주는 인터페이스
clone() 메소드 사용해 , 객체 복사본을 만들 수 있다

#### Serializable

객체를 직렬화할 수 있음을 나타내주는 인터페이스
객체 상태를 파일 에 저장하거나 네트워크 통해 전송 가능
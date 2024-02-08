멀티 스레드 프로세스에서는
여러 프로세스가 메모리 공유하기 때문에 다른 스레드가 간섭하는 문제가 발생 가능

특정 스레드가 진행중인 작업을 다른 스레드가 간섭하지 못하게 할 필요가 있다
=> 동기화 ( Synchronization )

#### critical section

다른 스레드가 간섭하지 못하는 영역
`synchronized` 키워드를 사용해서 설정

##### Method Synchronization

```java
public synchronized void method(){
	...
}
```

메소드가 한번에 한 스레드에서만 실행되게 보장

##### Block Synchronization

```java
synchronized (object){

}
```

특정 영역을 임계 설정 -> 해당 영역이 한 스레드에서만 실행되게 보장
##### lock


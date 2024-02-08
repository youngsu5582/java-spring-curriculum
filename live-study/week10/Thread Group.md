
서로 관련된 스레드를 묶어서 관리하는 개념
디렉토리 개념과 유사 - 하위 스레드 그룹 포함 가능
-> 보안 및 조직화를 위해 도입된 개념

- 동일 그룹 이나 하위 그룹 속성 변경 가능 ( 다른 스레드 그룹은 변경 불가능 )
- 반드시 하나의 그룹에 속함 ( 지정 안할 시 , main 스레드 그룹에 속함 )
- 자신 생성한 스레드 그룹과 우선순위 상속 받음

```java
Thread(ThreadGroup group, String name)
Thread(ThreadGroup group, Runnable target)
```

스레드는 스레드 그룹 관리 + 그룹 대한 일괄적인 작업 처리 가능

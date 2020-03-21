# 39_1 - UI 객체에서 비즈니스 로직 분리하기

비즈니스 로직을 별도의 클래스로 분리하면,
UI 구현 방식이 변경되더라도 비즈니스 로직을 재사용할 수 있다.

## 학습목표

- Presentation/Service(Business)/Persistence Layer의 구조를 이해한다.

### Presentation Layer

- UI를 담당한다.

### Business(Service) Layer

- 업무 로직을 담당한다.
- 트랜잭션 제어를 담당한다.

### Persistence Layer

- 데이터 저장을 담당한다.


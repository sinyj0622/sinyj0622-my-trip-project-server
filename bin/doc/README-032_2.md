# 32_2 - Application Server 구조로 변경: 규칙1에 따라 통신하는 서버 만들기

### 클라이언트와 서버 사이의 기본 통신 규칙을 정한다

- 요청과 응답은 `Stateless` 방식으로 처리한다. 
- 클라이언트가 요청할 때 서버와 연결하고, 서버에서 응답하면 연결을 끊는다.

규칙1) 단순한 명령어 전송과 실행 결과 수신
```

서버에 연결 요청        -------------->           연결 승인
명령(CRLF)              -------------->           명령처리
화면 출력               <--------------           응답(CRLF)
화면 출력               <--------------           응답(CRLF)
명령어 실행 완료        <--------------           !end!(CRLF)
서버와 연결 끊기
```




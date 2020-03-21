# 28_5 - 특정 기능을 수행하는 코드를 메서드로 분리하기 

- "Extract Method" 리팩토링 기법으로 기능 별로 코드를 분리하기

- ServerApp.java 변경
  - if~ else~ 분기문에 작성한 코드를 별도의 메서드로 분리
  - listBoard() : 게시물 목록 데이터 요청 처리
  - addBoard() : 게시물 데이터 등록 요청 처리
  - detailBoard() : 게시물 조회 요청 처리
  - updateBoard() : 게시물 변경 요청 처리
  - deleteBoard() : 게시물 삭제 요청 처리
  - listMember() : 회원 목록 데이터 요청 처리
  - addMember() : 회원 데이터 등록 요청 처리
  - detailMember() : 회원 조회 요청 처리
  - updateMember() : 회원 변경 요청 처리
  - deleteMember() : 회원 삭제 요청 처리
  - listPlan() : 여행 계획 목록 데이터 요청 처리
  - addPlan() : 여행 계획 등록 요청 처리
  - detailPlan() : 여행 계획 조회 요청 처리
  - updatePlan() : 여행 계획 변경 요청 처리
  - deletePlan() : 여행 계획 삭제 요청 처리      

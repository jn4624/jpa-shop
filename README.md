### 1. 기능 목록
- 회원 기능
  - 회원 등록
  - 회원 조회
- 상품 기능
  - 상품 등록
  - 상품 수정
  - 상품 조회
- 주문 기능
  - 상품 주문
  - 주문 내역 조회
  - 주문 취소
- 기타 요구사항
  - 상품은 재고 관리가 필요하다.
  - 상품의 종류는 도서, 음반, 영화가 있다.
  - 상품을 카테고리로 구분할 수 있다.
  - 상품 주문시 배송 정보를 입력할 수 있다.

### 2. 도메인 모델 분석
<img src="https://github.com/jn4624/jpa-shop-web/assets/60414800/198db36d-8fbf-44c6-89db-be2aab7c2614" width="600">

### 3. 엔티티 분석
<img src="https://github.com/jn4624/jpa-shop-web/assets/60414800/92ef8742-4573-4a22-b968-39c83a9422c8" width="600">

### 4. 테이블 분석
<img src="https://github.com/jn4624/jpa-shop-web/assets/60414800/85573ad7-934a-4f29-8e33-dfb21528a8d2" width="600">

### 5. 애플리케이션 아키텍처
<img src="https://github.com/jn4624/jpa-shop-web/assets/60414800/a2e65d68-6148-45d8-9ed6-75015ce82e2e" width="600">

- 계층형 구조 사용
  - controller, web: 웹 계층
  - service: 비즈니스 로직, 트랜잭션 처리
  - repository: JPA를 직접 사용하는 계층, 엔티티 매니저 사용
  - domain: 엔티티가 모여 있는 계층, 모든 계층에서 사용


- 패키지 구조
  - jpashop
    - domain
    - exception
    - repository
    - service
    - web


  개발 순서: 서비스, 레포지토리 계층 개발 -> 테스트 케이스 작성 및 검증 -> 웹 계층 적용

### 참고
- 엔티티가 비즈니스 로직을 가지고 객체 지향의 특성을 적극 활용하는 것: 도메인 모델 패턴
- 엔티티에는 비즈니스 로직이 거의 없고 서비스 계층에서 대부분의 비즈니스 로직을 처리하는 것: 트랜잭션 스크립트 패턴

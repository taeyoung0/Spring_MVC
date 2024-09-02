# Spring_MVC

## 웹 - HTTP 기반

- HTML, TEXT
  
- IMAGE, 음성, 영상, 파일
  
- JSON, XML (API)
  
- 거의 모든 형태의 데이터 전송 가능
  
- 서버간에 데이터를 주고 받을 때도 대부분 HTTP 사용

## 웹 서버(Web Server)

- HTTP 기반으로 동작

- 정적 리소스 제공, 기타 부가기능

- 정적(파일) HTML, CSS, JS, 이미지, 영상

- 예) NGINX, APACHE


## 웹 애플리케이션 서버(WAS - Web Application Server)

- HTTP 기반으로 동작

- 웹 서버 기능 포함+ (정적 리소스 제공 가능)

- 프로그램 코드를 실행해서 애플리케이션 로직 수행

- 동적 HTML, HTTP API(JSON)

- 서블릿, JSP, 스프링 MVC

- 예) 톰캣(Tomcat) Jetty, Undertow

## 웹 서버, 웹 애플리케이션 서버(WAS) 차이

- 웹 서버는 정적 리소스(파일), WAS는 애플리케이션 로직

- 사실은 둘의 용어도 경계도 모호함

- 웹 서버도 프로그램을 실행하는 기능을 포함하기도 함

- 웹 애플리케이션 서버도 웹 서버의 기능을 제공함

- 자바는 서블릿 컨테이너 기능을 제공하면 WAS

- 서블릿 없이 자바코드를 실행하는 서버 프레임워크도 있음

- WAS는 애플리케이션 코드를 실행하는데 더 특화

## 서블릿

### HTTP 요청, 응답 흐름

- HTTP 요청시

  - WAS는 Request, Response 객체를 새로 만들어서 서블릿 객체 호출

  - 개발자는 Request 객체에서 HTTP 요청 정보를 편리하게 꺼내서 사용

  - 개발자는 Response 객체에 HTTP 응답 정보를 편리하게 입력

  - WAS는 Response 객체에 담겨있는 내용으로 HTTP 응답 정보를 생성

### 서블릿 컨테이너

- 톰캣처럼 서블릿을 지원하는 WAS를 서블릿 컨테이너라고 함

- 서블릿 컨테이너는 서블릿 객체를 생성, 초기화, 호출, 종료하는 생명주기 관리

- 서블릿 객체는 싱글톤으로 관리
  - 고객의 요청이 올 때 마다 계속 객체를 생성하는 것은 비효율

  - 최초 로딩 시점에 서블릿 객체를 미리 만들어두고 재활용

  - 모든 고객 요청은 동일한 서블릿 객체 인스턴스에 접근

  - 공유 변수 사용 주의

  - 서블릿 컨테이너 종료시 함께 종료

- JSP도 서블릿으로 변환 되어서 사용

- 동시 요청을 위한 멀티 쓰레드 처리 지원

### 쓰레드

- 애플리케이션 코드를 하나하나 순차적으로 실행하는 것은 쓰레드

- 자바 메인 메서드를 처음 실행하면 main이라는 이름의 쓰레드가 실행

- 쓰레드가 없다면 자바 애플리케이션 실행이 불가능

- 쓰레드는 한번에 하나의 코드 라인만 수행

- 동시 처리가 필요하면 쓰레드를 추가로 생성

### 요청 마다 쓰레드 생성

#### 장단점

- 장점
  - 동시 요청을 처리할 수 있다
  - 리소스(CPU, 메모리)가 허용할 때 까지 처리가능
  - 하나의 쓰레드가 지연 되어도, 나머지 쓰레드는 정상 동작한다


- 단점
  - 쓰레드는 생성 비용은 매우 비싸다
  - 고객의 요청이 올 때 마다 쓰레드를 생성하면, 응답 속도가 늦어진다
  - 쓰레드는 컨텍스트 스위칭 비용이 발생한다
  - 쓰레드 생성에 제한이 없다
  - 고객 요청이 너무 많이 오면, CPU, 메모리 임계점을 넘어서 서버가 죽을 수 있다

### 쓰레드 풀

#### 요청 마다 쓰레드 생성의 단점 보완
- 특징
  - 필요한 쓰레드를 쓰레드 풀에 보관하고 관리한다.
  - 쓰레드 풀에 생성 가능한 쓰레드의 최대치를 관리한다. 톰캣은 최대 200개 기본 설정 (변경 가능)

- 사용
  - 쓰레드가 필요하면, 이미 생성되어 있는 쓰레드를 쓰레드 풀에서 꺼내서 사용한다.
  - 사용을 종료하면 쓰레드 풀에 해당 쓰레드를 반납한다.

- 최대 쓰레드가 모두 사용중이어서 쓰레드 풀에 쓰레드가 없으면?
  - 기다리는 요청은 거절하거나 특정 숫자만큼만 대기하도록 설정할 수 있다.

- 장점
  - 쓰레드가 미리 생성되어 있으므로, 쓰레드를 생성하고 종료하는 비용(CPU)이 절약되고, 응답 시간이 빠르다.
  - 생성 가능한 쓰레드의 최대치가 있으므로 너무 많은 요청이 들어와도 기존 요청은 안전하게 처리할 수 있다.


## HTTP API
### 다양한 시스템 연동
- 주로 JSON 형태로 데이터 통신
- UI 클라이언트 접점
- 앱 클라이언트(아이폰, 안드로이드, PC 앱)
- 웹 브라우저에서 자바스크립트를 통한 HTTP API 호출
- React, Vue.js 같은 웹 클라이언트
- 서버 to 서버
- 주문 서버 -> 결제 서버
- 기업간 데이터 통신

### 서버사이드 렌더링, 클라이언트 사이드 렌더링
- SSR - 서버 사이드 렌더링
  - HTML 최종 결과를 서버에서 만들어서 웹 브라우저에 전달
  - 주로 정적인 화면에 사용
  - 관련기술: JSP, 타임리프 -> 백엔드 개발자

- CSR - 클라이언트 사이드 렌더링
  - HTML 결과를 자바스크립트를 사용해 웹 브라우저에서 동적으로 생성해서 적용
  - 주로 동적인 화면에 사용, 웹 환경을 마치 앱 처럼 필요한 부분부분 변경할 수 있음
  - 예) 구글 지도, Gmail, 구글 캘린더
  - 관련기술: React, Vue.js -> 웹 프론트엔드 개발자

- 참고
  - React, Vue.js를 CSR + SSR 동시에 지원하는 웹 프레임워크도 있음
  - SSR을 사용하더라도, 자바스크립트를 사용해서 화면 일부를 동적으로 변경 가능
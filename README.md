# 개인 프로젝트 - 할 일 카드를 작성, 수정, 삭제, 조회하는 api 만들기

## 🔍 루시드 다이어그램
![5](https://github.com/DanDanjoo/algorithm/assets/162088392/0a1a8e6e-81a5-4173-b4a4-ccae57503a92)


### 😊 설명
제가 제출한 과제는 역할끼리 묶는 계층형 구조로 구현 했습니다. Todo 항목을 관리하고, RESTful API를 제공합니다.  
주요 기능은 Todo 항목을 생성(Create), 조회(Get), 수정(Update), 삭제(Delete) 입니다.  
 


### 😊 구현 과정 및 어려웠던 점  
DB 테이블, 컬럼 설정 후 InteliJ와 연결하는 과정은 정말 재밌었습니다.  
특히, 깃허브로 공유할 때 모든 정보를 볼 수 있는 것을 application.properties 파일에서 application.yml로 바꿔서 정보(userid, password, url)를   
환경변수 설정을 통해 주입 시킬수 있다는 기능에 spring boots의 기능이 정말 다양하다고 느꼈습니다.  
ERD 작성 후 도메인 별로 분리하고 코드를 작성하려고 했으나 도메인으로 묶는 것이 아무래도 좀 어려웠습니다. 

##### 2024-05-17T09:14:39.042+09:00 ERROR 3136 --- [nio-8080-exec-1] o.a.c.c.C.[.[.[/].[dispatcherServlet]  : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Handler dispatch failed: kotlin.NotImplementedError: An operation is not implemented: Not yet implemented] with root cause  

test중에 500번대 에러가 발생하였습니다.  아마 기능 구현에 대한 미흡으로 이런 에러코드가 나온게 아닌가 추측합니다.   
그 전에 db관련 컬럼 오류도 발생했는데, 컬럼명 대문자 소문자 구분에 대한 오류였습니다.  
500번대 오류는 서버의 내부 에러 서버부하, db 오류 때문에 발생하는 것을 경험했습니다. 





### 😊 개선할 점 및 추가 계획  
위 다이어그램으로 도메인별로 나누고 선택구현까지 하려고 했으나, 필수구현부터 천천히 해보려고 합니다.  
결국, 기한 내에 과제를 완성시키지는 못했지만, 분명 배울점이 있었습니다. 다음주 24일에는 위 다이어그램으로 꼭 제출하겠습니다!  


### 🛠️ 상세 설명
#### ❗controller - TodoListController
 
@PostMapping - 새로운 Todo 항목을 생성하며, CreateTodoRequest객체를 받아 TodoService를 통해 처리하고, HttpStatus.CREATED와 함께 반환합니다.  
@GetMapping("/{id}"): 지정된 ID의 Todo 항목을 조회하여, TodoService를 통해 가져온 후 HttpStatus.OK와 함께 반환합니다.  
@PutMapping("/{id}"): 지정된 ID의 Todo 항목을 수정하며, UpdateTodoRequest 객체를 받아 TodoService를 통해 처리하고, HttpStatus.OK와 함께 반환합니다.  
@DeleteMapping("/{id}"): 지정된 ID의 Todo 항목을 삭제하여, TodoService를 통해 처리하고, HttpStatus.NO_CONTENT와 함께 반환합니다.  
각각 Swagger를 사용하여 API 문서를 자동 생성하고, 자세한 설명을 볼 수 있습니다.   


#### ❗dto - CreateTodoRequest, TodoResponse, UpdateTodoRequest
CreateTodoRequest데이터 클래스는 새로운 Todo 항목을 생성할 때 사용됩니다.  
필드로 title, description, userid, createdAt을 포함합니다.  
createdAt 필드는 기본값으로 현재 시간을 설정합니다.  

TodoResponse데이터 클래스는 Todo 항목에 대한 응답 데이터를 나타냅니다.  
필드로 id, title, description, userid, createdAt을 포함합니다.  
createdAt 필드는 기본값으로 현재 시간을 설정합니다.  

UpdateTodoRequest데이터 클래스는 기존 Todo 항목을 수정할 때 사용됩니다.
필드로 title, description, userid를 포함합니다.  
ID와 생성 시간은 포함되지 않고 수정 가능한 필드만 정의합니다.



#### ❗extension - toResponse
Todo모델을 TodoResponse DTO로 변환하는 확장 함수에 대해 정의하고, Todo.toResponse()는 Todo객체를 TodoResponse객체로 변환합니다.  
List<Todo>.toResponse()는 Todo 객체의 리스트를 TodoResponse객체의 리스트로 변환하는 역할을 합니다.

#### ❗model - Todo
JPA 엔티티로, 데이터베이스의 todo테이블과 매핑됩니다.  
title 필드는 title컬럼과 매핑되며, Todo항목의 제목을 뜻하고,  
description필드는 description컬럼과 매핑되며, Todo항목의 설명을 나타내며 기본값은 null입니다.  
userId 필드는 userid컬럼과 매핑되며, Todo항목을 생성한 사용자의 ID를 나타내고,  
createdAt필드는 created_at컬럼과 매핑되며, Todo 항목이 생성된 시간을 나타냅니다. 기본값은 현재 시간입니다.  
id 필드는 기본 키로 설정되어 있으며, @GeneratedValue어노테이션을 통해 자동으로 생성됩니다.  
그리고, @Id어노테이션은 id 필드가 엔티티의 기본 키임을 뜻합니다.  
@GeneratedValue(strategy = GenerationType.IDENTITY) 어노테이션은 ID 값이 데이터베이스에서 자동으로 생성되도록 합니다.  

##### 😭 보조 생성자 쪽 description과 title을 매개변수로 받아 기본 값을 설정하는데, 아무래도 잘못된 필드 할당을 포함하고 있어 수정해야 할 것 같습니다.  
(튜터님의 조언이 필요합니다.)


#### ❗repository - TodoRepository

JpaRepository<Todo, Long>을 상속받아 Todo엔티티에 대한 CRUD작업을 담당합니다.  
주 역할은 Spring Data JPA가 제공하는 기본 메서드들을 사용하여 데이터베이스와 상호작용하는 것입니다.  


#### ❗service - TodoService, TodoServiceImpl

TodoService클래스는 Todo항목에 대한 CRUD작업을 정의하는 인터페이스입니다.  
메서드는 Todo항목의 목록 조회, ID로 Todo조회, Todo생성, 수정, 삭제를 수행하고,  
각 메서드는 요청에 따라 적절한 응답을 반환하거나 Todo를 조작하는 역할을 합니다.  

TodoServiceImpl클래스는 TodoService인터페이스를 구현하고, 이를 통해 Todo 항목의 생성, 조회, 수정, 삭제 기능을 제공합니다.


#### ❗exception - GlobalExceptionHandler, ModelNotFoundException + (dto) ErrorResponse
GlobalExceptionHandler는 전역 예외 처리를 담당하는 클래스로,  
@RestControllerAdvice어노테이션을 통해 모든 컨트롤러에서 발생하는 예외를 처리합니다.  
ModelNotFoundException은 모델을 찾을 수 없을 때 발생하는 예외 클래스입니다.  
예외 메시지에는 찾으려는 모델의 이름과 해당 ID가 포함됩니다.  
(dto) ErrorResponse는 예외에 대한 응답을 나타내는 데이터 전송 객체입니다.



#### ❗infra.swagger - SwaggerConfig

SwaggerConfig는 Swagger설정을 정의하는 Spring의 구성 클래스입니다



## 💻 실행 예시  

![K-114](https://github.com/DanDanjoo/KHW3/assets/162088392/0a42e71c-5c24-479b-a9b3-33b986262f6f)

##### 2024-05-17T09:14:39.042+09:00 ERROR 3136 --- [nio-8080-exec-1] o.a.c.c.C.[.[.[/].[dispatcherServlet]  : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Handler dispatch failed: kotlin.NotImplementedError: An operation is not implemented: Not yet implemented] with root cause




## 📈 과제 요구사항

### 필수 구현 기능(요구사항)

- [ ]  할일카드 작성 기능
    - `할 일 제목`, `할일 내용`, `작성일`, `작성자 이름` 을 저장할 수 있습니다.
    - 저장된 할 일의 정보를 반환 받아 확인할 수 있습니다.
- [ ]  선택한 할 일 조회 기능
    - 선택한 할 일의 정보를 조회할 수 있습니다.
    - 반환 받은 할 일 정보에는 `할 일 제목`,`할일 내용`, `작성일`, `작성자 이름`정보가 들어있습니다.
- [ ]  할 일카드 목록 조회 기능
    - 등록된 할 일 전체를 조회할 수 있습니다.
    - 조회된 할 일 목록은 작성일 기준 내림차순으로 정렬 되어있습니다.
- [ ]  선택한 할 일 수정 기능
    - 선택한 할 일의 `할 일 제목`, `작성자명`, `작성 내용`을 수정할 수 있습니다.
    - 수정된 할 일의 정보를 반환 받아 확인할 수 있습니다.
- [ ]  선택한 할 일 삭제 기능
    - 선택한 게시글을 삭제할 수 있습니다.

## ✉️ 과제 제출 방법
- 과제 제출은 2회차에 나누어 제출합니다.
- 제출 링크 : https://nbcamp.spartacodingclub.kr/mypage/assignments
- 1차 제출 기한 : 05/17(금) 14:00
    - 1차 목표는 STEP 1까지.
    - 제출 이후, 피드백이 제공됩니다. 피드백을 기반으로 과제 보완 및 추가 STEP을 도전합니다.
- 2차 제출 기한 : 05/24(금) 14:00
    - 제출 이후, 피드백 및 과제 해설 세션이 진행됩니다.
 
      
## 환경설정
Language : Kotlin  
IDEA : IntelliJ  
JDK : 17.0.10  
Database : super base&postgre sql  
springframework.boot : 3.2.5

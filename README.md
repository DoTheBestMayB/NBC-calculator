# 기능목록

### 도메인

___

- CalculateController
    - 계산기(Model)와 IO(View) 사이에서 계산기 동작 과정을 처리한다.
- Calculator
    - [ ] 더하기, 빼기, 나누기, 곱하기, 나머지 연산을 할 수 있다.
    - 각 기능을 담당하는 클래스를 생성자로 주입받아 사용한다.
    - 각 클래스는 AbstractOperation interface 또는 sealed class를 상속하도록 구현한다.
    - 이때 interface와 sealed class 중 무엇을 선택했는지에 대한 근거를 별도로 정리한다.
- AddOperation
    - 덧셈 연산을 담당한다.
- SubtractOperation
    - 빼기 연산을 담당한다.
- MultiplyOperation
    - 곱하기 연산을 담당한다.
- DivideOperation
    - 나눗셈 연산을 담당한다.
- RemainerOperation
    - 나머지 연산을 담당한다.
- StatementParser
    - 문자열을 계산식에 필요한 값으로 분리한 후 리스트로 반환한다.
    - Calculator가 리스트에 담긴 순서에 따라 순차적으로 연산할 수 있도록 정렬한다.
    - 계산식이 올바르지 않으면 IllegalArgumentException을 던진다.
- IOHandler
    - [ ] 출력을 할 수 있다.
    - [ ] 사용자로부터 입력을 받을 수 있다.

### 데이터

___

- Operator
    - 지원하는 연산자 char를 의미하는 Enum class
- Expression
    - 계산에 필요한 값들을 가지고 있는 data class
    - Expression이 Expression을 포함할 수 있다. -> 괄호 대응

### 테스트

___

- [ ] 단일 연산만 입력하는 케이스를 작성한다.
- [ ] 연산자 간의 우선순위가 없는 계산식을 입력한 케이스를 작성한다.
- [ ] 연산자 간의 우선순위가 있는 계산식을 입력한 케이스를 작성한다.
- [ ] 연산자 간의 우선순위가 있고, 괄호식을 포함하는 계산식을 입력한 케이스를 작성한다.
    - 1+(2+(3+6*7)/2)%3-5

## Refactor - 선택 구현 기능

- [ ] 연산 클래스들을 AbstractOperation라는 클래스명으로 만들어 사용하여 추상화하고 Calculator 클래스의 내부 코드를 변경한다.

## 확인사항

- [ ] SOLID 원칙을 준수했는가?
- [ ] MVC 패턴에 따라 구현했는가?
- [ ] 객체지향 생활체조 원칙을 준수했는가?
    1. 한 메서드에 오직 한 단계의 들여쓰기만 한다.
    2. else 예약어를 쓰지 않는다.
    3. 모든 원시 값과 문자열을 포장한다.
    4. 한 줄에 점을 하나만 찍는다.
    5. 줄여 쓰지 않는다(축약 금지).
    6. 모든 엔티티를 작게 유지한다.
    7. 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
    8. 일급 컬렉션을 쓴다.
    9. getter/setter/프로퍼티를 쓰지 않는다.
- [ ] 발생할 수 있는 모든 케이스에 대한 테스트 케이스를 작성했는가?
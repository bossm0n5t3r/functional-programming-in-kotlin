# 02. 코틀린으로 함수형 프로그래밍 시작하기

## 02.10 코틀린 표준 라이브러리

### Comparison Table (let, run, with, apply, also)

|           |      let      |      run      |     with      |    apply    |   also    |
| :-------: | :-----------: | :-----------: | :-----------: | :---------: | :-------: |
| 코드 블록 |    람다식     |  람다 리시버  |  람다 리시버  | 람다 리시버 |  람다식   |
|   접근    |      it       |     this      |     this      |    this     |    it     |
|  반환값   | 람다식 반환값 | 람다식 반환값 | 람다식 반환값 |  자기 자신  | 자기 자신 |

### Use function

```kotlin

import java.io.FileInputStream

val property = Properties()
FileInputStream("config.properties").use {
    property.load(it)
} // FileInputStream 이 자동으로 close 됨
```

## 02.11 변성(Variance)

### Code

- [Variant.kt](./Variant.kt)

# 설정

## 라이브러리 추가

springdoc 를 사용

![img_3.png](img_3.png)

Swagger 사용 할 수 있는 방법에는 springdoc 와 springFox가 존재하는 데,springFox는 오래전에 업데이트로 마지막 업데이트가 `2024-06-14` 이다.

심지어 spring boot 특정 버전부터는 제대로 적용되지도 않는 문제가 존재한다.

그래서, springdoc를 사용하여 `Swagger` 을 적용 시켰다.

## config 파일 추가

그 후, SwaggerConfig 파일을 생성해서, 제목과 설명 그리고 버전을 설정해줬다.

![img_4.png](img_4.png)

# 결과물

![img_5.png](img_5.png)

# Docker compose 설정

![img_13.png](img_13.png)
## 설정

**zookeeper**

    `services`: zookeeper
    `image`: wurstmeister 주키퍼 사용
    `ports`: 호스트 OS와 컨테이너의 포트를 2181로 통일
    `restart`: unless-stopped로 설정해서 
                부팅시 자동으로 컨테이너를 재시작하게 설정했습니다.
---

**kafka**

    `services`: kafka
    `build`: src/main/java
    `ports`: 호스트 OS와 컨테이너의 포트를 9092로 통일
    `image`: wurstmeister 카프카 사용
    `environment`:
        `KAFKA_ADVERTISED_HOST_NAME`: 카프카 브로커가 외부 클라이언트에 공개할 이름
        `KAFKA_ZOOKEEPER_CONNECT`: 카프카와 주키퍼가 통신할 때 사용하는 주소 
    `volumes`:
        `-/var/run/docker.sock:/var/run/docker.sock`
    `restart`: unless-stopped로 설정해서 
                부팅시 자동으로 컨테이너를 재시작하게 설정했습니다.

### wurstmeister을 사용한 이유
`docker search kafka` 명령어로 가장 STARTS를 뭘 받았는 지 살펴봤습니다.
`wurstmeister`이 가장 STARTS가 많고, `zookeeper`, `kafka`를 같이 제공 하기 때문에 채택 했습니다.
![img_10.png](img_10.png)

# Docker를 통한 kafka 설정

명령어 `docker-compose up -d`를 사용 해서 백그라운드로 컨테이너를 실행 했습니다.

![img_11.png](img_11.png)

# Kafka 실행 결과

## Kafka

### Listener
![img_12.png](img_12.png)

### Consumer
![img_14.png](img_14.png)

`userId`을 메세지로 반환이 잘 된 것을 알 수 있습니다.
![img_8.png](img_8.png)
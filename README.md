<p align=center>
  <strong>SpringBoot on Container</strong> <br>
  <img src="https://geekflare.com/wp-content/uploads/2020/03/docker-security-495x160.jpg" />
</p>

------------------------------------------------------------------------------------------------------------------------------------------------------------------
## MySql Commands.

To create a mysql container to use with springboot do the commands bellow;

```java
sudo docker pull mysql/mysql-server:latest
docker  network create --driver bridge mysql-network

docker run -p 6603:3306 --network mysql-network --detach --name=mysql-docker 
   -e MYSQL_ROOT_PASSWORD=adm -e MY_DATABASE=starbucks -e MY_USER=root mysql
   
sudo docker exec -it mysql-docker bash
mysql -uroot -p
create database starbucks
show databases;
```

------------------------------------------------------------------------------------------------------------------------------------------------------------------
PROPERTIES

properties -> para comunicar na maquina local

server.port=8089

spring.datasource.url=jdbc:mysql://127.0.0.1:6603/starbucks

spring.datasource.username=root

spring.datasource.password=adm

spring.jpa.hibernate.ddl-auto = update

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect

properties -> para quando for subir um container do service

server.port=8089

spring.datasource.url=jdbc:mysql://mysql-docker:3306/starbucks

spring.datasource.username=root

spring.datasource.password=adm

spring.jpa.hibernate.ddl-auto = update

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect

------------------------------------------------------------------------------------------------------------------------------------------------------------------
SPRINGBOOT

1. alterar pom para gerar exec
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M4</version>
                <configuration>
                    <testFailureIgnore>false</testFailureIgnore>
                    <skip>false</skip>
                    <includes>
                        <include>**/*Test.java</include>
                        <include>**/Test*.java</include>
                        <include>**/*Tests.java</include>
                        <include>**/*TestCase.java</include>
                    </includes>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <mainClass>com.project.starbucksapi.StarbucksApiApplication</mainClass>
                    <executable>true</executable>
                    <classifier>exec</classifier>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>build-info</goal>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

2. clean install maven
incluir .jar gerado na pasta desejada

3. criar Dockerfile na raiz do projeto

FROM openjdk:11
ARG JAR_FILE=target/starbucks-api-1.0.0-exec.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9600
CMD ["./app.jar"]

4. executar comando na pasta do jar(/projetos/pessoal/starbucks-api)
starbucks -> neste caso é o nome da imagem docker
sudo docker build -t starbucks .

log da execução;

andre.matos@dxtcps010065:~/projetos/pessoal/starbucks-api$ docker build -t starbucks .
Sending build context to Docker daemon  136.5MB
Step 1/5 : FROM openjdk:11
 ---> e782567c0965
Step 2/5 : ARG JAR_FILE=target/starbucks-api-1.0.0-exec.jar
 ---> Running in b0509b6d9d34
Removing intermediate container b0509b6d9d34
 ---> fbe15dfd1fbd
Step 3/5 : COPY ${JAR_FILE} app.jar
 ---> 7f107f6db612
Step 4/5 : EXPOSE 8080
 ---> Running in c1d2d8ec4765
Removing intermediate container c1d2d8ec4765
 ---> 04247b6a18dc
Step 5/5 : CMD ["./app.jar"]
 ---> Running in 06b6a2b588cc
Removing intermediate container 06b6a2b588cc
 ---> 01ac3848f855
Successfully built 01ac3848f855
Successfully tagged starbucks:latest

5. executar a imagem criada
docker run -p 8089:8089 --network mysql-network -d --name starbucks-api starbucks

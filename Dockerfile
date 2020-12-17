# 指定基础镜像
FROM openjdk:8
# 复制文件
ADD target/authority-1.0.0.jar authority-1.0.0.jar
ENTRYPOINT ["java", "-jar", "authority-1.0.0.jar"]
# 开启指定的端口
EXPOSE 8090
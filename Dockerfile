# Created to run stagegage java services
FROM phonyphonecall/spring-boot-base
MAINTAINER scott_hendrickson

# pull in the service
RUN git clone https://github.com/StageGageHub/Artist-Service.git

#Spin up spring boot on startup
ENTRYPOINT ["mvn","-f","/Artist-Service/pom.xml"]

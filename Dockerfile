FROM airdock/oracle-jdk

ADD target/server-0.0.1-SNAPSHOT.jar /opt/server-0.0.1-SNAPSHOT.jar

WORKDIR /opt
ENTRYPOINT java -jar org.janus.ServerApplication
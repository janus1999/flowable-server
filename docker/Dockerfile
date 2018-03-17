FROM airdock/oracle-jdk

ADD ../target/server*.jar /opt

EXPOSE 8990 8990

ENTRYPOINT java -jar org.janus.ServerApplication
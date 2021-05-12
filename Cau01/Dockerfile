FROM openjdk:16
COPY src /src
RUN javac src/com/company/Main.java -d bin
CMD java -cp bin com.company.Main
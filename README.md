## Server Sent Events [Proof of Concept]
This is a POC using [Dropwizard](github.com/codahale/dropwizard) with [Server-sent Events support](https://github.com/jetty-project/jetty-eventsource-servlet)

__Usage__

* Clone this repo.
* Open a terminal and move into project directory, then execute:

```
$ ./gradlew build -Dorg.gradle.java.home=<JDK_PATH>
$ java -jar target/server-sent-events-poc.1.0-SNAPSHOT.jar server \
  config.yaml
```

* Open a new terminal and execute:

```
$ curl localhost:8080/sse -H"Accept: text/event-stream"
```

* Open yet another terminal and execute:

```
$ curl --location --request POST 'http://localhost:8080/v1/score/live-scores' \
--header 'Content-Type: application/json' \
--data-raw '{"homeTeam": "MU", "visitingTeam": "LIV", "homeScore": 2, "visitingScore": 1, "updateTimestamp": 1661264057}'
```

* You should see the following in the first terminal:

```
~ $ curl localhost:8080/sse -H"Accept: text/event-stream"

data: Score(homeTeam=MU, visitingTeam=LIV, homeScore=2, visitingScore=1, updateTimestamp=1661264057)
```




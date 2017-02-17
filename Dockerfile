FROM java:8

ENV LEIN_ROOT yes

RUN apt-get update && apt-get install -y \
	clojure1.6 \
	&& apt-get clean \
  	&& rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

ADD https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein lein 	
RUN chmod a+x lein

RUN mv lein /usr/bin

RUN lein 

WORKDIR /usr/src/app

COPY project.clj /usr/src/app/

RUN lein deps

COPY . /usr/src/app

RUN lein with-profile docker do clean, ring uberjar

CMD ["java", "-jar", "target/sogeti-server-0.0.1-standalone.jar"]
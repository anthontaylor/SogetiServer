[![Build Status](https://travis-ci.org/anthontaylor/SogetiServer.svg?branch=master)](https://travis-ci.org/anthontaylor/SogetiServer)
[![Coverage Status](https://coveralls.io/repos/github/anthontaylor/SogetiServer/badge.svg?branch=master)](https://coveralls.io/github/anthontaylor/SogetiServer?branch=master)

# sogeti-server

## Prerequisites

[Leiningen][] 2.0.0 or above.

[leiningen]: https://github.com/technomancy/leiningen

[Docker] []
[Docker]: https://www.docker.com

## Running

To start a web server for the application, run:

    lein ring server

To view view the json response, add /name to the end of the url.
Default port is http://localhost:3000/ and you can get basic json response at http://localhost:3000/

To connect to database

    docker-compose up
    
## Deploying

To deploy application as jar file and start the jar file server
    
    lein ring uberjar
    
    java -jar target/sogeti-server-0.1.0-SNAPSHOT-standalone.jar/

## Testing

To run unit tests, run:

    lein test

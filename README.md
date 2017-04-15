## Travis CI

[![Build Status](https://travis-ci.org/anthontaylor/SogetiServer.svg?branch=master)](https://travis-ci.org/anthontaylor/SogetiServer)
[![codecov](https://codecov.io/gh/anthontaylor/SogetiServer/branch/master/graph/badge.svg)](https://codecov.io/gh/anthontaylor/SogetiServer)

## Prerequisites

[Leiningen][] 2.0.0 or above.

[leiningen]: https://github.com/technomancy/leiningen

[Docker]

[Docker]: https://www.docker.com

[Docker-Compose]

[Docker-Compose]: https://github.com/docker/compose

## Running

To migrate database table

    lein migrate

To connect to database and api

    docker-compose up

To build api

    docker-compose build api
    
To start a web server for the application, run:

    lein ring server
    
## Deploying

To deploy application as jar file and start the jar file server
    
    lein ring uberjar
    
    java -jar target/sogeti-server-0.1.0-SNAPSHOT-standalone.jar/

## Testing

To run unit tests, run:

    lein test

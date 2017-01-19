# sogeti-server

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

To view view the json response, add /name to the end of the url.
Default port is http://localhost:3000/ and you can get basic json response at http://localhost:3000/

To deploy application as jar file and start the jar file server
    
    lein ring uberjar
    
    java -jar target/sogeti-server-0.1.0-SNAPSHOT-standalone.jar/

## Testing

To run unit tests, run:

    lein test

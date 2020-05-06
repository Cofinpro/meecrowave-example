# meecrowave-example
Small example project demonstrating the usage of [Meecrowave][meecrowave].
I use this repository for my blog posts on [kivio.org][kivio].

## Usage
In the root directory run `mvn clean install`.

Create a Meecrowave distribution while changing into _person-server_ and invoke `mvn
meecrowave:bundle`.

If you simply want to run the server on command line call `mvn meecrowave:run` inside
_person-server_.

## Build a Docker image
_person-server_ can be build as Docker image. There is Fabric8 Maven Docker plugin embedded in
pom.xml. The commands

```
mvn clean package meecrowave:bundle docker:build
```
will build a Docker image given by the embedded Dockerfile.

[meecrowave]: https://openwebbeans.apache.org/meecrowave/
[kivio]: http://kivio.org

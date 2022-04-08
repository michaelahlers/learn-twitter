ThisBuild / scalaVersion := "2.13.6"

//resolvers += Resolver.githubPackages("michaelahlers", "datadog-finagle")
//resolvers += "michaelahlers/datadog-finagle".at("https://maven.pkg.github.com/michaelahlers/datadog-finagle")
resolvers += Resolver.url("Ahlers Consulting Artifacts (public)", url("https://ahlers-consulting-artifacts-public.s3.amazonaws.com/"))(Resolver.ivyStylePatterns)

libraryDependencies ++=
  "ch.qos.logback" % "logback-classic" % "1.2.5" ::
    "net.logstash.logback" % "logstash-logback-encoder" % "6.6" ::
    //"com.datadoghq" %% "dd-finagle" % "0.2.3" ::
    "com.datadoghq" %% "dd-finagle_21.6" % "0.2.9-SNAPSHOT" ::
    "io.scalaland" %% "chimney" % "0.6.1" ::
    Nil

/**
 * ”Finagle server without metrics” is fixed by depending on `finagle-stats`.
 * @see [[https://github.com/seratch/finagle-playground/blob/5c004bdb17a346936549ec00d731db5b35b4cddc/hello-twitter-server/src/main/scala/example/BasicServer.scala#L9-L16]]
 */
libraryDependencies ++=
  ("com.twitter" %% "finagle-stats" % "21.6.0" ::
    "com.twitter" %% "finatra-http-server" % "21.6.0" ::
    "com.twitter" %% "inject-logback" % "21.6.0" ::
    "com.twitter" %% "inject-server" % "21.6.0" ::
    "com.twitter" %% "inject-slf4j" % "21.6.0" ::
    "com.twitter" %% "inject-utils" % "21.6.0" ::
    "com.twitter" %% "twitter-server-logback-classic" % "21.6.0" ::
    "com.twitter" %% "util-validator" % "21.6.0" ::
    Nil)
    .map(_
      .cross(CrossVersion.for3Use2_13))

libraryDependencies ++=
  (("com.twitter" %% "finatra-http-server" % "21.6.0" % Test).classifier("tests") ::
    ("com.twitter" %% "inject-server" % "21.6.0" % Test).classifier("tests") ::
    ("com.twitter" %% "inject-app" % "21.6.0" % Test).classifier("tests") ::
    ("com.twitter" %% "inject-core" % "21.6.0" % Test).classifier("tests") ::
    ("com.twitter" %% "inject-modules" % "21.6.0" % Test).classifier("tests") ::
    Nil)
    .map(_
      .cross(CrossVersion.for3Use2_13))

libraryDependencies ++=
  ("com.softwaremill.diffx" %% "diffx-scalatest" % "0.5.4" % Test ::
    "com.github.alexarchambault" %% "scalacheck-shapeless_1.15" % "1.3.0" % Test ::
    "io.circe" %% "circe-core" % "0.14.1" % Test ::
    "org.scalacheck" %% "scalacheck" % "1.16.0" % Test ::
    "org.scalamock" %% "scalamock" % "5.1.0" % Test ::
    "org.scalatest" %% "scalatest" % "3.2.9" % Test ::
    "org.scalatestplus" %% "scalacheck-1-15" % "3.2.9.0" % Test ::
    Nil)
    .map(_
      .cross(CrossVersion.for3Use2_13))

dependencyOverrides ++=
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.11.4" ::
    Nil

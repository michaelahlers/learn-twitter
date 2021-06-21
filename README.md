# Learn Twitter

A personal sandbox project for getting familiar with Twitter's technology stack (_e.g._, [Finatra][github-finatra], [Finagle][github-finagle]).

## Development

Includes [sbt-revolver][github-sbt-revolver] for rapid server restarts on code changes:

```sbt
~reStart ahlers.learn.twitter.MessageHttpServerApp
```

Optionally, change the port number:

```sbt
~reStart ahlers.learn.twitter.MessageHttpServerApp -- -http.port=8080
```

Terminate the server process with:

```sbt
reStop
```

## Deployment

### Heroku

Uses [sbt-heroku][github-sbt-heroku] for application configuration and deployment:

```sbt
deployHeroku
```

To choose a specific deployment stage:

```sbt
set Compile / herokuAppName := "ahlers-learn-twitter-preview"; deployHeroku
```

[github-finatra]: https://github.com/twitter/finatra
[github-finagle]: https://github.com/twitter/finagle
[github-sbt-revolver]: https://github.com/spray/sbt-revolver
[github-sbt-heroku]: https://github.com/heroku/sbt-heroku

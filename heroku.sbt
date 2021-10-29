Compile / herokuAppName :=
  Map(
    ("preview", "ahlers-learn-twitter-preview"),
    ("public", "ahlers-learn-twitter-public"))
    .apply(sys.props.getOrElse("stage", "preview"))

Compile / herokuSkipSubProjects := false

Compile / herokuBuildpacks :=
  // "https://github.com/heroku/heroku-buildpack-apt.git" ::
  "https://github.com/DataDog/heroku-buildpack-datadog.git" ::
    "heroku/jvm" ::
    Nil

Compile / herokuJdkVersion := "15"

Compile / herokuProcessTypes += ("web", "target/universal/stage/bin/learn-twitter -- -http.port=:$PORT")

/**
 * {{{
 * heroku config:add --app=ahlers-learn-twitter-preview APP_NAME=ahlers-learn-twitter-preview
 * heroku config:add --app=ahlers-learn-twitter-preview DD_AGENT_MAJOR_VERSION=7
 * heroku config:add --app=ahlers-learn-twitter-preview DD_API_KEY=???
 * heroku config:add --app=ahlers-learn-twitter-preview DD_APM_ENABLED=true
 * heroku config:add --app=ahlers-learn-twitter-preview DD_DYNO_HOST=true
 * heroku config:add --app=ahlers-learn-twitter-preview DD_PROCESS_AGENT=true
 * heroku config:add --app=ahlers-learn-twitter-preview DD_SITE=datadoghq.com
 * }}}
 *
 * @see [[https://app.datadoghq.com/signup/agent#heroku]]
 * @see [[https://github.com/DataDog/heroku-buildpack-datadog]]
 */

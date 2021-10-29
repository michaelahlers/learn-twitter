package ahlers.learn.twitter

import ahlers.learn.twitter.messages.{ MessageServiceInMemoryModule, MessageWebService }
import com.google.inject.Module
import com.twitter.finagle.http.{ Request, Response }
import com.twitter.finagle.stats.StatsReceiver
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.{ ExceptionMappingFilter, LoggingMDCFilter, TraceIdMDCFilter }
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.inject.Logging

/**
 * @since June 04, 2021
 * @author <a href="mailto:michael@ahlers.consulting">Michael Ahlers</a>
 */
class LearnTwitterHttpServer
  extends HttpServer
    with Logging {

  // override protected def statsReceiverModule =
  //  StatsReceiverModule

  override val modules: Seq[Module] =
    super.modules :+
      MessageServiceInMemoryModule

  override protected def start() =
    injector.instance[StatsReceiver]

  override protected def warmup() =
    handle[LearnTwitterWarmupHandler]()

  override def configureHttp(router: HttpRouter) =
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[ExceptionMappingFilter[Request]]
      .add[MessageWebService]

}

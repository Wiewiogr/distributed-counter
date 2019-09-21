package pl.tw.counter.gatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BasicSimulation extends Simulation {

  val httpProtocol = http
    .baseUrls(
      "http://localhost:8080",
      "http://localhost:8081",
      "http://localhost:8082"
    )

  val scn = scenario("Incrementation") // A scenario is a chain of requests and pauses
    .exec(http("increment")
    .post("/increment"))

  setUp(
    scn.inject(
//      rampUsersPerSec(0) to 100 during 60,
      constantUsersPerSec(10) during 600,
//      rampUsersPerSec(100) to 0 during 60,
    )
      .protocols(httpProtocol)
  )
}

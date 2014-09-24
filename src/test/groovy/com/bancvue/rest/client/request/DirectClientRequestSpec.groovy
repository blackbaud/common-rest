package com.bancvue.rest.client.request

import com.bancvue.rest.client.ImmutableClient
import com.bancvue.rest.client.request.DirectClientRequest
import javax.ws.rs.client.WebTarget
import spock.lang.Specification

class DirectClientRequestSpec extends Specification {

	ImmutableClient client = ImmutableClient.createDefault()

	def "should throw SchemeRequiredException if uri has no scheme"() {
		when:
		new DirectClientRequest(client, "localhost:8080")

		then:
		thrown(DirectClientRequest.MalformedUriException)
	}

	def "should initialize delegate with constructed WebTarget"() {
		when:
		DirectClientRequest request = new DirectClientRequest(client, "http://localhost:8080")

		then:
		WebTarget target = request.delegate.resource
		assert target.getUri().getScheme() == "http"
		assert target.getUri().getPort() == 8080
		assert target.getUri().getHost() == "localhost"
	}

}
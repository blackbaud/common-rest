package com.bancvue.rest.client

import com.sun.jersey.api.client.ClientResponse
import spock.lang.Specification

import javax.ws.rs.core.Response

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class GetResponseTest extends Specification {

	def "acquireResponseAsType should return entity from response"() {
		ClientResponse clientResponse = mock(ClientResponse)
		GetResponse getResponse = new GetResponse(clientResponse)
		when(clientResponse.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
		when(clientResponse.getEntity(String)).thenReturn("value")

		when:
		String entity = getResponse.acquireResponseAsType(String)

		then:
		assert entity == "value"
	}

	def "acquireResponseAsType should throw runtime exception if status not found"() {
		ClientResponse clientResponse = mock(ClientResponse)
		GetResponse getResponse = new GetResponse(clientResponse)
		when(clientResponse.getStatus()).thenReturn(Response.Status.NOT_FOUND.getStatusCode());

		when:
		getResponse.acquireResponseAsType(Object)

		then:
		thrown(RuntimeException)
	}

}
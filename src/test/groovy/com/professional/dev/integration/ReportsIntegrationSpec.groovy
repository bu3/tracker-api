package com.professional.dev.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.json.BasicJsonTester
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.MediaType.APPLICATION_JSON

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ReportsIntegrationSpec extends Specification {

    @Autowired
    TestRestTemplate testRestTemplate

    BasicJsonTester json = new BasicJsonTester(getClass())

    //TODO interesting for a blog post
    def "Should save a report"() {
        given:
        def createReportRequest = '{ "firstName": "John", "lastName": "Doe" }'

        when:
        def response = save(createReportRequest)

        then:
        response.statusCode == CREATED
        json.from(response.body).assertThat().with {
            extractingJsonPathStringValue("@.firstName").isEqualTo("John")
            extractingJsonPathStringValue("@.lastName").isEqualTo("Doe")
            extractingJsonPathNumberValue("@.id").isNotNull()
        }

        when:
        response = testRestTemplate.getForEntity('/reports', String)

        then:
        response.statusCode == OK
        json.from(response.body).assertThat().with {
            extractingJsonPathArrayValue("@").hasSize(1)
            extractingJsonPathStringValue("@[0].firstName").isEqualTo("John")
            extractingJsonPathStringValue("@[0].lastName").isEqualTo("Doe")
            extractingJsonPathNumberValue("@[0].id").isNotNull()
        }
    }

    private ResponseEntity<String> save(String content) {
        def headers = new HttpHeaders()
        headers.setContentType(APPLICATION_JSON)
        def entity = new HttpEntity<String>(content ,headers)
        testRestTemplate.postForEntity("/reports", entity, String)
    }
}

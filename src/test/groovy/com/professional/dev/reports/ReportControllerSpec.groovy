package com.professional.dev.reports

import spock.lang.Specification

class ReportControllerSpec extends Specification {

    def "Should return a list of reports"() {
        given:
        def reports = [new Report(), new Report()]
        ReportRepository reportRepository = Mock(ReportRepository)
        ReportController controller = new ReportController(reportRepository)

        when:
        def results = controller.findAll()

        then:
        1 * reportRepository.findAll() >> reports
        results == reports
    }
}

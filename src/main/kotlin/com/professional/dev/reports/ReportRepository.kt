package com.professional.dev.reports

import org.springframework.data.repository.CrudRepository


interface ReportRepository : CrudRepository<Report, Long> {
}
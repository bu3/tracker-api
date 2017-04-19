package com.professional.dev.reports

import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class ReportController(val repository: ReportRepository) {

    @GetMapping("/reports")
    fun findAll() = repository.findAll()

    @PostMapping("/reports")
    @ResponseStatus(CREATED)
    fun save(@RequestBody @Valid report: Report) = repository.save(report)
}


package com.professional.dev.reports

import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Report {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @NotEmpty
    lateinit var firstName: String

    @NotEmpty
    lateinit var lastName: String
}

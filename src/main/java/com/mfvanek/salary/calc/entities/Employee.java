package com.mfvanek.salary.calc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    @NotNull
    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @NotBlank
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Min(value = 1)
    @Max(value = 8)
    @Column(name = "hours_per_day", nullable = false)
    private int standardHoursPerDay;

    @NotNull
    @DecimalMax(value = "5000.00")
    @DecimalMin(value = "100.00")
    @Column(name = "salary_per_hour", nullable = false)
    private BigDecimal salaryPerHour;

    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Set<Salary> salaries = new HashSet<>();

    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Set<Ticket> tickets = new HashSet<>();
}

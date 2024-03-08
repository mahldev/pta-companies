package org.iesbelen.pta.companies.domain;

import java.io.Serializable;

import org.iesbelen.pta.sector.domain.Sector;
import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Sector sector;

    @Column(name = "number_of_employees")
    private Integer numberOfEmployees;

    @Column(name = "revenues")
    private Long revenues;

    @Column(name = "description")
    private String description;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;

    @Embedded
    private CompanyLocation location;
}

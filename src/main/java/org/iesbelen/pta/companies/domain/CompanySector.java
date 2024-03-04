package org.iesbelen.pta.companies.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "company_sectors")
@Data
@NoArgsConstructor
public class CompanySector implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "companySector")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Company> companies;

    public CompanySector(String name) {
        this.name = name;
        this.companies = new ArrayList<>();
    }

}

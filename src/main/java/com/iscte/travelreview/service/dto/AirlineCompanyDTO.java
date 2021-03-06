package com.iscte.travelreview.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.iscte.travelreview.domain.AirlineCompany} entity.
 */
public class AirlineCompanyDTO implements Serializable {

    private Long id;

    private String name;

    private String website;

    private String email;

    private String headquarters;

    private LocalDate foundation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public LocalDate getFoundation() {
        return foundation;
    }

    public void setFoundation(LocalDate foundation) {
        this.foundation = foundation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AirlineCompanyDTO)) {
            return false;
        }

        AirlineCompanyDTO airlineCompanyDTO = (AirlineCompanyDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, airlineCompanyDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AirlineCompanyDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", website='" + getWebsite() + "'" +
            ", email='" + getEmail() + "'" +
            ", headquarters='" + getHeadquarters() + "'" +
            ", foundation='" + getFoundation() + "'" +
            "}";
    }
}

package com.iscte.travelreview.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.iscte.travelreview.domain.Accommodation} entity.
 */
public class AccommodationDTO implements Serializable {

    private Long id;

    private String name;

    private String type;

    private Boolean sustainableAccommodation;

    private String website;

    private String email;

    private String phone;

    private String address;

    private Double classification;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSustainableAccommodation() {
        return sustainableAccommodation;
    }

    public void setSustainableAccommodation(Boolean sustainableAccommodation) {
        this.sustainableAccommodation = sustainableAccommodation;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getClassification() {
        return classification;
    }

    public void setClassification(Double classification) {
        this.classification = classification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccommodationDTO)) {
            return false;
        }

        AccommodationDTO accommodationDTO = (AccommodationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, accommodationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccommodationDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", sustainableAccommodation='" + getSustainableAccommodation() + "'" +
            ", website='" + getWebsite() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", classification=" + getClassification() +
            "}";
    }
}

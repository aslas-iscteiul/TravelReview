package com.iscte.travelreview.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.iscte.travelreview.domain.Score} entity.
 */
public class ScoreDTO implements Serializable {

    private Long id;

    private Integer number;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScoreDTO)) {
            return false;
        }

        ScoreDTO scoreDTO = (ScoreDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, scoreDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ScoreDTO{" +
            "id=" + getId() +
            ", number=" + getNumber() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}

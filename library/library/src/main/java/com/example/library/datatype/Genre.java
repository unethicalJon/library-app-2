package com.example.library.datatype;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genre {
    NOVEL, THRILLER, FANTASY, CRIME, MYSTERY, HISTORY,
    @JsonProperty("biography and autobiography")BIOGRAPHY_AND_AUTOBIOGRAPHY,
    POETRY,
    @JsonProperty("for children")FOR_CHILDREN,
    SCIENTIFIC



}

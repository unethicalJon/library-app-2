package com.example.library.datatype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Section {

    @JsonProperty("foreign book") FOREIGN_BOOK,
    @JsonProperty("albanian book") ALBANIAN_BOOK,
    MAGAZINE
}

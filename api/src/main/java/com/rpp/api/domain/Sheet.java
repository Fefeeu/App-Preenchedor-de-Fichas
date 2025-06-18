package com.rpp.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sheet {
    private String characterAlignment;
    private String characterBackground;
    private String characterClass;
    private String characterExperience;
    private String characterLevel;
    private String characterName;
    private String characterRace;
}

package com.loco.v1.wise.locomotive.entity.Trains;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRN_COACH")
public class TrainCoach {

    private int coachNumber;
    private String coachType;
}

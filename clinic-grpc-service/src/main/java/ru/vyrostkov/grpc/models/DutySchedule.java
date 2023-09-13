package ru.vyrostkov.grpc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vyrostkov.grpc.enumUnit.DaysOfWeek;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "duty_schedules", schema = "vetclinic")
public class DutySchedule extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "vet_id")
    private Vet vet;
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "day_of_week")
    private DaysOfWeek dayOfWeek;
    @Column(name = "end_time", columnDefinition = "time")
    private Instant endTime;
    @Column(name = "start_time", columnDefinition = "time")
    private Instant startTime;
}

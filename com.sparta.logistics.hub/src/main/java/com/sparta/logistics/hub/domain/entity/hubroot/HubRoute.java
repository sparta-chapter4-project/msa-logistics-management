package com.sparta.logistics.hub.domain.entity.hubroot;

import com.sparta.logistics.hub.domain.entity.BaseEntity;
import com.sparta.logistics.hub.domain.entity.hub.Hub;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HubRoute extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "prev_hub")
    private HubRoute prevHub;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_hub")
    private Hub currentHub;

    @OneToOne(mappedBy = "prevHub")
    private HubRoute nextHub;

    private Integer time;
    public static HubRoute create(Hub currentHub, HubRoute prevHubRoute, Integer time) {
        return HubRoute.builder()
                .prevHub(prevHubRoute)
                .currentHub(currentHub)
                .time(time)
                .build();
    }
}

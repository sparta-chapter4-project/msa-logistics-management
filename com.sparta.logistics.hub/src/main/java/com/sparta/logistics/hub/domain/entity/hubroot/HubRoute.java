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
    @JoinColumn(name = "prev_hub_route")
    private HubRoute prevHubRoute;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_hub")
    private Hub currentHub;

    @OneToOne(mappedBy = "prevHubRoute")
    private HubRoute nextHubRoute;

    private Integer time;

    public static HubRoute create(Hub currentHub, HubRoute prevHubRoute, Integer time) {
        return HubRoute.builder()
                .prevHubRoute(prevHubRoute)
                .currentHub(currentHub)
                .time(time)
                .build();
    }

    public void update(HubRoute prevHubRoute, Integer time) {
        this.prevHubRoute = prevHubRoute;
        if(!time.equals(this.time))
            this.time = time;
    }

    public void updatePrev(HubRoute prevHubRoute){
        this.prevHubRoute = prevHubRoute;
    }

    public void updateNext(HubRoute nextHubRoute) {
        this.nextHubRoute = nextHubRoute;
    }

    public void deleteRelation() {
        this.nextHubRoute = null;
        this.prevHubRoute = null;
    }
}

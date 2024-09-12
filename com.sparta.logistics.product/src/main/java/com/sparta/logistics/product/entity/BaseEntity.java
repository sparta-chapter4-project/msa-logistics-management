package com.sparta.logistics.product.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column
    private Long createdBy;

    @LastModifiedDate
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Column
    private Long updatedBy;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletedAt;

    @Column
    private Long deletedBy;

    @Column
    @Builder.Default
    private Boolean isDeleted = false;

    public void delete() {
        this.deletedAt = LocalDateTime.now();
        this.isDeleted = true;
    }
}

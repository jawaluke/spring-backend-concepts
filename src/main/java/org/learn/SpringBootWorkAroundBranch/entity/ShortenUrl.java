package org.learn.SpringBootWorkAroundBranch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "url_short", schema = "system_design")
public class ShortenUrl {

    @Id
    @Column(name = "short_url")
    String shortURL;

    @Column(name = "original_url")
    String originalURL;

    @Column(name = "created_time")
    Date createdTime;

    @Column(name = "expire_time")
    Date expireTime;
}

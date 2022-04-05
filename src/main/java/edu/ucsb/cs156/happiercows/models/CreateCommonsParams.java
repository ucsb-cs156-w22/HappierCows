package edu.ucsb.cs156.happiercows.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.AccessLevel;


import org.springframework.security.core.GrantedAuthority;

import edu.ucsb.cs156.happiercows.entities.User;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CreateCommonsParams {
  private String name;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private double cowPrice;
  private double milkPrice;
  private double startingBalance;
  private int cowsPerUserThereshold;
  private int userCount;
}

package com.frzlyv.transactions.report;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frzlyv.transactions.user.UserEntity;

import lombok.RequiredArgsConstructor;

/**
 * ReportServiceController
 */
@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

  private final ReportService reportService;

  @GetMapping("/general")
  GeneralReportDto getGeneralReport(@AuthenticationPrincipal UserEntity currentUser) {
    return reportService.getGeneralReport(currentUser);
  }

}

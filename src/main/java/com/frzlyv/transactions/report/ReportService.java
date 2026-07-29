package com.frzlyv.transactions.report;

import com.frzlyv.transactions.user.UserEntity;

/**
 * ReportService
 */
public interface ReportService {

  GeneralReportDto getGeneralReport(UserEntity currentUser);

}

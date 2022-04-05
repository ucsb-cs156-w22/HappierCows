package edu.ucsb.cs156.happiercows.controllers.jobs;

import edu.ucsb.cs156.happiercows.services.LogJobService;
import io.swagger.annotations.ApiImplicitParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogJobController extends JobController {
  @Autowired
  private LogJobService logJob;

  @PostMapping("/log")
  @ApiImplicitParam(name = "X-API-KEY", value="API key", required=true, paramType="header", dataTypeClass=String.class)
  public String logTest() {
    logJob.performLog();
    return "cool";
  }

  @PostMapping("/milkCows")
  @ApiImplicitParam(name = "X-API-KEY", value="API key", required=true, paramType="header", dataTypeClass=String.class)
  public String milkCows() {
    logJob.performMilkCows();
    return "milking cows";
  }

  @PostMapping("/updateHealth")
  @ApiImplicitParam(name = "X-API-KEY", value="API key", required=true, paramType="header", dataTypeClass=String.class)
  public String updateHealth() {
    logJob.performUpdateHealth();
    return "updating health";
  }

  // @PostMapping("/killCows")
  // @ApiImplicitParam(name = "X-API-KEY", value="API key", required=true, paramType="header", dataTypeClass=String.class)
  // public String killCows() {
  //   logJob.performKillCows();
  //   return "killing cows";
  // }

  // @PostMapping("/generateReport")
  // @ApiImplicitParam(name = "X-API-KEY", value="API key", required=true, paramType="header", dataTypeClass=String.class)
  // public String generateReport() {
  //   logJob.performGenerateReport();
  //   return "generating report";
  // }
}

package com.accolite.opportunities.cuccumber.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.accolite.opportunities.entities.User;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import java.util.List;

/**
 * Step Definition Class for User.
 *
 * <p>Uses Java Lambda style step definitions so that we don't need to worry
 * about method naming for each step definition</p>
 */
public class UserSteps extends AbstractSteps implements En {

  public UserSteps() {

    Given("user with the following attributes", (DataTable employeeDt) -> {
      testContext().reset();
      List<User> employeeList = employeeDt.asList(User.class);

      // First row of DataTable has the employee attributes hence calling get(0) method.
      super.testContext()
          .setPayload(employeeList.get(0));
    });

    When("user saves {string}", (String testContext) -> {
      String createEmployeeUrl = "/users";

      // AbstractSteps class makes the POST call and stores response in TestContext
      executePost(createEmployeeUrl);
    });

    /**
     * This can be moved to a Class named 'CommonSteps.java so that it can be reused.
     */
    Then("the save {string}", (String expectedResult) -> {
      Response response = testContext().getResponse();

      switch (expectedResult) {
        case "IS SUCCESSFUL":
          assertThat(response.statusCode()).isIn(200, 201);
          break;
        case "FAILS":
          assertThat(response.statusCode()).isBetween(400, 504);
          break;
        default:
          fail("Unexpected error");
      }
    });

  }
}

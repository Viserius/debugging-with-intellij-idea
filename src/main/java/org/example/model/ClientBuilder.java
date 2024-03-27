package org.example.model;

import java.time.LocalDate;

public class ClientBuilder {
  private String myFirstName;
  private String myLastName;
  private Company myCompany;
  private Twitter myTwitter;
  private LocalDate dob;

  public String getFirstName() {
    return myFirstName;
  }

  public void setFirstName(String firstName) {
    myFirstName = firstName;
  }

  public String getLastName() {
    return myLastName;
  }

  public void setLastName(String lastName) {
    myLastName = lastName;
  }

  public Company getCompany() {
    return myCompany;
  }

  public void setCompany(Company company) {
    myCompany = company;
  }

  public Twitter getTwitter() {
    return myTwitter;
  }

  public void setTwitter(Twitter twitter) {
    myTwitter = twitter;
  }

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  public Client build() {
    Client client = new Client(myFirstName, myLastName, myCompany, myTwitter);
    if (dob != null) {
      client.setDob(dob);
    }
    return client;
  }
}

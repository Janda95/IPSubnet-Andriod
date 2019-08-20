package com.example.subnetapp;

public class IpClass {
  public int ipInt;
  public int cidr;
  public String ipBinary;

  public IpClass(int ipInt, int cidr, String ipBinary){
    this.ipInt = ipInt;
    this.cidr = cidr;
    this.ipBinary = ipBinary;
  }

  public int getIpDecimal(){
    return ipInt;
  }

  public int getCidr(){
    return cidr;
  }

  public String getIpBinary(){
    return ipBinary;
  }
}

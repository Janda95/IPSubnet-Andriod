package com.example.subnetapp;

import java.sql.Struct;

public class SubNetCalculator {

  private String rootSubnetString;
  private int rootSubnetBinary;
  private int cidr;
  private int ipInt;

  private IpClass ipClass;

  //For using functions in SplitterActivity
  public SubNetCalculator(String ip, String cidr, int ipNum) {
    try {
      this.cidr = Integer.parseInt(cidr);
      ipInt = ipNum;
    } catch (NumberFormatException nfe) {}
    rootSubnetString = ip;

    ipClass = new IpClass(ipInt, this.cidr, ip);

  }

  public int getBinaryNumber() {
    return ipInt;
  }

  public int getCidr() {
    return cidr;
  }

  public int ipToCidrIp(int aCidr, int ip){
    int localip = ip;
    int localcidr = aCidr;

    //make into binary formatted string
    String binaryNum = String.format("%32s", Integer.toBinaryString(localip)).replace(' ', '0');

    System.out.println(binaryNum);

    StringBuilder builderBinaryNum = new StringBuilder(binaryNum);

    //change binary string substring to 0 after cidr
    for(int i = localcidr; i < binaryNum.length(); i++) {
      builderBinaryNum.setCharAt(i, '0');
    }

    binaryNum = builderBinaryNum.toString();

    //change back into int
    System.out.println(binaryNum);

    return 0;
  }

  public String trimCidrIp(){
    int localip = ipInt;
    int localcidr = cidr;

    //make into binary formatted string
    String binaryNum = String.format("%32s", Integer.toBinaryString(localip)).replace(' ', '0');

    System.out.println(binaryNum);

    StringBuilder builderBinaryNum = new StringBuilder(binaryNum);

    //change binary string substring to 0 after cidr
    for(int i = localcidr; i < binaryNum.length(); i++) {
      builderBinaryNum.setCharAt(i, '0');
    }

    binaryNum = builderBinaryNum.toString();

    //change back into int
    System.out.println(binaryNum);

    return binaryNum;
  }

}
